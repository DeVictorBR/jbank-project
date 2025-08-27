package dev.victor.jbank.service;

import dev.victor.jbank.controller.dto.CreateWalletDto;
import dev.victor.jbank.controller.dto.DepositMoneyDto;
import dev.victor.jbank.entity.Deposit;
import dev.victor.jbank.entity.Wallet;
import dev.victor.jbank.exception.DeleteWalletException;
import dev.victor.jbank.exception.WalletDataAlreadyExistsException;
import dev.victor.jbank.exception.WalletNotFoundException;
import dev.victor.jbank.repository.DepositRepository;
import dev.victor.jbank.repository.WalletRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final DepositRepository depositRepository;

    public WalletService(WalletRepository walletRepository, DepositRepository depositRepository) {
        this.walletRepository = walletRepository;
        this.depositRepository = depositRepository;
    }


    public Wallet createWallet(CreateWalletDto dto) {

        Optional<Wallet> wallet = walletRepository.findByCpfOrEmail(dto.cpf(), dto.email());

        if(wallet.isPresent()) {
            throw new WalletDataAlreadyExistsException("cpf or email already exists");
        }

        Wallet newWallet = new Wallet();
        newWallet.setBalance(BigDecimal.ZERO);
        newWallet.setName(dto.name());
        newWallet.setCpf(dto.cpf());
        newWallet.setEmail(dto.email());

        return walletRepository.save(newWallet);
    }

    public boolean deleteWallet(UUID walletId) {
        Optional<Wallet> wallet = walletRepository.findById(walletId);
        if (wallet.isPresent()) {
            if (wallet.get().getBalance().compareTo(BigDecimal.ZERO) != 0) {
                throw new DeleteWalletException("The balance is not zero");
            }
            walletRepository.deleteById(walletId);
        }
        return wallet.isPresent();
    }

    @Transactional
    public void depositMoney(UUID walletId, DepositMoneyDto dto, String ipAddress) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("There is no wallet with id"));

        Deposit deposit = new Deposit();
        deposit.setWallet(wallet);
        deposit.setDepositValue(dto.value());
        deposit.setDepositDateTime(OffsetDateTime.now());
        deposit.setIpAddress(ipAddress);

        depositRepository.save(deposit);
        wallet.setBalance(wallet.getBalance().add(dto.value()));
        walletRepository.save(wallet);
    }
}

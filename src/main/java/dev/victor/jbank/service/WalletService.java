package dev.victor.jbank.service;

import dev.victor.jbank.controller.dto.CreateWalletDto;
import dev.victor.jbank.entity.Wallet;
import dev.victor.jbank.exception.WalletDataAlreadyExistsException;
import dev.victor.jbank.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
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
}

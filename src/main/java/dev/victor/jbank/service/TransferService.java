package dev.victor.jbank.service;

import dev.victor.jbank.controller.dto.TransferMoneyDto;
import dev.victor.jbank.entity.Transfer;
import dev.victor.jbank.entity.Wallet;
import dev.victor.jbank.exception.TransferException;
import dev.victor.jbank.exception.WalletNotFoundException;
import dev.victor.jbank.repository.TransferRepository;
import dev.victor.jbank.repository.WalletRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;

    public TransferService(TransferRepository transferRepository, WalletRepository walletRepository) {
        this.transferRepository = transferRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public void transferMoney(@Valid TransferMoneyDto dto) {
        Wallet sender = walletRepository.findById(dto.sender())
                .orElseThrow(() -> new WalletNotFoundException("Sender does not exist"));

        Wallet receiver = walletRepository.findById(dto.receiver())
                .orElseThrow(() -> new WalletNotFoundException("Receiver does not exist"));

        if (sender.getBalance().compareTo(dto.value()) < 0) {
            throw new TransferException("Insufficient balance.");
        }

        persistTransfer(dto, sender, receiver);
        updateWallets(dto, sender, receiver);
    }

    private void updateWallets(TransferMoneyDto dto, Wallet sender, Wallet receiver) {
        sender.setBalance(sender.getBalance().subtract(dto.value()));
        receiver.setBalance(receiver.getBalance().add(dto.value()));
        walletRepository.save(sender);
        walletRepository.save(receiver);
    }

    private void persistTransfer(TransferMoneyDto dto, Wallet sender, Wallet receiver) {
        Transfer transfer = new Transfer();
        transfer.setSender(sender);
        transfer.setTransferValue(dto.value());
        transfer.setReceiver(receiver);
        transfer.setTransferDateTime(OffsetDateTime.now());
        transferRepository.save(transfer);
    }
}

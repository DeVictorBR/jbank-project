package dev.victor.jbank.repository;

import dev.victor.jbank.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    Optional<Wallet> findByCpfOrEmail(String cpf, String email);
}

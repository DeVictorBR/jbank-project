package dev.victor.jbank.repository.dto;

import java.math.BigDecimal;
import java.time.Instant;

public interface StatementView {

    String getStatementId();
    String getType();
    BigDecimal getStatementValue();
    String getWalletReceiver();
    String getWalletSender();
    Instant getStatementDateTime();
}

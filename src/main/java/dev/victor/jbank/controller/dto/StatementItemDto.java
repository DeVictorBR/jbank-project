package dev.victor.jbank.controller.dto;

import java.math.BigDecimal;

public record StatementItemDto(String statementId,
                               String type,
                               String literal,
                               BigDecimal value,
                               java.time.Instant dateTime,
                               StatementOperation operation) {
}

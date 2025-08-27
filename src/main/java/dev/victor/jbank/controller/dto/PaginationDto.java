package dev.victor.jbank.controller.dto;

public record PaginationDto(Integer page,
                            Integer pagesize,
                            Long totalElements,
                            Integer totalPages) {
}

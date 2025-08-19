package com.example.t1.project.payment.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

public class PaymentRequestDto {

    @NotNull(message = "productId cannot be null or empty")
    private Long productId;
    @NotNull(message = "accountNumber cannot be null or empty")
    private Long accountNumber;
    @NotNull(message = "amount cannot be null or empty")
    private BigDecimal amount;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentRequestDto that = (PaymentRequestDto) o;
        return Objects.equals(productId, that.productId) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, accountNumber, amount);
    }

    @Override
    public String toString() {
        return "PaymentRequestDto{" +
                "productId=" + productId +
                ", accountNumber=" + accountNumber +
                ", amount=" + amount +
                '}';
    }
}

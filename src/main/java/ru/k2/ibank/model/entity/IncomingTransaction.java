package ru.k2.ibank.model.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "incoming_transaction", schema = "ibank_schema", catalog = "ibank")
public class IncomingTransaction {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotEmpty(message = "Recipient account number cant be empty")
    @Size(max = 20, message = "Max size for recipient account number is 20 characters")
    @Column(name = "recipient_account_number", updatable = false)
    private String recipientAccountNumber;

    @NotEmpty(message = "Recipient name cant be empty")
    @Size(max = 255, message = "Max size for recipient name is 255 characters")
    @Column(name = "recipient_name", updatable = false)
    private String recipientName;

    @NotEmpty(message = "Recipient surname cant be empty")
    @Size(max = 255, message = "Max size for recipient surname is 255 characters")
    @Column(name = "recipient_surname", updatable = false)
    private String recipientSurname;

    @NotEmpty(message = "Sender name cant be empty")
    @Size(max = 255, message = "Max size for sender name is 255 characters")
    @Column(name = "sender_name", updatable = false)
    private String senderName;

    @NotEmpty(message = "Sender surname cant be empty")
    @Size(max = 255, message = "Max size for sender surname is 255 characters")
    @Column(name = "sender_surname", updatable = false)
    private String senderSurname;

    @NotEmpty(message = "Sender bank SWIFT cant be empty")
    @Size(max = 11, message = "Max size for sender bank swift is 11 characters")
    @Column(name = "sender_bank_swift", updatable = false)
    private String senderBankSwift;

    @NotEmpty(message = "Sender account number cant be empty")
    @Size(max = 20, message = "Max size for sender account number is 20 characters")
    @Column(name = "sender_account_number", updatable = false)
    private String senderAccountNumber;

    @NotEmpty(message = "Money amount cant be empty")
    @Column(name = "money_amount", updatable = false)
    private BigDecimal moneyAmount;

    @NotEmpty(message = "Currency code cant be empty")
    @Size(max = 3, message = "Max size for currency code is 3 characters")
    @Column(name = "currency_code", updatable = false)
    private String currencyCode;

    @Size(max = 255, message = "Max size for payment purpose is 255 characters")
    @Column(name = "payment_purpose", updatable = false)
    private String paymentPurpose;

    @Column(name = "transaction_time", updatable = false)
    private Timestamp transactionTime;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomingTransaction that = (IncomingTransaction) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return transactionTime.toString() + ": from " + senderSurname + " " + senderName + " to " + recipientSurname + " " + recipientName;
    }
}

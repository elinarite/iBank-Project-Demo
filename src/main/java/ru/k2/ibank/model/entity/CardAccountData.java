package ru.k2.ibank.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "card_account_data", schema = "ibank_schema", catalog = "ibank")
public class CardAccountData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotEmpty(message = "Card number cant be empty")
    @Size(max = 16, message = "Max size card number is 16 chars")
    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @NotEmpty(message = "Card account number cant be empty")
    @Size(max = 20, message = "Max size card number is 20 chars")
    @Column(name = "card_account_number", nullable = false)
    private String cardAccountNumber;

    @NotNull(message = "Card issue date cant be empty")
    @Column(name = "card_issue_date", nullable = false)
    private Timestamp cardIssueDate;

    @NotNull(message = "Card expiration date cant be empty")
    @Column(name = "card_expiration_date", nullable = false)
    private Timestamp cardExpirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_account_currency_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.JOIN)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CurrencyData currencyData;

    @NotNull(message = "Card account balance cant be empty")
    @Column(name = "card_account_balance", columnDefinition = "Card account balance by default 0", nullable = false)
    private BigDecimal cardAccountBalance;

    @NotNull(message = "Is blocked cant be empty")
    @Column(name = "is_blocked", columnDefinition = "Is blocked by default false", nullable = false)
    private boolean isBlocked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_client_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.JOIN)
    private ClientData clientData;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardAccountData that = (CardAccountData) o;
        return Objects.equals(id, that.id) && Objects.equals(cardAccountNumber, that.cardAccountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardAccountNumber);
    }

    @Override
    public String toString() {
        return "id=" + id + ", cardNumber='" + cardNumber + ", cardAccountNumber=" + cardAccountNumber + ", currencyData=" + currencyData + ", cardAccountBalance=" + cardAccountBalance + ", isBlocked=" + isBlocked;
    }
}
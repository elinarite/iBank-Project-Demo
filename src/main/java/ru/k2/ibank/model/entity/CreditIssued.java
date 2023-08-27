package ru.k2.ibank.model.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credit_issued", schema = "ibank_schema", catalog = "ibank")
public class CreditIssued {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    // TODO 04.08.2023 Anton - complete relationship to ClientData
    @NotEmpty(message = "Client ID cant be empty")
    @Column(name = "client_id", updatable = false)
    private Long clientId;

    // TODO 04.08.2023 Anton - complete relationship to ManagerData
    @NotEmpty(message = "Manager ID cant be empty")
    private Long managerId;

    @NotEmpty(message = "Credit body cant be empty")
    @Column(name = "credit_body", updatable = false)
    private BigDecimal creditBody;

    @NotEmpty(message = "Credit issued date cant be empty")
    @Column(name = "credit_issued_date", updatable = false)
    private Timestamp creditIssuedDate;

    @NotEmpty(message = "Credit next payment cant be empty")
    private Timestamp creditNextPayment;

    private BigDecimal creditFine;

    @NotEmpty(message = "Credit exp date cant be empty")
    @Column(name = "credit_exp_date", updatable = false)
    private Timestamp creditExpDate;

    @NotEmpty(message = "Credit interest cant be empty")
    private BigDecimal creditInterest;

    @NotEmpty(message = "Credit monthly payment cant be empty")
    private BigDecimal creditMonthlyPayment;

    private BigDecimal creditOverdue;

    @NotEmpty(message = "Credit left cant be empty")
    private BigDecimal creditLeft;

    @ManyToOne
    @JoinColumn(name = "credit_offer_id", referencedColumnName = "id")
    @NotEmpty(message = "Credit offer ID cant be empty")
    private CreditOffer creditOfferId;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditIssued that = (CreditIssued) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // ToDo 01.08.2023 Anton - clientId должен выводить фамилию и имя клиента
    @Override
    public String toString() {
        return "Credit " + creditOfferId + " issued for " + clientId;
    }
}

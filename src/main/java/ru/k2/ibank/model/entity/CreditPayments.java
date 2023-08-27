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
@Table(name = "credit_payments", schema = "ibank_schema", catalog = "ibank")
public class CreditPayments {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    // ToDo 04.08.2023 Anton - complete relationship to ClientData
//    @ManyToOne
//    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @NotEmpty(message = "Client ID cant be empty")
    @Column(name = "client_id", updatable = false)
    private Long clientId;

    @ManyToOne
    @JoinColumn(name = "credit_issued_id", referencedColumnName = "id")
    @NotEmpty(message = "Credit issued ID cannot be empty")
    private CreditIssued creditIssuedId;

    private Timestamp paymentDate;

    @NotEmpty(message = "Payment amount cant be empty")
    private BigDecimal paymentAmount;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditPayments that = (CreditPayments) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // ToDo 01.08.2023 Anton - clientId должен выводить фамилию и имя клиента
    @Override
    public String toString() {
        return paymentDate.toString() + ": from client" + clientId;
    }
}

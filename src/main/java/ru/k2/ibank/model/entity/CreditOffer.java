package ru.k2.ibank.model.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credit_offer", schema = "ibank_schema", catalog = "ibank")
public class CreditOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotEmpty(message = "Credit name cant be empty")
    @Size(max = 50, message = "Max size for credit name is 50 characters")
    private String creditName;

    @NotNull(message = "Credit valid from cant be empty")
    private Timestamp creditValidFrom;

    private Timestamp creditValidTill;

    @NotNull(message = "Credit interest cant be empty")
    @Column(name = "credit_interest", updatable = false)
    private Double creditInterest;

    @NotNull(message = "Credit fine cant be empty")
    @Column(name = "credit_fine", updatable = false)
    private Double creditFine;

    private Short creditMinTerm;

    private Short creditMaxTerm;

    private BigDecimal creditMinAmount;

    private BigDecimal creditMaxAmount;

    @Size(max = 255, message = "Max size for credit remark is 255 characters")
    private String creditRemarks;

    @ManyToOne
    @JoinColumn(name = "credit_currency", referencedColumnName = "id")
    @NotNull(message = "Credit currency cant be empty")
    private CurrencyData currencyData;

    @OneToMany(mappedBy = "creditOffer")
    private List<CreditReq> creditReqs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditOffer that = (CreditOffer) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Credit: " + creditName + ", valid till " + creditValidTill;
    }
}

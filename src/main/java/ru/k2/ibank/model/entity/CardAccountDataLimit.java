package ru.k2.ibank.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "card_account_data_limit", schema = "ibank_schema", catalog = "ibank")
public class CardAccountDataLimit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull(message = "Card account data id not must be blank")
    @JoinColumn(name = "card_account_data_id", referencedColumnName = "id", nullable = false)
    private CardAccountData cardAccountData;


    @Column(name = "monthly_limit_amount")
    private BigDecimal monthlyLimitAmount;

    @Column(name = "monthly_limit_used")
    private BigDecimal monthlyLimitUsed;

    @Column(name = "monthly_limit_month")
    private Date monthlyLimitMonth;

    @Column(name = "daily_limit_amount")
    private BigDecimal dailyLimitAmount;

    @Column(name = "daily_limit_used")
    private BigDecimal dailyLimitUsed;

    @Column(name = "daily_limit_date")
    private Date dailyLimitDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardAccountDataLimit that = (CardAccountDataLimit) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", cardAccountData: " + cardAccountData +
                ", monthlyLimitAmount: " + monthlyLimitAmount +
                ", monthlyLimitUsed: " + monthlyLimitUsed +
                ", monthlyLimitMonth: " + monthlyLimitMonth +
                ", dailyLimitAmount: " + dailyLimitAmount +
                ", dailyLimitUsed: " + dailyLimitUsed +
                ", dailyLimitDate: " + dailyLimitDate;
    }
}
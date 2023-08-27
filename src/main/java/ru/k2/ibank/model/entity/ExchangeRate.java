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
@Table(name = "exchange_rate", schema = "ibank_schema", catalog = "ibank")
public class ExchangeRate {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exch_ticker", referencedColumnName = "id")
    @NotEmpty(message = "Exchange ticker cant be empty")
    private ExchangeTicker exchTicker;

    @NotEmpty(message = "Rate cant be empty")
    private BigDecimal rate;

    private Timestamp rateDate;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRate that = (ExchangeRate) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return exchTicker.getTickerName() + " rate: " + rate.toString();
    }
}

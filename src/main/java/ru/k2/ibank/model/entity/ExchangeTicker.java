package ru.k2.ibank.model.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exchange_ticker", schema = "ibank_schema", catalog = "ibank")
public class ExchangeTicker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotEmpty(message = "Ticker name cant be empty")
    @Size(max = 15, message = "Max size for ticker name is 15 characters")
    private String tickerName;

    @ManyToOne
    @JoinColumn(name = "currency_from", referencedColumnName = "id")
    @NotNull(message = "Currency from cant be empty")
    private CurrencyData currencyDataFrom;

    @ManyToOne
    @JoinColumn(name = "currency_to", referencedColumnName = "id")
    @NotNull(message = "Currency to cant be empty")
    private CurrencyData currencyDataTo;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeTicker that = (ExchangeTicker) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return tickerName;
    }
}

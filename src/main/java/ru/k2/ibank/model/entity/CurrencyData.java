package ru.k2.ibank.model.entity;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "currency_data", schema = "ibank_schema", catalog = "ibank")
public class CurrencyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotEmpty(message = "Currency code cant be empty")
    @Column(name = "currency_code", nullable = false)
    @Size(max = 3, message = "Max size for currency code is 3 characters")
    private String currencyCode;

    @NotEmpty(message = "Currency name cant be empty")
    @Size(max = 24, message = "Max size for currency name is 24 characters")
    @Column(name = "currency_name", nullable = false)
    private String currencyName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyData that = (CurrencyData) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Currency: " + currencyName + ", code: " + currencyCode;
    }
}
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
@Table(name = "country_data", schema = "ibank_schema", catalog = "ibank")
public class CountryData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotEmpty(message = "Country name cant be empty")
    @Size(max = 255, message = "Max size for country name is 255 characters")
    private String countryName;

    @NotEmpty(message = "Country code cant be empty")
    @Size(max = 3, message = "Max size for country code is 3 characters")
    private String countryCode;

    @OneToOne
    @JoinColumn(name = "country_currency", referencedColumnName = "id")
    @NotNull(message = "Country currency cant be empty")
    private CurrencyData countryCurrency;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryData that = (CountryData) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Country " + countryName + ", code: " + countryCode + ", currency: " + countryCurrency.getCurrencyCode();
    }
}

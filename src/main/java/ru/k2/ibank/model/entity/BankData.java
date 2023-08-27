package ru.k2.ibank.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank_data", schema = "ibank_schema", catalog = "ibank")
public class BankData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Schema(description = "Bank name", example = "JPMorgan Chase Bank")
    @NotEmpty(message = "Bank name cant be empty")
    @Size(max = 255, message = "Max size for bank name is 255 characters")
    private String bankName;

    @Schema(description = "Bank address", example = "383 Madison Ave, New York, NY 10179")
    @Size(max = 255, message = "Max size for bank address is 255 characters")
    private String bankAddress;

    @Schema(description = "Bank country, link to another schema", example = "'2' (where ID 2 = USA in another schema)")
    @ManyToOne
    @JoinColumn(name = "bank_country", referencedColumnName = "id")
    private CountryData bankCountry;

    @Schema(description = "Bank IBAN", example = "FR1420041010050500013M02606")
    @NotEmpty(message = "Bank IBAN cant be empty")
    @Size(max = 34, message = "Max size for bank IBAN is 34 characters")
    private String bankIban;

    @Schema(description = "Bank SWIFT", example = "CHASUS33XXX")
    @NotEmpty(message = "Bank SWIFT cant be empty")
    @Size(max = 11, message = "Max size for bank SWIFT is 11 characters")
    private String bankSwift;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankData that = (BankData) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Bank " + bankName + ", located in " + bankCountry.getCountryName() + ", SWIFT: " + bankSwift + ", IBAN: " + bankIban;
    }
}

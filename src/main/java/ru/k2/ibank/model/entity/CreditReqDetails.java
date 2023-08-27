package ru.k2.ibank.model.entity;

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
@Table(name = "credit_req_details", schema = "ibank_schema", catalog = "ibank")
public class CreditReqDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotEmpty(message = "Credit requirement name cant be empty")
    @Size(max = 30, message = "Max size for credit requirement name is 30 characters")
    private String creditReqName;

    @Size(max = 255, message = "Max size for credit requirement description is 255 characters")
    private String creditReqDescription;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditReqDetails that = (CreditReqDetails) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return creditReqName + ": " + creditReqDescription;
    }
}

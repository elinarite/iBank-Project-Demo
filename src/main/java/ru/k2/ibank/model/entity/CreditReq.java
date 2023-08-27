package ru.k2.ibank.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Filter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credit_req", schema = "ibank_schema", catalog = "ibank")
public class CreditReq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @Filter(name = "id")
    @JoinColumn(name = "credit_offer_id", referencedColumnName = "id")
    @NotNull(message = "Credit offer ID cant be empty")
    private CreditOffer creditOffer;

    @ManyToOne
    @JoinColumn(name = "credit_req_details_id", referencedColumnName = "id")
    @NotNull(message = "Credit requirement details cant be empty")
    private CreditReqDetails creditReqDetails;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditReq that = (CreditReq) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package ru.k2.ibank.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "card_account_status", schema = "ibank_schema", catalog = "ibank")
public class CardAccountStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Card manager id not must be blank")
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ManagerData managerData;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Card block reason not must be blank")
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "block_reason_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private BlockReason blockReason;

    @NotNull(message = "Card block date not must be blank")
    @Column(name = "card_block_date", nullable = false)
    private Timestamp cardBlockDate;

    @Column(name = "card_unblock_date")
    private Timestamp cardUnblockDate;

    @OneToOne (fetch = FetchType.LAZY)
    @NotNull(message = "Card account data id not must be blank")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "card_account_id", referencedColumnName = "id", nullable = false, updatable = false)
    private CardAccountData cardAccountData;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardAccountStatus that = (CardAccountStatus) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", manager: " + managerData +
                ", blockReason: " + blockReason +
                ", cardAccount: " + cardAccountData +
                ", cardBlockDate: " + cardBlockDate +
                ", cardUnblockDate: " + cardUnblockDate;
    }
}
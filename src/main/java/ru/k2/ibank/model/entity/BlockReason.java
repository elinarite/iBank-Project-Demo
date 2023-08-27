package ru.k2.ibank.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "block_reason", schema = "ibank_schema", catalog = "ibank")
public class BlockReason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotEmpty(message = "Block reason cant be empty")
    @Size(max = 30, message = "Max size for block reason is 30 characters")
    @Column(name = "block_reason", nullable = false)
    private String blockReason;

    @Size(max = 255, message = "Max size for block_description is 255 characters")
    @Column(name = "block_description")
    private String blockDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockReason that = (BlockReason) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Block reason: " + blockReason + ", block description: " + blockDescription;
    }
}
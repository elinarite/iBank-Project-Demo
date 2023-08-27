package ru.k2.ibank.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client_data")
public class ClientData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Client name cannot be empty")
    @Size(max = 255, message = "Max size for client name is 255 characters")
    @Column(name = "client_name", nullable = false)
    private String clientName;

    @NotEmpty(message = "Client surname cannot be empty")
    @Size(max = 255, message = "Max size for client surname is 255 characters")
    @Column(name = "client_surname", nullable = false)
    private String clientSurname;

    @Size(max = 255, message = "Max size for client middlename is 255 characters")
    @Column(name = "client_middlename")
    private String clientMiddlename;

    @NotEmpty(message = "Client gender cannot be empty")
    @Size(max = 1, message = "Max size for client gender is 1 character")
    @Column(name = "client_gender", nullable = false)
    private char clientGender;

    @NotEmpty(message = "Client birthdate cannot be empty")
    @Column(name = "client_birthdate", nullable = false)
    private Date clientBirthdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_country_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CountryData clientCountry;

    @NotEmpty(message = "Client address cannot be empty")
    @Size(max = 255, message = "Max size for client address is 255 characters")
    @Column(name = "client_address", nullable = false)
    private String clientAddress;

    @NotEmpty(message = "Client email cannot be empty")
    @Size(max = 100, message = "Max size for client email is 100 characters")
    @Column(name = "client_email", nullable = false)
    private String clientEmail;

    @NotEmpty(message = "Client phone cannot be empty")
    @Size(max = 20, message = "Max size for client phone is 20 characters")
    @Column(name = "client_phone", nullable = false)
    private String clientPhone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "client_manager_id", referencedColumnName = "id", nullable = false)
    private ManagerData clientManager;

    @NotEmpty(message = "Client passport cannot be empty")
    @Size(max = 50, message = "Max size for client passport is 50 characters")
    @Column(name = "client_passport", nullable = false, unique = true)
    private String clientPassport;

    @NotEmpty(message = "Client passport issue date cannot be empty")
    @Column(name = "client_passport_issue_date", nullable = false)
    private Date clientPassportIssueDate;

    @NotEmpty(message = "Client passport department code cannot be empty")
    @Size(max = 10, message = "Max size for client passport department code is 10 characters")
    @Column(name = "client_passport_department_code", nullable = false)
    private String clientPassportDepartmentCode;

    @NotEmpty(message = "Client passport expiration date cannot be empty")
    @Column(name = "client_passport_exp_date", nullable = false)
    private Date clientPassportExpDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientData that = (ClientData) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
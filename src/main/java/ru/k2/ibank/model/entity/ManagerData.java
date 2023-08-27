package ru.k2.ibank.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "manager_data", schema = "ibank_schema", catalog = "ibank")
public class ManagerData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotEmpty(message = "Manager name cant be empty")
    @Size(max = 255, message = "Max size for manager name is 255 characters")
    @Column(name = "manager_name", nullable = false)
    private String managerName;

    @Size(max = 255, message = "Max size for manager middle name is 255 characters")
    @Column(name = "manager_middlename", updatable = false)
    private String managerMiddlename;

    @NotEmpty(message = "Manager surname cant be empty")
    @Size(max = 255, message = "Max size for manager surname is 255 characters")
    @Column(name = "manager_surname", nullable = false)
    private String managerSurname;

    @NotNull(message = "Manager birthday cant be empty")
    @Column(name = "manager_birthday", nullable = false)
    private Date managerBirthday;

    @NotEmpty(message = "Manager gender cant be empty")
    @Size(max = 1, message = "Max size for manager gender is 1 character")
    @Column(name = "manager_gender", nullable = false)
    private char managerGender;

    @NotEmpty(message = "Manager passport cant be empty")
    @Size(max = 50, message = "Max size for manager passport is 50 characters")
    @Column(name = "manager_passport", nullable = false)
    private String managerPassport;

    @NotNull(message = "Manager passport issue date cant be empty")
    @Column(name = "manager_passport_iss_date", nullable = false)
    private Date managerPassportIssDate;

    @NotNull(message = "Manager passport exp date cant be empty")
    @Column(name = "manager_passport_exp_date", nullable = false)
    private Date managerPassportExpDate;

    @NotEmpty(message = "Manager phone cant be empty")
    @Size(max = 20, message = "Max size for manager phone is 20 characters")
    @Column(name = "manager_phone", nullable = false)
    private String managerPhone;

    @NotEmpty(message = "Manager email cant be empty")
    @Size(max = 100, message = "Max size for manager email is 100 characters")
    @Column(name = "manager_email", nullable = false)
    private String managerEmail;

    @NotNull(message = "Manager hiring date cant be empty")
    @Column(name = "manager_hiring_date", nullable = false)
    private Date managerHiringDate;

    @Column(name = "manager_dismiss_date")
    private Date managerDismissDate;

    @NotEmpty(message = "Manager passport department code cant be empty")
    @Size(max = 10, message = "Max size for manager passport department code is 10 characters")
    @Column(name = "manager_passport_department_code", nullable = false)
    private String managerPassportDepartmentCode;

    @NotEmpty(message = "Manager address cant be empty")
    @Size(max = 255, message = "Max size for Manager address code is 255 characters")
    @Column(name = "manager_adress", nullable = false)
    private String managerAddress;


    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "manager_country_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CountryData countryData;

    @OneToMany(mappedBy = "clientManager", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ClientData> clients;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagerData that = (ManagerData) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Name: " + managerName + ", surname:  " + managerSurname;
    }
}
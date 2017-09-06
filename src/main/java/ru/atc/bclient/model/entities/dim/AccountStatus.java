package ru.atc.bclient.model.entities.dim;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "dim_account_status", schema = "bclient")
@Entity
@Access(AccessType.FIELD)
public class AccountStatus {
    @Id
    @Column(name = "account_status_id")
    @Access(AccessType.PROPERTY)
    private int id;

    @Column(name = "account_status_code")
    @Enumerated(EnumType.STRING)
    @NotNull
    private AccountStatusCode code;

    @Column(name = "account_status_name")
    @NotNull
    private String name;

    @Override
    public String toString() {
        return "AccountStatus{" +
                "id=" + id +
                ", statusCode=" + code +
                ", statusName='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountStatusCode getCode() {
        return code;
    }

    public void setCode(AccountStatusCode statusCode) {
        this.code = statusCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String statusName) {
        this.name = statusName;
    }
}

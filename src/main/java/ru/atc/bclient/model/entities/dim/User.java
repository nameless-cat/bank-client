package ru.atc.bclient.model.entities.dim;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "dim_user", schema = "bclient", uniqueConstraints = {@UniqueConstraint(columnNames = "user_login", name = "uk_user_login ")})
@Entity
@Access(AccessType.FIELD)
public class User {
    @Id
    @Column(name = "user_id")
    @SequenceGenerator(name = "secUserId", sequenceName = "seq_user_id", schema = "bclient")
    @GeneratedValue(generator = "secUserId", strategy = GenerationType.SEQUENCE)
    @Access(AccessType.PROPERTY)
    private int id;
    @Column(name = "user_login")
    @NotNull
    private String login;
    @Column(name = "user_full_name")
    @NotNull
    private String fullName;
    @Column(name = "user_password")
    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rel_user_legal_entity", schema = "bclient",
            joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "legal_entity_id")})
    private List<LegalEntity> legalEntities;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", legalEntities=" + legalEntities +
                '}'+'\n';
    }

    public int getId() {
        return id;
    }

    public List<LegalEntity> getLegalEntities() {
        return legalEntities;
    }

    public void setLegalEntities(List<LegalEntity> legalEntities) {
        this.legalEntities = legalEntities;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

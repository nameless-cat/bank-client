package ru.atc.bclient.model.entities.dim;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = LegalEntity.ALL, query = "SELECT dle FROM LegalEntity dle"),
})
@Access(AccessType.FIELD)
@Table(name = "dim_legal_entity", schema = "bclient")
@Entity
public class LegalEntity{
    public static final String ALL = "DimLegalEntity.getAll";
    @Id
    @Column(name = "legal_entity_id")
    @SequenceGenerator(name = "secLegalEntityId", sequenceName = "seq_legal_entity_id", schema = "bclient")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "secLegalEntityId")
    @Access(AccessType.PROPERTY)
    private int id;

    @Column(name = "legal_entity_short_name")
    @NotNull
    private String shortName;

    @Column(name = "legal_entity_full_name")
    @NotNull
    private String fullName;

    @Column(name = "legal_entity_inn")
    @NotNull
    private String inn;

    @Column(name = "legal_entity_kpp")
    @NotNull
    private String kpp;

    @Column(name = "legal_entity_ogrn")
    @NotNull
    private String ogrn;

    @Column(name = "legal_address")
    private String address;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "legalEntity")
    private List<Account> accounts;


    @Override
    public String toString() {
        return "LegalEntity{" +
                "id=" + id +
                ", shortName='" + shortName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", inn='" + inn + '\'' +
                ", kpp='" + kpp + '\'' +
                ", ogrn='" + ogrn + '\'' +
                ", address='" + address + '\'' +
                '}'+'\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LegalEntity that = (LegalEntity) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}

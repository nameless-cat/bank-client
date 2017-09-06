package ru.atc.bclient.model.entities.dim;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Table(name = "dim_bank", schema = "bclient")
@Entity
@Access(AccessType.FIELD)
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Bank implements Serializable{
    @Id
    @Column(name = "bank_id")
    @SequenceGenerator(name = "seqBank", sequenceName = "seq_bank_id", schema = "bclient")
    @GeneratedValue(generator = "seqBank", strategy = GenerationType.SEQUENCE)
    @Access(AccessType.PROPERTY)
    private int id;
    @Column(name = "bank_name")
    @NotNull
    private String name;
    @Column(name = "bank_inn")
    @NotNull
    private String inn;
    @Column(name = "bank_kpp")
    @NotNull
    private String kpp;
    @Column(name = "bank_bic")
    @NotNull
    private String bic;
    @Column(name = "bank_corr_acc")
    @NotNull
    private String corrAcc;

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", inn='" + inn + '\'' +
                ", kpp='" + kpp + '\'' +
                ", bic='" + bic + '\'' +
                ", corrAcc='" + corrAcc + '\'' +
                '}'+'\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bank bank = (Bank) o;

        return id == bank.id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getCorrAcc() {
        return corrAcc;
    }

    public void setCorrAcc(String corrAcc) {
        this.corrAcc = corrAcc;
    }
}

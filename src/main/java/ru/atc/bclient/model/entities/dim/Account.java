package ru.atc.bclient.model.entities.dim;

import com.fasterxml.jackson.annotation.JsonView;
import ru.atc.bclient.cache.Cache;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import ru.atc.bclient.web.controller.view.View;

@NamedQueries({@NamedQuery(name = Account.All, query = "SELECT ac FROM Account ac")})
@Table(name = "dim_account", schema = "bclient", uniqueConstraints = {@UniqueConstraint(columnNames = {"account_num"}, name = "uk_account_num")})
@Entity
@Access(AccessType.FIELD)
public class Account{
    private static final long serialVersionUID = 1L;

    public static final String All = "account.getAll";

    @Id
    @Column(name = "account_id")
    @SequenceGenerator(name = "seqAccountId", schema = "bclient", sequenceName = "seq_account_id")
    @GeneratedValue(generator = "seqAccountId", strategy = GenerationType.SEQUENCE)
    @Access(AccessType.PROPERTY)
    private int id;

    @Column(name = "account_name")
    @NotNull
    private String name;

    @JsonView(View.PaymentsWithDate.class)
    @Column(name = "account_num")
    private String num;

    @Column(name = "currency_code")
    @NotNull
    private String currencyCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "legal_entity_id")
    private LegalEntity legalEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_status_id")
    private AccountStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", num='" + num + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                //", legalEntity=" + legalEntity +
                //", accountStatus=" + status +
                //", bank=" + bank +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return id == account.id;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public LegalEntity getLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(LegalEntity legalEntity) {
        this.legalEntity = legalEntity;
    }

    public AccountStatus getStatus() {
        Cache cache = Cache.getCache();
        return cache.getAccountStatusById(status.getId());
    }

    public void setStatus(AccountStatus accountStatus) {
        this.status = accountStatus;
    }

    public Bank getBank() {
        Cache cache = Cache.getCache();
        return cache.getBankById(bank.getId());
        //return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

}

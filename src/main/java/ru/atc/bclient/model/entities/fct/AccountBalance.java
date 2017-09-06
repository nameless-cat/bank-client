package ru.atc.bclient.model.entities.fct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.atc.bclient.model.entities.dim.Account;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "fct_account_balance", schema = "bclient")
@Entity
@Access(AccessType.FIELD)
public class AccountBalance {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "account_balance_id")
    @SequenceGenerator(name = "seqAccountBalance", sequenceName = "seq_account_balance_id", schema = "bclient")
    @GeneratedValue(generator = "seqAccountBalance", strategy = GenerationType.SEQUENCE)
    @Access(AccessType.PROPERTY)
    private int id;
    @Column(name = "account_balance_date")
    @NotNull
    private Date date;
    @Column(name = "account_balance_amt")
    @NotNull
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;


    @Override
    public String toString() {
        return "AccountBalance{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", account=" + account +
                '}'+"\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}

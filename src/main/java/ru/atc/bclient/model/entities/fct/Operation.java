package ru.atc.bclient.model.entities.fct;

import ru.atc.bclient.model.entities.dim.Account;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Table(name = "fct_operation", schema = "bclient")
@Entity
@Access(AccessType.FIELD)
public class Operation {
    @Id
    @Column(name = "operation_id")
    @SequenceGenerator(name = "seqOperationId", sequenceName = "seq_operation_id", schema = "bclient")
    @GeneratedValue(generator = "seqOperationId", strategy = GenerationType.SEQUENCE)
    @Access(AccessType.PROPERTY)
    private int id;

    @Column(name = "operation_date")
    @NotNull
    private Date date;

    @Column(name = "operation_amt")
    @NotNull
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "debet_account_id")
    @NotNull
    private Account debetAccount;

    @ManyToOne
    @JoinColumn(name = "kredit_account_id")
    @NotNull
    private Account creditAccount;

    @Column(name = "operation_descr")
    private String description;

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

    public Account getDebetAccount() {
        return debetAccount;
    }

    public void setDebetAccount(Account debetAccount) {
        this.debetAccount = debetAccount;
    }

    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Account kreditAccount) {
        this.creditAccount = kreditAccount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

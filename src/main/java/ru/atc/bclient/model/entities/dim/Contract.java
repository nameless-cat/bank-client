package ru.atc.bclient.model.entities.dim;

import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDateTime;
import ru.atc.bclient.web.controller.view.View;

@Table(name = "dim_contract", schema = "bclient")
@Entity
@Access(AccessType.FIELD)
public class Contract {
    @Id
    @Column(name = "contract_id")
    @SequenceGenerator(name = "contractId", sequenceName = "seq_contract_id", schema = "bclient")
    @GeneratedValue(generator = "contractId", strategy = GenerationType.SEQUENCE)
    @Access(AccessType.PROPERTY)
    private int id;

    @JsonView(View.PaymentsWithDate.class)
    @Column(name = "contract_name")
    @NotNull
    private String name;

    @JsonView(View.PaymentsWithDate.class)
    @Column(name = "contract_num")
    @NotNull
    private String num;

    @Column(name = "contract_open_date")
    @NotNull
    private Date openDate;

    @Column(name = "contract_close_date")
    @NotNull
    private Date closeDate;

    @ManyToOne
    @JoinColumn(name = "issuer_legal_entity_id")
    @NotNull
    private LegalEntity issuerLegalEntity;

    @ManyToOne
    @JoinColumn(name = "singer_legal_entity_id")
    @NotNull
    private LegalEntity singerLegalEntity;

    @Column(name = "currency_code")
    @NotNull
    private String currencyCode;

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", num='" + num + '\'' +
                ", openDate=" + openDate +
                ", closeDate=" + closeDate +
                ", issuerLegalEntity=" + issuerLegalEntity +
                ", singerLegalEntity=" + singerLegalEntity +
                ", currencyCode='" + currencyCode + '\'' +
                '}'+"\n";
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

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public LegalEntity getIssuerLegalEntity() {
        return issuerLegalEntity;
    }

    public void setIssuerLegalEntity(LegalEntity issuerLegalEntity) {
        this.issuerLegalEntity = issuerLegalEntity;
    }

    public LegalEntity getSingerLegalEntity() {
        return singerLegalEntity;
    }

    public void setSingerLegalEntity(LegalEntity singerLegalEntity) {
        this.singerLegalEntity = singerLegalEntity;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}

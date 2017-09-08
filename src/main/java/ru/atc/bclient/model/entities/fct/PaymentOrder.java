package ru.atc.bclient.model.entities.fct;

import com.fasterxml.jackson.annotation.JsonView;
import ru.atc.bclient.cache.Cache;
import ru.atc.bclient.model.entities.dim.Account;
import ru.atc.bclient.model.entities.dim.Contract;
import ru.atc.bclient.model.entities.dim.LegalEntity;
import ru.atc.bclient.model.entities.dim.PaymentOrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import ru.atc.bclient.web.controller.view.View;

@Table(name = "fct_payment_order", schema = "bclient")
@Entity
@Access(AccessType.FIELD)
public class PaymentOrder {

  @JsonView(View.PaymentsWithDate.class)
  @Id
  @Column(name = "payment_order_id")
  @SequenceGenerator(name = "seqPaymentOrderId", sequenceName = "seq_payment_order_id", schema = "bclient")
  @GeneratedValue(generator = "seqPaymentOrderId", strategy = GenerationType.SEQUENCE)
  @Access(AccessType.PROPERTY)
  private int id;

  @JsonView(View.PaymentsWithDate.class)
  @Column(name = "payment_order_num")
  @NotNull
  private int num;

  @JsonView(View.PaymentsWithDate.class)
  @Column(name = "payment_order_date")
  @NotNull
  private Date date;

  @ManyToOne
  @JoinColumn(name = "sender_legal_entity_id")
  @NotNull
  private LegalEntity senderLegalEntity;

  @JsonView(View.PaymentsWithDate.class)
  @ManyToOne
  @JoinColumn(name = "sender_account_id")
  @NotNull
  private Account senderAccount;

  @JsonView(View.PaymentsWithDate.class)
  @ManyToOne
  @JoinColumn(name = "recipient_legal_entity_id")
  @NotNull
  private LegalEntity recipientLegalEntity;

  @JsonView(View.PaymentsWithDate.class)
  @ManyToOne
  @JoinColumn(name = "recipient_account_id")
  @NotNull
  private Account recipientAccount;

  @JsonView(View.PaymentsWithDate.class)
  @ManyToOne
  @JoinColumn(name = "contract_id")
  private Contract contract;

  @JsonView(View.PaymentsWithDate.class)
  @Column(name = "currency_code")
  @NotNull
  private String currencyCode;

  @JsonView(View.PaymentsWithDate.class)
  @Column(name = "payment_order_amt")
  @NotNull
  private BigDecimal orderAmount;

  @JsonView(View.PaymentsWithDate.class)
  @Column(name = "payment_reason")
  private String reason;

  @JsonView(View.PaymentsWithDate.class)
  @Column(name = "payment_priority_code")
  private String priorityCode;

  @JsonView(View.PaymentsWithDate.class)
  @ManyToOne
  @JoinColumn(name = "payment_order_status_id")
  @NotNull
  private PaymentOrderStatus status;

  @JsonView(View.PaymentsWithDate.class)
  @Column(name = "reject_reason")
  private String rejectReason;

  @Override
  public String toString() {
    return "PaymentOrder{" +
        "id=" + id +
        ", num=" + num +
        ", date=" + date +
        ", senderLegalEntity=" + senderLegalEntity +
        ", senderAccount=" + senderAccount +
        ", recipientLegalEntity=" + recipientLegalEntity +
        ", recipientAccount=" + recipientAccount +
        ", contract=" + contract +
        ", currencyCode='" + currencyCode + '\'' +
        ", orderAmount=" + orderAmount +
        ", reason='" + reason + '\'' +
        ", priorityCode='" + priorityCode + '\'' +
        ", paymentOrderStatus=" + status +
        ", rejectReason='" + rejectReason + '\'' +
        '}';
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public LegalEntity getSenderLegalEntity() {
    return senderLegalEntity;
  }

  public void setSenderLegalEntity(LegalEntity senderLegalEntity) {
    this.senderLegalEntity = senderLegalEntity;
  }

  public Account getSenderAccount() {
    return senderAccount;
  }

  public void setSenderAccount(Account senderAccount) {
    this.senderAccount = senderAccount;
  }

  public LegalEntity getRecipientLegalEntity() {
    return recipientLegalEntity;
  }

  public void setRecipientLegalEntity(LegalEntity recipientLegalEntity) {
    this.recipientLegalEntity = recipientLegalEntity;
  }

  public Account getRecipientAccount() {
    return recipientAccount;
  }

  public void setRecipientAccount(Account recipientAccount) {
    this.recipientAccount = recipientAccount;
  }

  public Contract getContract() {
    return contract;
  }

  public void setContract(Contract contract) {
    this.contract = contract;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public BigDecimal getOrderAmount() {
    return orderAmount;
  }

  public void setOrderAmount(BigDecimal orderAmount) {
    this.orderAmount = orderAmount;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getPriorityCode() {
    return priorityCode;
  }

  public void setPriorityCode(String priorityCode) {
    this.priorityCode = priorityCode;
  }

  public PaymentOrderStatus getStatus() {
    Cache cache = Cache.getCache();
    return cache.getPaymentStatusById(status.getId());
  }

  public void setStatus(PaymentOrderStatus paymentOrderStatus) {
    this.status = paymentOrderStatus;
  }

  public String getRejectReason() {
    return rejectReason;
  }

  public void setRejectReason(String rejectReason) {
    this.rejectReason = rejectReason;
  }
}

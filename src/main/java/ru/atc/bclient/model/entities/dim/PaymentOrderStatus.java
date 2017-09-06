package ru.atc.bclient.model.entities.dim;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "dim_payment_order_status", schema = "bclient", uniqueConstraints = {@UniqueConstraint(columnNames = "payment_order_status_code", name = "uk_payment_order_status_code")})
@Entity
@Access(AccessType.FIELD)
public class PaymentOrderStatus {
    @Id
    @Column(name = "payment_order_status_id")
    @SequenceGenerator(name = "seqPaymentOrderId", sequenceName = "seq_payment_order_id", schema = "bclient")
    @GeneratedValue(generator = "seqPaymentOrderId", strategy = GenerationType.SEQUENCE)
    @Access(AccessType.PROPERTY)
    private int id;
    @Column(name = "payment_order_status_code")
    @Enumerated(EnumType.STRING)
    @NotNull
    private OrderStatusCode code;
    @Column(name = "payment_order_status_name")
    @NotNull
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatusCode getCode() {
        return code;
    }

    public void setCode(OrderStatusCode statusCode) {
        this.code = statusCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String statusName) {
        this.name = statusName;
    }
}

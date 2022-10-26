package com.ll.exam.final__2022_10_08.app.order.entity;

import com.ll.exam.final__2022_10_08.app.base.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true, exclude = {"order"})
public class OrderItem extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    private Order order;

    private int quantity;

    // 가격
    private int price; // 권장판매가
    private int salePrice; // 실제판매가
    private int wholesalePrice; // 도매가

    // 통계, 정산을 위해
    private int pgFee; // 결제대행사 수수료
    private int payPrice; // 결제금액
    private int refundPrice; // 환불금액
    private boolean isPaid; // 결제여부

    public OrderItem(Order order, int quantity) {
        this.order = order;
        this.quantity = quantity;

    }

    public int calculatePrice() {
        return salePrice * quantity;
    }

    public void setPaymentDone() {
        this.pgFee = 0;
        this.payPrice = calculatePrice();
        this.isPaid = true;
    }

    public void setRefundDone() {
        this.refundPrice = payPrice;
    }
}
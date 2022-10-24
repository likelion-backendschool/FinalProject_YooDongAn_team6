package com.ll.exam.final__2022_10_08.app.order.entity;

import com.ll.exam.final__2022_10_08.app.base.entity.BaseEntity;
import com.ll.exam.final__2022_10_08.app.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@ToString(callSuper = true)
@Table(name ="orders")
public class Order extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private LocalDateTime payDate;

    private boolean readyStatus;
    private boolean isPaid;
    private boolean isCanceled;
    private boolean isRefunded;
    private String name;


}

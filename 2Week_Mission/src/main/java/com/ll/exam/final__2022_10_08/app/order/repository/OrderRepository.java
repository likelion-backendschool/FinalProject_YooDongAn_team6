package com.ll.exam.final__2022_10_08.app.order.repository;

import com.ll.exam.final__2022_10_08.app.member.entity.Member;
import com.ll.exam.final__2022_10_08.app.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByMember(Member member);
}

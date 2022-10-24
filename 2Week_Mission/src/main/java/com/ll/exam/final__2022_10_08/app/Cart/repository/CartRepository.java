package com.ll.exam.final__2022_10_08.app.Cart.repository;

import com.ll.exam.final__2022_10_08.app.Cart.entity.CartItem;
import com.ll.exam.final__2022_10_08.app.member.entity.Member;
import com.ll.exam.final__2022_10_08.app.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByMemberAndProduct(Member member, Product product);
}

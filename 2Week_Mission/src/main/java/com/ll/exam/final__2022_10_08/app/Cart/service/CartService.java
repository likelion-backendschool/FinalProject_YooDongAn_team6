package com.ll.exam.final__2022_10_08.app.Cart.service;

import com.ll.exam.final__2022_10_08.app.Cart.entity.CartItem;
import com.ll.exam.final__2022_10_08.app.Cart.repository.CartRepository;
import com.ll.exam.final__2022_10_08.app.member.entity.Member;
import com.ll.exam.final__2022_10_08.app.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public void addItem(Member member, Product product, int quantity) {
        CartItem prevCartItem = cartRepository.findByMemberAndProduct(member, product).orElse(null);
        if(prevCartItem != null) {
            prevCartItem.setQuantity(prevCartItem.getQuantity() + quantity);
            cartRepository.save(prevCartItem);
            return;
        }
        CartItem cartItem = CartItem.builder()
                .member(member)
                .product(product)
                .quantity(quantity)
                .build();
        cartRepository.save(cartItem);
    }

}

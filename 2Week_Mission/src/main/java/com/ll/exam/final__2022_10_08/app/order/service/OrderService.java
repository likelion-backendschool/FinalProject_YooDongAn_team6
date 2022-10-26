package com.ll.exam.final__2022_10_08.app.order.service;

import com.ll.exam.final__2022_10_08.app.Cart.entity.CartItem;
import com.ll.exam.final__2022_10_08.app.Cart.service.CartService;
import com.ll.exam.final__2022_10_08.app.member.entity.Member;
import com.ll.exam.final__2022_10_08.app.member.service.MemberService;
import com.ll.exam.final__2022_10_08.app.myBook.service.MyBookService;
import com.ll.exam.final__2022_10_08.app.order.entity.Order;
import com.ll.exam.final__2022_10_08.app.order.entity.OrderItem;
import com.ll.exam.final__2022_10_08.app.order.exception.alreadyPaidException;
import com.ll.exam.final__2022_10_08.app.order.repository.OrderRepository;
import com.ll.exam.final__2022_10_08.app.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final MemberService memberService;
    private final MyBookService myBookService;

    @Transactional
    public Order order(Member member) {
        List<CartItem> cartItems = cartService.findAllByMember(member);
        Order order = orderRepository.findByMember(member).orElseThrow();
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            if(product.isOrderable()) {
                orderItems.add(new OrderItem(order, cartItem.getQuantity()));
            }
            cartService.deleteItem(cartItem);
        }
        return create(member, orderItems);
    }
    @Transactional
    public Order create(Member member, List<OrderItem> orderItems) {
        Order order = Order.builder()
                .member(member)
                .build();
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.order();
        orderRepository.save(order);
        return order;
    }

    public void cancel(@PathVariable("orderId") Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        if (order.isReadyStatus()) {
            orderRepository.deleteById(orderId);
        } else {
            throw new alreadyPaidException("이미 결제가 완료된 상품입니다!");
        }

    }

    @Transactional
    public void payByRestCashOnly(Order order) {
        Member member = order.getMember();
        long restCash = member.getRestCash();
        int totalPrice = order.calculatePrice();

        if (totalPrice > restCash) {
            memberService.addCash(member, totalPrice, "주문결제__포인트충전후결제");
        }
        memberService.addCash(member, totalPrice * -1, "주문결제__예치금결제");

        order.setPaymentDone();
        orderRepository.save(order);

    }

    @Transactional
    public void refund(Order order) {
        int payPrice = order.getPayPrice();
        memberService.addCash(order.getMember(), payPrice, "주문환불__예치금환불");
        order.setRefundDone();
        orderRepository.save(order);

    }

}

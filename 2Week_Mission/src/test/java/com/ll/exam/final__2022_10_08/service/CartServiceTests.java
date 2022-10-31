package com.ll.exam.final__2022_10_08.service;

import com.ll.exam.final__2022_10_08.app.Cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class CartServiceTests {
    @Autowired
    private CartService cartService;





}

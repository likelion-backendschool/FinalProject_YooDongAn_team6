package com.ll.exam.final__2022_10_08.app.myBook.service;

import com.ll.exam.final__2022_10_08.app.member.entity.Member;
import com.ll.exam.final__2022_10_08.app.myBook.entity.MyBook;
import com.ll.exam.final__2022_10_08.app.myBook.repository.MyBookRepository;
import com.ll.exam.final__2022_10_08.app.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyBookService {
    private final MyBookRepository myBookRepository;

    public void save(Member member, Product product) {
        MyBook myBook = MyBook.builder()
                .member(member)
                .product(product)
                .build();
        myBookRepository.save(myBook);
    }

}

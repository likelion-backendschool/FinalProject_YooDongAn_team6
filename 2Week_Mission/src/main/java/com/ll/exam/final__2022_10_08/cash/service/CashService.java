package com.ll.exam.final__2022_10_08.cash.service;

import com.ll.exam.final__2022_10_08.app.member.entity.Member;
import com.ll.exam.final__2022_10_08.cash.entity.CashLog;
import com.ll.exam.final__2022_10_08.cash.repository.CashRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashService {
    private final CashRepository cashRepository;

    public CashLog addCash(Member member, long price, String eventType) {
        CashLog cashLog = CashLog.builder()
                .member(member)
                .price(price)
                .eventType(eventType)
                .build();
        cashRepository.save(cashLog);

        return cashLog;
    }
}

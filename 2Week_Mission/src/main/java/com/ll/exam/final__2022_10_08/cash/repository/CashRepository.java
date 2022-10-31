package com.ll.exam.final__2022_10_08.cash.repository;

import com.ll.exam.final__2022_10_08.cash.entity.CashLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashRepository extends JpaRepository<CashLog, Long> {
}

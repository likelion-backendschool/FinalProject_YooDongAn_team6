package com.example.mutbooks.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @CreatedBy
    private LocalDateTime createDate;

    @LastModifiedBy
    private LocalDateTime updateDate;
}

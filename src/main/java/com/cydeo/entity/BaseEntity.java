package com.cydeo.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isDeleted = false;

    @Column(nullable = false,updatable = false)
    private LocalDateTime insertDateTime;
    @Column(nullable = false,updatable = false)
    private Long insertUserId;
    @Column(nullable = false)
    private LocalDateTime lastUpdateDateTime;
    @Column(nullable = false)
    private Long lastUpdateUserId;

//bu asagidaki iki kodu farkli bir sinifa alacagiz.Cunku spring tarafindan bize verilen listener mekanizmasini kullanacagiz.Bunun icin springin bize sagladigi ozellikleri o sinifa aktarmaliyiz.Miras birakmaliyiz.Bu ozellikleri BaseEntity sinifina aktaramayiz.
//    @PrePersist
//    private void onPrePersist(){
//        this.insertDateTime = LocalDateTime.now();
//        this.lastUpdateDateTime=LocalDateTime.now();
//        this.insertUserId=1L;
//        this.lastUpdateUserId=1L;
//    }
//
//    @PreUpdate
//    private void onPreUpdate(){
//        this.lastUpdateDateTime=LocalDateTime.now();
//        this.lastUpdateUserId=1L;
//    }




}

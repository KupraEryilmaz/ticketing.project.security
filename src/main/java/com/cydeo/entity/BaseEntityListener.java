package com.cydeo.entity;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Component
public class BaseEntityListener extends AuditingEntityListener {


    //    uygulamada herhangi bir aktivite oldugu zaman AuditingEntityListener dinleyecek.Kimi dinleyecek BaseEntity.Neden BaseEntity i dinleyecek. Cunku bu field lara BaseEntity sahip

//    @PrePersist
//    private void onPrePersist(BaseEntity baseEntity){ //AuditingEntityListener BaseEntity i dinleyecek
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////sisteme giris yapan kisiye dair tum bilgilerini al diyoruz.
//        baseEntity.setInsertDateTime(LocalDateTime.now());
//        baseEntity.setLastUpdateDateTime(LocalDateTime.now());
//
//        if(authentication != null && !authentication.getName().equals("anonymousUser")){//anonymousUser ne demek gecerli isim degil
//            Object principal = authentication.getPrincipal();
//            baseEntity.setInsertUserId(((UserPrincipal) principal).getId());//bu kodu ilk yazdigimiz zaman getId() hata verdi neden cunku UserPrincipal id field ina sahip degildi UserPrincipal sinifina Id field ini ekledik.Peki neden UserPrincipal i kullandik.Cunku Spring kendi User indan anlar id ise Bizim user entity sinifimizdan geliyor.UserPrincipal araciligi ile entity spring in User ina ceviriyoruz.
//            baseEntity.setLastUpdateUserId( ((UserPrincipal) principal).getId());
//        }
//    }
//
//    @PreUpdate
//    private void onPreUpdate(BaseEntity baseEntity){
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        baseEntity.setLastUpdateDateTime(LocalDateTime.now());
//
//        if(authentication != null && !authentication.getName().equals("anonymousUser")){
//            Object principal = authentication.getPrincipal();
//            baseEntity.setLastUpdateUserId( ((UserPrincipal) principal).getId());
//        }
//    }



}

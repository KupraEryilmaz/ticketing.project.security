package com.cydeo.entity.common;

import com.cydeo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//bu sinifi kullanarak db deki user entity i Spring in User ina donusturuyorum.


public class UserPrincipal implements UserDetails { //spring belirli fieldlar ile calisiyor bunlari ben nereden alacagim UserDetails class indan

    private final User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorityList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(this.user.getRole().getDescription());
        authorityList.add(authority);
        return authorityList;
    }
    //yetkilendirme tanimlamaya ne zaman ihtiyac duyarsak GrantedAuthority implementationunu kullanmaya ihtiyac duyariz.SimpleGrantedAuthority(this.user.getRole().getDescription() role i manuel olarak yazmiyoruz. bu sekilde cagiriyoruz.

    @Override
    public String getPassword() {
        return this.user.getPassWord();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;//bu field a kendi User class imizda sahip degiliz o nedenle true yaziyoruz.False olursa spring security kimligi dogrulamaz
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {//enable anlami: bir siteye kayit oldugumuz zaman site email veya mesaj yolu ile aktivasyon kodu gonderiyor.Anlami bu.Fakat biz user i olustururken suan  ki sistemde buna sahip degiliz. O nedenle false oluyor.Cunku user i olusturuken enabled a dair formdan bize bir bilgi gelmiyor.UserDto da  private boolean enabled; atama yapmadigimiz icin default olarak false degerini aliyor.false oldgugu icinde sisteme login yapmamiza izin verilmiyor.bunu cozmek icin save methodundan once set.enabled(true) diyecegiz
        return this.user.isEnabled();
    }

    public Long getId(){
        return this.user.getId();
    }




}
    //private final User user;//mapper sinifini tamamladik.Fakat user i bir yerde kullandik mi? Henuz hayir.Peki simdi ne yapmaliyim.Bunun icin service ihtiyacim var. loadByUserName() methodunu overide edecegim.Cunku bu method user entity i userprincipal mapper sinifi araciligi ile donusturerek UI a verecek.Bunun icin bu methodu override etmeliyim.Cunku biz springin default olarak sundugu user i kullanmak istemiyoruz.Springin default olarak verdigi tum seyleri override ediyoruz. SecurityService interfacesini olusturuyoruz. UserDetailsService interfacesini implement edecegim.Neden override ediyoruz.Override etmezsek spring entity user i springin user ina donusturerek UI a verecegini bilemez.Springin user ini UI a verir
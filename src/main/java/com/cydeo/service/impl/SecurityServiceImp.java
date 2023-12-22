package com.cydeo.service.impl;

import com.cydeo.entity.User;
import com.cydeo.entity.common.UserPrincipal;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImp implements SecurityService {
    private final UserRepository userRepository;


    public SecurityServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;

    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//bu method ile amacim ne? kimlik dogrulamasi yapmak.fakat bu method benim user entity den anlamaz.spring in user in dan anlar.Peki bunun icin ne yapacagim.UserPrincipal ile entity i spring user ina cevirecegim

        User user = userRepository.findByUserNameAndIsDeleted(username,false);

        if(user==null){
            throw new UsernameNotFoundException(username); //SPRING bizim yerimize exeption handling yapiyor.
        }


        return new UserPrincipal(user);
    }

    // bu method ile ne diyoruz. DB den user i al ve onu spring user a cevir.UserPrincipal sinifini kullanarak
}

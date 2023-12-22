package com.cydeo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication  //this includes @Configuration
public class TicketingProjectSecurityApplication {
//ilk olarak pom.xml e security dependency i ekliyoruz.
    public static void main(String[] args) {

        SpringApplication.run(TicketingProjectSecurityApplication.class, args);
    }

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
    

    @Bean
    public PasswordEncoder passwordEncoder(){//Bu sinifa ait methodu kullanarak kendi passwordumu encode yapmak istiyorum.
       return new BCryptPasswordEncoder();//PasswordEncoder bir interface BCryptPasswordEncoder() ise bir implementation ve bir cok developer bunu kullaniyor.API ya gectigimiz zaman API bir structer kullanacak.API arka planda bu implementation u kullanacak.Biz direk olarak BCryptPasswordEncoder() i kullanmayacagiz.biz diger belli seyleri kullanacagiz.Fakat UI da encoding ile kendimizi tanistiriyoruz.Bu methodun asil amaci ne? Bizim passwordumuzu aliyor.Belirli logicler ekliyor.Daha sonrasinda onu encoding password e ceviriyor.Neden cunku biz ne zaman bir user olustursak passwordunu database e o sekilde kaydedemeyiz.Encoding passworde cevirdikten sonra kaydedebiliriz.
        }
    }
//Spring soyle soyluyor eger herhangi bir spring security i kullanmak istersen benim objemi kullanmaya ihtiyac duyacaksin.bizim kendi objemiz olsa dahi.Spring dto objeni benim objeme cevirmek zorundasin diyor.Neden spring boyle diyor.diyelim ki abc sirketi UserDto classinda 30 field a sahip. def sirketi UserDTO classinda 100 field a sahip.Peki spring bu fieldlarin hangisini alip hangisini security icin kullanacagini nasil bilecek.Bilemez. O nedenle kendisinin olustudugu belirli standartlara sahip objeye cevrilmesini istiyor.Spring boylelikle bizim classlarimizdaki fieldlar ile ilgilenmiyor.Spring benim standartlarim var.Benim security mi kullanmak istiyorsaniz benim stanartlarima uymak zorundasiniz diyor.Peki benim sorumlulugum ne?Springin bize sunmus oldugu yapiyi overwrite etmek


// Programi calistirdiktan sonra browser da acilan sayfayi gormek istemiyorum.Bunun icin ne yapacagim.Kendi user ve passwordumu olusturacagim.Buradan sonra ki adim Security config sinifi


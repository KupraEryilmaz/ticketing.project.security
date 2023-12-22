package com.cydeo.config;

import com.cydeo.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfig {

    private final SecurityService securityService;
    private final AuthSuccessHandler authSuccessHandler;

    public SecurityConfig(SecurityService securityService, AuthSuccessHandler authSuccessHandler) {
        this.securityService = securityService;
        this.authSuccessHandler = authSuccessHandler;
    }

    //Burada ne yapacagim peki? Spring in bana saglamis oldugu user ve passwordunu kullanmak istemiyorum.Kendi user ve passwordumu tanitmak istiyorum.Kendi olusturdugum user ve passwordun kimlik dogrulamasini yapmak istiyorum.


//I was creating my users
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder){
//
//
//        List<UserDetails> userList = new ArrayList<>();
//
//        userList.add(
//                new User("mike",encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")))//yetkilendirme tanimlamaya ne zaman ihtiyac duyarsan GrantedAuthority implementationunu kullanmaya ihtiyac duyarsin.How you defining your authority for this user(Bu kullanici icin yetkinizi nasil tanimliyorsunuz). data databaseden gelmeyip manuel olarak bizim tarafimizdan olusturuluyorsa  yetkilendirme tanimlama islemi SimpleGrantedAuthority("ROLE_ADMIN")) bu sekilde yapilmali. ROLE_ADMIN formatinda olusturmazsak  spring role u  anlamaz.spring naming conversion her ne zaman manuel olarak olusturdugumuz user in role u ile tanistiracak olsak bu sekilde yazmak zorundayiz.Direk admin yazamayiz.
//        );
//
//        userList.add(
//                new User("ozzy",encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER")))
//        );
        //ne yaptim iki tane user in kimlik dogrulamasini ve yetkilendirme icin gerekli olan role un tanimini yaptim

//     return new InMemoryUserDetailsManager(userList);
//    }
// userdetails interface oldugu icin o return olamaz bu implementationunu kullanacagiz.Bu implement ile olusturdugum objeleri memory e kaydedecegim.Suan icin database ile calismadigimiz icin buraya kaydedecegiz.uygulamalarda buraya kaydetmek yaygin degil fakat test asamasinda burayi kaydetmek icin kullanabiliriz.
//    // Bu method sonucunda programimi calistirdigim zaman konsolda password gorunmuyor.Cunku overwrite ettim. springe dedik ki default olarak olusturdugun user i ve password u kullanma ben kendi olusturdugum userimi kullanmak istiyorum.
//
//            //Genel ozet security dependency i intelije e ekledikten sonra browserda security log in sayfasi cikti bu sayfayi gecmeden kendi log in sayfama giris yapamiyorum.spring kimlik dogrulamaya ihtiyacim var diyor.Spring in bana saglamis oldugu user ve passwordunu girerek uygulamama giris yapabilirim fakat bunu istemiyorum cunku kendi user im ile tanistirmak istiyorum .burada oldugu gibi kendi user ve passwordumu olusturuyorum.
//            Spring kimlik dogrulama icin kendi User sinifini kullaniyor.Bu spring in kurali.Peki biz springin user ini UI a nasil tasiyacagiz? Service ile . Neden cunku bu bir business logic bu nedenle bu islemi service layerda yapmaliyiz.UserDetailsService de bulunan loadByUsername methodu ile springin user ini UI a tasiyacagiz.Bu service interface oldugu icin bir implementation sinifina ihtiyac duyuyor.Suan database ile calismiyoruz.Bu nedenle InMemoryUserDetailsMangar implementation sinifini kullanacagiz.Bu implementation sinifinda loadByUserName methodu implement ediliyor.Bu method return olarak UserDetails donduruyor.UserDetails bir interface oldugu icin dondurulemez o nedenle onun implementation siniflarindan biri dondurulmeli.Bu da springin bize sunmus oldugu User sinifi.Suana kadar yaptigimiz sey bu kendi olusturdugumuz userlari(spring standardina gore) bu sekilde UI a tasiyoruz.Suan database ile calismiyorum.Manuel olarak olusturdugum user i tasiyorum.
//Bu methodu database ile calisirken kullanmiyorum.Manuel user olustururken kullaniyorum
//    }
//SecurityFilterChain bu interface ile kendi formumu springe tanitiyorum ve kimlik dogrulama icin benim formumu kullan diyorum.Tabi bu method icinde farkli islemlerde yapiyoruz.Yalnizca form tanitma islemi yapilmiyor.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

            return http
                    .authorizeRequests()//biz security i calistirdigimiz zaman butun sayfalari yetkilendirmeye ihtiyac duyariz.spring zaten butun sayfalara yetkilendirme yapmasi gerektigini bilmiyor mu ? Suan biz her seyi overwrite ediyoruz.Bu sayfaya bir seyler yazmaya basladigimiz zaman Spring de kendi kullandigi default seyleri kullanmiyor
                   // .antMatchers(("/user/**")).hasRole("ADMIN")//user end pointi ile baslayan sayfalara sadece admin erisebilsin diyoruz.hasRole methodunda ROLE_ on eki var.//hasAuthority() methodunu kullandigim zaman datada role ROLE_ADMIN seklinde ise hasAuthority("ROLE_ADMIN") seklinde olusturmaliyim.hasRole("ADMIN") methodunu kullandigim zaman kullanmak zorunda degilim.hasRole() methodu icerisinde zaten ROLE_ on ekini ekleyen kod var.

                    //antMatchers() page ile iliskili.Bu directory de olabilir controllerda olabilir.
                    .antMatchers(("/user/**")).hasAuthority("Admin")//data.sql de role Admin seklinde oldugu icin hasAuthority() metodunu kullaniyorum.Cunku birebir ayni olmali hasRole()metodunu kullanirsam basina "Role_" on ekini ekler birebir erismez.Program dogru calismaz
                    .antMatchers("/project/**").hasAuthority("Manager")
                    .antMatchers("/task/employee/**").hasAuthority("Employee")
                    .antMatchers("/task/**").hasAuthority("Manager")
//                    .antMatchers("/task/**").hasAnyRole("EMPLOYEE","ADMIN") birden fazla role bir sayfaya erisebiliyorsa bunu kullanabiliriz.
//                    .antMatchers("task/**").hasAuthority("ROLE_EMPLOYEE")   hasAuthority methodunu kullandigimiz zaman springin name convectionunu kullanmak zorundayim.
                    .antMatchers("/","/login","/fragments/**","/assets/**","/images/**")
                    .permitAll()// ben bazi sayfalara yetkilendirmeden haric tutmak istiyorum .antMachers icerisindeki sayfalari ve directoryleri yetkilendirmeden haric tutuyorum. hangi kullanici olursa olsun gormeli o nedenle buralara yetkilendirme yapmadik.Yani herkes bu end point ve bu klasorlere erisebilsin permitAll() ile bunu dedik
                    .anyRequest().authenticated() //bir ustteki antMatchers icerisindeki requestler haric diger requestlerin kimligi dogrulanmali cunku diger requestler antmatchers icinde degil. bu sayfalari gormek icin user ve password u girmek zorundayiz
                    .and()
//                    .httpBasic() //localhost:8080 yazdigimda spring tarafindan default olarak verilen login sayfasini kulanacagimi soyluyorum  ileride bu kismi degistirip kendi login sayfamizi ekleyecegiz.
                    .formLogin()//yetkilendirme icin springin bana verdigi login pop up ini degil kendi login sayfami kullanmak istiyorum.Kendi login sayfami spring ile tanistiriyorum
                       .loginPage("/login")//login controller login view i veriyor.
//                       .defaultSuccessUrl("/welcome") //login islemi basarili olduktan sonra hangi sayfanin acilmasini istiyorsak burada tanimlayabiliriz.
                    .successHandler(authSuccessHandler)
                       .failureUrl("/login?error=true") //giris basarisiz olursa bu sayfa gorunsun
                       .permitAll() //login page herkes tarafindan erisilebilir olmali
                    .and()
                    .logout()
                       .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//lutfen soyler misin bu link nerede? logout butonuna bakiyor.logout butonu nerede diye bakiyor.biz nerede tanimladik header icerisinde bu method o bilgiye ihtiyac duyuyor
                       .logoutSuccessUrl("/login")
                    .and()
                    .rememberMe()//diyelim ki browserden programa log in yaptik.login yaptiktan sonra sag klik incele cookie sil islemlerini yaptiktan sonra program icerisinde herhangi bir islem yapmak istedigimde program benden tekrar user ve password yazmami ister neden cunku login yaptiktan sonra cookie sildim.Bunun onune gecmek icin rememberme methodunu kullanabiliriz.login yaptiktan sonra silmis olsak bile tekrar username ve password girmeden programda farkli bir islem yapabiliriz.
                       .tokenValiditySeconds(120)
                       .key("cydeo")//bu anahtara dayanarak beni hatırla mekanizması olusturuyor. Bilgilerimizi buna dayanarak saklamanın arkasında herhangi bir isim olabilir
                       .userDetailsService(securityService)//kim oldugumu hatirla peki UI a benim bilgilerimi kim tasiyor service.Bunun icin inject edecegiz
                    .and().build();
            //bu islemler bir kere yapiliyor ve muhtemelen biz ise girmeden once bu kisimlar yapilmis olunacak

    }
}
//Peki neden ticketing main classinda olusturdugumuz encoder bean i burada olusturmadik.Cunku Password encoder dan sadece obje olusturmak istiyorum.Sadece obje olusturmak istedigim siniflari Bean olarak runner sinifinda tanimliyorum.Fakat cok fazla modification yapacaksak ayri config sinifinda yapmaliyiz.

//UI kismini actik ve Spring security nin user i karsimiza geldi.Peki bu user bize nasil geliyor.Java programimizdan UI kismina data yi ne araciligi ile gonderiyoruz.Service araciligi ile peki bize Security nin user objesini kim gonderiyor.UserDetailService interfacesi .Bu service icerisinde bulunan loadUserByUsername methodunu kullanarak user objesini UI a tasiyor



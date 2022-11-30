package alphabravo.springsecurity.config;

import alphabravo.springsecurity.service.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class MyWebConfig extends WebSecurityConfigurerAdapter {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public MyWebConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }
    @Bean
    protected PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                //.antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/users").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout().logoutUrl("/");

    }
}

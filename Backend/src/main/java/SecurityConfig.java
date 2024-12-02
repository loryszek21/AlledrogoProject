import com.example.backend.model.MyAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final MyAppUserService myAppUserService;

    public SecurityConfig(MyAppUserService myAppUserService) {
        this.myAppUserService = myAppUserService;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return myAppUserService;
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myAppUserService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
            .formLogin(httpForm -> {
                httpForm
                    .loginPage("/AlledrogoProject/frontend/src/app/(site)/login/page.js").permitAll(); //insert login page here
                httpForm
                    .defaultSuccessUrl("/AlledrogoProject/frontend/src/app/(site)/home/page.js");
            })
            .authorizeHttpRequests(registry -> {
                registry
                    .requestMatchers("/AlledrogoProject/frontend/src/app/(site)/registry/page.js").permitAll(); //insert registration page here
                registry
                    .anyRequest()
                    .authenticated();   
            })
            .build();
    }
}

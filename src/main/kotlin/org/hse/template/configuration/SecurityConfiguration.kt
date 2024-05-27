package org.hse.template.configuration

import org.hse.template.service.NewUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration(
    private val newUserDetailsService: NewUserDetailsService
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder();
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val provider: DaoAuthenticationProvider = DaoAuthenticationProvider()
        provider.setUserDetailsService(newUserDetailsService)
        provider.setPasswordEncoder(passwordEncoder())
        return provider
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .httpBasic(Customizer.withDefaults())
            .authorizeHttpRequests { c ->
                c
                    .requestMatchers("/account/signup", "/swagger-ui/index.html/**").permitAll()
                    .requestMatchers("/admin/**").hasAuthority("ADMIN")
                    .requestMatchers("/user/**").hasAuthority("DEFAULT")
                    .requestMatchers("/", "/home", "/login").permitAll()
                    .anyRequest().permitAll()
            }
            .formLogin {
                it.loginPage("/account/login")
                    //.defaultSuccessUrl("/swagger-ui/index.html", true)
                    .permitAll()
            }
            .logout{
                it.logoutUrl("/account/logout")
                    //.logoutSuccessUrl("/swagger-ui/index.html")
                    .permitAll()
            }
            .userDetailsService(newUserDetailsService)

        return http.build()
    }
}
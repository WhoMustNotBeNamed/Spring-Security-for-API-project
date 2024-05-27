package org.hse.template.service

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.hse.template.model.Account
import org.hse.template.repository.AccountRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Service

@Service
class AccountService (
    private val passwordEncoder: PasswordEncoder,
    private val accountRepository: AccountRepository,
    private val userDetailsService: NewUserDetailsService
) {
    fun adduser(name: String, password: String) {
        val userRole = if (name == "admin") {
            Account.AuthorityType.ADMIN
        } else {
            Account.AuthorityType.DEFAULT
        }

        val user = Account(name = name, password = passwordEncoder.encode(password), role = userRole)

        accountRepository.save(user)
    }

    fun login(name: String, password: String) {
        val userDetails = try {
            userDetailsService.loadUserByUsername(name)
        } catch (e: Exception) { null }

        if (userDetails == null || !passwordEncoder.matches(password, userDetails.password)) {
            throw Exception("Invalid username or password")
        }

        val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        SecurityContextHolder.getContext().authentication = authentication
    }

    fun logout(request: HttpServletRequest, response: HttpServletResponse) {
        val auth = SecurityContextHolder.getContext().authentication
        if (auth != null) {
            SecurityContextLogoutHandler().logout(request, response, auth)
        }
        SecurityContextHolder.clearContext()
    }
}
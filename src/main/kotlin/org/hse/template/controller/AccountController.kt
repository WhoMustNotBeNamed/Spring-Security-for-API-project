package org.hse.template.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.hse.template.service.AccountService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account")
class AccountController (
    val accountService: AccountService,
){
    @PostMapping("/signup")
    fun addUser(@RequestParam username : String, @RequestParam password : String) =
        accountService.adduser(username, password)

    @PostMapping("/login")
    fun login(@RequestParam username : String, @RequestParam password : String) =
        accountService.login(username, password)

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest, response: HttpServletResponse) {
        accountService.logout(request, response)
    }
}
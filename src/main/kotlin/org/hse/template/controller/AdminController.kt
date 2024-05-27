package org.hse.template.controller

import org.hse.template.client.rest.api.ApiApp
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("admin")
class AdminController (
    val apiApp: ApiApp
) {
    @GetMapping
    fun hello() = "Hello admin!"

    @GetMapping("/notes")
    fun getNotes() = apiApp.getNotes()
}
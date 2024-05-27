package org.hse.template.controller

import org.hse.template.client.rest.api.ApiApp
import org.hse.template.service.AccountService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class DefaultController (
    val apiApp: ApiApp
) {

    @GetMapping
    fun hello() = "Hello user!"

    @GetMapping("/movies")
    fun getMovies(@RequestParam title: String, @RequestParam year: String) =
        apiApp.getMovies(title, year)

    @GetMapping("/series")
    fun getSeries(@RequestParam title: String, @RequestParam year: String) =
        apiApp.getSeries(title, year)
}
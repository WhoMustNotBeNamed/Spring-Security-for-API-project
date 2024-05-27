package org.hse.template.client.rest.api

import org.hse.template.client.rest.model.MovieSearch
import org.hse.template.client.rest.model.ObsidianFiles
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "api-app")
interface ApiApp {

    @GetMapping("/omdb/movies")
    fun getMovies(@RequestParam title: String,
                  @RequestParam year: String
    ) : MovieSearch

    @GetMapping("/omdb/series")
    fun getSeries(@RequestParam title: String,
                  @RequestParam year: String
    ) : MovieSearch

    @GetMapping("/obsidian/notes")
    fun getNotes() : ObsidianFiles
}
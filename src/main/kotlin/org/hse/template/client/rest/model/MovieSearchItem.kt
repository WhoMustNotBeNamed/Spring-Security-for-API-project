package org.hse.template.client.rest.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.data.annotation.Id

data class MovieSearchItem(
    @JsonProperty("Title")
    @Schema(description = "Название фильма")
    val title: String,
    @JsonProperty("Year")
    @Schema(description = "Год выпуска фильма")
    val year: String,
    @JsonProperty("imdbID")
    @Schema(description = "ID фильма")
    val imdbID: String,
    @JsonProperty("Type")
    @Schema(description = "Тип фильма")
    val type: String,
    @JsonProperty("Poster")
    @Schema(description = "Постер фильма")
    val poster: String
)
package org.hse.template.client.rest.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.annotation.Generated
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

data class MovieSearch(
    @JsonProperty("Search")
    @Schema(description = "Список фильмов")
    val search: List<MovieSearchItem>,
    @JsonProperty("totalResults")
    @Schema(description = "Общее количество результатов")
    val totalResults: String,
    @JsonProperty("Response")
    @Schema(description = "Статус ответа")
    val response: String
)
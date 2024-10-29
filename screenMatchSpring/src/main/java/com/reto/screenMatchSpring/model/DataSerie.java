package com.reto.screenMatchSpring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSerie(
        @JsonAlias("Title")
        String titulo,
        @JsonAlias("totalSeasons")
        Integer totalTemporadas,
        @JsonAlias("imdbRating")
        String evaluacion,
        String Poster,
        @JsonAlias("Genre")
        String genre,
        String Actors,
        String Plot
) {
}

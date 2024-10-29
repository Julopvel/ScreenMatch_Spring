package com.reto.screenMatchSpring.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataEpisode(
        @JsonAlias("Title")
        String titulo,
        @JsonAlias("Episode")
        Integer numeroEpisodio,
        @JsonAlias("imdbRating")
        String evaluacion,
        @JsonAlias("Released")
        String fechaLanzamiento
) {
}

package com.reto.screenMatchSpring.dto;

import com.reto.screenMatchSpring.model.Genre;

public record SerieDTO(
        Long id,
        String title,
        Integer totalSeasons,
        Double rating,
        String poster,
        Genre genre,
        String actors,
        String plot) {
}

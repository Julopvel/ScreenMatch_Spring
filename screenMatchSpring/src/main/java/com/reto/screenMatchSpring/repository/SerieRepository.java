package com.reto.screenMatchSpring.repository;

import com.reto.screenMatchSpring.model.Episode;
import com.reto.screenMatchSpring.model.Genre;
import com.reto.screenMatchSpring.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    List<Serie> findTop10ByOrderByRatingDesc();

    List<Serie> findByGenre(Genre genre);

    @Query("SELECT s FROM Serie s WHERE s.totalSeasons <= :totalSeasons")
    List<Serie> seriesBySeasonsNumber(int totalSeasons);

    @Query("SELECT s FROM Serie s WHERE s.rating <= :ratingNumber")
    List<Serie> seriesByRating(double ratingNumber);

    Optional<Serie> findByTitleContainsIgnoreCase(String serieName);

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE s = :serie ORDER BY e.rating DESC LIMIT 10")
    List<Episode> top10Episodes(Serie serie);

    @Query("SELECT s FROM Serie s JOIN s.episodes e GROUP BY s ORDER BY MAX(e.releasedDate) DESC LIMIT 10")
    List<Serie> getNewReleases();

    @Query("SELECT e FROM Serie s JOIN s.episodes e WHERE s.id = :id AND e.season = :seasonNumber")
    List<Episode> getSeasonsByNumber(Long id, Long seasonNumber);

}

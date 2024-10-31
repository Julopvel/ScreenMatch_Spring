package com.reto.screenMatchSpring.service;

import com.reto.screenMatchSpring.dto.EpisodeDTO;
import com.reto.screenMatchSpring.dto.SerieDTO;
import com.reto.screenMatchSpring.model.Episode;
import com.reto.screenMatchSpring.model.Genre;
import com.reto.screenMatchSpring.model.Serie;
import com.reto.screenMatchSpring.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SerieService {

    private SerieRepository serieRepository;

    @Autowired
    public SerieService(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public List<SerieDTO> transformData(List<Serie> series){
        return series.stream()
                .map(serie -> new SerieDTO(serie.getId(), serie.getTitle(),
                        serie.getTotalSeasons(), serie.getRating(),
                        serie.getPoster(), serie.getGenre(),
                        serie.getActors(), serie.getPlot()))
                .collect(Collectors.toList());
    }

    public List<EpisodeDTO> fromEpisodeToEpisodeDTO(List<Episode> episodes){
        return episodes.stream()
                .map(episode -> new EpisodeDTO(episode.getSeason(),
                        episode.getTitle(), episode.getEpisodeNumber()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> getAllSeries() {
        return transformData(serieRepository.findAll());
    }

    public List<SerieDTO> getTop10Series() {
        return transformData(serieRepository.findTop10ByOrderByRatingDesc());
    }

    public List<SerieDTO> getNewReleases() {
        return transformData(serieRepository.getNewReleases());
    }

    public SerieDTO getByById(Long id) {
        Optional<Serie> serieOptional = serieRepository.findById(id);
        if (serieOptional.isPresent()){
            Serie serie = serieOptional.get();
            return new SerieDTO(serie.getId(), serie.getTitle(), serie.getTotalSeasons(),
                    serie.getRating(), serie.getPoster(), serie.getGenre(),
                    serie.getActors(), serie.getPlot());
        }
        return null;
    }

    public List<EpisodeDTO> getAllSeasons(Long id) {
        Optional<Serie> serieOptional = serieRepository.findById(id);
        if (serieOptional.isPresent()){
            Serie serie = serieOptional.get();
            return serie.getEpisodes().stream()
                    .map(episode -> new EpisodeDTO(episode.getSeason(),
                            episode.getTitle(), episode.getEpisodeNumber()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodeDTO> getSeasonsByNumber(Long id, Long seasonNumber) {
        return fromEpisodeToEpisodeDTO(serieRepository.getSeasonsByNumber(id, seasonNumber));

//        return serieRepository.getSeasonsByNumber(id, seasonNumber).stream()
//                .map(episode -> new EpisodeDTO(episode.getSeason(),
//                        episode.getTitle(), episode.getEpisodeNumber()))
//                .collect(Collectors.toList());

    }

    public List<EpisodeDTO> getTopEpisodes(Long id) {
        Optional<Serie> serieOptional = serieRepository.findById(id);
        if (serieOptional.isPresent()){
            Serie serie = serieOptional.get();
            return fromEpisodeToEpisodeDTO(serieRepository.top10Episodes(serie));

//            return serieRepository.top10Episodes(serie).stream()
//                    .map(episode -> new EpisodeDTO(episode.getSeason(),
//                            episode.getTitle(), episode.getEpisodeNumber()))
//                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<SerieDTO> getSeriesByGenre(String genre) {
        Genre category = Genre.fromString(genre);
        return transformData(serieRepository.findByGenre(category));

    }
}

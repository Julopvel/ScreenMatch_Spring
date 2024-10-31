package com.reto.screenMatchSpring.controller;

import com.reto.screenMatchSpring.dto.EpisodeDTO;
import com.reto.screenMatchSpring.dto.SerieDTO;
import com.reto.screenMatchSpring.model.Genre;
import com.reto.screenMatchSpring.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class serieController {

    private SerieService serieService;

    @Autowired
    public serieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping()
    public List<SerieDTO> getAllSeries(){
        return serieService.getAllSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> getTop10(){
        return serieService.getTop10Series();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> getNewReleases(){
        return serieService.getNewReleases();
    }

    @GetMapping("/{id}")
    public SerieDTO getById(@PathVariable Long id){
        return serieService.getByById(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodeDTO> getAllSeasons(@PathVariable Long id){
        return serieService.getAllSeasons(id);
    }

    @GetMapping("/{id}/temporadas/{seasonNumber}")
    public List<EpisodeDTO> getSeasonsByNumber(@PathVariable Long id, @PathVariable Long seasonNumber){
        return serieService.getSeasonsByNumber(id, seasonNumber);
    }

    @GetMapping("/{id}/temporadas/top")
    public List<EpisodeDTO> getTopEpisodes(@PathVariable Long id){
        return serieService.getTopEpisodes(id);
    }

    @GetMapping("/categoria/{genre}")
    public List<SerieDTO> getSeriesByGenre(@PathVariable String genre){
        return serieService.getSeriesByGenre(genre);
    }

}

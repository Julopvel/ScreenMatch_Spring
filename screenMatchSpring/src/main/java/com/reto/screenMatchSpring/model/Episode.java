package com.reto.screenMatchSpring.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer season;
    private String title;
    private Integer episodeNumber;
    private Double rating;
    private LocalDate releasedDate;
    @ManyToOne()
    private Serie serie;

    public Episode(){
    }

    public Episode(Integer temporada, DataEpisode d){
        this.season = temporada;
        this.title = d.titulo();
        this.episodeNumber = d.numeroEpisodio();
        try {
            this.rating = Double.valueOf(d.evaluacion());
        } catch (NumberFormatException e) {
            this.rating = 0.0;
        }
        try {
            this.releasedDate = LocalDate.parse(d.fechaLanzamiento());
        }catch (DateTimeParseException e){
            this.releasedDate = null;
        }
    }

    public Integer getSeason() {
        return season;
    }

    public String getTitle() {
        return title;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public Double getRating() {
        return rating;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return
                "temporada=" + season +
                ", titulo='" + title + '\'' +
                ", numeroEpisodio=" + episodeNumber +
                ", evaluacion=" + rating +
                ", fechaLanzamiento=" + releasedDate;
    }

}

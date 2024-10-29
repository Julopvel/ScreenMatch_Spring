package com.reto.screenMatchSpring.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    private Integer totalSeasons;
    private Double rating;
    private String poster;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String actors;
    private String plot;
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episode> episodes;


    public Serie(){
    }

    public Serie(DataSerie dataSerie){
        this.title = dataSerie.titulo();
        this.totalSeasons = dataSerie.totalTemporadas();
        this.rating = OptionalDouble.of(Double.valueOf(dataSerie.evaluacion())).orElse(0);
        this.poster = dataSerie.Poster();
        this.genre = Genre.fromString(dataSerie.genre().split(",")[0].trim());
        this.actors = dataSerie.Actors();
        this.plot = dataSerie.Plot();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public Double getRating() {
        return rating;
    }

    public String getPoster() {
        return poster;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        episodes.forEach(e -> e.setSerie(this));
        this.episodes = episodes;
    }

    @Override
    public String toString() {
        return
                "genre=" + genre +
                ", title='" + title + '\'' +
                ", totalSeasons=" + totalSeasons +
                ", rating=" + rating +
                ", poster='" + poster + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", episodes=" + episodes + '\'';
    }
}

package com.reto.screenMatchSpring.commandLine;

import com.reto.screenMatchSpring.model.*;
import com.reto.screenMatchSpring.repository.SerieRepository;
import com.reto.screenMatchSpring.service.CallingAPI;
import com.reto.screenMatchSpring.service.TransformData;

import java.util.*;
import java.util.stream.Collectors;

public class CommandLine {

    private Scanner keyboard = new Scanner(System.in);
    private CallingAPI callingAPI = new CallingAPI();
    private final String URL_BASE = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=f143078a";  //Agregar a variable de ambiente
    private TransformData transformData = new TransformData();

    private SerieRepository serieRepository;
    private List<Serie> series;
    private Optional<Serie> searchedSerie;   //Como variable global


    public CommandLine(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void displayMenu(){

        int option = -1;
        while (option != 0){
            String menu = """
                    1 - Search series.
                    2 - Display all searched series.
                    3 - Search episodes of a serie.
                    4 - Top 10 series.
                    5 - Search series by genre.
                    6 - Filter series by number of seasons.
                    7 - Filter series by rating.
                    8 - Search series by title.
                    9 - Top 10 episodes of a serie.
                    
                    0 - Exit
                    """;
            System.out.println(menu);
            option = keyboard.nextInt();
            keyboard.nextLine();

            switch (option){
                case 1:
                    searchWebSerie();
                    break;
                case 2:
                    displayAllSearchedSeries();
                    break;
                case 3:
                    searchEpisodesOfASerie();
                    break;
                case 4:
                    top10Series();
                    break;
                case 5:
                    searchSeriesByGenre();
                    break;
                case 6:
                    filterSeriesByNumberOfSeasons();
                    break;
                case 7:
                    filterSeriesByRating();
                    break;
                case 8:
                    searchSeriesByTitle();
                    break;
                case 9:
                    top10EpisodesOfSerie();
                    break;

                case 0:
                    System.out.println("*** Thank you for testing the application ***");
                    break;
                default:
                    System.out.println("Non valid option!");

            }
        }

    }

    private DataSerie getDataSerie(){
        /*
        Busca e imprime los datos generales de la serie ingresada por el usuario
         */
        System.out.println("Please enter the name of the serie you want to search:  ");
        String nombreSerie = keyboard.nextLine();
        String json = callingAPI.getData(
                URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        DataSerie datos = transformData.getData(json, DataSerie.class);

        return datos;
    }



    private void searchWebSerie() {
        /*
        Guarda la información de la serie buscada en la BD.
         */
        DataSerie dataSerie = getDataSerie();
        Serie serie = new Serie(dataSerie);
        serieRepository.save(serie);
        System.out.println(dataSerie);
    }

    private void displayAllSearchedSeries() {
        /*
        Muestra todas las series almacenadas en la BD.
         */
        series = serieRepository.findAll();

        series.stream()
                .sorted(Comparator.comparing(Serie::getTitle)) //Organiza por orden alfabetico
                        .forEach(s -> System.out.println(
                                s.getTitle() + " - Genre: " + s.getGenre() + " - Seasons: " + s.getTotalSeasons()
                        ));
//        series.stream()
//                .sorted(Comparator.comparing(Serie::getGenre))
//                .forEach(System.out::println);

        System.out.println("\n");
    }

    private void searchEpisodesOfASerie() {
        /*
        Busca e imprime los datos de todos los episodios de las temporadas
         */
        displayAllSearchedSeries();
        System.out.println("Please enter the name of the serie you want to search:  ");
        String serieName = keyboard.nextLine();

        Optional<Serie> serie = series.stream()
                .filter(s -> s.getTitle().toLowerCase().contains(serieName.toLowerCase()))
                .findFirst();

        if (serie.isPresent()){
            var foundSerie = serie.get();

            List<DataSeasons> seasons = new ArrayList<>();

            for (int i = 1; i <= foundSerie.getTotalSeasons() ; i++) {
                String json = callingAPI.getData(
                        URL_BASE + foundSerie.getTitle().replace(" ", "+") + "&Season="+i+API_KEY);
                DataSeasons dataSeasons = transformData.getData(json, DataSeasons.class);
                seasons.add(dataSeasons);
            }
            seasons.forEach(System.out::println);

            List<Episode> episodes = seasons.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episode(d.temporada(), e)))
                    .collect(Collectors.toList());

            foundSerie.setEpisodes(episodes);     //Assigns the ID that corresponds to the serie
            serieRepository.save(foundSerie);
        }
    }

    private void top10Series() {
        List<Serie> topSeries = serieRepository.findTop10ByOrderByRatingDesc();
        topSeries.forEach(s ->
                System.out.println("Serie: " + s.getTitle() + ", Rating: " + s.getRating()));
        System.out.println("\n");
    }

    private void searchSeriesByGenre() {
        List<String> categories = new ArrayList<>();

        System.out.println("Enter one of the following categories to display a list of series that match");
        for (Genre genre : Genre.values()){
            categories.add(genre.toString().toLowerCase());
        }
        System.out.println(categories);

        String category = keyboard.nextLine();
        var genre = Genre.fromString(category);
        List<Serie> seriesByGenre = serieRepository.findByGenre(genre);
        System.out.println("Series of the category " + genre);
        seriesByGenre.forEach(System.out::println);
        System.out.println("\n");
    }

    private void filterSeriesByNumberOfSeasons() {
        System.out.println("How many seasons? ");
        int totalSeasons = keyboard.nextInt();
        keyboard.nextLine();

        List<Serie> filteredSerie = serieRepository.seriesBySeasonsNumber(totalSeasons);
        System.out.println("*** Filtered series with less than or equal to" + totalSeasons + " seasons***");
        filteredSerie.forEach(
                fs -> System.out.println(fs.getTitle() + " - seasons: " + fs.getTotalSeasons())
        );
        System.out.println("\n");
    }

    private void filterSeriesByRating() {
        System.out.println("Rating number? ");
        double ratingNumber = keyboard.nextDouble();
        // ratingNumber only accepts numbers separated by (,) not by dot(.)
        List<Serie> filteredSeries = serieRepository.seriesByRating(ratingNumber);
        System.out.println("*** Filtered series with less than or equal to" + ratingNumber + " of rating***");
        filteredSeries.forEach(
                fs -> System.out.println(fs.getTitle() + " - rating: " + fs.getRating()));
        System.out.println("\n");
    }

    private void searchSeriesByTitle() {
        System.out.println("Enter the name of the serie you are looking for: ");
        var serieName = keyboard.nextLine();

        searchedSerie = serieRepository.findByTitleContainsIgnoreCase(serieName);

        if (searchedSerie.isPresent()){
            System.out.println("The searched serie is:  " + searchedSerie);
        } else System.out.println("Serie not found");
    }

    private void top10EpisodesOfSerie() {
        searchSeriesByTitle();

        if (searchedSerie.isPresent()) {
            Serie serie = searchedSerie.get();
            List<Episode> topEpisodes = serieRepository.top10Episodes(serie);
            topEpisodes.forEach(e -> System.out.printf(
                    "Serie: %s - Season: %s - Episode: %s - Episode Title: %s - Rating: %s\n",
                    e.getSerie().getTitle(), e.getSeason(), e.getEpisodeNumber(), e.getTitle(), e.getRating()
            ));
        }
    }
}

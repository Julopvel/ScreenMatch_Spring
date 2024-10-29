package com.reto.screenMatchSpring.commandLine;

import com.reto.screenMatchSpring.model.Serie;
import com.reto.screenMatchSpring.repository.SerieRepository;
import com.reto.screenMatchSpring.service.CallingAPI;
import com.reto.screenMatchSpring.service.TransformData;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
                    System.out.println("Gracias por probar la aplicación");
                    break;
                default:
                    System.out.println("Opción invalida");

            }
        }

    }



    private void searchWebSerie() {
    }

    private void displayAllSearchedSeries() {
    }

    private void searchEpisodesOfASerie() {
    }

    private void top10Series() {
    }

    private void searchSeriesByGenre() {
    }

    private void filterSeriesByNumberOfSeasons() {
    }

    private void filterSeriesByRating() {
    }

    private void searchSeriesByTitle() {
    }

    private void top10EpisodesOfSerie() {
    }
}

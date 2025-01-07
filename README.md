# ScreenMatch
ScreenMatch application is a series catalog of your favorite shows. Through the OMDb API and a command line menu, the user will be able to:

- 1 - Search series.
- 2 - Display all searched series.
- 3 - Search episodes of a serie.
- 4 - Top 10 series.
- 5 - Search series by genre.
- 6 - Filter series by number of seasons.
- 7 - Filter series by rating.
- 8 - Search series by title.
- 9 - Top 10 episodes of a serie.
  
- 0 - Exit.
  
## Tools used
- Java.
- Spring Boot.
- PostgreSQL.

## Project structure
- `screenMatchSpring/src/main/java`: ScreenMatch application Back-end.
- `ScreenMatch-front-end-main`: ScreenMatch application Front-end.

## Endpoints
- `GET`:
  - `/series/top5`: displays top 5 rating series.
  - `/series/lanzamientos`: displays recently added series.
  - `/series/{id}`: displays a specific serie
  - `/series/{id}/temporadas/todas`: displays every chapter of every season.
  - `/series/{id}/temporadas/{seasonNumber}`: gets the episodes of a specific season.
  - `/series/{id}/temporadas/top`: gets the top 10 rating episodes of a serie.  

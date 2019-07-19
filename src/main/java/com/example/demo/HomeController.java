package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("/")

    public String index(Model model){
        // create a director
        Director director = new Director();
        director.setName("Stephen Bullock");
        director.setGenre("Sci Fi");

        // create a movie
        Movie movie = new Movie();
        movie.setTitle("DeathStar Ewoks");
        movie.setYear(2011);
        movie.setDescription("About Ewoks on the DeathStar...");

        // add the movie to an empty list
        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);

        // add the list of movies to the director's movie list
        director.setMovies(movies);

        // save the director to the DB
        directorRepository.save(director);

        // grad all the directors from the DB and send them to the template
        model.addAttribute("directors", directorRepository.findAll());
        return "index";
    }
}

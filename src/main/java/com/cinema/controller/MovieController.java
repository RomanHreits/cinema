package com.cinema.controller;

import com.cinema.model.Movie;
import com.cinema.model.dto.movie.MovieRequestDto;
import com.cinema.model.dto.movie.MovieResponseDto;
import com.cinema.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ModelMapper modelMapper;

    public MovieController(MovieService movieService, ModelMapper modelMapper) {
        this.movieService = movieService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public void create(@RequestBody MovieRequestDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);
        movieService.add(movie);
    }

    @GetMapping
    public List<MovieResponseDto> getAll() {
        return movieService.getAll()
                .stream()
                .map(movie -> modelMapper.map(movie, MovieResponseDto.class))
                .collect(Collectors.toList());
    }
}

package com.example.cinema.service;

import com.example.cinema.model.Film;
import com.example.cinema.repository.FilmRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> findAll(String genre) {
        if (genre == null || genre.isBlank()) {
            return filmRepository.findAll();
        }
        return filmRepository.findByGenreContainingIgnoreCase(genre);
    }

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public Film findById(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Film introuvable: " + id));
    }

    public Film save(Film film) {
        return filmRepository.save(film);
    }

    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }

    public long count() {
        return filmRepository.count();
    }
}

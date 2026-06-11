package com.example.cinema.repository;

import com.example.cinema.model.Film;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findByGenreContainingIgnoreCase(String genre);
}

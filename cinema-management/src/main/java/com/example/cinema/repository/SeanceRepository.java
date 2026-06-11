package com.example.cinema.repository;

import com.example.cinema.model.Seance;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeanceRepository extends JpaRepository<Seance, Long> {
    List<Seance> findBySalleContainingIgnoreCase(String salle);

    List<Seance> findByDateHeureBetween(LocalDateTime start, LocalDateTime end);

    List<Seance> findBySalleContainingIgnoreCaseAndDateHeureBetween(String salle, LocalDateTime start, LocalDateTime end);
}

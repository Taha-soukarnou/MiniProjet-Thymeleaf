package com.example.cinema.service;

import com.example.cinema.model.Seance;
import com.example.cinema.repository.SeanceRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SeanceService {

    private final SeanceRepository seanceRepository;

    public SeanceService(SeanceRepository seanceRepository) {
        this.seanceRepository = seanceRepository;
    }

    public List<Seance> findAll(String salle, LocalDate date) {
        boolean hasSalle = salle != null && !salle.isBlank();
        boolean hasDate = date != null;

        if (hasSalle && hasDate) {
            return seanceRepository.findBySalleContainingIgnoreCaseAndDateHeureBetween(salle, date.atStartOfDay(), date.plusDays(1).atStartOfDay());
        }
        if (hasSalle) {
            return seanceRepository.findBySalleContainingIgnoreCase(salle);
        }
        if (hasDate) {
            return seanceRepository.findByDateHeureBetween(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
        }
        return seanceRepository.findAll();
    }

    public List<Seance> findAll() {
        return seanceRepository.findAll();
    }

    public Seance findById(Long id) {
        return seanceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Seance introuvable: " + id));
    }

    public Seance save(Seance seance) {
        return seanceRepository.save(seance);
    }

    public void deleteById(Long id) {
        seanceRepository.deleteById(id);
    }

    public long count() {
        return seanceRepository.count();
    }
}

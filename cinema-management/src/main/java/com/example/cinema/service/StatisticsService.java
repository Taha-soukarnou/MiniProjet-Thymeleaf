package com.example.cinema.service;

import com.example.cinema.model.Seance;
import com.example.cinema.repository.BilletRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private final SeanceService seanceService;
    private final BilletService billetService;
    private final BilletRepository billetRepository;

    public StatisticsService(SeanceService seanceService, BilletService billetService, BilletRepository billetRepository) {
        this.seanceService = seanceService;
        this.billetService = billetService;
        this.billetRepository = billetRepository;
    }

    public List<TauxRemplissageDto> tauxRemplissageParSeance() {
        return seanceService.findAll().stream()
                .map(this::toTauxRemplissage)
                .toList();
    }

    public List<RecetteFilmDto> recettesParFilm() {
        return billetRepository.recettesParFilm().stream()
                .map(row -> new RecetteFilmDto((String) row[0], (BigDecimal) row[1]))
                .toList();
    }

    public BigDecimal totalRecettes() {
        return billetRepository.totalRecettes();
    }

    private TauxRemplissageDto toTauxRemplissage(Seance seance) {
        long billetsVendus = billetService.countSoldBySeance(seance.getId());
        double taux = seance.getCapaciteSalle() == null || seance.getCapaciteSalle() == 0
                ? 0
                : (billetsVendus * 100.0) / seance.getCapaciteSalle();
        return new TauxRemplissageDto(seance, billetsVendus, taux);
    }
}

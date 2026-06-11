package com.example.cinema.service;

import com.example.cinema.model.Billet;
import com.example.cinema.model.StatutBillet;
import com.example.cinema.repository.BilletRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BilletService {

    private final BilletRepository billetRepository;

    public BilletService(BilletRepository billetRepository) {
        this.billetRepository = billetRepository;
    }

    public List<Billet> findAll(StatutBillet statut) {
        if (statut == null) {
            return billetRepository.findAll();
        }
        return billetRepository.findByStatut(statut);
    }

    public List<Billet> findAll() {
        return billetRepository.findAll();
    }

    public Billet findById(Long id) {
        return billetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Billet introuvable: " + id));
    }

    public Billet sell(Billet billet) {
        billet.setId(null);
        billet.setStatut(StatutBillet.VENDU);
        billet.setDateAchat(LocalDateTime.now());
        return billetRepository.save(billet);
    }

    public Billet save(Billet billet) {
        return billetRepository.save(billet);
    }

    public void cancel(Long id) {
        Billet billet = findById(id);
        billet.setStatut(StatutBillet.ANNULE);
        billetRepository.save(billet);
    }

    public void deleteById(Long id) {
        billetRepository.deleteById(id);
    }

    public long countSoldBySeance(Long seanceId) {
        return billetRepository.countBySeanceIdAndStatut(seanceId, StatutBillet.VENDU);
    }

    public long count() {
        return billetRepository.count();
    }
}

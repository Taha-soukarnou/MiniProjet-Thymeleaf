package com.example.cinema.repository;

import com.example.cinema.model.Billet;
import com.example.cinema.model.StatutBillet;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BilletRepository extends JpaRepository<Billet, Long> {
    List<Billet> findByStatut(StatutBillet statut);

    long countBySeanceIdAndStatut(Long seanceId, StatutBillet statut);

    @Query("select b.seance.film.titre, coalesce(sum(b.prix), 0) from Billet b where b.statut = com.example.cinema.model.StatutBillet.VENDU group by b.seance.film.id, b.seance.film.titre order by b.seance.film.titre")
    List<Object[]> recettesParFilm();

    @Query("select coalesce(sum(b.prix), 0) from Billet b where b.statut = com.example.cinema.model.StatutBillet.VENDU")
    BigDecimal totalRecettes();
}

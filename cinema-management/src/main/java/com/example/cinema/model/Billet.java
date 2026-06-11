package com.example.cinema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Billet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le prix est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit etre positif")
    private BigDecimal prix;

    @NotNull(message = "Le statut est obligatoire")
    @Enumerated(EnumType.STRING)
    private StatutBillet statut;

    @NotNull(message = "La date d'achat est obligatoire")
    private LocalDateTime dateAchat;

    @NotNull(message = "La seance est obligatoire")
    @ManyToOne
    private Seance seance;

    public Billet() {
    }

    public Billet(BigDecimal prix, StatutBillet statut, LocalDateTime dateAchat, Seance seance) {
        this.prix = prix;
        this.statut = statut;
        this.dateAchat = dateAchat;
        this.seance = seance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public StatutBillet getStatut() {
        return statut;
    }

    public void setStatut(StatutBillet statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDateTime dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }
}

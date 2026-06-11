package com.example.cinema.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La date et l'heure sont obligatoires")
    private LocalDateTime dateHeure;

    @NotBlank(message = "La salle est obligatoire")
    private String salle;

    @NotNull(message = "La version est obligatoire")
    @Enumerated(EnumType.STRING)
    private VersionSeance version;

    @NotNull(message = "La capacite de la salle est obligatoire")
    @Min(value = 1, message = "La capacite doit etre positive")
    private Integer capaciteSalle;

    @NotNull(message = "Le film est obligatoire")
    @ManyToOne
    private Film film;

    @OneToMany(mappedBy = "seance", cascade = CascadeType.REMOVE)
    private List<Billet> billets = new ArrayList<>();

    public Seance() {
    }

    public Seance(LocalDateTime dateHeure, String salle, VersionSeance version, Integer capaciteSalle, Film film) {
        this.dateHeure = dateHeure;
        this.salle = salle;
        this.version = version;
        this.capaciteSalle = capaciteSalle;
        this.film = film;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public VersionSeance getVersion() {
        return version;
    }

    public void setVersion(VersionSeance version) {
        this.version = version;
    }

    public Integer getCapaciteSalle() {
        return capaciteSalle;
    }

    public void setCapaciteSalle(Integer capaciteSalle) {
        this.capaciteSalle = capaciteSalle;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public List<Billet> getBillets() {
        return billets;
    }

    public void setBillets(List<Billet> billets) {
        this.billets = billets;
    }
}

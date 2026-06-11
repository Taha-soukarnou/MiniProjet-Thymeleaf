package com.example.cinema;

import com.example.cinema.model.Billet;
import com.example.cinema.model.Film;
import com.example.cinema.model.Seance;
import com.example.cinema.model.StatutBillet;
import com.example.cinema.model.VersionSeance;
import com.example.cinema.service.BilletService;
import com.example.cinema.service.FilmService;
import com.example.cinema.service.SeanceService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CinemaManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner sampleData(FilmService filmService, SeanceService seanceService, BilletService billetService) {
        return args -> {
            Film inception = filmService.save(new Film("Inception", "Science-fiction", 148));
            Film amelie = filmService.save(new Film("Le Fabuleux Destin d'Amelie Poulain", "Comedie", 122));
            Film parasite = filmService.save(new Film("Parasite", "Drame", 132));

            Seance s1 = seanceService.save(new Seance(LocalDateTime.now().plusDays(1).withHour(19).withMinute(30), "Salle 1", VersionSeance.VOST, 120, inception));
            Seance s2 = seanceService.save(new Seance(LocalDateTime.now().plusDays(1).withHour(21).withMinute(0), "Salle 2", VersionSeance.VF, 90, amelie));
            Seance s3 = seanceService.save(new Seance(LocalDateTime.now().plusDays(2).withHour(20).withMinute(15), "Salle 1", VersionSeance.VO, 100, parasite));

            billetService.save(new Billet(new BigDecimal("75.00"), StatutBillet.VENDU, LocalDateTime.now().minusHours(5), s1));
            billetService.save(new Billet(new BigDecimal("75.00"), StatutBillet.VENDU, LocalDateTime.now().minusHours(3), s1));
            billetService.save(new Billet(new BigDecimal("60.00"), StatutBillet.VENDU, LocalDateTime.now().minusHours(2), s2));
            billetService.save(new Billet(new BigDecimal("60.00"), StatutBillet.ANNULE, LocalDateTime.now().minusHours(1), s2));
            billetService.save(new Billet(new BigDecimal("80.00"), StatutBillet.VENDU, LocalDateTime.now().minusMinutes(30), s3));
        };
    }
}

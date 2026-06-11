package com.example.cinema.controller;

import com.example.cinema.service.BilletService;
import com.example.cinema.service.FilmService;
import com.example.cinema.service.SeanceService;
import com.example.cinema.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final FilmService filmService;
    private final SeanceService seanceService;
    private final BilletService billetService;
    private final StatisticsService statisticsService;

    public DashboardController(FilmService filmService, SeanceService seanceService, BilletService billetService, StatisticsService statisticsService) {
        this.filmService = filmService;
        this.seanceService = seanceService;
        this.billetService = billetService;
        this.statisticsService = statisticsService;
    }

    @GetMapping({"/classic", "/classic/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("filmCount", filmService.count());
        model.addAttribute("seanceCount", seanceService.count());
        model.addAttribute("billetCount", billetService.count());
        model.addAttribute("totalRecettes", statisticsService.totalRecettes());
        return "dashboard";
    }
}

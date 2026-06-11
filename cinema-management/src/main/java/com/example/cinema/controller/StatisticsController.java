package com.example.cinema.controller;

import com.example.cinema.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/classic/stats")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public String stats(Model model) {
        model.addAttribute("tauxRemplissage", statisticsService.tauxRemplissageParSeance());
        model.addAttribute("recettesParFilm", statisticsService.recettesParFilm());
        model.addAttribute("totalRecettes", statisticsService.totalRecettes());
        return "stats/index";
    }
}

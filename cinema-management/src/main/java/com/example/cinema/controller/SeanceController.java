package com.example.cinema.controller;

import com.example.cinema.model.Seance;
import com.example.cinema.model.VersionSeance;
import com.example.cinema.service.FilmService;
import com.example.cinema.service.SeanceService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/classic/seances")
public class SeanceController {

    private final SeanceService seanceService;
    private final FilmService filmService;

    public SeanceController(SeanceService seanceService, FilmService filmService) {
        this.seanceService = seanceService;
        this.filmService = filmService;
    }

    @GetMapping
    public String list(
            @RequestParam(required = false) String salle,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {
        model.addAttribute("seances", seanceService.findAll(salle, date));
        model.addAttribute("salle", salle);
        model.addAttribute("date", date);
        return "seances/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        addFormAttributes(model, new Seance(), "Planifier une seance");
        return "seances/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute Seance seance, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            addFormAttributes(model, seance, seance.getId() == null ? "Planifier une seance" : "Modifier une seance");
            return "seances/form";
        }
        seanceService.save(seance);
        return "redirect:/classic/seances";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        addFormAttributes(model, seanceService.findById(id), "Modifier une seance");
        return "seances/form";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        seanceService.deleteById(id);
        return "redirect:/classic/seances";
    }

    private void addFormAttributes(Model model, Seance seance, String pageTitle) {
        model.addAttribute("seance", seance);
        model.addAttribute("films", filmService.findAll());
        model.addAttribute("versions", VersionSeance.values());
        model.addAttribute("pageTitle", pageTitle);
    }
}

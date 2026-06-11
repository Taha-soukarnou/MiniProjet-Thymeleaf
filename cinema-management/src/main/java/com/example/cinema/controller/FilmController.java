package com.example.cinema.controller;

import com.example.cinema.model.Film;
import com.example.cinema.service.FilmService;
import jakarta.validation.Valid;
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
@RequestMapping("/classic/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String genre, Model model) {
        model.addAttribute("films", filmService.findAll(genre));
        model.addAttribute("genre", genre);
        return "films/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("film", new Film());
        model.addAttribute("pageTitle", "Ajouter un film");
        return "films/form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute Film film, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", film.getId() == null ? "Ajouter un film" : "Modifier un film");
            return "films/form";
        }
        filmService.save(film);
        return "redirect:/classic/films";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("film", filmService.findById(id));
        model.addAttribute("pageTitle", "Modifier un film");
        return "films/form";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        filmService.deleteById(id);
        return "redirect:/classic/films";
    }
}

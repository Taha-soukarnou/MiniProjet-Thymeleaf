package com.example.cinema.controller;

import com.example.cinema.model.Billet;
import com.example.cinema.model.StatutBillet;
import com.example.cinema.service.BilletService;
import com.example.cinema.service.SeanceService;
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
@RequestMapping("/classic/billets")
public class BilletController {

    private final BilletService billetService;
    private final SeanceService seanceService;

    public BilletController(BilletService billetService, SeanceService seanceService) {
        this.billetService = billetService;
        this.seanceService = seanceService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) StatutBillet statut, Model model) {
        model.addAttribute("billets", billetService.findAll(statut));
        model.addAttribute("statut", statut);
        model.addAttribute("statuts", StatutBillet.values());
        return "billets/list";
    }

    @GetMapping("/new")
    public String sellForm(Model model) {
        addFormAttributes(model, new Billet());
        return "billets/form";
    }

    @PostMapping
    public String sell(@Valid @ModelAttribute Billet billet, BindingResult bindingResult, Model model) {
        if (bindingResult.hasFieldErrors("prix") || bindingResult.hasFieldErrors("seance")) {
            addFormAttributes(model, billet);
            return "billets/form";
        }
        billetService.sell(billet);
        return "redirect:/classic/billets";
    }

    @PostMapping("/{id}/cancel")
    public String cancel(@PathVariable Long id) {
        billetService.cancel(id);
        return "redirect:/classic/billets";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        billetService.deleteById(id);
        return "redirect:/classic/billets";
    }

    private void addFormAttributes(Model model, Billet billet) {
        model.addAttribute("billet", billet);
        model.addAttribute("seances", seanceService.findAll());
    }
}

package com.example.cinema.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PremiumUiController {

    private static final List<MovieUi> MOVIES = List.of(
            new MovieUi("Dune: Part Two", "Science-fiction", "166 min", "12", "https://images.unsplash.com/photo-1542204165-65bf26472b9b?auto=format&fit=crop&w=500&q=80"),
            new MovieUi("Oppenheimer", "Biopic", "180 min", "9", "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?auto=format&fit=crop&w=500&q=80"),
            new MovieUi("Parasite", "Drame", "132 min", "8", "https://images.unsplash.com/photo-1536440136628-849c177e76a1?auto=format&fit=crop&w=500&q=80"),
            new MovieUi("Grand Budapest", "Comedie", "100 min", "6", "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?auto=format&fit=crop&w=500&q=80")
    );

    private static final List<ScreeningUi> SCREENINGS = List.of(
            new ScreeningUi("Dune: Part Two", "Salle 1", "19:30", "VF", "92%"),
            new ScreeningUi("Oppenheimer", "Salle 3", "20:15", "VOST", "78%"),
            new ScreeningUi("Parasite", "Salle VIP", "21:00", "VO", "64%"),
            new ScreeningUi("Grand Budapest", "Salle 2", "22:10", "VF", "48%")
    );

    private static final List<TicketUi> TICKETS = List.of(
            new TicketUi("#TK-9201", "Dune: Part Two", "Salle 1 - 19:30", "07/06/2026 10:12", "85 DH", "Sold"),
            new TicketUi("#TK-9200", "Oppenheimer", "Salle 3 - 20:15", "07/06/2026 09:46", "95 DH", "Sold"),
            new TicketUi("#TK-9199", "Parasite", "Salle VIP - 21:00", "07/06/2026 09:18", "120 DH", "Cancelled")
    );

    @GetMapping({"", "/", "/dashboard"})
    public String dashboard(Model model) {
        return page(model, "dashboard", "Dashboard", "Cinema overview and business analytics");
    }

    @GetMapping("/films")
    public String films(Model model) {
        return page(model, "films", "Films", "Search, filter and manage the movie catalogue");
    }

    @GetMapping({"/films/new", "/films/edit"})
    public String filmForm(Model model) {
        return page(model, "film-form", "Add / Edit Film", "Movie information");
    }

    @GetMapping("/seances")
    public String seances(Model model) {
        return page(model, "seances", "Seances", "Calendar-inspired screening planning");
    }

    @GetMapping({"/seances/new", "/seances/edit"})
    public String seanceForm(Model model) {
        return page(model, "seance-form", "Add / Edit Screening", "Movie selection and screening details");
    }

    @GetMapping("/billets")
    public String billets(Model model) {
        return page(model, "billets", "Billets", "Ticket sales, cancellation and history");
    }

    @GetMapping("/billets/new")
    public String sellTicket(Model model) {
        return page(model, "sell-ticket", "Sell Ticket", "Split-screen purchase workflow");
    }

    @GetMapping("/stats")
    public String stats(Model model) {
        return page(model, "stats", "Statistics & Analytics", "Revenue, occupancy and top films");
    }

    private String page(Model model, String page, String title, String subtitle) {
        model.addAttribute("page", page);
        model.addAttribute("title", title);
        model.addAttribute("subtitle", subtitle);
        model.addAttribute("movies", MOVIES);
        model.addAttribute("screenings", SCREENINGS);
        model.addAttribute("tickets", TICKETS);
        return "premium";
    }

    record MovieUi(String title, String genre, String duration, String screenings, String poster) {}
    record ScreeningUi(String movie, String room, String time, String version, String occupancy) {}
    record TicketUi(String id, String movie, String screening, String date, String price, String status) {}
}

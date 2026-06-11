package com.example.cinema.service;

import com.example.cinema.model.Seance;

public record TauxRemplissageDto(Seance seance, long billetsVendus, double taux) {
}

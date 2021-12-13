package com.example.demo.controllers;

import com.example.demo.collections.Player;
import com.example.demo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/v1/player")
public class PlayerController {

    @Autowired
    private PlayerService service;

    @GetMapping("obtener/mayores34/{club}")
    public Flux<Player> obtenerJugadoresMayores34DelClub(@PathVariable("club") String club) {
        return service.obtenerJugadoresMayores34DelClub(club);
    }

    @GetMapping("obtener/ranking-por-pais")
    public Flux<Player> obtenerMayorRankingCadaPais() {
        return service.obtenerMayorRankingCadaPais();
    }
}

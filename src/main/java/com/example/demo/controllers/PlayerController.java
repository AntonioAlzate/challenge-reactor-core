package com.example.demo.controllers;

import com.example.demo.collections.Player;
import com.example.demo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/v1/player")
public class PlayerController {

    @Autowired
    private PlayerService service;

    @GetMapping("mayores-34")
    public Flux<Player> obtenerJugadoresMayores34DelClub(@RequestParam("club") String club) {
        return service.obtenerJugadoresMayores34DelClub(club);
    }

    @GetMapping("ranking-por-pais")
    public Flux<Player> obtenerMayorRankingCadaPais() {
        return service.obtenerMayorRankingCadaPais();
    }
}

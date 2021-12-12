package com.example.demo.services;

import com.example.demo.collections.Player;
import com.example.demo.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlayerService {

    public static final String NO_EXISTEN_JUGADORES = "No se encontraron jugadores para el equipo ";

    @Autowired
    private PlayerRepository repository;

    public Flux<Player> obtenerJugadoresMayores34DelClub(String club) {
        Flux<Player> result = repository.findByClub(club);

        return result
                .filter(jugador -> jugador.getAge() > 34)
                .doOnNext(player -> System.out.println(player.getName()))
                .switchIfEmpty(Mono.error(new RuntimeException(NO_EXISTEN_JUGADORES + club)));
    }

}
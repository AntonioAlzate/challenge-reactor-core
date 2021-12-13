package com.example.demo.services;

import com.example.demo.collections.Player;
import com.example.demo.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    // En este método se traen los jugadores se agrupan por nacionalidad
    // luego se pasa a listas para cada nacionalidad y se selecciona el de mayor número de victorias
    public Flux<Player> obtenerMayorRankingCadaPais(){
        return repository.findAll()
                .groupBy(player -> player.getNational())
                .flatMap(Flux::collectList)
                .map(players -> obtenerJugadorMayorWinners(players))
                .flatMap(players -> Flux.fromStream(players.parallelStream()));

    }

    private List<Player> obtenerJugadorMayorWinners(List<Player> players) {
        List<Player> playersRanking = new ArrayList<>();
        players.forEach(player -> {
            if(playersRanking.isEmpty())
                playersRanking.add(player);

            if(playersRanking.get(0).getWinners() < player.getWinners()){
                playersRanking.remove(0);
                playersRanking.add(player);
            }
        });
        return playersRanking;
    }
}

package com.example.demo.services;

import com.example.demo.collections.Player;
import com.example.demo.repositories.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @InjectMocks
    PlayerService service;

    @Mock
    PlayerRepository repository;

    @Test
    void obtenerJugadoresMayores34Test() {
        Flux<Player> playersFlux = Flux.fromIterable(obtenerJugadoresDummy());
        String club = "Juventus";

        Mockito.when(repository.findByClub(club)).thenReturn(playersFlux);

        Flux<Player> response = service.obtenerJugadoresMayores34DelClub(club);

        StepVerifier.create(response)
                .expectNextCount(3)
                .expectComplete();

        Mockito.verify(repository).findByClub(club);
    }

    @Test
    void obtenerMayorRankingCadaPais() {
        Flux<Player> playersFlux = Flux.fromIterable(obtenerJugadoresDummy());

        Mockito.when(repository.findAll()).thenReturn(playersFlux);

        Flux<Player> response = service.obtenerMayorRankingCadaPais();

        StepVerifier.create(response)
                .expectNextCount(5)
                .expectComplete();

        StepVerifier.create(response)
                .expectNextMatches(player ->
                        player.getNational().equals("Brasil") && player.getWinners() == 10)
                .expectNextMatches(player ->
                        player.getNational().equals("Colombia") && player.getWinners() == 3)
                .expectNextMatches(player ->
                        player.getNational().equals("Argentina") && player.getWinners() == 5)
                .expectNextMatches(player ->
                        player.getNational().equals("Perú") && player.getWinners() == 2)
                .expectNextMatches(player ->
                        player.getNational().equals("Alemania") && player.getWinners() == 7
                )
                .verifyComplete();

        Mockito.verify(repository).findAll();
    }

    private List<Player> obtenerJugadoresDummy() {
        List<Player> players = new ArrayList<>();
        players.add(new Player(3, "sss", 33, "icon", "Brasil", 7, 1, "juventus"));
        players.add(new Player(4, "ddd", 33, "icon", "Brasil", 8, 1, "juventus"));
        players.add(new Player(5, "fff", 30, "icon", "Brasil", 10, 1, "juventus"));
        players.add(new Player(6, "ggg", 33, "icon", "Colombia", 3, 1, "juventus"));
        players.add(new Player(1, "xxx", 33, "icon", "Argentina", 5, 1, "juventus"));
        players.add(new Player(2, "aaa", 33, "icon", "Argentina", 4, 1, "juventus"));
        players.add(new Player(7, "hhh", 37, "icon", "Perú", 2, 1, "juventus"));
        players.add(new Player(8, "jjj", 35, "icon", "Alemania", 7, 1, "juventus"));
        players.add(new Player(9, "kkk", 40, "icon", "Alemania", 4, 1, "juventus"));

        return players;
    }
}
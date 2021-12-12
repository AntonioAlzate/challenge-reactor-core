package com.example.demo.repositories;

import com.example.demo.collections.Player;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PlayerRepository extends ReactiveMongoRepository<Player, String> {
    Flux<Player> findByClub(String club);
}

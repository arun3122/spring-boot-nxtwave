package com.example.player.controller;

import com.example.player.model.Player;
import com.example.player.service.PlayerJpaService;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class PlayerController {

    @Autowired
    public PlayerJpaService playerObj;

    @GetMapping("/players")
    public ArrayList<Player> getPlayers() {
        return playerObj.getPlayers();
    }

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player) {
        return playerObj.addPlayer(player);
    }

    @GetMapping("/players/{playerId}")
    public Player getPlayerById(@PathVariable("playerId") int playerId) {
        return playerObj.getPlayerById(playerId);
    }

    @PutMapping("/players/{playerId}")
    public Player updatePlayer(@PathVariable("playerId") int playerId, @RequestBody Player player) {
        return playerObj.updatePlayer(playerId, player);
    }

    @DeleteMapping("players/{playerId}")
    public void deletePlayer(@PathVariable int playerId) {
        playerObj.deletePlayer(playerId);
    }

}
package com.example.player.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.example.player.service.PlayerH2Service;
import com.example.player.model.Player;


@RestController
public class PlayerController {

    @Autowired
    public PlayerH2Service playerObj;

    @DeleteMapping("/players/{playerId}")
    public void deletePlayer(@PathVariable("playerId") int playerId) {
        playerObj.deletePlayer(playerId);
    }

    @PutMapping("/players/{playerId}")
    public Player updatePlayer(@PathVariable("playerId") int playerId, @RequestBody Player player) {
        return playerObj.updatePlayer(playerId, player);
    }

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player) {
        return playerObj.addPlayer(player);
    }

    @GetMapping("/players/{playerId}") 
    public Player getPlayerById(@PathVariable("playerId") int playerId) {
        return playerObj.getPlayerById(playerId);
    }

    @GetMapping("/players") 
    public ArrayList<Player> getPlayers() {
        return playerObj.getPlayers();
    }
}
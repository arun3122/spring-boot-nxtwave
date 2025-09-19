/*
 * You can use the following import statements
 * 
 * import org.springframework.web.bind.annotation.*;
 * import java.util.ArrayList;
 * 
 */

package com.example.player;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
class PlayerController {
    PlayerService playersObj = new PlayerService();

    @DeleteMapping("/players/{playerId}")
    public void deletePlayer(@PathVariable("playerId") int id) {
        playersObj.deletePlayer(id);
    }

    @PutMapping("/players/{playerId}")
    public Player updatePlayer(@PathVariable("playerId") int id, @RequestBody Player player) {
        return playersObj.updatePlayer(id, player);
    }

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player) {
        return playersObj.addPlayer(player);
    } 

    @GetMapping("/players/{playerId}")
    public Player getPlayer(@PathVariable("playerId") int id) {
        return playersObj.getPlayerById(id);
    }

    @GetMapping("/players")
    public ArrayList<Player> getPlayers() {
        return playersObj.getPlayers();
    } 
}
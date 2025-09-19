package com.example.player.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.player.model.PlayerRowMapper;
import com.example.player.repository.PlayerRepository;
import com.example.player.model.Player;

@Service
public class PlayerH2Service implements PlayerRepository {
    
    @Autowired
    private JdbcTemplate db;

    @Override
    public void deletePlayer(int playerId) {
        db.update("DELETE FROM team WHERE playerId = ?", playerId);
    }

    @Override
    public Player updatePlayer(int playerId, Player player) {
        if (player.getPlayerName() != null) {
            db.update("UPDATE team SET playerName = ? WHERE playerId = ?", player.getPlayerName(), playerId);
        }

        if (player != null) {
            db.update("UPDATE team SET jerseyNumber = ? WHERE playerId = ?", player.getJerseyNumber(), playerId);
        }

        if (player.getRole() != null) {
            db.update("UPDATE team SET role = ? WHERE playerId = ?", player.getRole(), playerId);
        }
        
        return getPlayerById(playerId);
    }

    @Override
    public Player addPlayer(Player player) {
        db.update("INSERT INTO team(playerName, jerseyNumber, role) VALUES (?, ?, ?)", player.getPlayerName(), player.getJerseyNumber(), player.getRole());
        Player newPlayer = db.queryForObject("SELECT * FROM team WHERE playerName = ? AND jerseyNumber = ? AND role = ?", new PlayerRowMapper(), player.getPlayerName(), player.getJerseyNumber(), player.getRole());
        return newPlayer;
    }

    @Override
    public ArrayList<Player> getPlayers() {
        List<Player> playerList = db.query("SELECT * FROM team", new PlayerRowMapper()); 
        ArrayList<Player> players = new ArrayList<>(playerList);

        return players;
    }

    @Override
    public Player getPlayerById(int playerId) {
        try {
            Player uniquePlayer = db.queryForObject("SELECT * FROM team WHERE playerId = ?", new PlayerRowMapper(), playerId);
            return uniquePlayer;
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
       
    }
}
package com.example.player.service;

import com.example.player.model.Player;
import com.example.player.repository.PlayerJpaRepository;
import com.example.player.repository.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class PlayerJpaService implements PlayerRepository {

    @Autowired
    private PlayerJpaRepository playerJpaRepository;

    @Override
    public ArrayList<Player> getPlayers() {
        List<Player> playersList = playerJpaRepository.findAll();
        ArrayList<Player> players = new ArrayList<>(playersList);
        return players;
    }

    @Override
    public Player addPlayer(Player player) {
        playerJpaRepository.save(player);
        return player;
    }

    @Override
    public Player getPlayerById(int playerId) {
        try {
            Player uniquePlayer = playerJpaRepository.findById(playerId).get();
            return uniquePlayer;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Player updatePlayer(int playerId, Player player) {
        try {
            Player existingPlayer = playerJpaRepository.findById(playerId).get();
            
            if(existingPlayer.getPlayerName() != null) {
                existingPlayer.setPlayerName(player.getPlayerName());
            }

            if(existingPlayer.getJerseyNumber() > 0) {
                existingPlayer.setJerseyNumber(player.getJerseyNumber());
            }

            if(existingPlayer.getRole() != null) {
                existingPlayer.setRole(player.getRole());
            }

            playerJpaRepository.save(existingPlayer);
            return existingPlayer;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deletePlayer(int playerId) {
        try {
            playerJpaRepository.deleteById(playerId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
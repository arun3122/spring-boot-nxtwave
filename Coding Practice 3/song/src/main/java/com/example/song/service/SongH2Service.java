package com.example.song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.song.model.SongRowMapper;
import com.example.song.model.Song;
import com.example.song.repository.SongRepository;

@Service
public class SongH2Service implements SongRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public void deleteSong(int songId) {
        db.update("DELETE FROM playlist WHERE songId = ?", songId);
    }

    @Override
    public Song updateSong(int songId, Song song) {
        
        if (song.getSongName() != null) {
            db.update("UPDATE playlist SET songName = ? WHERE songId = ?", song.getSongName(), songId);
        }

        if (song.getLyricist() != null) {
            db.update("UPDATE playlist SET lyricist = ? WHERE songId = ?", song.getLyricist(), songId);
        }

        if (song.getSinger() != null) {
            db.update("UPDATE playlist SET singer = ? WHERE songId = ?", song.getSinger(), songId);
        }

        if (song.getMusicDirector() != null) {
            db.update("UPDATE playlist SET musicDirector = ? WHERE songId = ?", song.getMusicDirector(), songId);
        }

        return getSongById(songId);
    }

    @Override
    public Song addSong(Song song) {
        db.update("INSERT INTO playlist(songName, lyricist, singer, musicDirector) VALUES(?, ?, ?, ?)", 
                   song.getSongName(), song.getLyricist(), song.getSinger(), song.getMusicDirector());
        Song newSong = db.queryForObject("SELECT * FROM playlist WHERE songName = ? AND lyricist = ? AND singer = ? AND musicDirector = ?",
                                         new SongRowMapper(), song.getSongName(), song.getLyricist(), song.getSinger(), song.getMusicDirector());
        return getSongById(newSong.getSongId());
    }

    @Override
    public Song getSongById(int songId) {
        try {
            Song uniqueSong = db.queryForObject("SELECT * FROM playlist WHERE songId = ?", new SongRowMapper(), songId);
            return uniqueSong;
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
    }

    @Override 
    public ArrayList<Song> getSongs() {
        List<Song> songsList = db.query("SELECT * FROM playlist", new SongRowMapper());
        ArrayList<Song> songs = new ArrayList<>(songsList);
        return songs;
    }

}
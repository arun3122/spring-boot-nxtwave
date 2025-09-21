package com.example.song.service;

import com.example.song.model.Song;
import com.example.song.repository.SongJpaRepository;
import com.example.song.repository.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class SongJpaService implements SongRepository {

    @Autowired
    private SongJpaRepository songJpaRepository;

    @Override
    public ArrayList<Song> getSongs() {
        List<Song> songList = songJpaRepository.findAll();
        ArrayList<Song> songs = new ArrayList<>(songList);
        
        return songs;
    }

    @Override
    public Song addSong(Song song) {
        songJpaRepository.save(song);
        return song;
    }

    @Override
    public Song getSongById(int songId) {
        try {
            Song uniqueSong = songJpaRepository.findById(songId).get();
            return uniqueSong;
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Song updateSong(int songId, Song song) {
        try {
            Song existingSong = songJpaRepository.findById(songId).get();

            if(existingSong.getSongName() != null) {
                existingSong.setSongName(song.getSongName());
            }

            if(existingSong.getLyricist() != null) {
                existingSong.setLyricist(song.getLyricist());
            }

            if(existingSong.getSinger() != null) {
                existingSong.setSinger(song.getSinger());
            }

            if(existingSong.getMusicDirector() != null) {
                existingSong.setMusicDirector(song.getMusicDirector());
            }

            songJpaRepository.save(existingSong);
            return existingSong;
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteSong(int songId) {
        try {
            songJpaRepository.deleteById(songId);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
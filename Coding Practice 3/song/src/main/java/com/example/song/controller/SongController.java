package com.example.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.example.song.service.SongH2Service;
import com.example.song.model.Song;

@RestController
public class SongController {

    @Autowired
    public SongH2Service songObj;

    @DeleteMapping("/songs/{songId}") 
    public void deleteSong(@PathVariable("songId") int songId) {
        songObj.deleteSong(songId);
    }

    @PutMapping("/songs/{songId}")
    public Song updateSong(@PathVariable("songId") int songId, @RequestBody Song song) {
        return songObj.updateSong(songId, song);
    }

    @PostMapping("/songs")
    public Song addSong(@RequestBody Song song) {
        return songObj.addSong(song);
    }

    @GetMapping("/songs/{songId}") 
    public Song getSongById(@PathVariable("songId") int songId) {
        return songObj.getSongById(songId);
    }

    @GetMapping("/songs")
    public ArrayList<Song> getSongs() {
        return songObj.getSongs();
    }
}
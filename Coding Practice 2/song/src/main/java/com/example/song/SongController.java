/*
 * You can use the following import statements
 * 
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 * 
 */

// Write your code here

package com.example.song;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
class SongController {
    SongService songsObj = new SongService();

    @DeleteMapping("/songs/{songId}")
    public void deleteSong(@PathVariable int songId) {
        songsObj.deleteSong(songId);
    }

    @PutMapping("/songs/{songId}")
    public Song updateSong(@PathVariable int songId, @RequestBody Song song) {
        return songsObj.updateSong(songId, song);
    }

    @GetMapping("/songs/{songId}")
    public Song getSongById(@PathVariable int songId) {
        return songsObj.getSongById(songId);
    }

    @GetMapping("/songs")
    public ArrayList<Song> getSongs() {
        return songsObj.getSongs();
    }

    @PostMapping("/songs") 
    public Song addSong(@RequestBody Song song) {
        return songsObj.addSong(song);
    }
}
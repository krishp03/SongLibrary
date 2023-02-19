package songlib;

import java.util.ArrayList;
import java.util.Collections;

public class Library {
    private ArrayList<Song> library;
    private int current = -1;

    public boolean addSong(String name, String artist, String album, int year){
        if(name == "" || artist == ""){
            throw new IllegalArgumentException("Song name and artist must be specified");
        }
        Song s = new Song(name, artist, album, year);
        if(library.contains(s)) return false;
        library.add(s);
        Collections.sort(library);
        current = library.indexOf(s);
        return true;
    }

    public boolean edit(String name, String artist, String album, int year){
        if(name == "" || artist == ""){
            throw new IllegalArgumentException("Song name and artist must be specified");
        }
        Song s = new Song(name, artist, album, year);
        if(library.contains(s) && !s.equals(library.get(current))) return false;
        library.remove(current);
        library.add(s);
        Collections.sort(library);
        current = library.indexOf(s);
        return true;
    }

    public void delete(){
        if(current != -1){
            library.remove(current);
            // If there is no next song, the previous song should be selected 
            if(current > library.size()) current--;
        }
    }
}

package songlib;

import java.util.Collections;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class Library {

    public boolean addSong(ObservableList<Song> library, String name, String artist, String album, int year){
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

    public boolean edit(ObservableList<Song> library, String name, String artist, String album, int year){
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

    public boolean delete(){
        if(current != -1){
            library.remove(current);
            // If there is no next song, the previous song should be selected 
            if(current > library.size()) current--;
            return true;
        }
        return false;
    }

    public Song getCurrentSong() {
        return library.get(current);
    }

    public ListView<Song> getCurrentSong() {
        return library.get(current);
    }

}

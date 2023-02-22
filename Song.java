/*
 * Song Library Design & Implementation with GUI
 * By Krish Patel and Roshan Varadhan
*/
package org.openjfx;

public class Song implements Comparable<Song>{

    private String name;
    private String artist;
    private String album;
    private String year;

    public Song(String name, String artist, String album, String year){
        this.name = name.trim();
        this.artist = artist.trim();
        if(album != null && !album.isBlank()){
            this.album = album.trim();
        } else{
            album = "";
        }
        if(year == null || year.isBlank()) {
            year = "";
        } else {
            int yearNum = Integer.parseInt(year);
            if(yearNum < 0){
                throw new IllegalArgumentException();
            }
            this.year = year.trim();
        }
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    // Compare by name first, and if names are duplicates, compare by artist
    public int compareTo(Song s) {
        if(name.compareTo(s.getName()) != 0){
            return name.toLowerCase().compareTo(s.getName().toLowerCase());
        }
        return artist.toLowerCase().compareTo(s.getArtist().toLowerCase());
    }

    @Override
    // Equivalent if name AND artist are same(case INsensitive)
    public boolean equals(Object o){
        if(o == this){
            return true;
        }

        if(!(o instanceof Song)){
            return false;
        }

        Song s = (Song)o;

        return s.getName().equalsIgnoreCase(name)
                && s.getArtist().equalsIgnoreCase(artist);
    }

    public String toString() {
        return name+" | By "+artist;
    }
}

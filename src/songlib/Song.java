package songlib;

public class Song implements Comparable<Song>{

    private String name;
    private String artist;
    private String album;
    private int year;

    public Song(String name, String artist, String album, int year){
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.year = year;
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

    public int getYear() {
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

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    // Compare by name first, and if names are duplicates, compare by artist
    public int compareTo(Song s) {
        if(name.compareTo(s.getName()) != 0){
            return name.compareTo(s.getName());
        }
        return artist.compareTo(s.getArtist());
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

}

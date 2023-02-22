/*
 * Song Library Design & Implementation with GUI
 * By Krish Patel and Roshan Varadhan
 */
package org.openjfx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.net.URL;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;


public class PrimaryController implements Initializable{

    @FXML BorderPane screenPane;
    @FXML GridPane addPane;
    @FXML GridPane editPane;
    @FXML GridPane mainPane;
    @FXML TextField addSongField;
    @FXML TextField addArtistField;
    @FXML TextField addAlbumField;
    @FXML TextField addYearField;
    @FXML TextField editSongField;
    @FXML TextField editArtistField;
    @FXML TextField editAlbumField;
    @FXML TextField editYearField;
    @FXML Button buttonConfirmAdd;
    @FXML Button buttonCancelAdd;
    @FXML Button buttonUpdate;
    @FXML Button buttonCancelEdit;
    @FXML Button buttonAdd;
    @FXML Button buttonDelete;
    @FXML Button buttonEdit;
    @FXML Label titleLabel;
    @FXML Label artistLabel;
    @FXML Label albumLabel;
    @FXML Label yearLabel;
    @FXML ListView<Song> songListView = new ListView<Song>();

    private void clearAdd(){
        addSongField.setText("");
        addArtistField.setText("");
        addAlbumField.setText("");
        addYearField.setText("");
    }

    private void showSong(){
        if (songListView.getItems().size()==0){
            titleLabel.setText("Song Library");
            artistLabel.setText("By Krish Patel and Roshan Varadhan");
            albumLabel.setText("CS 213");
            yearLabel.setText("2023");
            return;
        }
        Song curr = songListView.getSelectionModel().getSelectedItem();
        if(curr!=null) {
            titleLabel.setText(curr.getName());
            artistLabel.setText(curr.getArtist());
            albumLabel.setText(curr.getAlbum());
            yearLabel.setText(curr.getYear());
        }
    }

    public void buttonListener(ActionEvent e) throws IOException {
        Button act = (Button) e.getSource();
        if (act==buttonAdd) screenPane.setRight(addPane);
        else if(act==buttonEdit) {
            screenPane.setRight(editPane);
            Song s = songListView.getSelectionModel().getSelectedItem();
            editSongField.setText(s.getName());
            editArtistField.setText(s.getArtist());
            editAlbumField.setText(s.getAlbum());
            editYearField.setText(s.getYear());
        }
        else if(act==buttonDelete || act==buttonConfirmAdd || act==buttonUpdate) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
            if (act==buttonDelete){
                alert.setContentText("Confirm Delete?");
            }else if (act==buttonConfirmAdd){
                alert.setContentText("Confirm Add?");
            }else{
                alert.setContentText("Confirm Edit?");
            }
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if(act==buttonConfirmAdd){
                    Song s = createSong(addSongField.getText(), addArtistField.getText(), addAlbumField.getText(), addYearField.getText());
                    if(s!=null){
                        if(!songListView.getItems().contains(s)){
                            songListView.getItems().add(s);
                            Collections.sort(songListView.getItems());
                            songListView.getSelectionModel().select(s);
                        } else{
                            Alert a = new Alert(Alert.AlertType.ERROR, "This song already exists in the Library!!");
                            a.showAndWait();
                        }
                    }
                    clearAdd();
                }
                else if(act==buttonDelete){
                    int i = songListView.getSelectionModel().getSelectedIndex();
                    songListView.getItems().remove(songListView.getSelectionModel().getSelectedItem());
                    if (i>=songListView.getItems().size()){
                        if (i!=0) i--;
                    }
                    songListView.getSelectionModel().select(i);
                }
                else{
                    Song s = createSong(editSongField.getText(), editArtistField.getText(), editAlbumField.getText(), editYearField.getText());
                    if(s != null && (!songListView.getItems().contains(s) || songListView.getSelectionModel().getSelectedItem().equals(s))){
                        songListView.getItems().remove(songListView.getSelectionModel().getSelectedItem());
                        songListView.getItems().add(s);
                        Collections.sort(songListView.getItems());
                        songListView.getSelectionModel().select(s);
                    } else if(s != null){
                        Alert a = new Alert(Alert.AlertType.ERROR, "This song already exists in the Library");
                        a.showAndWait();
                    }
                }
                persistence();
                showSong();
                screenPane.setRight(mainPane);
            }
        }
        else if(act==buttonCancelAdd || act==buttonCancelEdit) {
            if(act==buttonCancelAdd) clearAdd();
            screenPane.setRight(mainPane);
        }
    }

    private void persistence() throws IOException {
        try{
            FileWriter f = new FileWriter("songs.txt", false);
            f.write("");
            f.close();
            BufferedWriter b = new BufferedWriter(new FileWriter("songs.txt", true));
            for (Song s : songListView.getItems()) {
                b.write(s.getName() + "|" + s.getArtist() + "|" + s.getAlbum() + "|" + s.getYear());
                b.write("\n");
            }
            b.close();
        } catch (IOException e){
        }

    }
    private Song createSong(String name, String artist, String album, String year) {
        try{
            if(name.isBlank() || artist.isBlank()){
                Alert a = new Alert(Alert.AlertType.ERROR, "A song needs to have at least a name and artist");
                a.showAndWait();
                return null;
            }
            if(name.contains("|") || artist.contains("|")){
                Alert a = new Alert(Alert.AlertType.ERROR, "No Vertical Bars Allowed!!");
                a.showAndWait();
                return null;
            }
            if(!album.isBlank() && album.contains("|")){
                Alert a = new Alert(Alert.AlertType.ERROR, "No Vertical Bars Allowed!!");
                a.showAndWait();
                return null;
            }
            Song s = new Song(name, artist, album, year);
            return s;
        } catch(Exception e){
            Alert a = new Alert(Alert.AlertType.ERROR, "If you include a year, it must be a positive integer");
            a.showAndWait();
            return null;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            BufferedReader songLibrary = new BufferedReader(new FileReader("songs.txt"));
            String part = songLibrary.readLine();
            while (part!=null){
                String fields[]=part.split("\\|");
                String name=fields[0];
                String artist=fields[1];
                String album=fields[2];
                String year=fields[3];
                if(album.equals("null")){
                    album="";
                }if(year.equals("null")){
                    year="";
                }
                Song s=createSong(name, artist, album, year);
                songListView.getItems().add(s);
                part = songLibrary.readLine();
            }
            songLibrary.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(songListView.getItems().size()>0){
            songListView.getSelectionModel().selectFirst();
        }
        showSong();
        songListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>(){

            @Override
            public void changed(ObservableValue<? extends Song> arg0, Song arg1, Song arg2) {
                showSong();
            }

        });

    }
}

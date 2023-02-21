package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class PrimaryController {

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
            titleLabel.setText("");
            artistLabel.setText("");
            albumLabel.setText("");
            yearLabel.setText("");
            return;
        }
        Song curr = songListView.getSelectionModel().getSelectedItem();
        if(curr!=null) {
            titleLabel.setText(curr.getName());
            artistLabel.setText(curr.getArtist());
            albumLabel.setText(curr.getAlbum());
            yearLabel.setText(Integer.toString(curr.getYear()));
        }
    }

    public void buttonListener(ActionEvent e) {
        Button act = (Button) e.getSource();
        if (act==buttonAdd) screenPane.setRight(addPane);
        else if(act==buttonEdit) {
            screenPane.setRight(editPane);
            Song s = songListView.getSelectionModel().getSelectedItem();
            editSongField.setText(s.getName());
            editArtistField.setText(s.getArtist());
            editAlbumField.setText(s.getAlbum());
            editYearField.setText(Integer.toString(s.getYear()));
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
                    Song s = new Song(addSongField.getText(), addArtistField.getText(), addAlbumField.getText(), Integer.parseInt(addYearField.getText()));
                    songListView.getItems().add(s);
                    Collections.sort(songListView.getItems());
                    songListView.getSelectionModel().select(s);
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
                    Song s = new Song(editSongField.getText(), editArtistField.getText(), editAlbumField.getText(), Integer.parseInt(editYearField.getText()));
                    songListView.getItems().remove(songListView.getSelectionModel().getSelectedItem());
                    songListView.getItems().add(s);
                    Collections.sort(songListView.getItems());
                    songListView.getSelectionModel().select(s);
                }
                showSong();
                screenPane.setRight(mainPane);
            }
        }
        else if(act==buttonCancelAdd || act==buttonCancelEdit) {
            if(act==buttonCancelAdd) clearAdd();
            screenPane.setRight(mainPane);
        }
    }
}

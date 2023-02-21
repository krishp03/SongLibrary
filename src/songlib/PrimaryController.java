// package songlib;
// import javafx.collections.FXCollections;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.scene.control.*;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.GridPane;

// import java.util.Comparator;
// import java.util.Optional;


// public class PrimaryController {

//     @FXML BorderPane screenPane;
//     @FXML GridPane addPane;
//     @FXML GridPane editPane;
//     @FXML GridPane mainPane;
//     @FXML TextField addSongField;
//     @FXML TextField addArtistField;
//     @FXML TextField addAlbumField;
//     @FXML TextField addYearField;
//     @FXML TextField editSongField;
//     @FXML TextField editArtistField;
//     @FXML TextField editAlbumField;
//     @FXML TextField editYearField;
//     @FXML Button buttonConfirmAdd;
//     @FXML Button buttonCancelAdd;
//     @FXML Button buttonUpdate;
//     @FXML Button buttonCancelEdit;
//     @FXML Button buttonAdd;
//     @FXML Button buttonDelete;
//     @FXML Button buttonEdit;
//     @FXML Label titleLabel;
//     @FXML Label artistLabel;
//     @FXML Label albumLabel;
//     @FXML Label yearLabel;
//     @FXML ListView<Song> songListView = new ListView<Song>();


//     private class sorter implements Comparator<Song> {
//         public int compare(Song a, Song b)
//         {
//             int x = a.getName().compareTo(b.getName());
//             if (x==0) return a.getArtist().compareTo(b.getArtist());
//             return x;
//         }
//     }

//     private void sort(){
//         sorter q = new sorter();
//         FXCollections.sort(songListView.getItems(), q);
//     }
    
//     private void clearAdd(){
//         addSongField.setText("");
//         addArtistField.setText("");
//         addAlbumField.setText("");
//         addYearField.setText("");
//     }

//     public void buttonListener(ActionEvent e) {
//         Button act = (Button) e.getSource();
//         if (act==buttonAdd) screenPane.setRight(addPane);
//         else if(act==buttonEdit) screenPane.setRight(editPane);
//         else if(act==buttonDelete || act==buttonConfirmAdd || act==buttonUpdate) {
//             Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "");
//             if (act==buttonDelete){
//                 alert.setContentText("Confirm Delete?");
//             }else if (act==buttonConfirmAdd){
//                 alert.setContentText("Confirm Add?");
//             }else{
//                 alert.setContentText("Confirm Edit?");
//             }
//             Optional<ButtonType> result = alert.showAndWait();
//             if (result.isPresent() && result.get() == ButtonType.OK) {
//                 if(act==buttonConfirmAdd){
//                     Song s = new Song(addSongField.getText(), addArtistField.getText(), addAlbumField.getText(), Integer.parseInt(addYearField.getText()));
//                     songListView.getItems().add(s);
//                     sort();
//                     clearAdd();
//                 }
//                 screenPane.setRight(mainPane);
//             }
//         }
//         else if(act==buttonCancelAdd || act==buttonCancelEdit) {
//             if(act==buttonCancelAdd) clearAdd();
//             screenPane.setRight(mainPane);
//         }
//     }

// }

package songlib;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class PrimaryController {

    @FXML
    private TextField addAlbumField;

    @FXML
    private TextField addArtistField;

    @FXML
    private GridPane addPane;

    @FXML
    private TextField addSongField;

    @FXML
    private TextField addYearField;

    @FXML
    private Label albumLabel;

    @FXML
    private Label artistLabel;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonCancelAdd;

    @FXML
    private Button buttonCancelEdit;

    @FXML
    private Button buttonConfirmAdd;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonUpdate;

    @FXML
    private TextField editAlbumField;

    @FXML
    private TextField editArtistField;

    @FXML
    private GridPane editPane;

    @FXML
    private TextField editSongField;

    @FXML
    private TextField editYearField;

    @FXML
    private GridPane mainPane;

    @FXML
    private BorderPane screenPane;

    @FXML
    private ListView<Song> library;

    @FXML
    private Label titleLabel;

    @FXML
    private Label yearLabel;

    private void clearAdd() {
        addSongField.setText("");
        addArtistField.setText("");
        addAlbumField.setText("");
        addYearField.setText("");
    }

    @FXML
    void addSong(MouseEvent event) {
        Button act = (Button) event.getSource();
        screenPane.setRight(addPane);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to add this song?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (act == buttonConfirmAdd) {
                try {
                    Song s = new Song(addSongField.getText(), addArtistField.getText(), addAlbumField.getText(),
                            Integer.parseInt(addYearField.getText()));
                    library.getItems().add(s);
                } catch (Exception e) {
                }
            }
        } else if (act == buttonCancelAdd) {
            clearAdd();
            screenPane.setRight(mainPane);
        }
    }

    @FXML
    void buttonListener(ActionEvent event) {

    }

    @FXML
    void deleteSong(MouseEvent event) {

    }

    @FXML
    void editSong(MouseEvent event) {

    }

    @FXML
    void listClick(MouseEvent event) {

    }

}

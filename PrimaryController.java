package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


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


    public void buttonListener(ActionEvent e) {
        Button act = (Button) e.getSource();
        if (act==buttonAdd) screenPane.setRight(addPane);
        else if(act==buttonEdit) screenPane.setRight(editPane);
        else if(act==buttonDelete || act==buttonConfirmAdd || act==buttonUpdate) {
            Dialog<String> dialog = new Dialog<String>();
            if (act==buttonDelete){
                dialog.setTitle("Delete");
                dialog.setContentText("Confirm Delete?");
            }else if (act==buttonConfirmAdd){
                dialog.setTitle("Add");
                dialog.setContentText("Confirm Add?");
            }else{
                dialog.setTitle("Update");
                dialog.setContentText("Confirm Edit?");
            }
            dialog.getDialogPane().getButtonTypes().add(new ButtonType("Yes", ButtonBar.ButtonData.YES));
            dialog.getDialogPane().getButtonTypes().add(new ButtonType("No", ButtonBar.ButtonData.NO));
            dialog.showAndWait();
        }
        else if(act==buttonCancelAdd || act==buttonCancelEdit) screenPane.setRight(mainPane);
    }


}

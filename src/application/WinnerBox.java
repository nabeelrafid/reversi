package application;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class WinnerBox {

    //Create variable

    public static void display(Stage gameWindow, Object object, Object object2, Object object3) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Winner");
        window.setMinWidth(250);
        Label winnerLabel = new Label();
        Label whiteLabel = new Label();
        Label blackLabel = new Label();
        
        winnerLabel.setText("Winner is: " + object);
        whiteLabel.setText("White: " + object2);
        blackLabel.setText("Black: " + object3);

        //Create okay button
        Button okayButton = new Button("Okay");


        //Clicking will set answer and close window
        okayButton.setOnAction(e -> {
            gameWindow.close();
            window.close();
        });
   

        VBox layout = new VBox(10);

        //Add buttons
        layout.getChildren().addAll(winnerLabel, whiteLabel, blackLabel, okayButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();



    }

}
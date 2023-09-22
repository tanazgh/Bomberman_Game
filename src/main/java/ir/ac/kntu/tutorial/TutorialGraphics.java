package ir.ac.kntu.tutorial;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class TutorialGraphics {
    private Stage stage;
    private Scene primaryScene;

    public TutorialGraphics(Stage stage, Scene primaryScene) {
        this.primaryScene = primaryScene;
        this.stage = stage;
    }

    public Scene setScene() {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 800, 600, Color.rgb(240, 240, 240));
        File file = new File("Tutorial.jpg");
        Image image = new Image(file.toURI().toString());
        Button button = setButton("Back", 600, 535);
        backButtonHandler(button);
        pane.getChildren().addAll(new ImageView(image), button);
        return scene;
    }

    private Button setButton(String label, int x, int y) {
        Button button = new Button(label);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setMinSize(200, 50);
        button.setStyle("-fx-background-color: rgba(1,255,255,100);" +
                "-fx-font-size: 20px;" + "-fx-width: 60px;" +
                "-fx-margin-left: 50px;" + "-fx-border-radius: 20px ;" +
                "-fx-effect: dropshadow(two-pass-box, rgba(0,0,0,0.8), 10, 0, 10, 10);" +
                "-fx-font-family: 'sans-serif' ;");
        button.setCursor(Cursor.cursor("HAND"));
        return button;
    }

    private void backButtonHandler(Button button) {
        button.setOnAction(event -> {
            stage.setScene(primaryScene);
            stage.show();
        });
    }
}

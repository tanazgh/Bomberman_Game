package ir.ac.kntu.menu;

import ir.ac.kntu.GameLoop;
import ir.ac.kntu.scenes.MainMap;
import ir.ac.kntu.tutorial.TutorialGraphics;
import ir.ac.kntu.utils.ScannerWindowsGenerator;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class MenuGraphics {
    private static Stage stage;
    private static Scene scene;
    private static Pane pane;

    public MenuGraphics(Stage stage, Scene scene, Pane pane) {
        this.stage = stage;
        this.scene = scene;
        this.pane = pane;
    }

    public void setPane() {
        File file = new File("Bomberman.jpg");
        Image image = new Image(file.toURI().toString());
        Button playButton = setButtons("Play", 500, 100);
        Button tutorialButton = setButtons("Tutorial", 500, 200);
        Button exitButton = setButtons("Exit", 500, 300);
        buttonHandler(playButton, "Play");
        buttonHandler(tutorialButton, "Tutorial");
        buttonHandler(exitButton, "Exit");
        pane.getChildren().addAll(new ImageView(image), playButton, tutorialButton, exitButton);
    }

    private Button setButtons(String label, int x, int y) {
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

    private void buttonHandler(Button button, String label) {
        if (label.equals("Play")) {
            button.setOnAction(event -> {
                Scene scene = playerNameScene();
                stage.setScene(scene);
                stage.show();
            });
        }else if (label.equals("Tutorial")) {
            button.setOnAction(event -> {
                TutorialGraphics tutorialGraphics = new TutorialGraphics(stage, scene);
                stage.setScene(tutorialGraphics.setScene());
                stage.show();
            });
        }else if (label.equals("Exit")) {
            button.setOnAction(event -> {
                stage.close();
            });
        }
    }

    private Scene playerNameScene() {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: rgb(70, 200, 255);");
        Scene scene = new Scene(pane, 400, 200);
        Label label = new Label("Enter Player Name: ");
        Label label1 = new Label("Player1:");
        Label label2 = new Label("Player2:");
        label1.setLayoutX(10);
        label1.setLayoutY(30);
        label2.setLayoutX(10);
        label2.setLayoutY(130);
        TextArea textArea1 = new TextArea();
        TextArea textArea2 = new TextArea();
        textAreaStylist(textArea1, 0);
        textAreaStylist(textArea2, 100);
        Button buttonSave = new Button("Save");
        Button buttonLoad = new Button("Load");
        buttonSave.setLayoutX(300);
        buttonSave.setLayoutY(30);
        buttonSave.setStyle("-fx-background-color: rgba(1,255,255,100);" + "-fx-font-size: 20px;" + "-fx-width: 60px;" +
                "-fx-margin-left: 50px;" + "-fx-border-radius: 20px ;" +
                "-fx-effect: dropshadow(two-pass-box,rgba(0,0,0,0.8),10,0,10,10);" + "-fx-font-family: 'sans-serif' ;");
        buttonSave.setOnAction(actionEvent -> {
            setButtonHandler(textArea1, textArea2, "Save");
        });
        buttonLoad.setLayoutX(300);
        buttonLoad.setLayoutY(130);
        buttonLoad.setStyle("-fx-background-color: rgba(1,255,255,100);" + "-fx-font-size: 20px;" + "-fx-width: 60px;" +
                "-fx-margin-left: 50px;" + "-fx-border-radius: 20px ;" +
                "-fx-effect: dropshadow(two-pass-box,rgba(0,0,0,0.8),10,0,10,10);" + "-fx-font-family: 'sans-serif' ;");
        pane.getChildren().addAll(label, textArea1, textArea2, buttonSave, label1, label2, buttonLoad);
        return scene;
    }

    private void setButtonHandler(TextArea textArea1, TextArea textArea2, String label) {
        ScannerWindowsGenerator swg = new ScannerWindowsGenerator();
        swg.scannerWindowsGenerator(label);
        MainMap.getInstance().setupScene();
        MainMap.getInstance().getPlayer1().setName(textArea1.getText());
        MainMap.getInstance().getPlayer2().setName(textArea2.getText());
        Scene s = MainMap.getInstance().getScene();
        s.setFill(Color.rgb(200, 255, 255));
        GameLoop.getInstance().setStage(stage);
        stage.setScene(s);
        stage.show();
    }

    private void textAreaStylist(TextArea textArea, int x) {
        textArea.setPrefRowCount(1);
        textArea.setPrefColumnCount(10);
        textArea.setLayoutX(100);
        textArea.setLayoutY(30+x);
    }

}

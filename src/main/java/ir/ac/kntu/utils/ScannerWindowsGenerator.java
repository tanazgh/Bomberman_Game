package ir.ac.kntu.utils;

import ir.ac.kntu.scenes.MainMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ScannerWindowsGenerator extends Application {
    private String labelName;

    public void scannerWindowsGenerator(String labelName) {
        this.labelName = labelName;
        Stage stage = new Stage();
        try {
            start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: rgb(150,255,255);");
        Scene scene = new Scene(pane, 400, 100);
        Label label = new Label("Enter File Name: ");
        TextArea textArea = new TextArea();
        textArea.setPrefRowCount(1);
        textArea.setPrefColumnCount(10);
        textArea.setLayoutX(100);
        textArea.setLayoutY(20);
        Button button = new Button("Choose");
        button.setLayoutX(300);
        button.setLayoutY(70);
        button.setOnAction(actionEvent -> {
            if (labelName.equals("Load")) {
                MainMap.getInstance().getPlayer1().setPath(textArea.getText()+"1");
                MainMap.getInstance().getPlayer2().setPath(textArea.getText()+"2");
                MainMap.getInstance().getPlayer1().read();
                MainMap.getInstance().getPlayer2().read();
            }else if (labelName.equals("Save")){
                MainMap.getInstance().getPlayer1().setPath(textArea.getText()+"1"+".txt");
                MainMap.getInstance().getPlayer2().setPath(textArea.getText()+"2"+".txt");
                MainMap.getInstance().getPlayer1().write();
                MainMap.getInstance().getPlayer2().write();
            }
            stage.close();
        });
        pane.getChildren().addAll(label, textArea, button);
        stage.setTitle("File");
        stage.setScene(scene);
        stage.show();
    }
}

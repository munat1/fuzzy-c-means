import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import java.util.Random;
import java.io.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.List;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import javafx.scene.layout.VBox;

public class Buttons{
    Stage stage = null;
    Boolean addOnClick = true;
    Animator animator;
    DataSet d_1 = null;
    DataSet d_2 = null;
    DataSet d_3 = null;

    public Buttons(Stage stage, DataSet d1,DataSet d2,DataSet d3){
    	this.stage=stage;
    	this.d_1 = d1;
      this.d_2 = d2;
      this.d_3 = d3;
    }

    public void setAnimator(Animator animator) {
        this.animator = animator;
    }

    public Boolean addOnClick() {
        return true;
    }

    public void addButton(Pane box, String name, EventHandler<ActionEvent> actionEvent) {
        Button btn = new Button(name);
        btn.setOnAction(actionEvent);
        box.getChildren().add(btn);
    }

    public void addAnimationStepButton(Pane box) {
        Button btn = new Button("Animate");
        btn.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            animator.drawNext();
          }
        });
        box.getChildren().add(btn);
    }

    public void addOnClickToggleButton(Pane box) {
        Button btn = new Button("No new Nodes");
        btn.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            if(addOnClick){
              btn.setText("New Nodes");
              addOnClick = false;
            } else {
              btn.setText("No new Nodes");
              addOnClick = true;
            }
          }
        });
        box.getChildren().add(btn);
    }
    public void addSaveFileButton(Pane box) {
        Button btn = new Button("Save file...");
        btn.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(stage);
            if(file != null){
              saveFile(getTextOutput(), file);
            }
          }
        });
    box.getChildren().add(btn);
    }

    public void saveFile(String content, File file) {
        try {
          FileWriter fileWriter = null;
          fileWriter = new FileWriter(file);
          fileWriter.write(content);
          fileWriter.close();
        } catch (IOException ex) {
        }
    }

    public String getTextOutput() {
        String output = "DatasetStart\r\n";
        for(Vector v : d_1.vList) {
            output += v._vector[0] + "\r\n" + v._vector[1] + "\r\n";
        }
        output += "DatasetEnd";
        return output;
    }
}
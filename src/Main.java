import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.io.*;
import java.util.Hashtable;
import javafx.stage.FileChooser;
import javafx.scene.text.*;
import java.text.DecimalFormat;
import javafx.scene.layout.Pane;
import javafx.scene.control.CheckBox;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.MalformedURLException;
 public class Main extends Application{

	HBox baseLayer = new HBox();
	VBox verticalBox = new VBox();
	HBox horizontalBox = new HBox();
	Pane coordinateSystem = new Pane();
	Pane layersContainer = new Pane();
	Pane pointsLayer = new Pane();
	Pane clusterLayer = new Pane();
	VBox informationBox = new VBox();
	VBox lengthinformationBox = new VBox();
	VBox animationinformationBox = new VBox();
	Scene scene = new Scene(baseLayer, 900, 627);
	DataSet dataset = new DataSet();
	int cluster_size = 3;
  	List<Vector> centers_not_cluster = new ArrayList<Vector>(cluster_size);
	List<Vector> centers = new ArrayList<>();
	double eps=0.0000000000000000000000000000001;
	Buttons buttons;
	int generationCount = 0;
	
    public static void main(String[] args) {
        launch(args);
    }
	public void start(Stage stage) {
		stage.setTitle("Clustering");
		addButtons(stage);
		setupPointsLayer();
		//setupClusterLayer();
		stage.setScene(scene);
		coordinateSystem.getChildren().add(pointsLayer);
		coordinateSystem.getChildren().add(clusterLayer);
		coordinateSystem.getChildren().add(layersContainer);
    	verticalBox.getChildren().add(coordinateSystem);
    	verticalBox.getChildren().add(horizontalBox);
    	baseLayer.getChildren().add(verticalBox);
		informationBox.getChildren().add(lengthinformationBox);
	    informationBox.getChildren().add(animationinformationBox);
	    baseLayer.getChildren().add(informationBox);
		stage.show();
	}

	public void setupPointsLayer(){
		Rectangle r = new Rectangle();
		r.setX(0);
		r.setY(0);
		r.setWidth(800);
		r.setHeight(600);
		r.setFill(Color.color(0,0,0,0));
		r.setOnMousePressed(new EventHandler<MouseEvent>() {
		  public void handle(MouseEvent event) {
		    if(buttons.addOnClick()){
		      addPoint(event.getX(), event.getY(), false);
		    }
		  }
		});
		pointsLayer.getChildren().add(r);
	}
	/*public void setupClusterLayer(){
		Rectangle r = new Rectangle();
		r.setX(0);
		r.setY(0);
		r.setWidth(800);
		r.setHeight(600);
		r.setFill(Color.color(0,0,0,0));
		clusterLayer.getChildren().add(r);
	}*/

	public void addPoint(double x, double y, boolean center) {
		Circle point = new Circle();
		point.setCenterX(x);
		point.setCenterY(y);
		
		if (center) {
			point.setRadius(80.0);
			point.setFill(Color.color(1,0,0,0.2));

		}
		else {point.setRadius(4.0);}

		int position = dataset.vList.size();
		dataset.addVectors(new Vector(x,y));
		point.setOnMouseDragged(new EventHandler<MouseEvent>() {
		  public void handle(MouseEvent event) {
		    double deltaX = Math.abs(point.getCenterX() - event.getSceneX());
		    double deltaY = Math.abs(point.getCenterY() - event.getSceneY());
		    if(deltaX + deltaY > 2){
		      point.setCenterX(event.getSceneX());
		      point.setCenterY(event.getSceneY());
		      dataset.vList.get(position).x = event.getSceneX();
		      dataset.vList.get(position).y = event.getSceneY();
		      //updateClustering();
		    }
		  }
		});
		pointsLayer.getChildren().add(point);
		/*if (center) {
			addClusterPoint(x,y);
		}*/
	}
	public void addClusterPoint(double x, double y) {
		Circle point = new Circle();
		point.setCenterX(x);
		point.setCenterY(y);
		point.setRadius(80.0);
		point.setFill(Color.color(1,0,0,0.2));
		clusterLayer.getChildren().add(point);
	}


  /*public void updateClustering() {
    clusterLayer.getChildren().clear();
    if(dataset.V.size() > 1){
      lengthinformationBox.getChildren().clear();
    }
  }*/



  public void addButtons(Stage stage){
    buttons = new Buttons(stage, dataset);
    buttons.addButton(horizontalBox, "Choose File...", new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
          PointLoader loader = new PointLoader(file);
          pointsLayer.getChildren().clear();
          clusterLayer.getChildren().clear();
          setupPointsLayer();
          dataset.clear();
          List<Vector> newVertices = loader.getVectors();
          for(Vector v : newVertices) {
            addPoint(v.x,v.y, false);
          }
          //dataset.calculateClustering(centers);
          //updateClustering();
        }
      }
    });
    buttons.addSaveFileButton(horizontalBox);
    //buttons.addOnClickToggleButton(horizontalBox);
    //buttons.addAnimationStepButton(horizontalBox);
    buttons.addButton(horizontalBox, "Random Set", new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        if(generationCount < 50){
			generationCount++;
			pointsLayer.getChildren().clear();
			setupPointsLayer();
			dataset.clear();
			Random rand = new Random();

			System.out.println("centers_not_cluster size = "+centers_not_cluster.size());
		while(centers_not_cluster.size()<cluster_size){

          	centers_not_cluster.add(new Vector(250 * rand.nextGaussian()+450,100 * rand.nextGaussian()+300));
          }
          for (int i = 0; i < centers_not_cluster.size(); i++) {
          	addPoint(centers_not_cluster.get(i).x, centers_not_cluster.get(i).y, false);
          }
          for (int j = 0; j < centers_not_cluster.size(); j++) {
	          for(int i = 0; i < 500; i++){
	          		//Random[] r = new Random();
	      			Random r_1 = new Random();
	      			Random r_2 = new Random();
	      			double x_1 = r_1.nextGaussian()*50;
	      			double x_2 = r_2.nextGaussian()*50;
	      			double x = x_1+centers_not_cluster.get(j).x;
	      			double y = x_2+centers_not_cluster.get(j).y;
	              	addPoint(x, y,false);
	            }
	        }
          }
      }
    });

    buttons.addButton(horizontalBox, "Clear", new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        pointsLayer.getChildren().clear();
        lengthinformationBox.getChildren().clear();
        animationinformationBox.getChildren().clear();
        pointsLayer.getChildren().clear();
        clusterLayer.getChildren().clear();
        dataset.clear();
        setupPointsLayer();
      }
    });
    buttons.addButton(horizontalBox, "Find Clustering", new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
      	//initializeCenters();
      	//pointsLayer.getChildren().clear();
        dataset.calculateClustering(eps);
        for (int i = 0; i < dataset.centers.size(); ++i) {
        	System.out.println(dataset.centers.get(i).toString());
        	double x = dataset.centers.get(i).vector[0];
        	double y = dataset.centers.get(i).vector[1];
        	addClusterPoint(x,y);
        }
        //updateClustering();
      }
    });
  }

}

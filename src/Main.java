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
	HBox horizontalBox_1 = new HBox();
	HBox horizontalBox_2 = new HBox();
	Pane coordinateSystem = new Pane();
	Pane layersContainer = new Pane();
	Pane pointsLayer = new Pane();
	Pane clusterLayer = new Pane();
	VBox informationBox = new VBox();
	VBox lengthinformationBox = new VBox();
	VBox animationinformationBox = new VBox();
	Scene scene = new Scene(baseLayer, 900, 627);
	int cluster_size = 3;
	DataSet dataset_1 = new DataSet(cluster_size);
	DataSet dataset_2 = new DataSet(cluster_size);
	DataSet dataset_3 = new DataSet(cluster_size);
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
    	verticalBox.getChildren().add(horizontalBox_1);
    	verticalBox.getChildren().add(horizontalBox_2);
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

		int position = dataset_1.vList.size();
		dataset_1.addVectors(new Vector(x,y));
		dataset_2.addVectors(new Vector(x,y));
		dataset_3.addVectors(new Vector(x,y));
		point.setOnMouseDragged(new EventHandler<MouseEvent>() {
		  public void handle(MouseEvent event) {
		    double deltaX = Math.abs(point.getCenterX() - event.getSceneX());
		    double deltaY = Math.abs(point.getCenterY() - event.getSceneY());
		    if(deltaX + deltaY > 2){
		      point.setCenterX(event.getSceneX());
		      point.setCenterY(event.getSceneY());
		      dataset_1.vList.get(position)._vector[0] = event.getSceneX();
		      dataset_1.vList.get(position)._vector[1] = event.getSceneY();
		      //updateClustering();
		    }
		  }
		});
		pointsLayer.getChildren().add(point);
		/*if (center) {
			addClusterPoint(x,y);
		}*/
	}
	public void addClusterPoint(double x, double y, int algo_number) {
		Circle point = new Circle();
		point.setCenterX(x);
		point.setCenterY(y);
		point.setRadius(80.0);
		if (algo_number == 0) {
			point.setFill(Color.color(1,0,0,0.2));
		}
		else if (algo_number == 1) {
			point.setFill(Color.color(0,1,0,0.2));
		}
		else if (algo_number == 2) {
			point.setFill(Color.color(0,0,1,0.2));
		}
		clusterLayer.getChildren().add(point);
	}


  /*public void updateClustering() {
    clusterLayer.getChildren().clear();
    if(dataset.V.size() > 1){
      lengthinformationBox.getChildren().clear();
    }
  }*/



  public void addButtons(Stage stage){
    buttons = new Buttons(stage, dataset_1, dataset_2, dataset_3);
    buttons.addButton(horizontalBox_1, "Choose File...", new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
          PointLoader loader = new PointLoader(file);
          pointsLayer.getChildren().clear();
          clusterLayer.getChildren().clear();
          setupPointsLayer();
          dataset_1.clear();
          dataset_2.clear();
          dataset_3.clear();
          List<Vector> newVertices = loader.getVectors();
          for(Vector v : newVertices) {
            addPoint(v._vector[0],v._vector[1], false);
          }
          //dataset.calculateClustering(centers);
          //updateClustering();
        }
      }
    });
    buttons.addSaveFileButton(horizontalBox_1);
    //buttons.addOnClickToggleButton(horizontalBox_1);
    //buttons.addAnimationStepButton(horizontalBox_1);
    buttons.addButton(horizontalBox_1, "Random Set", new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        if(generationCount < 50){
			generationCount++;
			pointsLayer.getChildren().clear();
			setupPointsLayer();
			dataset_1.clear();
			dataset_2.clear();
			dataset_3.clear();
			Random rand = new Random();
		while(centers_not_cluster.size()<cluster_size){

          	centers_not_cluster.add(new Vector(250 * rand.nextGaussian()+450,100 * rand.nextGaussian()+300));
          }
          for (int i = 0; i < centers_not_cluster.size(); i++) {
          	addPoint(centers_not_cluster.get(i)._vector[0], centers_not_cluster.get(i)._vector[1], false);
          }
          for (int j = 0; j < centers_not_cluster.size(); j++) {
	          for(int i = 0; i < 500; i++){
	          		//Random[] r = new Random();
	      			Random r_1 = new Random();
	      			Random r_2 = new Random();
	      			double x_1 = r_1.nextGaussian()*50;
	      			double x_2 = r_2.nextGaussian()*50;
	      			double x = x_1+centers_not_cluster.get(j)._vector[0];
	      			double y = x_2+centers_not_cluster.get(j)._vector[1];
	              	addPoint(x, y,false);
	            }
	        }
          }
      }
    });


    buttons.addButton(horizontalBox_1, "fkM data", new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
      	//initializeCenters();
      	clusterLayer.getChildren().clear();
        dataset_1.calculate_Fuzzy_c_means_Clustering(eps, true, 2);
        for (int i = 0; i < dataset_1.centers.size(); ++i) {
        	double x = dataset_1.centers.get(i)._vector[0];
        	double y = dataset_1.centers.get(i)._vector[1];
        	addClusterPoint(x,y,0);
		    //lengthinformationBox.getChildren().add(algoName);
		    //lengthinformationBox.getChildren().add(euclidRatio);
        }
        Text zielfunktion_fuzzy = new Text("Fuzzy-c-Means: Zielfunktion of Fuzzy-c-Means Clustering (centers sampled from dataset): " + dataset_1._zielfunktion_fuzzy);
        Text zielfunktion = new Text("Fuzzy-c-Means: Zielfunktion of kMeans Clustering (centers sampled from dataset): " + dataset_2._zielfunktion);

        lengthinformationBox.getChildren().add(zielfunktion_fuzzy);
        lengthinformationBox.getChildren().add(zielfunktion);
      }
    });
    buttons.addButton(horizontalBox_2, "fkM random", new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
      	//initializeCenters();
      	clusterLayer.getChildren().clear();
        dataset_2.calculate_Fuzzy_c_means_Clustering(eps, false, 2);
        for (int i = 0; i < dataset_2.centers.size(); ++i) {
        	double x = dataset_2.centers.get(i)._vector[0];
        	double y = dataset_2.centers.get(i)._vector[1];
        	addClusterPoint(x,y,1);
		    
		    //lengthinformationBox.getChildren().add(euclidRatio);
        }
        Text zielfunktion_fuzzy = new Text("Fuzzy-c-Means: Zielfunktion of Fuzzy-c-Means Clustering (centers sampled randomly): " + dataset_2._zielfunktion_fuzzy);
        Text zielfunktion = new Text("Fuzzy-c-Means: Zielfunktion of kMeans Clustering (centers sampled randomly): " + dataset_2._zielfunktion);

        lengthinformationBox.getChildren().add(zielfunktion_fuzzy);
        lengthinformationBox.getChildren().add(zielfunktion);
      }
    });
    buttons.addButton(horizontalBox_2, "kM from data", new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
      	//initializeCenters();
      	//clusterLayer.getChildren().clear();
        dataset_3.build_kmeans_Clusters(true);
        for (int i = 0; i < dataset_3.centers.size(); ++i) {
        	double x = dataset_3.centers.get(i)._vector[0];
        	double y = dataset_3.centers.get(i)._vector[1];
        	addClusterPoint(x,y,2);

        }
		Text zielfunktion = new Text("kMeans: Zielfunktion of kMeans Clustering (centers sampled randomly): " + dataset_2._zielfunktion);

        lengthinformationBox.getChildren().add(zielfunktion);
        //updateClustering();
      }
    });
    buttons.addButton(horizontalBox_2, "Clear", new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        pointsLayer.getChildren().clear();
        lengthinformationBox.getChildren().clear();
        animationinformationBox.getChildren().clear();
        pointsLayer.getChildren().clear();
        clusterLayer.getChildren().clear();
        dataset_1.clear();
        dataset_2.clear();
        dataset_3.clear();
        setupPointsLayer();
      }
    });
  }

}

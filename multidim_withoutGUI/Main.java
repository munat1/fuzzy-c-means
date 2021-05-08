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

public class Main{
	//List<Vector> randomized_centers;
	List<Vector> centers = new ArrayList<>();
	/*int cluster_size;
	int dimension;*/
	//DataSet dataset;

	public static void main(String[] args) {
		int cluster_size= Integer.parseInt(args[0]);
		double eps=0.0000000000000000000000000000001;
		int dimension = Integer.parseInt(args[1]);
		DataSet dataset = new DataSet(cluster_size, dimension);
		dataset.generateRandomPoints();
		/*for (int i = 0; i<dataset.vList.size(); i++){
			System.out.println(dataset.vList.get(i).toString());
		}*/
		dataset.build_kmeans_Clusters(true);

		System.out.println("ground truth centers:");
		for (int i = 0; i<dataset.ground_truth.size(); i++){
			System.out.println(dataset.ground_truth.get(i).toString());
		}
		System.out.println("centers from kmeans:");
		for (int i = 0; i<dataset.centers.size(); i++){
			System.out.println(dataset.centers.get(i).toString());
		}
		dataset.calculate_Fuzzy_c_means_Clustering(eps, true, 2.0);

		System.out.println("centers from fuzzy-kmeans:");
		for (int i = 0; i<dataset.centers.size(); i++){
			System.out.println(dataset.centers.get(i).toString());
		}
	}	

	/*public DataSet generateRandomPoints(int clustersize, int dim){
		List<Vector> randomized_centers = new ArrayList<Vector>(clustersize);
		DataSet dataset = new DataSet(clustersize, dim);

			pointsLayer.getChildren().clear();
			Random rand = new Random();
			while(randomized_centers.size()<clustersize){
				double[] data = new double[dim];
				for (int j = 0; j < dim; j++){
					data[j] = 250*rand.nextGaussian()+300;	
				}
				randomized_centers.add(new Vector(data));
			}
			for (int i = 0; i < randomized_centers.size(); i++) {
				Vector tmp = new Vector(randomized_centers.get(i)._vector);
				dataset.addVectors(tmp);
			}
			for (int j = 0; j < randomized_centers.size(); j++) {
				for(int i = 0; i < 500; i++){
					double[] data = new double[dim];
					for (int k = 0; k < dim; k++){
					//Random[] r = new Random();
						Random r = new Random();
						if (i%2==0) data[i] = randomized_centers.get(j)._vector[i]+r.nextGaussian()*100;
						else data[i] = randomized_centers.get(j)._vector[i]-r.nextGaussian()*100;
					}
					dataset.addVectors(new Vector(data));
				}
			}
			System.out.println("debug");
			return dataset;
	}*/
}

import java.util.Hashtable;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;
import java.lang.Math;
import java.util.*;
public class DataSet{
	public int _dimension;
	public int _center_amount;
	public List<Vector> vList = new ArrayList<>();
	public HashSet<Vector> V = new HashSet<>();
	public List<Vector> centers;
	public double _zielfunktion_fuzzy;
	public double _zielfunktion;
	public List<Vector> ground_truth;
	public int iteration_count;
	public Matrix hardcluster_matrix;


	public DataSet(int center_amount, int dimension){
		this._center_amount = center_amount;
		this._dimension = dimension;
		this.centers = new ArrayList<>(center_amount);
		this.ground_truth= new ArrayList<>(center_amount);
		this.iteration_count = 0;

	}

	public DataSet(List<Vector> vectors, int dimension, int center_amount, List<Vector> intitial_centers){
		this._center_amount = center_amount;
		this._dimension = dimension;
		this.centers = intitial_centers;
		this.vList = vectors;
		this.iteration_count = 0;
		this.hardcluster_matrix=new Matrix(dimension,vectors.size());
	}

	public DataSet(List<Vector> vectors, int dimension, int center_amount){
		this._center_amount = center_amount;
		this._dimension = dimension;
		this.centers = new ArrayList<>(center_amount);
		this.vList = vectors;
		this.iteration_count = 0;
		this.hardcluster_matrix=new Matrix(dimension,vectors.size());
	}

	public DataSet(DataSet d){
		this._center_amount = d._center_amount;
		this._dimension = d._dimension;
		this.centers = d.centers;
		this.vList = d.vList;
		this.V = d.V;
		this.iteration_count = 0;
		this.hardcluster_matrix=new Matrix(d.hardcluster_matrix);
	}

	public void addVectors(Vector v) {
		V.add(v);
		vList.add(v);
	}
	public void addVectors_via_array(double[] v){
		Vector _v = new Vector(v);	
		V.add(_v);
		vList.add(_v);
	}

	public void clear() {
		vList.clear();
		V.clear();
		//centers.clear();
	}


	public String toString() {
		String output="";
		for (Vector v : vList){
			output+=v.toString()+";";
		}
		return output;
	}


	public Vector scalarMultiplication(double a, Vector x){
		for (int i=0;i<x._vector.length;i++) {
			x._vector[i]*=a;
		}
		return x;
	}

	/*---------------------- Fuzzy c-means Methods ---------------------------------------------------------------------------------------*/

	public double eucDistance_squared(Vector a, Vector b){
		double distance = 0;
		try{
			for (int i=0;i<a._vector.length;i++) {
				distance+=Math.pow(a._vector[i]-b._vector[i],2);
			}
			return distance;
		}
		catch(Exception e){
			return 0;
		}
	}

	public double eucDistance(Vector a, Vector b){
		double distance = 0;
		try{
			for (int i=0;i<a._vector.length;i++) {
				distance+=Math.pow(a._vector[i]-b._vector[i],2);
			}
			return Math.sqrt(distance);
		}
		catch(Exception e){
			return 0;
		}
	}


	public Matrix _calculate_belongingMatrix(double m){
		double[][] matr = new double[vList.size()][centers.size()];
		double tmp=0;
        Matrix a = new Matrix(matr);
		double pot = (double) -2/(m-1);
		for (int i=0;i<vList.size();i++) {

			for (int j=0;j<centers.size();j++) {
				if (!centers.get(j)._compare(vList.get(i)) && centers.contains(vList.get(i))) tmp = 0;

				else if (centers.get(j)._compare(vList.get(i))) tmp = (float) 1/centers.size();

				else {
					double tmp_1 = 0;

					for (int k=0;k<centers.size();k++) {
						tmp_1 += Math.pow(eucDistance_squared(vList.get(i),centers.get(k)), pot);
					}
					tmp = Math.pow(eucDistance_squared(vList.get(i),centers.get(j)), pot)/tmp_1;
				}
				a.data[i][j] = tmp;
				tmp = 0;
			}
		}
		return a;
	}


	public void _updateCenters(Matrix belonging, double m){


		for (int i=0;i<centers.size();i++) {
			double divider = 0;
			Vector divided = new Vector(_dimension,0.0);
			for (int j=0;j<belonging.N;j++) {
				Vector tmp = new Vector(vList.get(j)._vector);
				tmp.scalar(Math.pow(belonging.data[j][i],m));
				divided.add(tmp);
				divider+=Math.pow(belonging.data[j][i],m);
			}
			divided.scalar(Math.pow(divider,-1));
			centers.set(i,divided);
		}
	}


	public void _generateRandomCenters_from_the_dataset(int size){
		while (centers.size()!= size){
			Random r = new Random();
			int x = r.nextInt(vList.size());
			if (!centers.contains(vList.get(x))) centers.add(vList.get(x));
		}
	}


	public void _generateRandomCenters_randomly(int size){
		while (centers.size()!= size){
			Random rand = new Random();
			double[] tmp= new double[_dimension] ;
			for (int i = 0; i < _dimension; i++){
				tmp[i] = 250 * rand.nextGaussian()+300;
			}
			centers.add(new Vector(tmp));
		}
	}

	public void calculate_Fuzzy_c_means_Clustering(double eps, List<Vector> init_centers, double m){
        iteration_count = 0;
        centers=init_centers;
		/*centers.clear();
		if (dataset) {
			_generateRandomCenters_from_the_dataset(_center_amount);
		}
		else _generateRandomCenters_randomly(_center_amount);*/
		if (m==1.0){
			build_kmeans_Clusters(eps, init_centers);
		}
		else{
			Matrix tmp_1 = new Matrix(_calculate_belongingMatrix(m));
			_updateCenters(tmp_1, m);
			//Matrix tmp_2 = new Matrix(_calculate_belongingMatrix(m));
			//Matrix tmp_3 = new Matrix(tmp_2);
            double n=Double.MAX_VALUE;
			while(n>eps){// && iteration_count <10 ){
                _updateCenters(tmp_1,m);
                Matrix tmp_2 = new Matrix(_calculate_belongingMatrix(m));
                Matrix tmp_3 = new Matrix(tmp_2);
                tmp_2._minus(tmp_1);
                n=tmp_2.norm();
                tmp_1.override(tmp_3);
                iteration_count++;
                if (iteration_count==100001) {
                    System.out.println("iteration count = 10000, stuck in a local minima");
                    break;}
			}
			calculate_zielfunktion_for_Fuzzy_c_means(tmp_1, m);
		}
	}

	public void calculate_zielfunktion_for_Fuzzy_c_means(Matrix belong, double m){
		for (int i = 0; i < belong.N; i++) {
			for (int j = 0; j < belong.M; j++) {
				_zielfunktion_fuzzy += Math.pow(belong.data[i][j], m) * eucDistance_squared(vList.get(i),centers.get(j));
			}
		}
	}

	/*public void sort_centers(){
		ArrayList<Vector> tmp = new ArrayList<>();
		for (int i=0;i<centers.size();i++){
			for (int j=0; j<centers.size();j++){

			}
		}
	}*/



	/*------------------------------------------------------------------------------------------------------------------------------*/


	/*---------------------- k-means Methods ---------------------------------------------------------------------------------------*/


	public void build_kmeans_Clusters(double eps, List<Vector> init_centers){
		//create _center_amount clusters
		//centers.clear();
		Matrix k_means_last = new Matrix(_center_amount,vList.size());
		/*if (dataset) {
			_generateRandomCenters_from_the_dataset(_center_amount);
		}
		else _generateRandomCenters_randomly(_center_amount);*/
		centers=init_centers;
		iteration_count = 0;
		double n = Double.MAX_VALUE;
		//while (iteration_count<10000){
		Matrix k_means = new Matrix(_center_amount,vList.size());
		Matrix k_means_old = new Matrix(_center_amount,vList.size());
		while(n>eps){
			//Matrix k_means = new Matrix(_center_amount,vList.size());
			for (int i = 0; i<vList.size(); i++) {
				double dist = Double.MAX_VALUE;
				int indexOf_assignedCenter = 0;
				for (int j = 0; j<centers.size(); j++){
					double d = eucDistance(vList.get(i),centers.get(j));
					if (dist > d){
						dist = d;
						indexOf_assignedCenter = j;
					}
				}
				k_means.set_value(i,indexOf_assignedCenter, 1);
			}

			k_means_old._minus(k_means);
			n=k_means_old.norm();

			for (int j = 0; j<k_means.M; j++) {
				double[] data = new double[_dimension];
				for (int k = 0; k<_dimension; k++){
					data[k] = 0;
				}
				Vector new_center = new Vector(data);
				int size_of_cluster = 0;
				for (int i = 0; i<k_means.N; i++) {
					if (k_means.data[i][j]==1) {
						size_of_cluster++;
						new_center.add(vList.get(i));
					}
				}
				for (int i = 0; i<_dimension; i++){
					centers.get(j)._vector[i] = new_center._vector[i]/size_of_cluster;
				}
			}
			k_means_old = new Matrix(k_means);
			iteration_count++;
		}
	//sort_centers();
	}

	public void hardclustering(){
		this.hardcluster_matrix = new Matrix(centers.size(), vList.size());
		for (int i=0; i<vList.size();i++){
			int index_of_cluster=0;
			double dist = Double.MAX_VALUE;
			for (int j=0; j<centers.size(); j++) {
				double tmp = eucDistance(vList.get(i),centers.get(j));
				if(dist>tmp){
					dist=tmp;
					index_of_cluster=j;
				}
			}
			this.hardcluster_matrix.set_value(i,index_of_cluster,1.0);
		}
	}

	public void calculate_zielfunktion_for_kmeans(Matrix belong){
		for (int i = 0; i < belong.N; i++) {
			for (int j = 0; j < belong.M; j++) {
				_zielfunktion+=belong.data[i][j]*eucDistance_squared(vList.get(i),centers.get(j));
			}
		}
	}
	public void generateRandomPoints(){
		List<Vector> randomized_centers = new ArrayList<Vector>(_center_amount);

			Random rand = new Random();
			while(randomized_centers.size()<_center_amount){
				double[] data = new double[_dimension];
				for (int j = 0; j < _dimension; j++){
					data[j] = 2000*rand.nextGaussian()+1000;	
				}
				randomized_centers.add(new Vector(data));
				ground_truth.add(new Vector(data));
				this.vList.add(new Vector(data));
			}
			for (int j = 0; j < randomized_centers.size(); j++) {
				for(int i = 0; i < 501; i++){
					double[] data = new double[_dimension];
					for (int k = 0; k < _dimension; k++){
					//Random[] r = new Random();
						Random r = new Random();
						if (i%2==0) data[k] = randomized_centers.get(j)._vector[k]+r.nextGaussian()*1000;
						else data[k] = randomized_centers.get(j)._vector[k]-r.nextGaussian()*1000;
					}
					this.vList.add(new Vector(data));
				}
			}
	}
}

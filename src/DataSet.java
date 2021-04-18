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
	int dimension;
	public List<Vector> vList = new ArrayList<>();
	public HashSet<Vector> V = new HashSet<>();
	public List<Vector> centers = new ArrayList<>(3);

	public DataSet(){

	}
    public void addVectors(Vector v) {
        V.add(v);
        vList.add(v);
    }

    public void clear() {
        vList.clear();
        V.clear();
        centers.clear();
    }

    public String toString() {
        return "Vertices[" + V + "]";
    }
    public double eucDistance_squared(Vector a, Vector b){
    	double distance = 0;
    	try{
    	for (int i=0;i<a.vector.length;++i) {
    		distance+=Math.pow(a.vector[i]-b.vector[i],2);
    	}
    	return distance;
    	}
    	catch(Exception e){
    		////System.out.println("Vectors don't have the same dimension.");
    		return 0;
    	}
    }
    public Vector scalarMultiplication(double a, Vector x){
    	for (int i=0;i<x.vector.length;++i) {
    		x.vector[i]*=a;
    	}
    	return x;
    }
    public double[][] calculate_belongingMatrix(){//List<Vector> centers){
    	////System.out.println("DEBUG---------------------calculate_belongingMatrix begin");
    	double[][] matr = new double[vList.size()][centers.size()];;
    	double tmp=0;
    	for (int i=0;i<vList.size();++i) {

    		for (int j=0;j<centers.size();++j) {
    			if (!centers.get(j).compare(vList.get(i)) && centers.contains(vList.get(i))) tmp = 0;
    			
    			else if (centers.get(j).compare(vList.get(i))) tmp = (float) 1/centers.size();
    			
    			else {
    				double tmp_1 = 0;
    				
    				for (int k=0;k<centers.size();k++) {
    					tmp_1 += Math.pow(eucDistance_squared(vList.get(i),centers.get(k)),-1);
    				}
    				tmp = Math.pow(eucDistance_squared(vList.get(i),centers.get(j)),-1)/tmp_1;
    			}
    			matr[i][j] = tmp;
    			tmp = 0;
    		}
    	}
    	return matr;
    }
    public void updateCenters(Matrix belonging){

    	
    	for (int i=0;i<centers.size();++i) {
	    	double divider = 0;
    		Vector divided = new Vector(0,0);
	    	for (int j=0;j<belonging.N;++j) {
	    		Vector tmp = new Vector(vList.get(j).x,vList.get(j).y);
	    		tmp.scalar(Math.pow(belonging.data[j][i],2));
	    		divided.add(tmp);
	    		divider+=Math.pow(belonging.data[j][i],2);
	    	}
	    	divided.scalar(Math.pow(divider,-1));
	    	centers.set(i,divided);
    	}
    }
    public void generateRandomCenters(int size){
		while (centers.size()!= size){
			Random r = new Random();
			int x = r.nextInt(vList.size());
			if (!centers.contains(vList.get(x))) centers.add(vList.get(x));
		}
    }
    public void calculateClustering(double eps){
    	
    	for (Vector v : vList) {
    	}
		generateRandomCenters(3);
		System.out.println("----------------init----------------");
		for (int i = 0; i<centers.size() ; ++i) {
			System.out.println(i + "-th center = "+centers.get(i).toString());
		}
    	System.out.println("DEBUG---------------------calculateClustering begin");
    	Matrix tmp_1 = new Matrix(calculate_belongingMatrix());
    	tmp_1.printMatrix();
    	updateCenters(tmp_1);
    	Matrix tmp_2 = new Matrix(calculate_belongingMatrix());
    	//tmp_2.printMatrix();
    	int iteration = 1;
    	Matrix tmp_3 = new Matrix(tmp_2.data);
    	while(tmp_2.minus(tmp_1).norm()>eps && iteration < 1000){
    		//System.out.println("DEBUG---------------------calculateClustering if abfrage begin");
    		System.out.println("----------------"+iteration + "-th iteration----------------");
			/*for (int i = 0; i<centers.size() ; ++i) {
				System.out.println(i + "-th center = "+centers.get(i).toString());
				System.out.println(i + "-th centers x = "+centers.get(i).get_X());
				System.out.println(i + "-th centers y = "+centers.get(i).get_Y());
			}*/
    		tmp_1 = new Matrix(tmp_3.data);
    		updateCenters(tmp_3);
    		tmp_2 = new Matrix(calculate_belongingMatrix());
    		tmp_3 = new Matrix(tmp_2.data);
    		++iteration;
    	}
    	//return centers;

    }
}
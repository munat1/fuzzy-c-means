import java.util.Hashtable;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;
import java.lang.Math;
public class Matrix{
	public static int N; //number of rows. --> in our fuzzy c-means, it means number of points in the dataset
	public static int M; //number of columns --> in our fuzzy c-means, it means number of clusters or centers
	public double[][] data;

    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[N][M];
        for (int i = 0; i<N; i++) {
            for (int j = 0; j<M; j++) {
                data[i][j] = 0;
            }
        }
    }

    public Matrix(Matrix A){
        this.M=A.M;
        this.N=A.N;
        this.data=new double[N][M];
        for (int i=0;i<A.N;i++){
            for (int j=0;j<A.M;j++){
                this.data[i][j]=A.data[i][j]; 
            }
        } 
    }
	public Matrix(double[][] data) {
    	N = data.length;
    	M = data[0].length;
    	this.data = new double[N][M];
    	for (int i = 0; i < N; i++)
    	    for (int j = 0; j < M; j++)
    	            this.data[i][j] = data[i][j];
	}

    public void set_value(int i, int j, double value){
        this.data[i][j] = value;
    }
    public void override(Matrix A){
        for (int i=0;i<A.N;i++){
            for (int j=0;j<A.M;j++){
                this.data[i][j]=A.data[i][j]; 
            }
        } 
    }
    public void _minus(Matrix B) {
        if (B.M != M || B.N != N) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                data[i][j] -= B.data[i][j];
    }

    public double norm(){
    	double norm = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
            	norm+=Math.pow(data[i][j],2);
        	}
    	}
    	return Math.sqrt(norm);
    }
    public String printMatrix(){
        String out="";
        for (int i = 0; i < N; i++){
            //System.out.println("i="+i);
            out+="("; 
            for (int j = 0; j < M; j++){
                out+= String.valueOf(data[i][j]) + ", ";
                //System.out.println("j="+j);
            }
            out+=")\n";   
        }
        return out;
    }

    public void clearMatrix(){
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                    this.data[i][j] = 0;
            }
        }
    }
}

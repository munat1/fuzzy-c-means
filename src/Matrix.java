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
        for (int i = 0; i<N; ++i) {
            for (int j = 0; j<M; ++j) {
                data[i][j] = 0;
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

    // return C = A - B
    public Matrix _minus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j];
        return C;
    }

    public double norm(){
    	double norm = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
            	norm+=Math.abs(data[i][j]);
        	}
    	}
    	return norm;
    }
    public void printMatrix(){
        System.out.println("U= "); 
        for (int i = 0; i < N; i++){
            System.out.print("("); 
            for (int j = 0; j < M; j++){
                System.out.print(data[i][j] + ", ");
            }
            System.out.println(")");   
        }
    }

    public void clearMatrix(){
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                    this.data[i][j] = 0;
                }
            }
        }
}
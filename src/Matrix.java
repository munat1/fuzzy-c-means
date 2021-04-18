import java.util.Hashtable;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;
import java.lang.Math;
public class Matrix{
	int N; //number of rows. --> in our fuzzy c-means, it means number of points in the dataset
	int M; //number of columns --> in our fuzzy c-means, it means number of clusters or centers
	double[][] data;

    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[N][M];
    }

	public Matrix(double[][] data) {
    	N = data.length;
    	M = data[0].length;
        //System.out.println("N yani satir sayisi = " + N);
        //System.out.println("M yani sütun sayisi = " + M);
    	this.data = new double[N][M];
    	for (int i = 0; i < N; i++)
    	    for (int j = 0; j < M; j++)
    	            this.data[i][j] = data[i][j];
	}

    // return C = A - B
    public Matrix minus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j];
        return C;
    }

    public double norm(){
        //System.out.println("DEBUG---------------------norm begin");
    	double norm = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
            	norm+=Math.abs(data[i][j]);
        	}
    	}
        //System.out.println("DEBUG---------------------norm end");
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
}
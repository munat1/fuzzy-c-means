import java.util.*;
public class Vector{
	public int dimension;
	public double[] vector;
	public double x;
	public double y;
	//constructor
	/*public Vector(int dim){
		this.dimension = dim;
		this.vector = new double[dim];
		for (int i=0; i<dim; ++i) {
			//Random r = new Random();
			//vector[i] = 700 * r.nextDouble();
			vector[i] = 0;
		}
	}*/
	public Vector(double x, double y){
		this.dimension = 2;
		this.vector = new double[2];
		vector[0] = x;
		vector[1] = y;
		this.x = x;
		this.y = y;
	}

	public double get_X(){
		return vector[0];
	}

	public double get_Y(){
		return vector[1];
	}

	public String toString(){
		String vec_str = "(";
		double[] vec = this.vector;
		for (int i = 0; i<vec.length ; ++i) {
			if (i<vec.length-1) vec_str += vec[i] + ", ";
			else vec_str += vec[i];
		}
		vec_str += ")";
		return vec_str;
	}
	public void add(Vector b){
		for (int i=0;i<b.vector.length;++i) {
			vector[i]+=b.vector[i];
		}
	}
	public boolean compare(Vector b){
		for (int i=0;i<b.vector.length;++i) {
			if (vector[i]!=b.vector[i]) return false;
		}
		return true;
	}
	public void scalar(double a){
		for (int i=0;i<vector.length;++i) {
			vector[i]*=a;
		}
	}
}
import java.util.*;
public class Vector{
	public int _dimension;
	public double[] _vector;
	//public double _x;
	//public double _y;
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
		this._dimension = 2;
		this._vector = new double[2];
		this._vector[0] = x;
		this._vector[1] = y;
		//this._x = _vector[0];
		//this._y = _vector[1];
	}

	public double get_X(){
		return _vector[0];
	}

	public double get_Y(){
		return _vector[1];
	}

	public String toString(){
		String vec_str = "(";
		double[] vec = this._vector;
		for (int i = 0; i<vec.length ; ++i) {
			if (i<vec.length-1) vec_str += vec[i] + ", ";
			else vec_str += vec[i];
		}
		vec_str += ")";
		return vec_str;
	}
	public void add(Vector b){
		for (int i=0;i<b._vector.length;++i) {
			_vector[i]+=b._vector[i];
		}
	}
	public boolean _compare(Vector b){
		for (int i=0;i<b._vector.length;++i) {
			if (_vector[i]!=b._vector[i]) return false;
		}
		return true;
	}
	public void scalar(double a){
		for (int i=0;i<_vector.length;++i) {
			_vector[i]*=a;
		}
	}
}
import java.util.*;
public class Vector{
	public int _dimension;
	public double[] _vector;
	
	public Vector(int x, double y){
		this._dimension = x;
		this._vector = new double[x];
		for (int i = 0; i < x; i++){
			_vector[i]=y;
		}
	}

	public Vector(double x, double y){
		this._dimension = 2;
		this._vector = new double[_dimension];
		_vector[0]=x;
		_vector[1]=y;
	}

	public Vector(double[] x){
		this._dimension = x.length;
		this._vector = new double[_dimension];
		for (int i = 0 ; i < _dimension; i++){
			this._vector[i]=x[i];
		}
	}
	public double get_X(){
		return _vector[0];
	}

	public double get_Y(){
		return _vector[1];
	}

	public String toString(){
		String vec_str = "";
		double[] vec = this._vector;
		for (int i = 0; i<vec.length ; i++) {
			if (i<vec.length-1) vec_str += vec[i] + ", ";
			else vec_str += vec[i];
		}
		return vec_str;
	}
	public void add(Vector b){
		for (int i=0;i<_vector.length;i++) {
			_vector[i]+=b._vector[i];
		}
	}
	public boolean _compare(Vector b){
		for (int i=0;i<b._vector.length;i++) {
			if (_vector[i]!=b._vector[i]) return false;
		}
		return true;
	}
	public void scalar(double a){
		for (int i=0;i<_vector.length;i++) {
			_vector[i]*=a;
		}
	}
}

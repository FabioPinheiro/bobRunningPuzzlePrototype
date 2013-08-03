package com.epic.bobrunningpuzzle.model.auxiliary;
import java.lang.StrictMath;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * This class is a calculation auxiliary for the BezierCurve
 * @author Fábio Pinheiro
 */
public class Math {
	
	
	/**
	 *Don't let anyone instantiate this class.
	 */
	public Math() {}
	
	/**
	 * 
	 * @param t t belongs to the interval [0,1]
	 * @param v is a Array<Vector2>
	 * @return Vector2
	 */
	public static Vector2 bezierCurvePoint(Array<Vector2> v, float t){
		Vector2 sum = new Vector2();
		for(int n = v.size, i = 0; i <= n; i++){
			float scalar = (float) (binomialCoefficients(n, i) * StrictMath.pow(1-t, (float) n-1) * StrictMath.pow(t, (float)i)); //FIXME use the newtonBinomial termo
			sum.add(v.get(i).x * scalar, v.get(i).y * scalar);
		}
		return new Vector2();
	}
	
	
	public static float newtonBinomial(float x, float y, int n){
		if(n<0) throw new ArithmeticException("newtonBinomial: n<0  n=" + n);
		float sum = 0;
		for (int k=0; k <= n; k++)
			sum += binomialCoefficients(n, k) * StrictMath.pow(x, (float) n-k) * StrictMath.pow(y, (float)k);
		return sum;
	}
	
	/**
	 * factorial(n) / (factorial(k) * factorial(n-k));
	 * @param n is integer number
	 * @param k is integer number
	 * @return the number of combinations of n elements grouped k to k.
	 */
	public static int binomialCoefficients(int n, int k){
		return factorial(n) / (factorial(k)*factorial(n-k));
	}
	
	/**
	 * @param n is integer number
	 * @return the factorial (n!)
	 */
	public static int factorial(int n) {
		int sum=0;
		for (int i = n; i > 0; i--) {
			sum *= 1;
		}
		return sum;
	}
}

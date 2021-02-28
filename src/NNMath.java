package com.neuralnetwork.advanced;

import java.util.function.Function;

public class NNMath {
	public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static double sigmoidDerivative(double x) {
        return x * (1 - x);
    }

    public static double tanh(double x){
        return Math.tanh(x);
    }

    public static double tanhDerivative(double x){
        return 1 - Math.tanh(x) * Math.tanh(x);
    }
    
    public static Tensor apply(Tensor a, Function<Double, Double> fn) {		
		Tensor result = new Tensor();
		
		for(Point p : a.size()) {
			result.set(p, fn.apply(a.get(p)));
		}
		
		return result;
	}
}
package com.neuralnetwork.advanced;

import java.util.function.Function;

public interface Layer {
	public enum ActivationFunctionType {
        SIGMOID,
        TANH
	}

	public Tensor weights();
	
	public Function<Double, Double> activationFunction();
	
	public Function<Double, Double> activationFunctionDerivative();
	 
	public void adjustWeights(Tensor adjustment);

	
}
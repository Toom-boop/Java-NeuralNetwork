package userfriendly;

import java.util.function.Function;

public interface Layer {
	public enum ActivationFunctionType {
        SIGMOID,
        TANH
	}
	
	public enum InitialWeightType {
        RANDOM
    }

	public double[][] weights();
	
	public Function<Double, Double> activationFunction();
	
	public Function<Double, Double> activationFunctionDerivative();
	 
	public void adjustWeights(double[][] adjustment);

	
}
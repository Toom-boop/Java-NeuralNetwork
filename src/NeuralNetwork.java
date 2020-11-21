package userfriendly;

public class NeuralNetwork {
	private Layer[] layers;
	private double[][][] outputLayers;
	private double learningRate;
	
	public NeuralNetwork(Layer... layers) {
		this(0.1, layers);
	}
	
	public NeuralNetwork(double leariningRate, Layer... layers) {
		if(layers.length == 0) throw new NullPointerException();
		this.layers = layers;
		outputLayers = new double[this.layers.length][0][0];
	}

	public double[][] run(double[][] input) {
		for(int i = 0; i < layers.length; i++) {
			if(i == 0) {
				outputLayers[i] = NNMath.apply(NNMath.matrixMultiply(input, layers[i].weights()), layers[i].activationFunction());
			}else {
				outputLayers[i] = NNMath.apply(NNMath.matrixMultiply(outputLayers[i-1], layers[i].weights()), layers[i].activationFunction());
			}
		}
		return outputLayers[outputLayers.length - 1];
	}
	
	public void train(double[][] inputs, double[][] outputs, int iterations) {
		for(int l = 0; l < iterations; l++) {
			run(inputs);
			double[][][] deltaLayers = new double[layers.length][0][0];
			for(int i = layers.length; i > 0; i--) {
				if(i == layers.length) {
		            deltaLayers[i] = NNMath.scalarMultiply(NNMath.matrixSubtract(outputs, outputLayers[i]), NNMath.apply(outputLayers[i], layers[i].activationFunctionDerivative()));
				}else {
		            deltaLayers[i] = NNMath.scalarMultiply(NNMath.matrixMultiply(deltaLayers[i+1], NNMath.matrixTranspose(layers[i+1].weights())), NNMath.apply(outputLayers[i], layers[i].activationFunctionDerivative()));
				}
				if(i == 0) {
					layers[i].adjustWeights(NNMath.apply(NNMath.matrixMultiply(NNMath.matrixTranspose(inputs), deltaLayers[i]), (x) -> learningRate * x));
				}else {
					layers[i].adjustWeights(NNMath.apply(NNMath.matrixMultiply(NNMath.matrixTranspose(outputLayers[i+1]), deltaLayers[i]), (x) -> learningRate * x));
				}
			}
			if(l % (iterations/10) == 0){
                System.out.println("Iteration " + l + "/" + iterations);
            }
		}
	}
}
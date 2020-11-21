public class NeuralNetwork {
	private final Layer[] layers;
    private double[][][] outputLayers;
    private final double learningRate;

    public NeuralNetwork(NeuronLayer... layers) {
        this(0.1, layers);
    }

    public NeuralNetwork(double learningRate, NeuronLayer... layers) {
        this.layers = layers;
        this.learningRate = learningRate;
        outputLayers = new double[layers.length][0][0];
    }
    
    public double[][] run(double[][] inputs) {
    	for(int i = 0; i<outputLayers.length; i++) {
    		if(i == 0) {
    			outputLayers[i] = NNMath.apply(NNMath.matrixMultiply(inputs, layers[i].weights()), layers[i].activationFunction());
    		}else {
    			outputLayers[i] = NNMath.apply(NNMath.matrixMultiply(outputLayers[i-1], layers[i].weights()), layers[i].activationFunction());
    		}
        }
        return outputLayers[outputLayers.length - 1];
    }

    public void train(double[][] inputs, double[][] outputs, int iterations) {
        for (int i = 0; i < numberOfTrainingIterations; i++) {
            run(inputs);
            
            double[][][] deltaLayers = new double[layers.length][0][0];
            
            for(int l = layers.length - 1; l>0; l--) {          	
            	if(l == layers.length - 1) {
                    deltaLayers[l] = NNMath.scalarMultiply(NNMath.matrixSubtract(outputs, outputLayers[l]), NNMath.apply(outputLayers[l], layers[l].activationFunctionDerivative()));
            	}else {
                    deltaLayers[l] = NNMath.scalarMultiply(NNMath.matrixMultiply(deltaLayers[l+1], NNMath.matrixTranspose(layers[l+1].weights())), NNMath.apply(outputLayers[l], layers[l].activationFunctionDerivative()));
            	}
            	
            	if(l == 0) {
                    layers[l].adjustWeights(NNMath.apply(NNMath.matrixMultiply(NNMath.matrixTranspose(inputs), deltaLayers[l]), (x) -> learningRate * x));
            	}else {
                    layers[l].adjustWeights(NNMath.apply(NNMath.matrixMultiply(NNMath.matrixTranspose(outputLayers[l-1]), deltaLayers[l]), (x) -> learningRate * x));
            	}
            }

            if(i % (iterations/10) == 0){
                System.out.println("Iteration " + i + " / " + iterations);
            }
        }
    }
}

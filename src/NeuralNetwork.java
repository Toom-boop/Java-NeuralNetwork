package neuralnetwork;

public class NeuralNetwork {
	private final Layer[] layers;
    private Tensor[] outputLayers;
    private final double learningRate;
    
    private final boolean logging;

    public NeuralNetwork(Layer... layers) {
        this(0.1, false, layers);
    }
    
    public NeuralNetwork(double learningRate, Layer... layers) {
        this(learningRate, false, layers);
    }

    public NeuralNetwork(double learningRate, boolean enabledLogging, Layer... layers) {
        this.layers = layers;
        this.learningRate = learningRate;
        outputLayers = new Tensor[layers.length];
        
        this.logging = enabledLogging;
    }
    
    public Tensor run(Tensor inputs) {
    	for(int i = 0; i<outputLayers.length; i++) {
    		if(i == 0) {
    			outputLayers[i] = NNMath.apply(inputs.multiply(layers[i].weights()), layers[i].activationFunction());
    		}else {
    			outputLayers[i] = NNMath.apply(outputLayers[i-1].multiply(layers[i].weights()), layers[i].activationFunction());
    		}
        }
        return outputLayers[outputLayers.length - 1];
    }

    public void train(Tensor inputs, Tensor outputs, int iterations) {
        for (int i = 0; i < iterations; i++) {
            run(inputs);
            
            Tensor[] deltaLayers = new Tensor[layers.length];
            
            for(int l = layers.length - 1; l>=0; l--) {
            	if(l == layers.length - 1) {
                    deltaLayers[l] = outputs.subtract(outputLayers[l]).multiply(outputLayers[l]);
            	}else {
                    deltaLayers[l] = deltaLayers[l+1].multiply(layers[l+1].weights()).multiply(outputLayers[l]);
            	}
            	
            	if(l == 0) {
                    layers[l].adjustWeights(NNMath.apply(inputs.transpose().multiply(deltaLayers[l]), (x) -> learningRate * x));
            	}else {
                    layers[l].adjustWeights(NNMath.apply(outputLayers[l-1].transpose().multiply(deltaLayers[l]), (x) -> learningRate * x));
            	}
            }

            if(logging && i % (iterations/10) == 0) {
                System.out.println("Iteration " + i + " of " + iterations);
            }
        }
    }
}
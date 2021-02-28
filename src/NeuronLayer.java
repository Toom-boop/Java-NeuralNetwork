import java.util.function.Function;

public class NeuronLayer implements Layer {
    public Tensor weights;

    public final ActivationFunctionType activationFunctionType;
    public final Function<Double, Double> activationFunction, activationFunctionDerivative;

    public NeuronLayer(String data) {
    	//File: SIGMOID%{{1.0,4.0,4.0,4.0,2.0,2.0},{6.0,9.0,2.0,7.0,2.0,3.0}}
        
        if(data.substring(0, data.indexOf("%")).equals("TANH")) {
        	activationFunctionType = ActivationFunctionType.TANH;
        	activationFunction = NNMath::tanh;
            activationFunctionDerivative = NNMath::tanhDerivative;
        }else {
        	activationFunctionType = ActivationFunctionType.SIGMOID;
        	activationFunction = NNMath::sigmoid;
            activationFunctionDerivative = NNMath::sigmoidDerivative;
        }      
        String[] rows = data.substring(data.indexOf("%") + 3, data.length() - 2).replaceAll("\\{", "").split("},");
        
        Tensor out = new Tensor();
        
        for(int i = 0; i<rows.length; i++) {
        	String[] items = rows[i].split(",");
        	for(int l = 0; l<items.length; l++) {
        		//TODO out.set(i, l, Double.parseDouble(items[l]));
        	}
        }
        this.weights = out;
    }
    
    public NeuronLayer(int inputSize, int outputSize) {
        this(ActivationFunctionType.TANH, inputSize, outputSize);
    }

    public NeuronLayer(ActivationFunctionType activationFunctionType, int inputSize, int outputSize) {
        weights = NeuronLayer.randomize(new Tensor(inputSize, outputSize));

        this.activationFunctionType = activationFunctionType;
        
        if (ActivationFunctionType.TANH == activationFunctionType) {
            activationFunction = NNMath::tanh;
            activationFunctionDerivative = NNMath::tanhDerivative;
        } else {
            activationFunction = NNMath::sigmoid;
            activationFunctionDerivative = NNMath::sigmoidDerivative;
        }
    }
    
    private static Tensor randomize(Tensor a) {
		Tensor result = new Tensor();
		for(Point p : a.size()) {
			result.set(p, Math.random());
		}
		return result;
	}

    @Override
    public void adjustWeights(Tensor adjustment) {
        this.weights = weights.add(adjustment);
    }

	@Override
	public Tensor weights() {
		return weights;
	}

	@Override
	public Function<Double, Double> activationFunction() {
		return activationFunction;
	}

	@Override
	public Function<Double, Double> activationFunctionDerivative() {
		return activationFunctionDerivative;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(activationFunctionType.name());
		builder.append("%");
		builder.append(weights);
		return builder.toString();
	}
}

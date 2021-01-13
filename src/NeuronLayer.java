import java.io.File;
import java.util.function.Function;

import wfe.tools.FileManager;

public class NeuronLayer implements Layer {
	public Tensor weights;

	public final ActivationFunctionType activationFunctionType;
    public final Function<Double, Double> activationFunction, activationFunctionDerivative;

    public NeuronLayer(File file) {
    	//File: SIGMOID%{{1.0,4.0,4.0,4.0,2.0,2.0},{6.0,9.0,2.0,7.0,2.0,3.0}}
    	String content = FileManager.read(file);
        
        if(content.substring(0, content.indexOf("%")).equals("TANH")) {
        	activationFunctionType = ActivationFunctionType.TANH;
        	activationFunction = NNMath::tanh;
            activationFunctionDerivative = NNMath::tanhDerivative;
        }else {
        	activationFunctionType = ActivationFunctionType.SIGMOID;
        	activationFunction = NNMath::sigmoid;
            activationFunctionDerivative = NNMath::sigmoidDerivative;
        }      
        String[] rows = content.substring(content.indexOf("%") + 3, content.length() - 2).replaceAll("\\{", "").split("},");
        
        Tensor out = new Tensor();
        
        for(int i = 0; i<rows.length; i++) {
        	String[] items = rows[i].split(",");
        	for(int l = 0; l<items.length; l++) {
        		out.set(i, l, Double.parseDouble(items[l]));
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
		for(int i = 0; i<a.size().x; i++) {
			for(int l = 0; l<a.size().y; l++) {
				result.set(i, l, Math.random());
			}
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

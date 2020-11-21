import java.util.function.Function;

public class NeuronLayer implements Layer {
	public double[][] weights;

    public final Function<Double, Double> activationFunction, activationFunctionDerivative;

    public NeuronLayer(int numberOfInputsPerNeuron, int numberOfNeurons) {
        this(ActivationFunctionType.SIGMOID, InitialWeightType.RANDOM, numberOfInputsPerNeuron, numberOfNeurons);
    }

    public NeuronLayer(ActivationFunctionType activationFunctionType, int numberOfInputsPerNeuron, int numberOfNeurons) {
        this(activationFunctionType, InitialWeightType.RANDOM, numberOfInputsPerNeuron, numberOfNeurons);
    }

    public NeuronLayer(ActivationFunctionType activationFunctionType, InitialWeightType initialWeightType, int numberOfInputsPerNeuron, int numberOfNeurons) {
        weights = new double[numberOfInputsPerNeuron][numberOfNeurons];

        for (int i = 0; i < numberOfInputsPerNeuron; ++i) {
            for (int j = 0; j < numberOfNeurons; ++j) {
                if (InitialWeightType.RANDOM == initialWeightType) {
                    weights[i][j] = (2 * Math.random()) - 1;
                }
            }
        }

        if (ActivationFunctionType.TANH == activationFunctionType) {
            activationFunction = NNMath::tanh;
            activationFunctionDerivative = NNMath::tanhDerivative;
        } else {
            activationFunction = NNMath::sigmoid;
            activationFunctionDerivative = NNMath::sigmoidDerivative;
        }
    }

    @Override
    public void adjustWeights(double[][] adjustment) {
        this.weights = NNMath.matrixAdd(weights, adjustment);
    }

	@Override
	public double[][] weights() {
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
}

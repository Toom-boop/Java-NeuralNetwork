package neuralnetwork;

import java.util.function.Function;

public class Main {
	public static void main(String[] args) {	
		Tensor inputs = new Tensor();
		inputs.set(0, 0, 1);
		inputs.set(0, 1, 1);
		inputs.set(0, 2, 1);
		
		inputs.set(1, 0, 1);
		inputs.set(1, 1, 1);
		inputs.set(1, 2, 0);
		
		inputs.set(2, 0, 0);
		inputs.set(2, 1, 1);
		inputs.set(2, 2, 1);
		
		inputs.set(3, 0, 0);
		inputs.set(3, 1, 1);
		inputs.set(3, 2, 0);
		
		Tensor outputs = new Tensor();
		outputs.set(0, 0, 1);
		outputs.set(0, 1, 1);
		outputs.set(0, 2, 0);
		outputs.set(0, 3, 0);

		Layer layer1 = new NeuronLayer(3, 2);
		Layer layer2 = new NeuronLayer(2, 1);
		
		NeuralNetwork net = new NeuralNetwork(0.2, layer1, layer2);
		
		net.train(inputs, outputs, 10000);
		
		Tensor input = new Tensor();
		input.set(0, 0, 0);
		input.set(0, 1, 1);
		input.set(0, 2, 0);
		
		System.out.print(String.format("%.5g%n", net.run(input).toDouble()));
		
		Function<Double, Double> activationFunction = NNMath::tanh;
		
		Tensor weights1 = randomize(new Tensor(3, 2));
		Tensor weights2 = randomize(new Tensor(2, 1));
		
		Tensor output1 = NNMath.apply(input.multiply(weights1), activationFunction);
		Tensor output2 = NNMath.apply(output1.multiply(weights2), activationFunction);
		
		for(int i = 0; i<10000; i++) {
			output1 = NNMath.apply(inputs.multiply(weights1), activationFunction);
			output2 = NNMath.apply(output1.multiply(weights2), activationFunction);
			
			Tensor delta2 = outputs.subtract(output2).multiply(output2);
			Tensor delta1 = delta2.multiply(weights2).multiply(output1);
			
			weights1 = weights1.add(NNMath.apply(inputs.transpose().multiply(delta1), (x) -> 0.2 * x));
			weights2 = weights2.add(NNMath.apply(output1.transpose().multiply(delta2), (x) -> 0.2 * x));
		}
		
		output1 = NNMath.apply(input.multiply(weights1), activationFunction);
		output2 = NNMath.apply(output1.multiply(weights2), activationFunction);
		
		System.out.print(String.format("%.5g%n", output2.toDouble()));
	}
	
	public static Tensor randomize(Tensor a) {
		Tensor result = new Tensor();
		for(int i = 0; i<a.size().x; i++) {
			for(int l = 0; l<a.size().y; l++) {
				result.set(i, l, Math.random());
			}
		}
		return result;
	}
}
public class ExampleNN {
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
	}
}

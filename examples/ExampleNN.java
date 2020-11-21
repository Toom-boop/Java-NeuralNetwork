public class ExampleNN {
	public static void main(String[] args) {
		NeuralNetwork net = new NeuralNetwork(new NeuronLayer(3, 3), new NeuronLayer(3, 3), new NeuronLayer(3, 1));
		
		double[][] inputs = new double[][] {{1, 0, 1}, {0, 1, 0}, {1, 1, 1}, {1, 1, 0}, {0, 1, 1}};
		double[][] outputs = new double[][] {{1}, {0}, {1}, {1}, {0}};
		
		net.train(inputs, outputs, 10000);
		
		print(net.run(new double[][] {{1, 0, 1}}));
	}
	
	public static void print(double[][] a) {
		for(int i = 0; i<a.length; i++) {
			for(int l = 0; l<a[0].length; l++) {
				if(a[i][l] <= 0) {
					System.out.print(".");
				}else if(a[i][l] == 1) {
					System.out.print("X");
				}else {
					System.out.print(a[i][l]);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}

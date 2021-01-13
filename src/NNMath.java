import java.util.function.Function;

public class NNMath {
	public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static double sigmoidDerivative(double x) {
        return x * (1 - x);
    }

    public static double tanh(double x){
        return Math.tanh(x);
    }

    public static double tanhDerivative(double x){
        return 1 - Math.tanh(x) * Math.tanh(x);
    }
    
    public static Tensor apply(Tensor a, Function<Double, Double> fn) {		
		Tensor result = new Tensor();
		
		for(int i = 0; i<a.size().x; i++) {
			for(int l = 0; l<a.size().y; l++) {
				result.set(i, l, fn.apply(a.get(i, l)));
			}
		}
		
		return result;
	}
}

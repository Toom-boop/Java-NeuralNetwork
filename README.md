# Java-NeuralNetwork
Simple NeuralNetwork in Java


## Create new NeuralNetwork

```JAVA 
NeuralNetwork net = new NeuralNetwork();
```

### Add Layer

```JAVA 
NeuralNetwork net = new NeuralNetwork(new NeuronLayer(3, 6), ... , new NeuronLayer(6, 1));
```

***
## Training
```JAVA
net.train(inputs, outputs, 10000);
```

***

## Run

```JAVA
Tensor output = net.run(inputs);
```

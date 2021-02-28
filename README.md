# Java NeuralNetwork

Currently not working.
Work in Progress...

# NeuralNetwork
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
Tensor inputs = new Tensor();
Tensor outputs = new Tensor();
net.train(inputs, outputs, 10000);
```

***

## Run

```JAVA
Tensor inputs = new Tensor();
Tensor output = net.run(inputs);
```

***

# Layer

## Create Layer
```JAVA
int inputSize = 3;
int outputSize = 2;

Layer layer = new NeuronLayer(inputSize, outputSize);
```
***
## Layer with different ActivationFunction
```JAVA
//Default: TANH
Layer layer = new NeuronLayer(ActivationFunctionType.SIGMOID, inputSize, outputSize);
```
***
## Save & Load Layer
```JAVA
//save
String data = layer.toString();
```
```JAVA
//load
Layer layer = new NeuronLayer(data);
```
***

# [Tensor](https://github.com/Toom-boop/Java-Tensor)
## Create Tensor
```JAVA
Tensor tensor = new Tensor();
```
***
## Set
```JAVA
//sets 1.2 to position 5,2
tensor.set(5, 2, 1.2);
```
***
## Get
```JAVA
//returns value at position 5, 2
tensor.get(5, 2);
```
***
## Add
```JAVA
Tensor a = new Tensor();
Tensor b = new Tensor();
Tensor c = a.add(b);
```
***
## Substract
```JAVA
Tensor a = new Tensor();
Tensor b = new Tensor();
Tensor c = a.subtract(b);
```
***
## Multiply
```JAVA
Tensor a = new Tensor();
Tensor b = new Tensor();
Tensor c = a.multiply(b);
```
***
## Divide
```JAVA
Tensor a = new Tensor();
Tensor b = new Tensor();
Tensor c = a.divide(b);
```
***
## Transpose
```JAVA
Tensor a = new Tensor();  //{{1.0, 2.0}, {3.0, 4.0}}
Tensor b = a.transpose(); //{{1.0, 3.0}, {2.0, 4.0}}
```

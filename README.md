# Annotation Processor Implementation

## Author
<Maaz Khan, 667601660>

## Design Pattern tested: Abstract Factory Pattern
In the abstract factory pattern, the process of object creation is abstracted. This means that in the client class, there is no code that instantiates a concrete class. An abstract factory is used to create concrete factories. The abstract factory creates instances of abstract products which in turn are used to create concrete products for the concrete factories. The client code calls an instance of the abstract factory to start the process of creation and only specifies the object that it wants returned.

The examples processed by the Annotation Processor are the following:

 - **GUI Factory:** This abstract factory creates an instance of an abstract product (Button) and is used to create either a concrete MacOSFactory or WindowsFactory which create their respective concrete buttons. The client takes in the systems OS as input to create the respective class. This is a basic example found online.
 - **Enemy Ship Factory:** This abstract factory creates concrete enemy ship factories (UFO Grunt and UFO Boss) with two abstract products (Weapon and Engine). The client creates instances of both concrete factories and implements its methods as well as the methods implemented in their concrete products. The client code is broken into abstract classes and concrete classes. This is another example found online.
 - **Animal Factory:** This abstract factory creates two animal factories (Lion and Fox) with two abstract products (Speak and Eats). The client creates instances of both concrete factories and implements the methods found in their concrete products. This is my own written implementation.

## Rules and Constraints of the Annotation Processor
I have defined five annotations:

 - @AbstractFactory
 - @ConcreteFactory
 - @AbstractProduct
 - @ConcreteProduct
 - @Client
 
 The Annotation Processor checks these rules in the following sequence:
 
 - **Rule 1:** *@AbstractFactory* can only annotate an interface
 - **Rule 2:** The abstract factory must create instances of abstract products
 - **Rule 3:** *@AbstractProduct* can only annotate an interface
 - **Rule 4:** *@ConcreteFactory* can only annotate a class
 - **Rule5:** *@ConcreteFactory* must implement an interface annotated with *@AbstractFactory* 
 - **Rule 6:** The concrete factory must override all abstract factory functions to create concrete products
 - **Rule 7:** *@ConcreteProduct* can only annotate a class
 - **Rule 8:** *@ConcreteFactory*  must implement an interface annotated with *@AbstractFactory*
 - **Rule 9:** *@Client* can only annotate a class
 - **Rule 10:** The client class constructor must take an Abstract Factory as a parameter
 - **Rule 11:** The client class must not possess fields which are Concrete Products

## Setup
The project must contain multiple modules. Annotations, the Annotation processor, and the implemented design patterns must exist in separate modules. 

In the gradle of the Annotation processor, we must add the following in the dependencies: `compile project(':annotations')`

In the gradle of the implemented design pattern, we must add the following dependencies: `compile project(':annotations')  
annotationProcessor project(':annotationprocessor')`

Define the fully-qualified name of your annotation processor class in the `src/main/resources/META-INF/services/javax.annotation.processing.Processor` file inside your annotation processor project.

Do `gradlew clean` before  `gradlew build`/`gradlew run` since annotation processor won't process files that have already been compiled.

## Logging and Config
The messager in the annotation processor takes the role of a logger. There is also no need for a config file. 

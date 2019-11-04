package Eating;

import oop2.annotationprocessing.annotations.ConcreteProduct;

@ConcreteProduct
public class FoxEats implements AnimalEats {
    @Override
    public void eats() {
        System.out.println("What does the fox eat?");
    }
}

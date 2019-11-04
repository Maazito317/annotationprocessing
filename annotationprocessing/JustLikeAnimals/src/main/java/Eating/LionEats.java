package Eating;

import oop2.annotationprocessing.annotations.ConcreteProduct;

@ConcreteProduct
public class LionEats implements AnimalEats {
    @Override
    public void eats() {
        System.out.println("I hunt and eat my prey");
    }
}

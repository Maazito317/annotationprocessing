package Speaking;

import Eating.AnimalEats;
import oop2.annotationprocessing.annotations.ConcreteProduct;

@ConcreteProduct
public class FoxSpeaks implements AnimalSpeaks {

    @Override
    public void speak() {
        System.out.println("What does the Fox say?");
    }
}

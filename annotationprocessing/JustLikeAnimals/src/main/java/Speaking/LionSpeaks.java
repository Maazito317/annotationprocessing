package Speaking;

import Eating.AnimalEats;
import oop2.annotationprocessing.annotations.ConcreteProduct;

@ConcreteProduct
public class LionSpeaks implements AnimalSpeaks {

    @Override
        public void speak() {
            System.out.println("Hear Me Roar!");
        }

}

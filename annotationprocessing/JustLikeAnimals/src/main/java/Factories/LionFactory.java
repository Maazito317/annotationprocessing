package Factories;

import Eating.AnimalEats;
import Eating.LionEats;
import Speaking.AnimalSpeaks;
import Speaking.LionSpeaks;
import oop2.annotationprocessing.annotations.ConcreteFactory;

@ConcreteFactory
public class LionFactory implements AnimalFactory {

    @Override
    public AnimalSpeaks createSpeak() {
        return new LionSpeaks();
    }

    @Override
    public AnimalEats createEats() {
        return new LionEats();
    }
}

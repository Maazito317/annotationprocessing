package Factories;

import Eating.AnimalEats;
import Eating.FoxEats;
import Speaking.AnimalSpeaks;
import Speaking.FoxSpeaks;
import oop2.annotationprocessing.annotations.ConcreteFactory;

@ConcreteFactory
public class FoxFactory implements AnimalFactory {

    @Override
    public AnimalSpeaks createSpeak() {
        return new FoxSpeaks();
    }

    @Override
    public AnimalEats createEats() {
        return new FoxEats();
    }
}

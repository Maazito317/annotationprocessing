package Factories;

import Eating.AnimalEats;
import Speaking.AnimalSpeaks;
import oop2.annotationprocessing.annotations.AbstractFactory;

@AbstractFactory
public interface AnimalFactory {
    AnimalSpeaks createSpeak();
    AnimalEats createEats();
}

package oop2.annotationprocessing.example1.factories;

import oop2.annotationprocessing.annotations.ConcreteFactory;
import oop2.annotationprocessing.example1.products.Button;
import oop2.annotationprocessing.example1.products.MacOSButton;

/**
 * Each concrete factory extends basic factory and responsible for creating
 * products of a single variety.
 */
@ConcreteFactory
public class MacOSFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new MacOSButton();
    }

}
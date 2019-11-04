package oop2.annotationprocessing.example1.factories;

import oop2.annotationprocessing.annotations.ConcreteFactory;
import oop2.annotationprocessing.example1.products.Button;
import oop2.annotationprocessing.example1.products.WindowsButton;

/**
 * Each concrete factory extends basic factory and responsible for creating
 * products of a single variety.
 */
@ConcreteFactory
public class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
package oop2.annotationprocessing.example1.factories;

import oop2.annotationprocessing.annotations.AbstractFactory;
import oop2.annotationprocessing.example1.products.Button;

/**
 * Abstract factory knows about all (abstract) product types.
 */
@AbstractFactory
public interface GUIFactory {
    Button createButton();
}
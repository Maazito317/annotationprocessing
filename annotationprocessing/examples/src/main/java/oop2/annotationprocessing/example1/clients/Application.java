package oop2.annotationprocessing.example1.clients;

import oop2.annotationprocessing.annotations.Client;
import oop2.annotationprocessing.example1.factories.GUIFactory;
import oop2.annotationprocessing.example1.products.Button;

@Client(name = "Application")
public class Application {
    private Button button;

    public Application(GUIFactory factory) {
        button = factory.createButton();
    }

    public void paint() {
        button.paint();
    }
}
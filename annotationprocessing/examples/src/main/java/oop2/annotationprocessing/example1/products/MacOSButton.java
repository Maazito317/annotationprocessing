package oop2.annotationprocessing.example1.products;

import oop2.annotationprocessing.annotations.ConcreteProduct;

/**
 * All products families have the same varieties (MacOS/Windows).
 *
 * This is another variant of a button.
 */
@ConcreteProduct
public class MacOSButton implements Button {

    @Override
    public void paint() {
        System.out.println("You have created MacOSButton.");
    }
}
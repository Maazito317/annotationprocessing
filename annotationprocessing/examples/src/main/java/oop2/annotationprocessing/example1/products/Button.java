package oop2.annotationprocessing.example1.products;

import oop2.annotationprocessing.annotations.AbstractProduct;

/**
 * Abstract Factory assumes that you have several families of products,
 * structured into separate class hierarchies (Button/Checkbox). All products of
 * the same family have the common interface.
 *
 * This is the common interface for buttons family.
 */
@AbstractProduct
public interface Button {
    void paint();
}

package Engines;

import oop2.annotationprocessing.annotations.AbstractProduct;

// Any part that implements the interface ESEngine
// can replace that part in any ship
@AbstractProduct
public interface ESEngine{

    // User is forced to implement this method
    // It outputs the string returned when the
    // object is printed

    public String toString();

}

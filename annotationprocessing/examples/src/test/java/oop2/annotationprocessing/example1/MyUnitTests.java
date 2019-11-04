package oop2.annotationprocessing.example1;

import oop2.annotationprocessing.annotations.*;
import oop2.annotationprocessing.example1.clients.Application;
import oop2.annotationprocessing.example1.factories.GUIFactory;
import oop2.annotationprocessing.example1.factories.MacOSFactory;
import oop2.annotationprocessing.example1.products.Button;
import oop2.annotationprocessing.example1.products.MacOSButton;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;

public class MyUnitTests {

    @Test
    public void testingAbstractProduct(){
        Annotation annotation = Button.class.getAnnotation(AbstractProduct.class);
        Assert.assertNotNull(annotation);
    }

    @Test
    public void testingAbstractFactory(){
        Annotation annotation = GUIFactory.class.getAnnotation(AbstractFactory.class);
        Assert.assertNotNull(annotation);
    }
    @Test
    public void testingConcreteProduct(){
        Annotation annotation = MacOSButton.class.getAnnotation(ConcreteProduct.class);
        Assert.assertNotNull(annotation);
    }
    @Test
    public void testingConcreteFactory(){
        Annotation annotation = MacOSFactory.class.getAnnotation(ConcreteFactory.class);
        Assert.assertNotNull(annotation);
    }
    @Test
    public void testingClient(){
        Annotation annotation = Application.class.getAnnotation(Client.class);
        Assert.assertNotNull(annotation);
    }

}

package oop2.annotationprocessing.annotationprocessor;

import oop2.annotationprocessing.annotations.*;
 import javax.annotation.processing.*;
 import javax.lang.model.SourceVersion;
 import javax.lang.model.element.*;
 import javax.lang.model.util.Elements;
 import javax.lang.model.util.Types;
 import javax.tools.Diagnostic;
import java.util.LinkedHashSet;
import java.util.Set;

public class AnnotationProcessor extends AbstractProcessor {
    private Types types;
    private Elements elements;

    @Override
    public void init (ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        types = processingEnv.getTypeUtils();
        elements = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //Messager is essentially used as a logger
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "Annotation Processor Running");
        Set<? extends Element> abstractFactories = roundEnvironment.getElementsAnnotatedWith(AbstractFactory.class);
        Set<? extends Element> abstractProducts = roundEnvironment.getElementsAnnotatedWith(AbstractProduct.class);
        Set<? extends Element> concreteFactories = roundEnvironment.getElementsAnnotatedWith(ConcreteFactory.class);
        Set<? extends Element> concreteProducts = roundEnvironment.getElementsAnnotatedWith(ConcreteProduct.class);

        /**
         * The first rule is to check that Abstract Factory annotation can only be applied to an interface.
         * Furthermore, we also check that Abstract Factory contains methods that contain Abstract Products.
         * The check then passes elements annotated correctly.
         */
        for (Element element : abstractFactories) {
            if (element.getKind() != ElementKind.INTERFACE) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        "AbstractFactory annotation can only be applied to interface: " + element);
            } else if (!createsAbstractProducts(element, abstractProducts)) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        "AbstractFactory interfaces must create AbstractProducts: " + element);
            } else {
                messager.printMessage(Diagnostic.Kind.NOTE, element + " passed AbstractFactory annotation check.");
            }
        }

        /**
         * The second rule checks that Abstract Product annotation is only applied to interfaces
         */
        for (Element element : abstractProducts) {
            if (element.getKind() != ElementKind.INTERFACE) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        "AbstractProduct annotation can only be applied to interface: " + element);
            } else {
                messager.printMessage(Diagnostic.Kind.NOTE, element + " passed AbstractProduct annotation check.");
            }
        }

        /**
         *  The third rule checks that Concrete Factory annotation is applied only to a class
         *  We also check that the Concrete Factory creates only Concrete Products
         *  We further check that it extends an Abstract Factory interface
         *  The check then passes elements annotated correctly.
         */
        for (Element element : concreteFactories) {
            if (element.getKind() != ElementKind.CLASS) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        "ConcreteFactory annotation can only be applied to class: " + element);
            } else if (!extendsAbstractInterface(element, abstractFactories)) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        element + " must extend an AbstractFactory interface");
            } else if (!createsConcreteProducts(element, abstractProducts)) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        "ConcreteFactory classes must create ConcreteProducts: " + element);
            } else {
                messager.printMessage(Diagnostic.Kind.NOTE, element + " passed ConcreteFactory annotation check.");
            }
        }

        /**
         *  The fourth rule checks that Concrete Product annotation is applied only to a class
         *  We further check that it extends an Abstract Product interface.
         *  The check then passes elements annotated correctly.
         */
        for (Element element : concreteProducts) {
            if (element.getKind() != ElementKind.CLASS) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        "ConcreteProduct annotation can only be applied to class: " + element);
            } else if (!extendsAbstractInterface(element, abstractProducts)) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        element + " must extend an AbstractProduct interface");
            } else {
                messager.printMessage(Diagnostic.Kind.NOTE, element + " passed ConcreteProduct annotation check.");
            }
        }

        /**
         * This last rule is for the client application. The client annotation is only applied to classes.
         * We also check that it does not create and concrete products.
         * And it's constructor must take in the Abstract Factory as input to pass the check
         */
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Client.class)) {
            if (element.getKind() != ElementKind.CLASS) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        "Client annotation can only be applied to class: " + element);
            } else if (keepsConcreteProducts(element, concreteProducts, messager)) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        element + " must not contain any ConcreteProducts");
            } else if (!keepsFactoryPointer(element, abstractFactories, messager)) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        element + " must use an AbstractFactory");
            } else {
                messager.printMessage(Diagnostic.Kind.NOTE, element + " passed Client annotation check.");
            }
        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotataions = new LinkedHashSet<String>();
        annotataions.add(Client.class.getCanonicalName());
        annotataions.add(AbstractFactory.class.getCanonicalName());
        annotataions.add(AbstractProduct.class.getCanonicalName());
        annotataions.add(ConcreteFactory.class.getCanonicalName());
        annotataions.add(ConcreteProduct.class.getCanonicalName());
        return annotataions;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * The function checks whether abstract factory contains instances of abstract products
     * @param element: Abstract factory elements
     * @param abstractProducts
     * @return boolean
     */
    boolean createsAbstractProducts(Element element, Set<? extends Element> abstractProducts) {
        for (Element e: element.getEnclosedElements()) {
            if (e.getKind() == ElementKind.METHOD) {
                ExecutableElement f = (ExecutableElement) e;
                boolean returnTypeFoundInAbstractProducts = false;
                for (Element p: abstractProducts) {
                    if (f.getReturnType().equals(p.asType())) {
                        returnTypeFoundInAbstractProducts = true;
                    }
                }
                if (!returnTypeFoundInAbstractProducts) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     *To check if concrete products are instantiated
     * @param element
     * @param abstractProducts
     * @return
     */
    boolean createsConcreteProducts(Element element, Set<? extends Element> abstractProducts) {
        for (Element e: element.getEnclosedElements()) {
            if (e.getKind() == ElementKind.METHOD && e.getAnnotation(Override.class) != null) {
                ExecutableElement f = (ExecutableElement) e;
                boolean returnTypeFoundInAbstractProducts = false;
                for (Element p: abstractProducts) {
                    if (f.getReturnType().equals(p.asType())) {
                        returnTypeFoundInAbstractProducts = true;
                    }
                }
                if (!returnTypeFoundInAbstractProducts) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This function verifies whether the concrete factory implements an interface
     * @param element of concrete factory
     * @param abstractInterfaces
     * @return boolean
     */
    boolean extendsAbstractInterface(Element element, Set<? extends Element> abstractInterfaces) {
        for (Element e: abstractInterfaces) {
            if (types.isSubtype(element.asType(), e.asType())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks override method to see if it returns concrete products in a concrete factory
     * @param element
     * @param concreteProducts
     * @param messager
     * @return
     */
    boolean keepsConcreteProducts(Element element, Set<? extends Element> concreteProducts, Messager messager) {
        for (Element e: element.getEnclosedElements()) {
            if (e.getKind() == ElementKind.FIELD) {
                for (Element p: concreteProducts) {
                    if (e.asType().equals(p.asType())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *To see if client calls an instance of Abstract factory in constructor
     * @param element
     * @param abstractFactories
     * @param messager
     * @return
     */
    boolean keepsFactoryPointer(Element element, Set<? extends Element> abstractFactories, Messager messager) {
        for (Element e: element.getEnclosedElements()) {
            if (e.getKind() == ElementKind.CONSTRUCTOR) {
                ExecutableElement f = (ExecutableElement) e;
                for (Element t: f.getParameters()) {
                    for (Element p: abstractFactories) {
                        if (t.asType().equals(p.asType())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
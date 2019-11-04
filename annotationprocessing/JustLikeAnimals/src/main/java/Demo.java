import Factories.AnimalFactory;
import Factories.FoxFactory;
import Factories.LionFactory;


public class Demo {


    private static Application configureApplication(String animal) {
        Application app;
        AnimalFactory factory;
        String animalName = animal.toLowerCase();
        if (animalName.contains("fox")) {
            factory = new FoxFactory();
            app = new Application(factory);
        } else {
            factory = new LionFactory();
            app = new Application(factory);
        }
        return app;
    }

    public static void main(String[] args) {
        Application app1 = configureApplication("Fox");
        app1.speak();
        app1.eats();
        Application app2 = configureApplication("lion");
        app2.speak();
        app2.eats();
    }
}


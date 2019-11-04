import Eating.AnimalEats;
import Factories.AnimalFactory;
import Speaking.AnimalSpeaks;
import oop2.annotationprocessing.annotations.Client;

@Client(name = "Application")
public class Application {
    private AnimalSpeaks speaking;
    private AnimalEats eating;

    public Application(AnimalFactory factory){
        speaking = factory.createSpeak();
        eating = factory.createEats();
    }

    public void speak() {speaking.speak();}
    public void eats() {eating.eats();}


}

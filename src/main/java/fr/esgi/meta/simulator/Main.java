package fr.esgi.meta.simulator;

import fr.esgi.meta.pattern.Decorator;
import fr.esgi.meta.utils.Parser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World !");

        try {
            new Parser().parse("zombies.xml");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        /* Decorator c# porting

        Decorator dede = new Decorator();
        Decorator.ComposantConcret c =  dede.new ComposantConcret();
        Decorator.DecorateurConcretA d1 = dede.new DecorateurConcretA();
        Decorator. DecorateurConcretB d2 = dede.new DecorateurConcretB();

        d1.SetComposant(c);
        d2.SetComposant(d1);
        d2.Operation();*/

    }


}

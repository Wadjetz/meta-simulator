package fr.esgi.meta.simulator;

import fr.esgi.meta.pattern.Decorator;

public class Main {

    public static void main(String[] args) {

        Decorator dede = new Decorator();

        System.out.println("Hello World !");

        Decorator.ComposantConcret c =  dede.new ComposantConcret();
        Decorator.DecorateurConcretA d1 = dede.new DecorateurConcretA();
        Decorator. DecorateurConcretB d2 = dede.new DecorateurConcretB();

        d1.SetComposant(c);
        d2.SetComposant(d1);
        d2.Operation();
    }


}

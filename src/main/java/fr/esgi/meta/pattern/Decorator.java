package fr.esgi.meta.pattern;

/**
 * Created by 626 on 07/01/2016.
 */
public class Decorator {

    //-----------------------------------------------------------------------------
    abstract class ComposantAbstrait {
        public abstract void Operation();
    }


    //-----------------------------------------------------------------------------
    final public class ComposantConcret extends ComposantAbstrait {

        public ComposantConcret() {
        }

        public void Operation() {
            System.out.println("ComponentConcret.Operation()");
        }
    }


    //-----------------------------------------------------------------------------
    abstract class DecorateurAbstrait extends ComposantAbstrait {
        ComposantAbstrait Composant;

        public void SetComposant(ComposantAbstrait composantImbrique) {
            Composant = composantImbrique;
        }

        public void Operation() {

            if (Composant != null) {
                Composant.Operation();
            }
        }
    }


    //-----------------------------------------------------------------------------
    final public class DecorateurConcretA extends DecorateurAbstrait {

        public void Operation() {
            super.Operation();
            System.out.println("ConcreteDecoratorA.Operation()");
        }
    }


    //-----------------------------------------------------------------------------
    final public class DecorateurConcretB extends DecorateurAbstrait {
        public void Operation() {
            super.Operation();

            System.out.println("ConcreteDecoratorB.Operation()");
        }
    }


}

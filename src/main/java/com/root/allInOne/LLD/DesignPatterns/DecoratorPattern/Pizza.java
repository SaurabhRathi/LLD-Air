package com.root.allInOne.LLD.DesignPatterns.DecoratorPattern;

public abstract class Pizza {
    abstract int cost();
}

class SimplePizza extends Pizza {

    @Override
    int cost() {
        return 50;
    }
}

class MargeritaaPizza extends Pizza {

    @Override
    int cost() {
        return 100;
    }
}

abstract class PizzaDecorator extends Pizza {

}

class ExtraCheese extends PizzaDecorator {
    final Pizza pizza;

    ExtraCheese(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    int cost() {
        return this.pizza.cost() + 20;
    }
}

class ExtraSauce extends PizzaDecorator {
    final Pizza pizza;

    ExtraSauce(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    int cost() {
        return this.pizza.cost() + 50;
    }
}


//decorator has-a Pizza and is-a Pizza also
//it has both the relationships...

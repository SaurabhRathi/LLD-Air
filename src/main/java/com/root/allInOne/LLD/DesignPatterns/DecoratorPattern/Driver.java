package com.root.allInOne.LLD.DesignPatterns.DecoratorPattern;


class VirtualFunctionByDefaultInJavaExample {
    public int getCost(Pizza pizza) {
        return pizza.cost();
    }
}
public class Driver {
    public static void main(String[] args) {
        MargeritaaPizza margeritaaPizza = new MargeritaaPizza();
        System.out.println(margeritaaPizza.cost());
        Pizza withExtraCheese = new ExtraCheese(margeritaaPizza);
        System.out.println(withExtraCheese.cost());
        Pizza withExtraCheeseAndSauce = new ExtraSauce(withExtraCheese);
        System.out.println(withExtraCheeseAndSauce.cost());

        System.out.println(new ExtraCheese(new ExtraSauce( new SimplePizza())).cost());
        VirtualFunctionByDefaultInJavaExample obj = new VirtualFunctionByDefaultInJavaExample();
        System.out.println(obj.getCost(margeritaaPizza));
        //prints the cost of passed subClass even though it doesn't get type-casted


    }
}

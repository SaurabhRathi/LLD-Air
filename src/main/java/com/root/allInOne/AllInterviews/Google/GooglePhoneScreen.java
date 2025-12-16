package com.root.allInOne.AllInterviews.Google;

import java.util.*;
// "static void main" must be defined in a public class.
public class GooglePhoneScreen {
    static CarRentalAssignment cra = new CarRentalAssignment();
    public static void main(String[] args) {
        Map<Integer, List<Rental>> x = cra.assignRentals(Arrays.asList(new Rental(0,2),new Rental(1,3),new Rental(5,6)), true);
        System.out.println(x.size());
        System.out.println(x);
    }
}
/*
Given a list of car rentals from last year with pickup and return times,
determine the number of cars we need to fulfill the demand, and produce a sample
assignment showing which car would have been rented to which booking.

You have full control on how to represent the input and output in your code.
Please choose wisely and write your code as cleanly as possible, as you would
write real production code.

Sample input:
Rental 1: (1, 5)
Rental 2: (2, 8)
Rental 3: (6, 9)
Rental 4: (4, 7)

Sample output:
1: (1, 5), (6, 9)
2: (2, 8)
3: (4, 7)

[][][][][][][]

0--1---4  5---1---8 --B--- -A----
   2---2---7

<7,B>

N -> N*logN
*/

class Rental {
    int start, end;
    Rental(int start, int end) {
        this.start = start; this.end = end;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}

class Car {
    int id;
    int availableAt;
    Car(int id, int availableAt) {
        this.id = id;
        this.availableAt = availableAt;
    }
}

class CarRentalAssignment {
    public Map<Integer, List<Rental>> assignRentals(List<Rental> rentals, boolean secondOne) {
        rentals.sort((a,b)->a.start-b.start);
        PriorityQueue<Car> queue = new PriorityQueue<>((a,b)->a.availableAt-b.availableAt);   //endTIme, id
        Map<Integer, List<Rental>> finalAssignments = new HashMap<>();
        int carCounter = 1;
        for(Rental rental: rentals) {
            if(queue.isEmpty() || queue.peek().availableAt>rental.start) {
                queue.add(new Car(carCounter, rental.end));
                finalAssignments.put(carCounter,  new ArrayList<>(List.of(rental)));
            } else {
                Car car = queue.poll();
                List<Rental> list = finalAssignments.get(car.id);
                list.add(rental); finalAssignments.put(car.id, list);
                car.availableAt = rental.end;
                queue.add(car);
            }
        }
        return finalAssignments;
    }

    //code from AI wrote in the interview
//    public Map<Integer, List<Rental>> assignRentals(List<Rental> rentals) throws IllegalArgumentException, IllegalStateException {
//
//        if(rentals == null) throw new IllegalArgumentException("The parameter rentals is null");
//
//        rentals.sort(Comparator.comparingInt(r-> r.start));
//
//        //minHeap
//        PriorityQueue<Car> queue = new PriorityQueue<Car>(Comparator.comparingInt(c-> c.availableAt));
//        Map<Integer, List<Rental>> assignments = new HashMap<Integer, List<Rental>>();
//        int counter = 0;
//
//        for(Rental rental: rentals) {
//            if(rental == null) throw new IllegalStateException("Rental cannot be null");
//
//            Car assignedCar = null;
//            if(!queue.isEmpty() && queue.peek().availableAt <= rental.start) {
//                assignedCar = queue.poll();
//            } else {
//                assignedCar = new Car(counter++, rental.end);
//                assignments.put(assignedCar.id, assignedCar.bookings);
//            }
//            assignedCar.bookings.add(rental);
//            assignedCar.availableAt = rental.end;
//            queue.add(assignedCar);
//        }
//        return assignments;
//
//    }
}

class CarRentalAssignmentTest {

    private CarRentalAssignment carRentalAssignment;

    public CarRentalAssignmentTest() {
        carRentalAssignment = new CarRentalAssignment();
    }

    //no rental -> should return empty map
    // @Test
    public void testNoRental() {
        List<Rental> rentals = new ArrayList();
        Map<Integer, List<Rental>> map = carRentalAssignment.assignRentals(rentals, true);
//        Assert.assertTrue(map!=null);
//        Assert.assertTrue(map.isEmpty());
    }

    //IllegalArgumentException
    // @Test
    public void testIllegalArgumentExceptionl() {
        List<Rental> rentals = null;
        try {
            Map<Integer, List<Rental>> map = carRentalAssignment.assignRentals(rentals, true);
//            Assert.fail();
        } catch (IllegalArgumentException e) {
            //exception caught. Test should pass.
        }
    }

    //IllegalStateException
    // @Test(Expected=IllegalStateException.class)
    public void testIllegalStateException() {
        List<Rental> rentals = Arrays.asList(new Rental(0,2),null,new Rental(1,3));
        Map<Integer, List<Rental>> map = carRentalAssignment.assignRentals(rentals, true);
    }

    //single rental -> only one key
    public void testSingleRental() {
        List<Rental> rentals = Arrays.asList(new Rental(0,2));
        Map<Integer, List<Rental>> map = carRentalAssignment.assignRentals(rentals, true);

//        Assert.assertEqual(map.size(),1);
//        Assert.assertEqual(map.get(0).size(),1);
//        Assert.assertEqual(map.get(0).get(0).start,0); //start
//        Assert.assertEqual(map.get(0).get(0).start,2); //end
    }

    //overlapping rentals
    public void testOveralppingRentals() {
        List<Rental> rentals = Arrays.asList(new Rental(0,2),new Rental(1,3),new Rental(5,6));
        Map<Integer, List<Rental>> map = carRentalAssignment.assignRentals(rentals, true);
//        Assert.assertEqual(map.size(),2);
    }
    //non-overlapping rentals

    //all rentals are overlapping -> every rental will have a new car assigned


}



package com.root.allInOne.LLD.ParkingLot;

//• A parking lot with multiple slot types.
//• Support for bikes, cars, and trucks.
//• Dynamic slot allocation based on vehicle size.
//• Payment processing with multiple methods.
//• Entry ticket issuance and exit validation.
//


interface Ticket {
    int getStartTime();
    String getPaymentStatus();
    int getAmount();
    int getVehicleNumber();

}

interface Slot{
    int getBasePricePerHour();
}

interface Vehicle {
    int getVehicleNumber();
    int getBasePricePerHour();
}

abstract class Payment {
    int timestamp;
    String status;
    int amount;
}
class CashPayment extends Payment { }
class CardPayment extends Payment {
    int cardNumber; String cardType;
}
class ParkingTicket implements Ticket {
    int ticketId;
    Vehicle vehicle;
    Slot slot;
    int startTime;
    Payment payment; //composition

    @Override
    public int getStartTime() {
        return 0;
    }

    @Override
    public String getPaymentStatus() {
        return null;
    }

    @Override
    public int getAmount() {
        return 0;
    }

    @Override
    public int getVehicleNumber() {
        return 0;
    }
}


class SmallVehicle implements Vehicle {
    int number;
    Slot slot;

    SmallVehicle() {
//        this.slot = new Small();
    }

    public int getVehicleNumber() {
        return number;
    }

    @Override
    public int getBasePricePerHour() {
        return slot.getBasePricePerHour();
    }
}

public class ParkingLot {

//    ParkingLot(int smallSlots, int medSlots, int largeSlot) {
//
//    }
//    List<Slot> smallslots; List<Slot> medslots; List<Slot> largelots;
//    Set<Vehicle> onboarded = new HashSet<>();
//
//    Ticket onboardVehicle(Vehicle vehicle) thows Exception {
//
//        //occupuy a slot
//        //create ticket
//    }
//
//    releaseVehicle() thows Exception  {
//        //release the slot
//        //make Payment
//    }
//
//    public static void main(String[] args) {
//        //ParkingLot p =
//        //vehicle type vt
//        //p.
//    }

}

class VechileFactory {
    //objects of differnt vehicles
}

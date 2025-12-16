//package com.root.allInOne.LLD.CarRental;
//
//
////• A system with multiple rental stores and vehicle categories.
////• Reservation management with conflict prevention.
////• Dynamic pricing implementation based on vehicle type and duration.
////• Ability to handle edge cases like late returns, damages, or early pickups.
//class Reservation {
//    int store;
//    Vehiclee vehicle;
//    int startTime; int endTime; String status;
//}
//
//class ReservationManager {
//
//}
//
//class Store {
//    PricingStrategy pricingStrategy;
//    Store(PricingStrategy pricingStrategy) {
//
//    }
//    int storeNum; int location; List<Vehiclee> list; List<Reservation> reservationList;
//
//    getFreeSlotsForAVehicle(Vehiclee v, int startTime, int endTime);
//
//    getFreeVehicles(int startTime, int endTime);
//
//    reserve(Vehiclee v, int start, int endTime) throw Exception {
//
//    }
//
//    update(Reservation reservation);
//
//    Reservation startTrip(int startTime, Reservation reservation) {
//
//    }
//
//    void endTrip(int endTime, Reservation reservation) {
//        int extraTime;
//        boolean damage;
//        pricingStrategy.cal(reservation.vehicle.vehicleType, duration, extraTime. damage);
//    }
//}
//
//enum VehicleType {
//    S,M,L
//}
//
//abstract class Vehiclee {
//    int vehicleNo;
//    VehicleType vehicleType;
//    int basePrice;
//
//    String vehicleStatus;
//
//    public Vehiclee(int vehicleNo, VehicleType vehicleType, int basePrice) {
//        this.vehicleNo = vehicleNo;
//        this.vehicleType = vehicleType; this.basePrice = basePrice;
//    }
//
//    abstract int basePrice();
//}
//
//class Car extends Vehiclee {
//    Car(int vehileNo) {
//        super(vehileNo, VehicleType.M, 200);
//    }
//    @Override
//    int basePrice() {
//        return basePrice;
//    }
//}
//
//interface PricingStrategy{}
//class AllTypesSamePriceStrategy implements PricingStrategy {}
//class PriceBasedOnTypeStretegy implements PricingStrategy {
//    int getPrice(Vehiclee vehiclee) {
//        return 0;
//    }
//}
//
////factory for vehicle
//
//
//public class CarRental {
//
//    List<Store> stores;
//
//
//}

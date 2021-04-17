package com.dojo.foundations.parking;

import com.dojo.foundations.parking.cell.Cell;
import com.dojo.foundations.parking.cell.CellTypeEnum;
import com.dojo.foundations.parking.cell.ParkingLot;
import com.dojo.foundations.parking.cell.RateEnum;
import com.dojo.foundations.parking.vehicle.Car;
import com.dojo.foundations.parking.vehicle.Motorcycle;
import com.dojo.foundations.parking.vehicle.Truck;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TheGarage {

    public static final double WAIVE_TIME = 0.02;
    public static final double HIGH_CC_OVERCHARGE = 1.2;
    public static final double HIGH_TON_OVERCHARGE = 1.35;

    public static final Pattern PATTERN_CAR_PLATE = Pattern.compile("([a-zA-Z]{3}[0-9]{3})", Pattern.CASE_INSENSITIVE);
    public static final Pattern PATTERN_MOTORCYCLE_PLATE = Pattern.compile("([a-zA-Z]{3}[0-9]{2}[a-zA-Z])", Pattern.CASE_INSENSITIVE);
    public static final Pattern PATTERN_TRUCK_PLATE = Pattern.compile("([a-zA-Z][0-9]{4})", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) throws Exception {

        // 1. Build Parking Lot
        ParkingLot parkingLot = new ParkingLot(Arrays.asList(
                new Cell("M1", CellTypeEnum.MOTORCYCLE, true, Optional.empty()),
                new Cell("M2", CellTypeEnum.MOTORCYCLE, true, Optional.empty()),
                new Cell("M3", CellTypeEnum.MOTORCYCLE, true, Optional.empty()),
                new Cell("M4", CellTypeEnum.MOTORCYCLE, true, Optional.empty()),
                new Cell("M5", CellTypeEnum.MOTORCYCLE, true, Optional.empty()),
                new Cell("M6", CellTypeEnum.MOTORCYCLE, true, Optional.empty()),
                new Cell("M7", CellTypeEnum.MOTORCYCLE, true, Optional.empty()),
                new Cell("M8", CellTypeEnum.MOTORCYCLE, true, Optional.empty()),
                new Cell("M9", CellTypeEnum.MOTORCYCLE, true, Optional.empty()),
                new Cell("M10", CellTypeEnum.MOTORCYCLE, true, Optional.empty()),
                new Cell("M11", CellTypeEnum.MOTORCYCLE, true, Optional.empty()),
                new Cell("M12", CellTypeEnum.MOTORCYCLE, true, Optional.empty()),
                new Cell("C1", CellTypeEnum.CAR, true, Optional.empty()),
                new Cell("C2", CellTypeEnum.CAR, true, Optional.empty()),
                new Cell("C3", CellTypeEnum.CAR, true, Optional.empty()),
                new Cell("C4", CellTypeEnum.CAR, true, Optional.empty()),
                new Cell("C5", CellTypeEnum.CAR, true, Optional.empty()),
                new Cell("C6", CellTypeEnum.CAR, true, Optional.empty()),
                new Cell("C7", CellTypeEnum.CAR, true, Optional.empty()),
                new Cell("C8", CellTypeEnum.CAR, true, Optional.empty()),
                new Cell("T1", CellTypeEnum.CAR, true, Optional.empty()),
                new Cell("T2", CellTypeEnum.CAR, true, Optional.empty()),
                new Cell("T3", CellTypeEnum.CAR, true, Optional.empty()),
                new Cell("T4", CellTypeEnum.CAR, true, Optional.empty())
        ));

        // 2. Map cells with vehicles
        Map<Cell, Motorcycle> parkingLotMotorcycleMap = new HashMap<>();
        Map<Cell, Car> parkingLotCarMap = new HashMap<>();
        Map<Cell, Truck> parkingLotTruckMap = new HashMap<>();

        // 3. Cars arrive
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\"The Garage\"");
        System.out.println("1) Motorcycle enters");
        System.out.println("2) Car enters");
        System.out.println("3) Truck enters");
        System.out.println("4) Motorcycle leaves ");
        System.out.println("5) Car leaves");
        System.out.println("6) Truck leaves");
        System.out.println("7) Get Parking lot occupation");
        System.out.println("8) Get free cells");
        System.out.println("0) CLOSE");

        Integer option = 1;

        while (option != 0) {
            option = Integer.parseInt(scanner.readLine());

            if (!isValidInput(option)) break;

            switch (option) {
                case 1:
                    System.out.println("Write the motorcycle plate:");
                    String motorcyclePlate = scanner.readLine();
                    Matcher matcherMotorcycle = PATTERN_MOTORCYCLE_PLATE.matcher(motorcyclePlate);
                    if (!matcherMotorcycle.find()) {
                        System.out.println("The plate is invalid");
                        return;
                    }
                    System.out.println("Write the motorcycle cc:");
                    int motorcycleCc = Integer.parseInt(scanner.readLine());

                    Optional<Cell> availableCellMotorcycle = parkingLot.getParkingLot().stream()
                            .filter(cell -> CellTypeEnum.MOTORCYCLE.equals(cell.getType()) && cell.isFree)
                            .findAny();

                    if (availableCellMotorcycle.isPresent()) {
                        availableCellMotorcycle.get().setFree(false);
                        availableCellMotorcycle.get().setEnterDateTime(Optional.of(LocalDateTime.now()));
                        parkingLotMotorcycleMap.put(availableCellMotorcycle.get(), new Motorcycle(motorcyclePlate, motorcycleCc));
                    }
                    parkingLotMotorcycleMap.forEach((cell, motorcycle) -> System.out.println("Cell: " + cell.code + " Plate: " + motorcycle.plate));
                    break;
                case 2:
                    System.out.println("Write the car plate:");
                    String carPlate = scanner.readLine();
                    Matcher matcherCar = PATTERN_CAR_PLATE.matcher(carPlate);
                    if (!matcherCar.find()) {
                        System.out.println("The plate is invalid");
                        return;
                    }

                    Optional<Cell> availableCellCar = parkingLot.getParkingLot().stream()
                            .filter(cell -> CellTypeEnum.CAR.equals(cell.getType()) && cell.isFree)
                            .findAny();

                    if (availableCellCar.isPresent()) {
                        availableCellCar.get().setFree(false);
                        availableCellCar.get().setEnterDateTime(Optional.of(LocalDateTime.now()));
                        parkingLotCarMap.put(availableCellCar.get(), new Car(carPlate));
                    }
                    parkingLotCarMap.forEach((cell, car) -> System.out.println("Cell: " + cell.code + " Plate: " + car.plate));
                    break;
                case 3:
                    System.out.println("Write the truck plate:");
                    String truckPlate = scanner.readLine();
                    Matcher matcherTruck = PATTERN_TRUCK_PLATE.matcher(truckPlate);
                    if (!matcherTruck.find()) {
                        System.out.println("The plate is invalid");
                        return;
                    }
                    System.out.println("Write the truck ton");
                    double truckTon = Double.parseDouble(scanner.readLine());

                    Optional<Cell> availableCellTruck = parkingLot.getParkingLot().stream()
                            .filter(cell -> CellTypeEnum.TRUCK.equals(cell.getType()) && cell.isFree)
                            .findAny();

                    if (availableCellTruck.isPresent()) {
                        availableCellTruck.get().setFree(false);
                        availableCellTruck.get().setEnterDateTime(Optional.of(LocalDateTime.now()));
                        parkingLotTruckMap.put(availableCellTruck.get(), new Truck(truckPlate, truckTon));
                    }
                    parkingLotTruckMap.forEach((cell, truck) -> System.out.println("Cell: " + cell.code + " Plate: " + truck.plate));
                    break;
                case 4:
                    System.out.println("Write the motorcycle plate:");
                    String motorcycleLeavingPlate = scanner.readLine();
                    Matcher matcherLeavingMotorcycle = PATTERN_MOTORCYCLE_PLATE.matcher(motorcycleLeavingPlate);
                    if (!matcherLeavingMotorcycle.find()) {
                        System.out.println("The plate is invalid");
                        return;
                    }
                    Optional<Map.Entry<Cell, Motorcycle>> leavingMotorcycleMap = parkingLotMotorcycleMap
                            .entrySet().stream()
                            .filter(entry -> motorcycleLeavingPlate.equals(entry.getValue().plate))
                            .findFirst();

                    if (leavingMotorcycleMap.isPresent()) {
                        double chargedAmountMotorcycle = calculateBillMotorcycle(leavingMotorcycleMap.get().getKey(), leavingMotorcycleMap.get().getValue());
                        System.out.println("The charged amount for motorcycle " + motorcycleLeavingPlate + " is $" + chargedAmountMotorcycle);
                        leavingMotorcycleMap.get().getKey().setFree(true);
                        leavingMotorcycleMap.get().getKey().setEnterDateTime(Optional.empty());
                    } else {
                        System.out.println("The motorcycle is not in the parking lot");
                    }

                    break;
                case 5:
                    System.out.println("Write the car plate:");
                    String carLeavingPlate = scanner.readLine();
                    Matcher matcherLeavingCar = PATTERN_CAR_PLATE.matcher(carLeavingPlate);
                    if (!matcherLeavingCar.find()) {
                        System.out.println("The plate is invalid");
                        return;
                    }
                    Optional<Map.Entry<Cell, Car>> leavingCarMap = parkingLotCarMap
                            .entrySet().stream()
                            .filter(entry -> carLeavingPlate.equals(entry.getValue().plate))
                            .findFirst();

                    if (leavingCarMap.isPresent()) {
                        double chargedAmountCar = calculateBillCar(leavingCarMap.get().getKey(), leavingCarMap.get().getValue());
                        System.out.println("The charged amount for car " + carLeavingPlate + " is $" + chargedAmountCar);
                        leavingCarMap.get().getKey().setFree(true);
                        leavingCarMap.get().getKey().setEnterDateTime(Optional.empty());
                    } else {
                        System.out.println("The car is not in the parking lot");
                    }
                    break;
                case 6:
                    System.out.println("Write the truck plate:");
                    String truckLeavingPlate = scanner.readLine();
                    Matcher matcherLeavingTruck = PATTERN_TRUCK_PLATE.matcher(truckLeavingPlate);
                    if (!matcherLeavingTruck.find()) {
                        System.out.println("The plate is invalid");
                        return;
                    }
                    Optional<Map.Entry<Cell, Truck>> leavingTruckMap = parkingLotTruckMap
                            .entrySet().stream()
                            .filter(entry -> truckLeavingPlate.equals(entry.getValue().plate))
                            .findFirst();

                    if (leavingTruckMap.isPresent()) {
                        double chargedAmountTruck = calculateBillTruck(leavingTruckMap.get().getKey(), leavingTruckMap.get().getValue());
                        System.out.println("The charged amount for truck " + truckLeavingPlate + " is $" + chargedAmountTruck);
                        leavingTruckMap.get().getKey().setFree(true);
                        leavingTruckMap.get().getKey().setEnterDateTime(Optional.empty());
                    } else {
                        System.out.println("The truck is not in the parking lot");
                    }

                    break;
                case 7:

                    break;
                case 8:

                    break;
            }

        }

    }

    private static boolean isValidInput(int option) {
        if (option < 0 || option > 6) {
            System.out.println("Wrong selection, please try again");
            return false;
        }
        return true;
    }

    private static double calculateBillMotorcycle(Cell cell, Motorcycle motorcycle) {
        double hours = (double) ChronoUnit.MINUTES
                .between(cell.getEnterDateTime().get(), LocalDateTime.now()) / 60;
        double completeHours = Math.floor(hours);
        double incompleteHours = hours - completeHours;
        int hoursToCharge = (incompleteHours <= WAIVE_TIME) ? (int) completeHours: (int) Math.ceil(hours);
        return (motorcycle.cc < 200) ?
                hoursToCharge * RateEnum.MOTORCYCLE.getRate() :
                hoursToCharge * RateEnum.MOTORCYCLE.getRate() * HIGH_CC_OVERCHARGE;
    }

    private static double calculateBillCar(Cell cell, Car car) {
        double hours = (double) ChronoUnit.MINUTES
                .between(cell.getEnterDateTime().get(), LocalDateTime.now()) / 60;
        double completeHours = Math.floor(hours);
        double incompleteHours = hours - completeHours;
        int hoursToCharge = incompleteHours <= WAIVE_TIME ? (int) completeHours: (int) Math.ceil(hours);
        return hoursToCharge * RateEnum.CAR.getRate();
    }

    private static double calculateBillTruck(Cell cell, Truck truck) {
        double hours = (double) ChronoUnit.MINUTES
                .between(cell.getEnterDateTime().get(), LocalDateTime.now()) / 60;
        double completeHours = Math.floor(hours);
        double incompleteHours = hours - completeHours;
        int hoursToCharge = incompleteHours <= WAIVE_TIME ? (int) completeHours: (int) Math.ceil(hours);
        return (truck.ton < 4.0) ?
                hoursToCharge * RateEnum.TRUCK.getRate() :
                hoursToCharge * RateEnum.TRUCK.getRate() * HIGH_TON_OVERCHARGE;
    }

}

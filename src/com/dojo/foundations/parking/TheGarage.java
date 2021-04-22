package com.dojo.foundations.parking;

import com.dojo.foundations.parking.cell.Cell;
import com.dojo.foundations.parking.cell.CellTypeEnum;
import com.dojo.foundations.parking.cell.ParkingLot;
import com.dojo.foundations.parking.vehicle.Car;
import com.dojo.foundations.parking.vehicle.Motorcycle;
import com.dojo.foundations.parking.vehicle.Truck;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TheGarage {

    private static final Pattern PATTERN_MOTORCYCLE_PLATE = Pattern.compile("([a-zA-Z]{3}[0-9]{2}[a-zA-Z])", Pattern.CASE_INSENSITIVE);
    public static final Pattern PATTERN_CAR_PLATE = Pattern.compile("([a-zA-Z]{3}[0-9]{3})", Pattern.CASE_INSENSITIVE);
    public static final Pattern PATTERN_TRUCK_PLATE = Pattern.compile("([a-zA-Z][0-9]{4})", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) throws Exception {

        // 1. Build Parking Lot
        ParkingLot parkingLot = buildParkingLot();

        // 2. Map cells with vehicles
        Map<Cell, Motorcycle> parkingLotMotorcycleMap = new HashMap<>();
        Map<Cell, Car> parkingLotCarMap = new HashMap<>();
        Map<Cell, Truck> parkingLotTruckMap = new HashMap<>();

        // 3. Cars arrive
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

        Integer option = 1;

        while (option != 0) {
            printGarageMenu();
            option = Integer.parseInt(scanner.readLine());

            if (!isValidInput(option)) break;

            switch (option) {
                case 1:
                    System.out.println("Write the motorcycle plate:");
                    String motorcyclePlate = scanner.readLine();
                    Matcher matcherMotorcycle = PATTERN_MOTORCYCLE_PLATE.matcher(motorcyclePlate);
                    evaluatePlate(matcherMotorcycle);
                    System.out.println("Write the motorcycle cc:");
                    int motorcycleCc = Integer.parseInt(scanner.readLine());

                    Optional<Cell> availableCellMotorcycle = parkingLot.getParkingLot().stream()
                            .filter(cell -> CellTypeEnum.MOTORCYCLE.equals(cell.getType()) && cell.isFree())
                            .findAny();

                    if (availableCellMotorcycle.isPresent()) {
                        availableCellMotorcycle.get().setFree(false);
                        availableCellMotorcycle.get().setEnterDateTime(Optional.of(LocalDateTime.now()));
                        parkingLotMotorcycleMap.put(availableCellMotorcycle.get(), new Motorcycle(motorcyclePlate, motorcycleCc));
                    }
                    parkingLotMotorcycleMap.forEach((cell, motorcycle) -> System.out.println("Cell: " + cell.getCode() + " Plate: " + motorcycle.getPlate()));
                    break;
                case 2:
                    System.out.println("Write the car plate:");
                    String carPlate = scanner.readLine();
                    Matcher matcherCar = PATTERN_CAR_PLATE.matcher(carPlate);
                    evaluatePlate(matcherCar);

                    Optional<Cell> availableCellCar = parkingLot.getParkingLot().stream()
                            .filter(cell -> CellTypeEnum.CAR.equals(cell.getType()) && cell.isFree())
                            .findAny();

                    if (availableCellCar.isPresent()) {
                        availableCellCar.get().setFree(false);
                        availableCellCar.get().setEnterDateTime(Optional.of(LocalDateTime.now()));
                        parkingLotCarMap.put(availableCellCar.get(), new Car(carPlate));
                    }
                    parkingLotCarMap.forEach((cell, car) -> System.out.println("Cell: " + cell.getCode() + " Plate: " + car.getPlate()));
                    break;
                case 3:
                    System.out.println("Write the truck plate:");
                    String truckPlate = scanner.readLine();
                    Matcher matcherTruck = PATTERN_TRUCK_PLATE.matcher(truckPlate);
                    evaluatePlate(matcherTruck);
                    System.out.println("Write the truck ton");
                    double truckTon = Double.parseDouble(scanner.readLine());

                    Optional<Cell> availableCellTruck = parkingLot.getParkingLot().stream()
                            .filter(cell -> CellTypeEnum.TRUCK.equals(cell.getType()) && cell.isFree())
                            .findAny();

                    if (availableCellTruck.isPresent()) {
                        availableCellTruck.get().setFree(false);
                        availableCellTruck.get().setEnterDateTime(Optional.of(LocalDateTime.now()));
                        parkingLotTruckMap.put(availableCellTruck.get(), new Truck(truckPlate, truckTon));
                    }
                    parkingLotTruckMap.forEach((cell, truck) -> System.out.println("Cell: " + cell.getCode() + " Plate: " + truck.getPlate()));
                    break;
                case 4:
                    System.out.println("Write the motorcycle plate:");
                    String motorcycleLeavingPlate = scanner.readLine();
                    Matcher matcherLeavingMotorcycle = PATTERN_MOTORCYCLE_PLATE.matcher(motorcycleLeavingPlate);
                    evaluatePlate(matcherLeavingMotorcycle);
                    Optional<Map.Entry<Cell, Motorcycle>> leavingMotorcycleMap = parkingLotMotorcycleMap
                            .entrySet().stream()
                            .filter(entry -> motorcycleLeavingPlate.equals(entry.getValue().getPlate()))
                            .findFirst();

                    if (leavingMotorcycleMap.isPresent()) {
                        Cell leavingCell = leavingMotorcycleMap.get().getKey();
                        Motorcycle leavingMotorcycle = leavingMotorcycleMap.get().getValue();
                        double chargedAmountMotorcycle = leavingMotorcycle.calculateBilling(leavingCell.obtainHoursToCharge());
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
                    evaluatePlate(matcherLeavingCar);
                    Optional<Map.Entry<Cell, Car>> leavingCarMap = parkingLotCarMap
                            .entrySet().stream()
                            .filter(entry -> carLeavingPlate.equals(entry.getValue().getPlate()))
                            .findFirst();

                    if (leavingCarMap.isPresent()) {
                        Cell leavingCell = leavingCarMap.get().getKey();
                        Car leavingCar = leavingCarMap.get().getValue();
                        double chargedAmountCar = leavingCar.calculateBilling(leavingCell.obtainHoursToCharge());
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
                    evaluatePlate(matcherLeavingTruck);
                    Optional<Map.Entry<Cell, Truck>> leavingTruckMap = parkingLotTruckMap
                            .entrySet().stream()
                            .filter(entry -> truckLeavingPlate.equals(entry.getValue().getPlate()))
                            .findFirst();

                    if (leavingTruckMap.isPresent()) {
                        Cell leavingCell = leavingTruckMap.get().getKey();
                        Truck leavingTruck = leavingTruckMap.get().getValue();
                        double chargedAmountTruck = leavingTruck.calculateBilling(leavingCell.obtainHoursToCharge());
                        System.out.println("The charged amount for truck " + truckLeavingPlate + " is $" + chargedAmountTruck);
                        leavingTruckMap.get().getKey().setFree(true);
                        leavingTruckMap.get().getKey().setEnterDateTime(Optional.empty());
                    } else {
                        System.out.println("The truck is not in the parking lot");
                    }

                    break;
                case 7:
                    System.out.println("There are " + parkingLot.getParkingLotOccupation() + " cells occupied.");
                    break;
                case 8:
                    System.out.println("There are " + parkingLot.getFreeCells() + " free cells.");
                    break;
                default:
                    break;
            }

        }

    }

    private static ParkingLot buildParkingLot() {
        return new ParkingLot(Arrays.asList(
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
            new Cell("T1", CellTypeEnum.TRUCK, true, Optional.empty()),
            new Cell("T2", CellTypeEnum.TRUCK, true, Optional.empty()),
            new Cell("T3", CellTypeEnum.TRUCK, true, Optional.empty()),
            new Cell("T4", CellTypeEnum.TRUCK, true, Optional.empty())
        ));
    }
    
    private static void printGarageMenu() {
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
    }

    private static boolean isValidInput(int option) {
        if (option < 0 || option > 8) {
            System.out.println("Wrong selection, please try again");
            return false;
        }
        return true;
    }

    private static void evaluatePlate(Matcher matcher) {
        if (!matcher.find()) {
            System.out.println("The plate is invalid");
            return;
        }
    }

}

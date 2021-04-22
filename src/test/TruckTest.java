package test;

import com.dojo.foundations.parking.vehicle.Car;
import com.dojo.foundations.parking.vehicle.Truck;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TruckTest {

    private Truck testTruck;

    @Before
    public void init() {
        testTruck = new Truck("testTruck123", 5.4);
    }

    @Test
    public void correctlyCalculateBillingTest() {
        Assert.assertTrue(testTruck.calculateBilling(4) == 99.9);
    }

    @Test
    public void wronglyCalculateBillingTest() {
        Assert.assertFalse(testTruck.calculateBilling(4) == 50);
    }

}
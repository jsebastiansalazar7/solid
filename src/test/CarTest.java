package test;

import com.dojo.foundations.parking.vehicle.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarTest {

    private Car testCar;

    @Before
    public void init() {
        testCar = new Car("testPlate123");
    }

    @Test
    public void correctlyCalculateBillingTest() {
        Assert.assertTrue(testCar.calculateBilling(2) == 21);
    }

    @Test
    public void wronglyCalculateBillingTest() {
        Assert.assertFalse(testCar.calculateBilling(2) == 99);
    }

}
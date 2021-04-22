package test;

import com.dojo.foundations.parking.vehicle.Motorcycle;
import com.dojo.foundations.parking.vehicle.Truck;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MotorcycleTest {

    private Motorcycle testMotorcyle;

    @Before
    public void init() {
        testMotorcyle = new Motorcycle("testMotorcycle123", 600);
    }

    @Test
    public void correctlyCalculateBillingTest() {
        Assert.assertTrue(testMotorcyle.calculateBilling(2) == 12);
    }

    @Test
    public void wronglyCalculateBillingTest() {
        Assert.assertFalse(testMotorcyle.calculateBilling(2) == 6);
    }

}
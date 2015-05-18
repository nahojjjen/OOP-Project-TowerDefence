package test.edu.chl.proximity.Utilities;

import edu.chl.proximity.Utilities.ProximityRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Johan
 * @2015-05-18.
 *
 * Tests random numbers. This test can actually fail even though it works, its just a very very small chance.
 */
public class ProximityRandomTest {

    @Test
    public void testGetRandomDouble() throws Exception {
        ProximityRandom test = new ProximityRandom();
        double timeLimit = 0.5*1000;// half a second
        double startTime = System.currentTimeMillis();
        for(int i =0; i<100000; i++){
            double aNumber = test.getRandomDouble();
            assertTrue(aNumber >= 0 && aNumber <= 1);
        }
        double elapsedtime= System.currentTimeMillis() - startTime;
        assertTrue(elapsedtime < timeLimit);
    }

    @Test
    public void testGetRandomFloat() throws Exception {

        ProximityRandom test = new ProximityRandom();
        double timeLimit = 0.5*10000;// half a second
        double startTime = System.currentTimeMillis();
        for(int i =0; i<10000; i++){
            float aNumber = test.getRandomFloat();
            assertTrue(aNumber >= 0 && aNumber <= 1);
        }
        double elapsedtime= System.currentTimeMillis() - startTime;
        assertTrue(elapsedtime < timeLimit);

    }

    @Test (expected=IllegalArgumentException.class)
    public void testGetRandomDoubleBetween() throws Exception {
        ProximityRandom test = new ProximityRandom();

        //tests a normal case between 0 and 1
        double upperLimit = 1;
        double lowerLimit = 0;
        boolean hasReachedLow1 = false;
        boolean hasReachedhigh1 = false;
        for (int i = 0; i< 100000; i++){
            double test1 = test.getRandomDoubleBetween(lowerLimit,upperLimit);
            assertTrue(test1 >= lowerLimit && test1 <= upperLimit);

            if (test1 < lowerLimit+0.1)
                hasReachedLow1 = true;
            if (test1 > upperLimit- 0.1)
                hasReachedhigh1 = true;
        }
        assertTrue(hasReachedhigh1 && hasReachedLow1);

        //tests a normal case between 0 and 1
        double upperLimit2 = 10;
        double lowerLimit2 = -10;
        boolean hasReachedLow2 = false;
        boolean hasReachedhigh2 = false;
        for (int i = 0; i< 100000; i++){
            double test2 = test.getRandomDoubleBetween(lowerLimit2,upperLimit2);
            assertTrue(test2 >= lowerLimit2 && test2 <= upperLimit2);

            if (test2 < lowerLimit2+0.1)
                hasReachedLow2 = true;
            if (test2 > upperLimit2- 0.1)
                hasReachedhigh2 = true;
        }
        assertTrue(hasReachedhigh2 && hasReachedLow2);

        //tests a normal case between 0 and 1
        double upperLimit3 = -10;
        double lowerLimit3 = -1000;
        boolean hasReachedLow3 = false;
        boolean hasReachedhigh3 = false;
        for (int i = 0; i< 1000000; i++){
            double test3 = test.getRandomDoubleBetween(lowerLimit3,upperLimit3);
            assertTrue(test3 >= lowerLimit3 && test3 <= upperLimit3);

            if (test3 < lowerLimit3+0.1)
                hasReachedLow3 = true;
            if (test3 > upperLimit3- 0.1)
                hasReachedhigh3 = true;
        }
        assertTrue(hasReachedhigh3 && hasReachedLow3);

        double same = test.getRandomDoubleBetween(1,1);
        assertTrue(same == 1);


        double error = test.getRandomDoubleBetween(1,-1);


    }
}
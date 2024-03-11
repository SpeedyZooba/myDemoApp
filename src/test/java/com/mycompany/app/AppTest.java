package com.mycompany.app;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName)
    {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(AppTest.class);
    }

    public void testShiftEquals()
    {
        String fShiftString = "abc";
        String bShiftString = "def";
        ArrayList<Integer> fShiftIndexes = new ArrayList<Integer>();
        ArrayList<Integer> bShiftIndexes = new ArrayList<Integer>();
        for (int i = 0; i < fShiftString.length(); i++)
        {
            fShiftIndexes.add(1);
        }
        for (int i = 0; i < bShiftString.length(); i++)
        {
            bShiftIndexes.add(2);
        }
        assertTrue(App.shiftComparison(fShiftString, fShiftIndexes, bShiftString, bShiftIndexes));
    }

    public void testShiftNotEquals()
    {
        String fShiftString = "abc";
        String bShiftString = "def";
        ArrayList<Integer> fShiftIndexes = new ArrayList<Integer>();
        ArrayList<Integer> bShiftIndexes = new ArrayList<Integer>();
        for (int i = 0; i < 3; i++)
        {
            fShiftIndexes.add(1);
        }
        for (int i = 0; i < 3; i++)
        {
            bShiftIndexes.add(1);
        }
        assertFalse(App.shiftComparison(fShiftString, fShiftIndexes, bShiftString, bShiftIndexes));
    }

    public void testAtLeastOneInputIsNull()
    {
        try 
        {
            String bShiftString = "def";
            ArrayList<Integer> fShiftIndexes = new ArrayList<Integer>();
            ArrayList<Integer> bShiftIndexes = new ArrayList<Integer>();
            for (int i = 0; i < 3; i++)
            {
                fShiftIndexes.add(1);
            }
            for (int i = 0; i < bShiftString.length(); i++)
            {
                bShiftIndexes.add(1);
            }
            boolean result = App.shiftComparison(null, fShiftIndexes, bShiftString, bShiftIndexes);
            fail("Expected exception was not thrown, result being " + result);
        }
        catch (NullPointerException e)
        {
            assertEquals("Every input must be at least empty.", e.getMessage());
        }
    }

    public void testIndexArrayIsEmpty()
    {
        try 
        {
            String fShiftString = "abc";
            String bShiftString = "def";
            ArrayList<Integer> fShiftIndexes = new ArrayList<Integer>();
            ArrayList<Integer> bShiftIndexes = new ArrayList<Integer>();
            for (int i = 0; i < bShiftString.length(); i++)
            {
                bShiftIndexes.add(1);
            }
            boolean result = App.shiftComparison(fShiftString, fShiftIndexes, bShiftString, bShiftIndexes);
            fail("Expected exception was not thrown, result being " + result);
        }
        catch (IndexOutOfBoundsException e)
        {
            assertTrue(true);
        }
    }

    public void testStringsAreEmpty()
    {
        String fShiftString = "";
        String bShiftString = "";
        ArrayList<Integer> fShiftIndexes = new ArrayList<Integer>();
        ArrayList<Integer> bShiftIndexes = new ArrayList<Integer>();
        for (int i = 0; i < 3; i++)
        {
            fShiftIndexes.add(1);
        }
        for (int i = 0; i < 3; i++)
        {
            bShiftIndexes.add(1);
        }
        assertTrue(App.shiftComparison(fShiftString, fShiftIndexes, bShiftString, bShiftIndexes));
    }
}

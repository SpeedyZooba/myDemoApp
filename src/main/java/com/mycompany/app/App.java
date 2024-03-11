package com.mycompany.app;

import java.util.ArrayList;

public class App 
{
    public static void main(String[] args)
    {
        System.out.println( "Hello World!" );
    }

    public static boolean shiftComparison(String forwardString, ArrayList<Integer> forwardShifts, String backwardString, ArrayList<Integer> backwardShifts)
    {
        StringBuilder forwardBuilder = new StringBuilder();
        StringBuilder backwardBuilder = new StringBuilder();
        if (forwardString == null || backwardString == null || forwardShifts == null || backwardShifts == null)
        {
            throw new NullPointerException("Every input must be at least empty.");
        }
        for (int i = 0; i < forwardString.length(); i++)
        {
            forwardBuilder.append(forwardString.charAt(i) + forwardShifts.get(i));
        }
        for (int i = 0; i < backwardString.length(); i++)
        {
            backwardBuilder.append(backwardString.charAt(i) - backwardShifts.get(i));
        }
        return forwardBuilder.toString().equals(backwardBuilder.toString());
    }
}
package com.mycompany.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import static spark.Spark.port;
import static spark.Spark.get;
import static spark.Spark.post;

public class App 
{
    public static void main(String[] args)
    {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "This is the main page for the application, go to /shiftcomparison to try it out.");
        post("/shiftcomparison", (req, res) -> {
            String forwardString = req.queryParams("string1").trim();
            String backwardString = req.queryParams("string2").trim();

            String forwardInts = req.queryParams("integer1");
            ArrayList<Integer> forwardShifts = new ArrayList<Integer>();
            Scanner forwardScanner = new Scanner(forwardInts);
            forwardScanner.useDelimiter("[;\r\n]+");
            while (forwardScanner.hasNext())
            {
                int value = Integer.parseInt(forwardScanner.next().replaceAll("\\s",""));
                forwardShifts.add(value);
            }
            forwardScanner.close();

            String backwardInts = req.queryParams("integer2");
            ArrayList<Integer> backwardShifts = new ArrayList<Integer>();
            Scanner backwardScanner = new Scanner(backwardInts);
            backwardScanner.useDelimiter("[;\r\n]+");
            while (backwardScanner.hasNext())
            {
                int value = Integer.parseInt(backwardScanner.next().replaceAll("\\s",""));
                backwardShifts.add(value);
            }
            backwardScanner.close();

            boolean result = App.shiftComparison(forwardString, forwardShifts, backwardString, backwardShifts);

            Map<String, Boolean> map = new HashMap<String, Boolean>();
            map.put("result", result);
            return new ModelAndView(map, "shiftcomparison.mustache");
        }, new MustacheTemplateEngine());
        
        get("/shiftcomparison", (req, res) -> {
            Map<String, String> map = new HashMap<String, String>();
            map.put("result", "the value that is yet unknown.");
            return new ModelAndView(map, "shiftcomparison.mustache"); 
        }, new MustacheTemplateEngine());
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
    static int getHerokuAssignedPort()
    {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null)
        {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
}
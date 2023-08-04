package com.testrigor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    /**
     *
     * Check README
     * @param args
     */
    public static void main(String[] args) {
        Map<String, String> criminals = new HashMap<>();
        criminals.put("Paul White Jr.", null);
        criminals.put("Paul White", "Roger Night, Peter Llong Jr.");
        criminals.put("Red Fortress", "Roger Rabbit, Ross Winter");
        criminals.put("Redford Fort", "Red Strong, Red Fort, Roger Fordson");
        criminals.put("Roger Fedexer", "Rob Ford, Pete Lord, Roger McWire");

        System.out.println("CRIMINALS DATA:");
        for (Map.Entry<String, String> criminal : criminals.entrySet()) {
            System.out.println(buildMatchString(criminal.getKey(), criminal.getValue()));
        }
        System.out.println();

        // Add as many as you want
        System.out.println("Searching: " + "Red Fortress" + "\nFound -> " + findCriminal(criminals, "Red Fortress"));
        System.out.println("Searching: " + "Roger" + "\nFound -> " + findCriminal(criminals, "Roger"));
        System.out.println("Searching: " + "Red" + "\nFound -> " + findCriminal(criminals, "Red"));
        System.out.println("Searching: " + "Llong Jr" + "\nFound -> " + findCriminal(criminals, "Llong Jr"));
        System.out.println("Searching: " + "Ford" + "\nFound -> " + findCriminal(criminals, "Ford"));
        System.out.println("Searching: " + "paul White" + "\nFound -> " + findCriminal(criminals, "paul White"));
        System.out.println("Searching: " + "Ross" + "\nFound -> " + findCriminal(criminals, "Ross"));
        System.out.println("Searching: " + "white jr." + "\nFound -> " + findCriminal(criminals, "white jr."));
        System.out.println("Searching: " + "Fort" + "\nFound -> " + findCriminal(criminals, "Fort"));


    }

    /**
     * TODO: Implement
     * No split() methods or secondary for-each loops are being used, which would
     * increase the time complexity of the algorithm, even though the responses will
     * not have the best quality.
     *
     * @param criminals
     * @param possibleName
     * @return
     */
    public static String findCriminal(Map<String, String> criminals, String possibleName) {
        if (possibleName == null || criminals == null) {
            return "No match";
        }
        ArrayList<String> possibleMatches = new ArrayList<>();
        /**
         * Looking for exact matches in all actual names.
         */
        for (Map.Entry<String, String> criminal : criminals.entrySet()) {
            if (criminal.getKey().equalsIgnoreCase(possibleName)) {
                return buildMatchString(criminal.getKey(), criminal.getValue());
            }
        }
        /**
         * Gathering all possible matches in the actual names.
         */
        for (Map.Entry<String, String> criminal : criminals.entrySet()) {
            // Looking for a partial match (case-insensitive)
            if (criminal.getKey().toLowerCase().contains(possibleName.toLowerCase())) {
                possibleMatches.add(criminal.getKey());
            }
        }
        /**
         * Analyzing each possible match, looking for exact matches in the tokens
         */
        for (String possibleMatch : possibleMatches) {
            String[] nameTokens = possibleMatch.split(" ");
            for (String token : nameTokens) {
                if (token.equalsIgnoreCase(possibleName)){
                    String aliases = criminals.get(possibleMatch);
                    return buildMatchString(possibleMatch, aliases);
                }
            }
        }
        /**
         * Checking for a partial match in the whole aliases' string (case-insensitive)
         */
        possibleMatches.clear();
        for (Map.Entry<String, String> criminal : criminals.entrySet()) {
            if (criminal.getValue() == null) {
                continue;
            }
            String aliasesLower = criminal.getValue().toLowerCase();
            if (aliasesLower.contains(possibleName.toLowerCase())) {
                return buildMatchString(criminal.getKey(), criminal.getValue());
            }
        }
        return "No match";
    }

    public static String buildMatchString(String actualName, String aliases) {
        String match = "";
        match += "First name: " + actualName + ". " + "Aliases: ";
        if (aliases != null)
            match += aliases;
        else
            match += "No aliases";
        return match;
    }
}

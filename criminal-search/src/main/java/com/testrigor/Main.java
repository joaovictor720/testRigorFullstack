package com.testrigor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    /**
     *
     * Check README
     */
    public static void main(String[] args) {
        Map<String, String> criminals = new HashMap<>();
        criminals.put("Paul White", "Roger Night, Peter Llong Jr.");
        criminals.put("Roger Fedexer", "Rob Ford, Pete Lord, Roger McWire");
        criminals.put("Paul White Jr.", null);
        criminals.put("Red Fortress", "Roger Rabbit, Ross Winter");
        criminals.put("Redford Fort", "Red Strong, Red Fort");
        //criminals.put("Redford Fort", "Red Strong, Red Fort");

        // Add as many as you want
        System.out.println("Searching: " + "Red Fortress" + "\nFound -> " + findCriminal(criminals, "Red Fortress"));
        System.out.println("Searching: " + "Roger" + "\nFound -> " + findCriminal(criminals, "Roger"));
        System.out.println("Searching: " + "Red" + "\nFound -> " + findCriminal(criminals, "Red"));
        System.out.println("Searching: " + "Llong Jr." + "\nFound -> " + findCriminal(criminals, "Llong Jr."));
        System.out.println("Searching: " + "Ford" + "\nFound -> " + findCriminal(criminals, "Ford"));
        System.out.println("Searching: " + "paul White" + "\nFound -> " + findCriminal(criminals, "paul White"));
        System.out.println("Searching: " + "Roger" + "\nFound -> " + findCriminal(criminals, "Roger"));
        System.out.println("Searching: " + "Ross" + "\nFound -> " + findCriminal(criminals, "Ross"));
        System.out.println("Searching: " + "white jr." + "\nFound -> " + findCriminal(criminals, "white jr."));
        System.out.println("Searching: " + "mcwire" + "\nFound -> " + findCriminal(criminals, "mcwire"));
        
    }

    /**
     * TODO: Implement
     *
     * @param criminals
     * @param possibleName
     * @return
     */
    public static String findCriminal(Map<String, String> criminals, String possibleName) {
        if (possibleName == null) {
            return "No match";
        }
        
        /**
         * Checking all actual names.
         */
        for (Map.Entry<String, String> criminal : criminals.entrySet()) {
            /**
             * Looks for an exact match with the full actual name.
             */ 
            if (criminal.getKey().toLowerCase().equals(possibleName.toLowerCase())) {
                return buildMatch(criminal.getKey(), criminal.getValue());
            }
            /**
             * Tokenizes the key, to look for an exact match in each word of the actual name.
             */
            String[] words = criminal.getKey().split(" ");
            for (String word : words) {
                if (word.toLowerCase().equals(possibleName.toLowerCase())) {
                    return buildMatch(criminal.getKey(), criminal.getValue());
                }
            }
        }
        
        /**
         * Checking all aliases.
         */
        for (Map.Entry<String, String> criminal : criminals.entrySet()) {
            try {
                /**
                 * Tokenizes the whole value, so that it returns an array of composite aliases.
                 */
                String[] aliases = criminal.getValue().split(", ");
                for (String alias : aliases) {
                    /**
                     * Looks for an exact match with each full alias.
                     */
                    if (alias.toLowerCase().equals(possibleName.toLowerCase())) {
                        return buildMatch(criminal.getKey(), criminal.getValue());
                    }
                    
                    /**
                     * Tokenizes the value, to look for an exact match in each word of the alias.
                     */
                    String[] words = alias.split(" ");
                    for (String word : words) {
                        if (word.toLowerCase().equals(possibleName.toLowerCase())) {
                            return buildMatch(criminal.getKey(), criminal.getValue());
                        }
                    }
                }
            } catch (NullPointerException npe){
                // Treating empty aliases (values)
                continue;
            }
        }
        /**
         * Checking for partial matches with the actual names (keys).
         */
        for (Map.Entry<String, String> criminal : criminals.entrySet()) {
            if (criminal.getKey().toLowerCase().contains(possibleName.toLowerCase())) {
                return buildMatch(criminal.getKey(), criminal.getValue());
            }
        }
        /**
         * Checking for partial matches with the aliases (values).
         */
        for (Map.Entry<String, String> criminal : criminals.entrySet()) {
            if (criminal.getValue().toLowerCase().contains(possibleName.toLowerCase())) {
                return buildMatch(criminal.getKey(), criminal.getValue());
            }
        }

        return "No match";
    }

    public static String buildMatch(String actualName, String aliases) {
        String match = "";
        match += "First name: " + actualName + ". ";
        match += "Aliases: " + aliases;
        return match;
    }
}

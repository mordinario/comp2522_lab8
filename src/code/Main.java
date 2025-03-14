import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * The Main class contains the main method which generates a list of countries.
 * The program performs various operations that filter and sort the countries,
 * such as length, ascending order, etc. and writes the output to a text file.
 *
 * @author Marcy Ordinario
 * @author Andrew Hwang
 * @author Daryan Worya
 * @version 1.0
 */
public class Main
{
    public static void main(final String[] args)
    {
        final int ONE = 1;
        final Path         countryPath;
        final Path         matchesPath;
        final Path         dataTxtPath;
        final List<String> countries;
        final List<String> outputLines;

        countryPath = Paths.get("src", "res", "week8countries.txt");
        matchesPath = Paths.get("src", "res", "matches");
        dataTxtPath = Paths.get("src", "res", "matches", "data.txt");

        try
        {
            if(Files.notExists(matchesPath))
            {
                Files.createDirectory(matchesPath);
            }

            if(Files.notExists(dataTxtPath))
            {
                Files.createFile(dataTxtPath);
            }

            countries = Files.readAllLines(countryPath);
            outputLines = new ArrayList<>();

            // LONGER THAN 10 CHARS
            System.out.println("Country names longer than 10 characters:");
            final List<String> longNames = filteredStream(countries)
                    .filter(s->s.length()>10)
                    .toList();
            outputLines.add("\n-----------------------------------------");
            outputLines.add("Country names longer than 10 characters:");
            outputLines.addAll(longNames);
            outputLines.add("");

            longNames.forEach(System.out::println);

            // SHORTER THAN 5 CHARS
            System.out.println("\nCountry names shorter than 5 characters:");
            final List<String> shortNames = filteredStream(countries)
                    .filter(s->s.length()<5)
                    .toList();
            outputLines.add("\n-----------------------------------------");
            outputLines.add("Country names shorter than 5 characters:");
            outputLines.addAll(shortNames);
            outputLines.add("");

            shortNames.forEach(System.out::println);

            // STARTING WITH "A"
            System.out.println("\nCountry names starting with 'A':");
            final List<String> startsWithA = filteredStream(countries)
                    .filter(s->s.startsWith("A"))
                    .toList();
            outputLines.add("\n-----------------------------------------");
            outputLines.add("Country names starting with 'A':");
            outputLines.addAll(startsWithA);
            outputLines.add("");

            startsWithA.forEach(System.out::println);

            // ENDING WITH "LAND"
            System.out.println("\nCountry names that end with the word \"LAND\":");
            final List<String> endsWithLand = filteredStream(countries)
                    .filter(s->s.endsWith("land"))
                    .toList();
            outputLines.add("\n-----------------------------------------");
            outputLines.add("Country names that end with the word \"LAND\":");
            outputLines.addAll(endsWithLand);
            outputLines.add("");

            endsWithLand.forEach(System.out::println);

            // CONTAINING "UNITED"
            System.out.println("\nCountry names that contain the word \"UNITED\":");
            final List<String> containsUnited = filteredStream(countries)
                    .filter(s->s.toLowerCase().contains("united"))
                    .toList();
            outputLines.add("\n-----------------------------------------");
            outputLines.add("Country names that contain the word \"UNITED\":");
            outputLines.addAll(containsUnited);
            outputLines.add("");

            containsUnited.forEach(System.out::println);

            // SORTED ALPHABETICALLY (ASC)
            System.out.println("\nCountry names in ascending order (alphabetically):");
            final List<String> ascendingOrder = filteredStream(countries)
                    .sorted()
                    .toList();
            outputLines.add("\n-----------------------------------------");
            outputLines.add("Country names in ascending order (alphabetically):");
            outputLines.addAll(ascendingOrder);
            outputLines.add("");

            ascendingOrder.forEach(System.out::println);

            // SORTED ALPHABETICALLY (DESC)
            System.out.println("\nCountry names in descending order (alphabetically)");
            final List<String> descendingOrder = filteredStream(countries)
                    .sorted(Comparator.reverseOrder())
                    .toList();
            outputLines.add("\n-----------------------------------------");
            outputLines.add("Country names in descending order (alphabetically):");
            outputLines.addAll(descendingOrder);
            outputLines.add("");

            descendingOrder.forEach(System.out::println);

            // UNIQUE FIRST LETTERS
            System.out.println("\nExtracting unique first letters of countries and storing it in a list:");
            final List<Character> uniqueFirstLetters = filteredStream(countries)
                    .map(s->s.charAt(0))
                    .distinct()
                    .sorted()
                    .toList();

            uniqueFirstLetters.forEach(System.out::println);

            outputLines.add("\n-----------------------------------------");
            outputLines.add("Extracting unique first letters of countries and storing it in a list:");
            uniqueFirstLetters.forEach(letter -> outputLines.add(letter.toString()));  // Convert Character to String
            outputLines.add("");

            // COUNTRY COUNT
            final long totalCountries = filteredStream(countries)
                    .count();

            System.out.println("\nTotal number of countries in the list: " + totalCountries);
            outputLines.add("\n-----------------------------------------");
            outputLines.add("Total number of countries in the list: " + totalCountries);
            outputLines.add("");

            // LONGEST NAME
            Optional<String> longestCountry = filteredStream(countries)
                    .max(Comparator.comparingInt(String::length));

            System.out.println("\nLongest country name: " + longestCountry.orElse("N/A"));
            outputLines.add("\n-----------------------------------------");
            outputLines.add("Longest country name: " + longestCountry.orElse("N/A"));
            outputLines.add("");

            // SHORTEST NAME
            Optional<String> shortestCountry = filteredStream(countries)
                    .min(Comparator.comparingInt(String::length));

            System.out.println("\nShortest country name: " + shortestCountry.orElse("N/A"));
            outputLines.add("\n-----------------------------------------");
            outputLines.add("Shortest country name: " + shortestCountry.orElse("N/A"));
            outputLines.add("");

            // UPPERCASE
            System.out.println("\nCountry names in all uppercase:");
            final List<String> uppercaseCountries = filteredStream(countries)
                    .map(String::toUpperCase)
                    .toList();

            uppercaseCountries.forEach(System.out::println);
            outputLines.add("\n-----------------------------------------");
            outputLines.add("Country names in all uppercase:");
            outputLines.addAll(uppercaseCountries);
            outputLines.add("");

            // LOWERCASE
            System.out.println("\nCountry names in all lowercase:");
            final List<String> lowercaseCountries = filteredStream(countries)
                    .map(String::toLowerCase)
                    .toList();

            lowercaseCountries.forEach(System.out::println);
            outputLines.add("\n-----------------------------------------");
            outputLines.add("Country names in all lowercase:");
            outputLines.addAll(lowercaseCountries);
            outputLines.add("");

            // MORE THAN ONE WORD (CONTAINING WHITESPACE)
            System.out.println("\nCountry names that contain more than one word:");
            final List<String> moreThanOneWord = filteredStream(countries)
                    .filter(s->s.split(" ").length > ONE)
                    .toList();

            moreThanOneWord.forEach(System.out::println);
            outputLines.add("\n-----------------------------------------");
            outputLines.add("Country names that contain more than one word:");
            outputLines.addAll(moreThanOneWord);
            outputLines.add("");

            // MAPPING NAME TO CHAR. COUNT
            System.out.println("\nCountry names and their character count");
            countries.forEach(s -> {
                final int charCount;
                charCount = s.length();
                System.out.println(s + ": " + charCount + " characters");
            });
            outputLines.add("\n-----------------------------------------");
            outputLines.add("Country names and their character count:");
            countries.forEach(s -> outputLines.add(s + ": " + s.length() + " characters"));
            outputLines.add("");

            // TRUE IF ANY NAME STARTS WITH Z, ELSE FALSE
            final boolean anyCountryStartWithZ = countries.stream()
                    .anyMatch(s->s.startsWith("Z"));
            System.out.println("Does any country name start with Z? " + anyCountryStartWithZ);
            outputLines.add("\n-----------------------------------------");
            outputLines.add("Does any country name start with Z? " + anyCountryStartWithZ);
            outputLines.add("");

            // TRUE IF ALL COUNTRY NAMES ARE LONGER THAN 3 CHARS, ELSE FALSE
            final boolean countryNamesLongerThan3 = countries.stream()
                    .allMatch(s->s.length() > 3);
            System.out.println("Are all country names longer than 3 characters? " + countryNamesLongerThan3);
            outputLines.add("\n-----------------------------------------");
            outputLines.add("Are all country names longer than 3 characters? " + countryNamesLongerThan3);
            outputLines.add("");

            // Write to `data.txt`
            Files.write(dataTxtPath, outputLines);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    private static Stream<String> filteredStream(final List<String> countryList)
    {
        return countryList.stream().filter(Objects::nonNull);
    }
}

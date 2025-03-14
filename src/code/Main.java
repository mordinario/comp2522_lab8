import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO FINISH THIS :D
 *
 * Javadoc >:D
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

        boolean matchesPathCreated;
        boolean dataTxtPathCreated;

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
                Files.createDirectory(dataTxtPath);
            }

            countries = Files.readAllLines(countryPath);

            // LONGER THAN 10 CHARS
            System.out.println("Country names longer than 10 characters");
            final List<String> longNames = filteredStream(countries)
                    .filter(s->s.length()>10)
                    .toList();

            longNames.forEach(System.out::println);

            // SHORTER THAN 5 CHARS
            System.out.println("\nCountry names shorter than 5 characters");
            final List<String> shortNames = filteredStream(countries)
                    .filter(s->s.length()<5)
                    .toList();

            shortNames.forEach(System.out::println);

            // STARTING WITH "A"
            System.out.println("\nCountry names that starting with the letter \"A\"");
            final List<String> startsWithA = filteredStream(countries)
                    .filter(s->s.startsWith("A"))
                    .toList();

            startsWithA.forEach(System.out::println);

            // ENDING WITH "LAND"
            System.out.println("\nCountry names that end with the word \"LAND\"");
            final List<String> endsWithLand = filteredStream(countries)
                    .filter(s->s.endsWith("land"))
                    .toList();

            endsWithLand.forEach(System.out::println);

            // CONTAINING "UNITED"
            System.out.println("\nCountry names that contain the word \"UNITED\"");
            final List<String> containsUnited = filteredStream(countries)
                    .filter(s->s.toLowerCase().contains("united"))
                    .toList();

            containsUnited.forEach(System.out::println);

            // SORTED ALPHABETICALLY (ASC)
            System.out.println("\nCountry names in ascending order (alphabetically)");
            final List<String> ascendingOrder = filteredStream(countries)
                    .sorted()
                    .toList();

            ascendingOrder.forEach(System.out::println);

            // SORTED ALPHABETICALLY (DESC)
            System.out.println("\nCountry names in descending order (alphabetically)");
            final List<String> descendingOrder = filteredStream(countries)
                    .sorted(Comparator.reverseOrder())
                    .toList();

            descendingOrder.forEach(System.out::println);

            // UNIQUE FIRST LETTERS
            System.out.println("\nExtracting unique first letters of countries and storing it in a list");
            final List<Character> uniqueFirstLetters = filteredStream(countries)
                    .map(s->s.charAt(0))
                    .distinct()
                    .sorted()
                    .toList();

            uniqueFirstLetters.forEach(System.out::println);

            // COUNTRY COUNT
            final long totalCountries = filteredStream(countries)
                    .count();

            System.out.println("\nTotal number of countries in the list: " + totalCountries);

            // LONGEST NAME
            Optional<String> longestCountry = filteredStream(countries)
                    .max(Comparator.comparingInt(String::length));

            System.out.println("\nLongest country name: " + longestCountry.orElse("N/A"));

            // SHORTEST NAME
            Optional<String> shortestCountry = filteredStream(countries)
                    .min(Comparator.comparingInt(String::length));

            System.out.println("\nShortest country name: " + shortestCountry.orElse("N/A"));

            // UPPERCASE
            System.out.println("\nCountry names in all uppercase");
            final List<String> uppercaseCountries = filteredStream(countries)
                    .map(String::toUpperCase)
                    .toList();

            uppercaseCountries.forEach(System.out::println);

            // LOWERCASE
            System.out.println("\nCountry names in all lowercase");
            final List<String> lowercaseCountries = filteredStream(countries)
                    .map(String::toLowerCase)
                    .toList();

            lowercaseCountries.forEach(System.out::println);

            // MORE THAN ONE WORD (CONTAINING WHITESPACE)
            System.out.println("\nCountry names that contain more than one word");
            final List<String> moreThanOneWord = filteredStream(countries)
                    .filter(s->s.split(" ").length > ONE)
                    .toList();

            moreThanOneWord.forEach(System.out::println);

            // MAPPING NAME TO CHAR. COUNT
            System.out.println("\nCountry names and their character count");
            countries.forEach(s -> {
                final int charCount;
                charCount = s.length();
                System.out.println(s + ": " + charCount + " characters");
            });

            // TRUE IF ANY NAME STARTS WITH Z, ELSE FALSE
            final boolean anyCountryStartWithZ = countries.stream()
                    .anyMatch(s->s.startsWith("Z"));
            System.out.println("Does any country name start with Z? " + anyCountryStartWithZ);

            // TRUE IF ALL COUNTRY NAMES ARE LONGER THAN 3 CHARS, ELSE FALSE
            final boolean countryNamesLongerThan3 = countries.stream()
                    .allMatch(s->s.length() > 3);
            System.out.println("Are all country names longer than 3 characters? " + countryNamesLongerThan3);
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

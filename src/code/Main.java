import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Main
{
    public static void main(final String[] args)
    {
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

            // COUNTRY COUNT

            // LONGEST NAME

            // SHORTEST NAME

            // UPPERCASE

            // LOWERCASE

            // MORE THAN ONE WORD (CONTAINING WHITESPACE)

            // MAPPING NAME TO CHAR. COUNT

            // TRUE IF ANY NAME STARTS WITH Z, ELSE FALSE

            // TRUE IF ALL COUNTRY NAMES ARE LONGER THAN 3 CHARS, ELSE FALSE
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

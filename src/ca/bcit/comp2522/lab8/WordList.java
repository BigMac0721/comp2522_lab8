package ca.bcit.comp2522.lab8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * WordList loads the list of countries used in the game.
 *
 * @author Giant
 * @author Jiahao Zhu
 *
 * @version 2026
 */
public class WordList
{
    public static final Path PATH;
    public static final List<String> COUNTRY_LIST;

    /*
     * Static initialization block.
     * This runs once when the class is first loaded.
     * It loads the country list from the text file.
     */
    static
    {
        PATH = Paths.get("src", "data", "countries.txt");

        try
        {
            COUNTRY_LIST = Files.readAllLines(PATH);
        }
        catch (final IOException e)
        {
            throw new RuntimeException("Could not load countries.txt", e);
        }
    }
}

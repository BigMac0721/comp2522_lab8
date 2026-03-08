package ca.bcit.comp2522.lab8;

import java.util.List;
import java.util.Scanner;

/**
 * Logic for the main game and the main game loop
 *
 * @author Giant
 * @author Jiahao Zhu
 *
 * @version 2026
 */
public class Game
{
    private final List<String> countries;
    private final String secretCountry;
    private final int countryLength;
    private int attempt;
    private static final int INIT_ATTEMPT_COUNTER = 0;
    private static final int INIT_POSITION = 0;
    private static final int FOR_LOOP_DEFAULT_INIT = 0;

    /**
     * Game constructor.
     * Initializes game variables and starts the game.
     */
    public Game()
    {
        attempt = INIT_ATTEMPT_COUNTER;
        countries = WordList.COUNTRY_LIST;
        secretCountry = pickSecretCountry();
        countryLength = secretCountry.length();

        printHeaderInformation();
        mainGame();
    }

    /**
     * Prints the game introduction information.
     */
    private void printHeaderInformation()
    {
        System.out.println("LUCKY VAULT — COUNTRY MODE. Type QUIT to exit.");
        System.out.println("Secret word length: " + countryLength);
        System.out.println("Current best: " + "-");
    }

    /**
     * Randomly selects a secret country from the list.
     *
     * @return randomly chosen country
     */
    private String pickSecretCountry()
    {
        final int randomIndex;
        randomIndex = (int) (Math.random() * countries.size());

        return countries.get(randomIndex);
    }

    /**
     * Checks if the user's guess is correct.
     *
     * @param input user's guess
     * @return true if the guess is correct, otherwise false
     */
    private boolean isCorrectGuess(String input)
    {
        int correctPosition;
        correctPosition = INIT_POSITION;

        if(input.length() != countryLength)
        {
            System.out.println("Wrong length ("  + input.length() + "). Need " + countryLength + ".");
        }
        else if(input.equals(secretCountry))
        {
            System.out.println("Correct in " + attempt + " attempts! Word was: " + secretCountry);

            return true;
        }
        else
        {
            for(int i = FOR_LOOP_DEFAULT_INIT; i < countryLength; i++)
            {
               if(input.charAt(i) == secretCountry.charAt(i))
               {
                   correctPosition++;
               }
            }

            System.out.println("Not it. " + correctPosition + " letter(s) correct (right position).");
        }

        return false;
    }

    /**
     * Checks if the user's input is empty.
     *
     * @param input user input
     *
     * @return true if empty or null, false otherwise
     */
    private boolean isEmptyGuess(final String input)
    {
        if(input == null || input.isEmpty())
        {
            System.out.println("Empty guess. Try again.");

            return true;
        }

        return false;
    }

    /**
     * Main game loop.
     * Continuously reads guesses from the user until:
     * - the correct answer is guessed
     * - or the user types QUIT
     */
    public void mainGame()
    {
        String input;
        final Scanner scan;

        scan = new Scanner(System.in);

        while(true)
        {
            System.out.print("Your guess: ");
            input = scan.nextLine();

            attempt++;

            if(isEmptyGuess(input))
            {
                continue;
            }

            if(input.equals("QUIT"))
            {
                System.out.println("Bye!");
                break;
            }

            input = input.trim();

            if(isCorrectGuess(input))
            {
                break;
            }
        }

        scan.close();
    }

    /**
     * Program entry point. instantiate the game object.
     *
     * @param args command line arguments(not used)
     */
    public static void main(final String[] args)
    {
        Game test = new Game();
    }
}

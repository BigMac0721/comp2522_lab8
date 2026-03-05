package ca.bcit.comp2522.lab8;

import java.util.List;
import java.util.Scanner;

/**
 * Logic for the main game and the main game loop
 *
 * @author Giant
 * @author
 *
 * @version 2026
 */
public class Game
{
    private final List<String> countries;
    private final String secretCountry;
    private final int countryLength;
    private int attempt;

    public Game()
    {
        attempt = 0;
        countries = WordList.COUNTRY_LIST;
        secretCountry = pickSecretCountry();
        countryLength = secretCountry.length();

        printHeaderInformation();
        mainGame();
    }

    private void printHeaderInformation()
    {
        System.out.println("LUCKY VAULT — COUNTRY MODE. Type QUIT to exit.");
        System.out.println("Secret word length: " + countryLength);
        System.out.println("Current best: " + "-");
    }

    private String pickSecretCountry()
    {
        final int randomIndex;
        randomIndex = (int) (Math.random() * countries.size());

        return countries.get(randomIndex);
    }

    private boolean isCorrectGuess(String input)
    {
        int correctPosition = 0;

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
            for(int i = 0; i < countryLength; i++)
            {
               if(input.charAt(i) == secretCountry.charAt(i))
               {
                   correctPosition++;
               }
            }

            System.out.println("Not it. " + correctPosition + " letter(s) corect (right position).");
        }

        return false;
    }

    private boolean isEmptyGuess(final String input)
    {
        if(input == null || input.isEmpty())
        {
            System.out.println("Empty guess. Try again.");

            return true;
        }

        return false;
    }

    public void mainGame()
    {
        String input;
        final Scanner scan = new Scanner(System.in);

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
    }

    public static void main(final String[] args)
    {
        Game test = new Game();
    }
}

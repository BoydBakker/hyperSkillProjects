package bullscows;
import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter a number between 1-36 for the secret code:");
        Scanner scanner = new Scanner(System.in);

        int codeLength = 0;
        try {
            codeLength = scanner.nextInt();
            if (codeLength <= 0 || codeLength > 36) {
                throw new IllegalArgumentException("Invalid code length. Please enter a value between 1 and 36.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter an integer number.");
            return; // End the program
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return; // End the program
        }

        System.out.println("Input a number between " + codeLength + "-36 for possible symbols in the code:");
        int symbolRange = Integer.MAX_VALUE;

        try {
            symbolRange = scanner.nextInt();
            if (symbolRange <= 0 || symbolRange > 36) {
                throw new IllegalArgumentException("Invalid symbol range. Please enter a value between " + codeLength + " and 36.");
            } else if (symbolRange < codeLength) {
                throw new IllegalArgumentException("It's not possible to generate a code with a length of " + codeLength + " with " + symbolRange + " unique symbols.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter an integer number.");
            return; // End the program
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return; // End the program
        }

        String numStr = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder asterisks = new StringBuilder();

        for (int i = 0; i < codeLength; i++) {
            asterisks.append("*");
        }

        int symbolRangeHelp = symbolRange - 1;

        if (symbolRange <= 10) {
            System.out.println("The secret is prepared: " + asterisks + " " + "(0-" + symbolRangeHelp + ").");

        } else if (symbolRange > 10) {
            System.out.println("The secret is prepared: " + asterisks + " " + "(0-9" + ", a-" + numStr.charAt(symbolRangeHelp) + ").");
        }

        System.out.println("Okay, let's start a game!");

        StringBuilder secrets = new StringBuilder(numStr);
        String extractedSubstring = secrets.substring(0, symbolRange);
        StringBuilder randomCode = new StringBuilder(extractedSubstring);

        // Fisher-Yates algorithm to generate a random code without duplicates
        Random random = new Random();
        for (int i = randomCode.length() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // Swap characters
            char temp = randomCode.charAt(i);
            randomCode.setCharAt(i, randomCode.charAt(j));
            randomCode.setCharAt(j, temp);
        }

        String secretNumber = randomCode.substring(0, codeLength);

        int cow = 0;
        int bull = 0;
        int turn = 1;
        String guess;
        Boolean win = false;

        while (!win) {
            String guesses = scanner.nextLine();
            guess = String.valueOf(guesses);
            if(guesses.length() != secretNumber.length()){
                System.out.println("Make sure the length of the answer is the same as the length of the secret code.");
                continue;
            }
            System.out.println("turn: " + turn);
            cow = 0;
            bull = 0;
            for (int i = 0; i < guess.length(); i++) {
                for (int j = 0; j < secretNumber.length(); j++) {
                    if (i == j && guesses.charAt(i) == secretNumber.charAt(j)) {
                        bull++;
                    } else if (guesses.charAt(i) == secretNumber.charAt(j) && i != j) {
                        cow++;
                    }
            }
            }
            if (bull == codeLength) {
                System.out.println("Grade: " + codeLength + " bulls\n" + "Congratulations! You guessed the secret code.");
                win = true;
                break;
            } else {
                System.out.println("Grade: " + bull + " bull(s) and " + cow + " cow(s).");
                turn++;
            }
        }
    }
}

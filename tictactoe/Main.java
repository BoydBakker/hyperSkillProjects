package tictactoe;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] arr = new char[3][3];
        int row, col;
        int count = 0;
        boolean winner = false;
        boolean impossible = false;

        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                arr[i][j] = ' ';
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("| ");
        }
        System.out.println("---------");

        while(!winner && !impossible) {
            String input = scanner.nextLine();

            if (!input.matches("\\d+\\s\\d+")) {
                System.out.println("Invalid input format. Please enter two integers separated by a space.");
                continue;
            }

            String[] coordinates = input.split(" ");
            row = Integer.parseInt(coordinates[0]) - 1;
            col = Integer.parseInt(coordinates[1]) - 1;

            if (row > 2 || row < 0 || col > 2 || col < 0) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } else if (arr[row][col] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            if (count % 2 == 0) {
                arr[row][col] = 'X';
            } else {
                arr[row][col] = 'O';
            }

            count++;

            System.out.println("---------");
            for (int i = 0; i < 3; i++) {
                System.out.print("| ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println("| ");
            }
            System.out.println("---------");

            for (int i = 0; i < 3; i++) {
                if (arr[i][0] != ' ' && arr[i][0] == arr[i][1] && arr[i][0] == arr[i][2]) {
                    System.out.println(arr[i][1] + " wins");
                    winner = true;
                    break;
                } else if (arr[0][i] != ' ' && arr[0][i] == arr[1][i] && arr[0][i] == arr[2][i]) {
                    System.out.println(arr[1][i] + " wins");
                    winner = true;
                    break;
                } else if (arr[0][0] != ' ' && arr[0][0] == arr[1][1] && arr[0][0] == arr[2][2]) {
                    System.out.println(arr[1][1] + " wins");
                    winner = true;
                    break;
                } else if (arr[0][2] != ' ' && arr[0][2] == arr[1][1] && arr[0][2] == arr[2][0]) {
                    System.out.println(arr[1][1] + " wins");
                    winner = true;
                    break;
                }
            }

            int impossibleCounter = 0;
            for(int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (arr[i][j] != ' ') {
                        impossibleCounter++;
                        if (impossibleCounter == 9 && winner == false) {
                            System.out.println("Draw");
                            impossible = true;
                        }
                    }
                }
            }


            }



    }
    }
package rip.haris;

import java.util.*;

public class TicTacToe {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static ArrayList<Integer> positions = new ArrayList<>();
    public static ArrayList<Integer> personPositions = new ArrayList<>();
    public static ArrayList<Integer> computerPositions = new ArrayList<>();
    private static final List<List<Integer>> WINNING_POSITIONS = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5, 6),
            Arrays.asList(7, 8, 9),
            Arrays.asList(1, 4, 7),
            Arrays.asList(2, 5, 8),
            Arrays.asList(3, 6, 9),
            Arrays.asList(1, 5, 9),
            Arrays.asList(3, 5, 7)
    );

    public static void main(String[] args) {
        char[][] board = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        welcome();
        while (true) {
            placeMove(board,"person");
            printBoard(board);
            if (checkGameOver()) break;
            placeMove(board,"computer");
            printBoard(board);
            if (checkGameOver()) break;
        }
    }

    public static void welcome() {
        System.out.println("----------------------");
        System.out.println("Welcome to TicTacToe!");
        System.out.println("----------------------");
        System.out.println();
    }

    public static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
         }
        System.out.println();
    }

    public static void placeMove(char[][] board, String player) {
        int position;
        char symbol;

        if (player.equals("person")) {
            symbol = 'X';

            System.out.print("Enter your move (1-9): ");
            position = personMove();
            personPositions.add(position);
        } else {
            symbol = 'O';

            position = computerMove();
            computerPositions.add(position);

            System.out.println("Computer played: " + position);
        }

        positions.add(position);

        switch (position) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
        }
    }

    public static int personMove() {
        int position;

        while (true) {
            position = scanner.nextInt();

            if (!positions.contains(position) && (position < 10 && position > 0)) {
                return position;
            }

            System.out.println("That position is already filled! Please try again!");
        }
    }

    public static int computerMove() {
        int position;

        while (true) {
            position = random.nextInt(1, 10);

            if (!positions.contains(position)) {
                return position;
            }
        }
    }

    public static String checkWinner() {
        for (List<Integer> list : WINNING_POSITIONS) {
            if (personPositions.containsAll(list)) {
                return "You won";
            } else if (computerPositions.containsAll(list)) {
                return "Computer won";
            }
        }

        if (positions.size() == 9) {
            return "Tie";
        }

        return "";
    }

    public static boolean checkGameOver() {
        String result = checkWinner();

        if (!result.isEmpty()) {
            System.out.println(result + "!");
            return true;
        } return false;
    }
}

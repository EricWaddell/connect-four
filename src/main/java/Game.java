import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {

    public static final int defaultPlayers = 2;
    public static final int defaultColumns = 7;
    public static final int defaultRows = 6;

    public static final String propertiesPath = "src/main/resources/Game.properties";

    private int players;
    private int columns;
    private int rows;

    private int turn;
    private Board board;
    private Player currentPlayer;

    public Game(){
        //Read properties file and default if necessary
        try {
            Scanner sc = new Scanner(new File(propertiesPath));
            if(sc.hasNextLine()){
                players = Integer.parseInt(sc.nextLine());
                if(players > 10){
                    throw new IllegalArgumentException();
                }
            } else {
                throw new IllegalArgumentException();
            }
            if(sc.hasNextLine()){
                columns = Integer.parseInt(sc.nextLine());
            } else {
                throw new IllegalArgumentException();
            }
            if(sc.hasNextLine()){
                rows = Integer.parseInt(sc.nextLine());
            } else {
                throw new IllegalArgumentException();
            }
        } catch (FileNotFoundException | IllegalArgumentException ex) {
            players = defaultPlayers;
            columns = defaultColumns;
            rows = defaultRows;
        }

        //Initialize remaining values
        turn = 1;
        currentPlayer = Player.findByPlayerNumber(turn);
        board = new Board(columns, rows);
    }

    //The overall game logic is quite simple, showing the turn is equivalent to a render call. Processing the turn is equivalent to a logic step. Since this isn't a real time game there is no issue with
    //locking these steps together, then we show again.
    public void run(){
        showTurn();
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            int entry;
            try {
                entry = Integer.parseInt(in.nextLine());
                processTurn(entry);
                showTurn();
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
                System.out.println("Invalid Choice - Please enter an integer between 1 and " + columns);
                showTurn();
                continue;
            }
        }
    }

    //Format is "Player <number> [<color>] wins!"
    private void showWinner(){
        System.out.println(board.boardRepresentation());
        StringBuilder sb = new StringBuilder();
        sb.append("Player ");
        sb.append(currentPlayer.getPlayerNumber());
        sb.append(" [");
        sb.append(currentPlayer.getPlayerColor());
        sb.append("] wins!");
        System.out.println(sb.toString());
    }

    private void showDraw(){
        System.out.println(board.boardRepresentation());
        System.out.println("Draw!");
    }

    private void showTurn(){
        System.out.println(board.boardRepresentation());
        System.out.print(linePrompt(currentPlayer, columns));
    }

    //The line prompt format is "Player <number> [<color>] - choose column (1-<columns>): "
    private String linePrompt(Player currentPlayer, int columns){
        StringBuilder sb = new StringBuilder();
        sb.append("Player ");
        sb.append(currentPlayer.getPlayerNumber());
        sb.append(" [");
        sb.append(currentPlayer.getPlayerColor());
        sb.append("] - choose column (1-");
        sb.append(columns);
        sb.append("): ");
        return sb.toString();
    }

    //Process turn includes exit logic on wins and losses.
    private void processTurn(int entry){
        //Our matrix is 0 indexed, the game is 1 indexed
        int column = entry - 1;
        //On invalid input we return early, avoiding the change in turn, keeping the current player the same.
        if(board.addPiece(currentPlayer, column) == -1){
            System.out.println("Invalid Choice - That column has no more space");
            return;
        }
        if(board.isWinner(currentPlayer)){
            showWinner();
            System.exit(0);
        }
        if(board.isDraw()){
            showDraw();
            System.exit(0);
        }
        changeTurn();
    }

    private void changeTurn(){
        turn++;
        if(turn > players){
            turn = 1;
        }
        currentPlayer = Player.findByPlayerNumber(turn);
    }
}

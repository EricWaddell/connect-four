public enum Player {
    PLAYER1(1,"RED",'R'),
    PLAYER2(2,"GREEN",'G'),
    PLAYER3(3,"BLUE",'B'),
    PLAYER4(4,"CYAN",'C'),
    PLAYER5(5,"MAGENTA",'M'),
    PLAYER6(6,"YELLOW",'Y'),
    PLAYER7(7,"WHITE",'W'),
    PLAYER8(8,"BLACK",'K'),
    PLAYER9(9,"SILVER",'S'),
    PLAYER10(10,"GOLD",'$');

    private final int playerNumber;
    private final String playerColor;
    private final char playerPiece;

    private Player (int playerNumber, String playerColor, char playerPiece) {
        this.playerNumber = playerNumber;
        this.playerColor = playerColor;
        this.playerPiece = playerPiece;
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    public String getPlayerColor() {
        return this.playerColor;
    }

    public char getPlayerPiece() {
        return this.playerPiece;
    }

    //It has been considered to use a hashmap for o(1) access to the elements of the player enum, but upon testing, iteration is still faster up to about 30 elements
    public static Player findByPlayerNumber(int number) {
        Player[] players = Player.values();
        for (Player player : players) {
            if (player.playerNumber == number) {
                return player;
            }
        }
        return null;
    }
}

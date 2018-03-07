public class Board {

    private int rows;
    private int columns;

    //This should be private. I've explained elsewhere that this code will not be including external dependencies like mocking frameworks so I have no way to inspect
    //the data in this class without making it public.
    public char [][] boardMatrix;

    //The Board constructor will take values from the Game.properties files if it exists and they are valid.
    //If any of the values are illegal for any reason all the default values will be used.
    public Board(int columns, int rows){
        this.rows = rows;
        this.columns = columns;

        boardMatrix = new char[rows][columns];
        clearBoard();
    }

    public int addPiece(Player player, int column) {
        int row = getLowestAvailableRow(column);
        if(row == -1)
            return row;
        boardMatrix[row][column] = player.getPlayerPiece();
        return 0;
    }

    //This method evaluates arbitrary boards for a specific player's win. It could be a decent candidate for optimization if needed. Rather than checking all possible combinations, pass in the column that
    //the last piece was added and then check in 7 directions (up is unnecessary) if the 4 pieces starting on the last added piece are the same character. Currently this method is preferred due to the small
    //default board size and the flexibility it offers.
    public boolean isWinner(Player player) {
        char piece = player.getPlayerPiece();
        //Horizontal
        for(int i=0;i<rows-3;i++){
            for(int j=0;j<columns;j++){
                if(boardMatrix[i][j] == piece && boardMatrix[i+1][j] == piece && boardMatrix[i+2][j] == piece && boardMatrix[i+3][j] == piece)
                    return true;
            }
        }
        //Vertical
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns-3;j++){
                if(boardMatrix[i][j] == piece && boardMatrix[i][j+1] == piece && boardMatrix[i][j+2] == piece && boardMatrix[i][j+3] == piece)
                    return true;
            }
        }
        //Ascending
        for(int i=0;i<rows-3;i++){
            for(int j=3;j<columns;j++){
                if(boardMatrix[i][j] == piece && boardMatrix[i+1][j-1] == piece && boardMatrix[i+2][j-2] == piece && boardMatrix[i+3][j-3] == piece)
                    return true;
            }
        }
        //Descending
        for(int i=3;i<rows;i++){
            for(int j=3;j<columns;j++){
                if(boardMatrix[i][j] == piece && boardMatrix[i-1][j-1] == piece && boardMatrix[i-2][j-2] == piece && boardMatrix[i-3][j-3] == piece)
                    return true;
            }
        }
        return false;
    }

    //isDraw is called after isWinner, since we know it is not a winner, we only need to check that all spots are taken up without a winner
    public boolean isDraw() {
        for(int i=0;i<rows;i++) {
            for(int j=0;j<columns;j++) {
                if(boardMatrix[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    public int getLowestAvailableRow(int column) {
        for(int i=0;i<rows;i++){
            if(boardMatrix[i][column] == ' '){
                return i;
            }
        }
        return -1;
    }

    public void clearBoard() {
        for(int i=0;i<rows;i++) {
            for(int j=0;j<columns;j++) {
                boardMatrix[i][j] = ' ';
            }
        }
    }

    //We draw from the top because I prefer thinking of the pieces falling towards 0.
    public String boardRepresentation() {
        int endrow = rows-1;
        int endcol = columns-1;
        StringBuilder sb = new StringBuilder();
        for(int i=endrow;i>=0;i--) {
            for(int j=0;j<columns;j++) {
                sb.append("|");
                sb.append(boardMatrix[i][j]);
            }
            sb.append("|");
            sb.append("\n");
        }
        return sb.toString();
    }
}

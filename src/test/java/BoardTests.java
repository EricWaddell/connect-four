public class BoardTests {

    //Writing tests without a testing framework is an exercise in futility.
    public static boolean addPieceTest(){
        Board board = new Board(7,6);
        board.addPiece(Player.PLAYER1,2);

        if(board.boardMatrix[0][2] == Player.PLAYER1.getPlayerPiece())
            return true;
        return false;
    }

    public static boolean clearBoardTest(){
        Board board = new Board(7,6);
        board.clearBoard();

        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                if(board.boardMatrix[i][j] != ' '){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean drawTest(){
        Board board = new Board(7,6);
        boolean p1turn = true;
        for(int j=0;j<7;j=j+2){
            for(int i=0;i<6;i++){
                if(p1turn){
                    board.addPiece(Player.PLAYER1,j);
                }else{
                    board.addPiece(Player.PLAYER2,j);
                }
                p1turn = !p1turn;
            }
        }
        for(int j=0;j<6;j++){
            for(int i=1;i<7;i=i+2){
                if(p1turn){
                    board.addPiece(Player.PLAYER1,i);
                }else{
                    board.addPiece(Player.PLAYER2,i);
                }
                p1turn = !p1turn;
            }
        }
        return board.isDraw();
    }

    public static boolean horizontalWinTest(){
        Board board = new Board(7,6);
        for(int i=0;i<4;i++){
            board.addPiece(Player.PLAYER1,i);
        }
        return board.isWinner(Player.PLAYER1);
    }

    public static boolean verticalWinTest(){
        Board board = new Board(7,6);
        for(int i=0;i<4;i++){
            board.addPiece(Player.PLAYER1,0);
        }
        return board.isWinner(Player.PLAYER1);
    }

    public static boolean ascendingWinTest(){
        Board board = new Board(7,6);
        board.addPiece(Player.PLAYER2,1);
        board.addPiece(Player.PLAYER2,2);
        board.addPiece(Player.PLAYER2,2);
        board.addPiece(Player.PLAYER2,3);
        board.addPiece(Player.PLAYER2,3);
        board.addPiece(Player.PLAYER2,3);
        board.addPiece(Player.PLAYER1, 0);
        board.addPiece(Player.PLAYER1, 1);
        board.addPiece(Player.PLAYER1, 2);
        board.addPiece(Player.PLAYER1, 3);
        return board.isWinner(Player.PLAYER1);
    }

    public static boolean descendingWinTest(){
        Board board = new Board(7,6);
        board.addPiece(Player.PLAYER2,0);
        board.addPiece(Player.PLAYER2,0);
        board.addPiece(Player.PLAYER2,0);
        board.addPiece(Player.PLAYER2,1);
        board.addPiece(Player.PLAYER2,1);
        board.addPiece(Player.PLAYER2,2);
        board.addPiece(Player.PLAYER1, 0);
        board.addPiece(Player.PLAYER1, 1);
        board.addPiece(Player.PLAYER1, 2);
        board.addPiece(Player.PLAYER1, 3);
        return board.isWinner(Player.PLAYER1);
    }

    public static void main(String[] args){
        System.out.println("addPieceTest: " + test(addPieceTest()));
        System.out.println("clearBoardTest: " + test(clearBoardTest()));
        System.out.println("drawTest: " + test(drawTest()));
        System.out.println("horizontalWinTest: " + test(horizontalWinTest()));
        System.out.println("verticalWinTest: " + test(verticalWinTest()));
        System.out.println("ascendingWinTest: " + test(ascendingWinTest()));
        System.out.println("descendingWinTest: " + test(descendingWinTest()));
    }

    public static String test(boolean test){
        if(test) {
            return "PASSED";
        } else {
            return "FAILED";
        }
    }
}

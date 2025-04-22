import javax.sound.midi.Soundbank;
import java.util.*;
public class game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //board of 3 * 3
        char[][] board = new char[3][3];
        //defining board with empty spaces
        for(int row=0; row<board.length; row++){
            for(int col=0; col<board.length; col++){
                board[row][col] = ' ';
            }
        }
        //Player can be X or O
        char player = 'X';
        //Game goingOn or finished
        boolean gameOver = false;

        //till the time, the game is not over, keep printing the board with available spaces
        while(!gameOver){
            printBoard(board);
            System.out.println("Player " + player + "'s turn: ");
            int row = sc.nextInt();
            int col = sc.nextInt();

            //check if that place is empty or available
            if(board[row][col] == ' '){
                //if so, place the element
                board[row][col] = player;
                //if game is over by this move, exit
                gameOver = haveWon(board, player);
                //if this player won by this move
                if(gameOver){
                     System.out.println("Player " + player + " has won!!!!!!!!!!!");
                }else{
                    //change the turn and give it to the other player
                    player = (player == 'X') ? 'O' : 'X';
                }
            }else{
                //or if place is not available
                System.out.println("Invalid move");
            }
        }
        printBoard(board);
    }

    private static void printBoard(char[][] board) {
        for(int row=0; row<board.length; row++){
            for(int col=0; col<board.length; col++){
                System.out.print(board[row][col] +" ");
            }
            System.out.println();
        }
    }
    private static boolean haveWon(char[][] board, char player) {
        // winning conditions: any row complete / any column complete / any diagonal complete

        //row check
        //iterate hrough each row and check for the presence of same player at each of its column
        for(int row=0; row<board.length; row++){
            if(board[row][0] == player && board[row][1] == player && board[row][2] == player ){
                return true;
            }
        }

        //column check
        for(int col=0; col<board[0].length; col++){
            if(board[0][col] == player && board[1][col] == player && board[2][col] == player ){
                return true;
            }
        }

        //diagonal check
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player){
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player){
            return true;
        }
        return false;
    }
}

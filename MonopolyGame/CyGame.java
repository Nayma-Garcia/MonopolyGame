package hw2;
/**
 * Model of a Monopoly-like game. Two players take turns rolling dice to move
 * around a board. The game ends when one of the players has at least
 * MONEY_TO_WIN money or one of the players goes bankrupt (has negative money).
 * 
 * @author YOUR_NAME_HERE
 */
public class CyGame {
/**
 * Do nothing square type.
 */
public static final int DO_NOTHING = 0;
/**
 * Pass go square type.
 */
public static final int PASS_GO = 1;
/**
 * Cyclone square type.
 */
public static final int CYCLONE = 2;
/**
 * Pay the other player square type.
 */
public static final int PAY_PLAYER = 3;
/**
 * Get an extra turn square type.
 */
public static final int EXTRA_TURN = 4;
/**
 * Jump forward square type.
 */
public static final int JUMP_FORWARD = 5;
/**
 * Stuck square type.
 */
public static final int STUCK = 6;
/**
 * Points awarded when landing on or passing over go.
 */
public static final int PASS_GO_PRIZE = 200;
/**
 * The amount payed to the other player per unit when landing on a
 * PAY_PLAYER square.
 */
public static final int PAYMENT_PER_UNIT = 20;
/**
 * The amount of money required to win.
 */
public static final int MONEY_TO_WIN = 400;
/**
 * The cost of one unit.
 */
public static final int UNIT_COST = 50;


	//Instance variables
		//keeps track of which player is playing
	private int playerTracker;
	
		//keeps track of player 1's square, money and units
	private int player1Square;
	private int player1Money;
	private int player1Units;
	
		//keeps track of player 2's square, money, and units
	private int player2Square;
	private int player2Money;
	private int player2Units;
	
	//getting a square type
	private int squareNumber;
	
	//number of squares
	private int numSquares;




// TODO: EVERTHING ELSE
// Note that this code will not compile until you have put in stubs for all
// the required methods.
// The method below is provided for you and you should not modify it.
// The compile errors will go away after you have written stubs for the
// rest of the API methods.

		//methods

	/**
	 * Constructs a game that has the number of squares and 
	 * starts both players on square one
	 * @param numSquares - number of squares
	 * @param startingMoney - amount of money they start with
	 */
	public CyGame(int numSquares, int startingMoney) {
		playerTracker = 1;
		player1Square = 0;
		player2Square = 0;
		player1Money = startingMoney;
		player2Money = startingMoney;
		player1Units = 1;
		player2Units = 1;
		this.numSquares = numSquares;
		
	}
	
	
	/**
	 * Indicates the current player attempts to buy one unit
	 */
	public void buyUnit() {
	if(isGameEnded() == false) { // checks if the game has ended
		
		if(getCurrentPlayer() == 1) { // checks to see if its player once
			if(getSquareType(player1Square) == DO_NOTHING && player1Money >= UNIT_COST) {
				player1Money -= UNIT_COST;
				player1Units += 1;
				endTurn();
				}
			}else if(getCurrentPlayer() == 2) {
				if(getSquareType(player2Square) == DO_NOTHING && player2Money >= UNIT_COST) {
					player2Money -= UNIT_COST;
					player2Units += 1;
					endTurn();
				}
			}
		}
	}
	
	
	
	/**
	 * Indicates the current player passes or completes their turn
	 */
	public void endTurn() {
			if(getCurrentPlayer() == 1) {
				playerTracker = 2;
				return;
			}else if(getCurrentPlayer() == 2) {
				playerTracker = 1;
				return;
			}
	}
	
	
	/**
	 * Gets the current player
	 * @return
	 */
	public int getCurrentPlayer() {
		return  playerTracker;
	}
	
	
	/**
	 * Gets player 1's money
	 * @return
	 */
	public int getPlayer1Money() {
		return player1Money;
	}
	
	
	/**
	 * Get player 1's square
	 * @return
	 */
	public int getPlayer1Square() {
		return player1Square;
	}
	
	
	/**
	 * Get Player 1's units
	 * @return
	 */
	public int getPlayer1Units() {
		return player1Units;
	}
	
	
	/**
	 * Gets player 2's money
	 * @return
	 */
	public int getPlayer2Money() {
		return player2Money;
	}
	
	
	/**
	 * Gets player 2's square
	 * @return
	 */
	public int getPlayer2Square() {
		return player2Square;
	}
	
	
	/**
	 * Gets player 2's units
	 * @return
	 */
	public int getPlayer2Units() {
		return player2Units;
	}
	
	
	/**
	 * Gets the type of square for the given square number
	 * @param square - the square number
	 * @return
	 */
	public int getSquareType(int square) {
		
		if(square == 0) {
			squareNumber = PASS_GO;
		}else if(square == numSquares - 1) {
			squareNumber = CYCLONE;
		}else if(square % 5 == 0) {
			squareNumber = PAY_PLAYER;
		}else if((square % 7 == 0) || (square % 11 == 0)) {
			squareNumber = EXTRA_TURN;
		}else if(square % 3 == 0) {
			squareNumber = STUCK;
		}else if(square % 2 == 0) {
			squareNumber = JUMP_FORWARD;
		}else {
			squareNumber = DO_NOTHING;
		}
		
		
		return squareNumber;
	}
	
	
	/**
	 * Returns true if game is over, false if otherwise
	 * @return
	 */
	public boolean isGameEnded() {
		
		if((player1Money >= MONEY_TO_WIN || player2Money >= MONEY_TO_WIN) || (player1Money < 0 || player2Money < 0)) {
			return true;
		}else {
			return false;
		}
		
		
		
	}
	
	
	/**
	 * Indicates the dice has been rolled
	 * @param value
	 */
	public void roll(int value) {
		
	
	//checking if the game has ended
	if(isGameEnded() == false)	{
		
		//Tracking Player 1's movements
		if(getCurrentPlayer() == 1) {
		
		//checks to see if the square is on STUCK before adding the roll value, if it is on STUCK it doesnt run
			if((getSquareType(player1Square) == STUCK) && (value % 2 != 0)) {
				endTurn();
				return;
			}else {
			
		// adds the value of the roll to the players square number
		player1Square += value;
		
		//goes through the square rules from the assignment spec sheet
		
		//running if its on the PASS_GO square or if it passes over it
		if((getSquareType(player1Square) == PASS_GO) || (getSquareType(player1Square) >= numSquares)) {
			player1Money += PASS_GO_PRIZE;
			endTurn();
			
		//running if its on the CYCLONE square
		}else if(getSquareType(player1Square) == CYCLONE) {
			player1Square = player2Square;
			endTurn();
			
		// running if its on the PAY_PLAYER square
		}else if(getSquareType(player1Square) == PAY_PLAYER) {
			player2Money += PAYMENT_PER_UNIT * player2Units;
			player1Money -= PAYMENT_PER_UNIT * player2Units;
			endTurn();
			
		//running if its on the EXTRA_TURN square
		}else if(getSquareType(player1Square) == EXTRA_TURN) {
			playerTracker = 1;
			
			
		//running if its on the STUCK square
		}else if(getSquareType(player1Square) == STUCK) {
			if(value % 2 == 0) {
				endTurn();
			}else {
				endTurn();
			}
			
		//running if its on the JUMP_FORWARD square
		}else if(getSquareType(player1Square) == JUMP_FORWARD) {
			player1Square += 4;
			//checking to see if after they jump forward 4 squares they land on the pass go square, if they do they get the prize
			if(getSquareType(player1Square) == PASS_GO || squareNumber >= numSquares) {
				player1Money += PASS_GO_PRIZE;
			}
			endTurn();
			
		//running if its on the DO_NOTHING square
		}else if(getSquareType(player1Square) == DO_NOTHING){
			endTurn();
		}
		}

		
	
	//Tracking Player  2's Movements
		}else if(getCurrentPlayer() == 2) {
			
			//checks to see if the square is on STUCK before adding the roll value, if it is on STUCK it doesnt run
			if((getSquareType(player2Square) == STUCK) && (value % 2 != 0)) {
				endTurn();
				return;
			}else {
				
			// adds the value of the roll value to the players square number
			player2Square += value;
			
			//goes through the square rules from the assignment spec sheet
			
			// running if its on the PAY_PLAYER square
			if(getSquareType(player2Square) == PASS_GO || getSquareType(player2Square) >= numSquares) {
				player2Money += PASS_GO_PRIZE;
				endTurn();
				
			//running if its on the CYCLONE square
			}else if(getSquareType(player2Square) == CYCLONE) {
				player2Square = player1Square;
				endTurn();
				
			// running if its on the PAY_PLAYER square
			}else if(getSquareType(player2Square) == PAY_PLAYER) {
				player1Money += PAYMENT_PER_UNIT * player1Units;
				player2Money -= PAYMENT_PER_UNIT * player1Units;
				endTurn();
				
			//running if its on the EXTRA_TURN square
			}else if(getSquareType(player2Square) == EXTRA_TURN) {
				playerTracker = 2;
				
				//running if its on the STUCK square
			}else if(getSquareType(player2Square) == STUCK) {
				if(value % 2 == 0) {
					endTurn();
				}else {
					endTurn();
				}
				
			//running if its on the JUMP_FORWARD square
			}else if(getSquareType(player2Square) == JUMP_FORWARD) {
				player2Square += 4;
				if(getSquareType(player2Square) == PASS_GO || squareNumber >= numSquares) {
					player2Money += PASS_GO_PRIZE;
				}
				endTurn();
				
				//running if its on the DO_NOTHING square
			}else if(getSquareType(player2Square) == DO_NOTHING){
				endTurn();
			}
		}
		}
	
	}
	}
	
	
	/**
	 * Indicates the current player attempts to sell one unit
	 */
	public void sellUnit() {
		
	if(isGameEnded() == false)	{
		if(getCurrentPlayer() == 1) {
		if(player1Units >= 1) {
			player1Money += UNIT_COST;
			player1Units -= 1;
			endTurn();
		}else if(getCurrentPlayer() == 2) {
		if(player2Units >= 1) {
			player2Money += UNIT_COST;
			player2Units -= 1;
			endTurn();
		}
		}

		}
	}
	}
	

/**
 * Returns a one-line string representation of the current game state. The
 * format is:
 * <p>
 * <tt>Player 1*: (0, 0, $0) Player 2: (0, 0, $0)</tt>
 * <p>
 * The asterisks next to the player's name indicates which players turn it
 * is. The numbers (0, 0, $0) indicate which square the player is on, how
 * many units the player has, and how much money the player has
 * respectively.
 * 
 * @return one-line string representation of the game state
 */
public String toString() {
String fmt = "Player 1%s: (%d, %d, $%d) Player 2%s: (%d, %d, $%d)";
String player1Turn = "";
String player2Turn = "";
if (getCurrentPlayer() == 1) {
player1Turn = "*";
} else {
player2Turn = "*";
}
return String.format(fmt,
player1Turn, getPlayer1Square(), getPlayer1Units(), getPlayer1Money(),
player2Turn, getPlayer2Square(), getPlayer2Units(), getPlayer2Money());
}
}

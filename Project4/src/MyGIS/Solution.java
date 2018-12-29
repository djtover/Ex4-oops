package MyGIS;
/**
 * This is class that represents the solution of the game
 * Field 1: This is the game that is to be with the game after it has run
 * @author djtov
 *
 */
public class Solution {
	
	private Game game;

	public Solution( Game g) {
		game = g;
	}
	public Solution(Solution other) {
		game = other.game;
	}
	public String toString() {
		return game.toString();
	}
	public Game getGame() {
		return game;
	}
}

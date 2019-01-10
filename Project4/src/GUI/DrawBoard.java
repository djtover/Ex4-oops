package GUI;

import java.util.ArrayList;

import FileFormat.FromBoard;
/**
 * This is a Thread that updates the location of the Pacman and Players in the Game
 * @author David Tover
 *
 */
public class DrawBoard implements Runnable{
	private MainWindow mw;
	/**
	 * This is a Constructor for the thread
	 * @param MW is the mainWindow that you would like to update
	 */
	public DrawBoard(MainWindow MW) {
		this.mw = MW;
	}
	@Override
	/**
	 * This is the run method in the thread that updates the game on where everyone is
	 */
	public void run() {
		// TODO Auto-generated method stub
		double time=100000;
		double totalTime = mw.getTime(mw.getPlay1().getStatistics());
		while( !mw.getPointsFruit().isEmpty() && totalTime<time ) {
			mw.repaint();
			ArrayList<String> board_data = mw.getPlay1().getBoard();
			FromBoard fb = new FromBoard(board_data);
			mw.updateBoard(fb);
			totalTime = mw.getTime(mw.getPlay1().getStatistics());
			System.out.println(mw.getPlay1().getStatistics());
			
//			mw.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("End Game:"+mw.getPlay1().getStatistics());
		mw.getPlay1().stop();
		mw.clear();
	}

}

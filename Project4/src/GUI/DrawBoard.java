package GUI;

import java.util.ArrayList;

import FileFormat.FromBoard;

public class DrawBoard implements Runnable{
	private MainWindow mw;
	
	public DrawBoard(MainWindow MW) {
		this.mw = MW;
	}
	@Override
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

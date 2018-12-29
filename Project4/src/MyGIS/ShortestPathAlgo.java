package MyGIS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

import MyCoords.MyCoords;
import Geom.Point3D;

/**
 * This class represents an algorithm that finds the shortest path between coordinates 
 * @author David Tover
 *
 */
public class ShortestPathAlgo {

	//	private Game game;
	private Solution s;
	/**
	 * This is a constructor of the class
	 * @param g input the game that you want to find the shortest paths between coordinates
	 */
	public ShortestPathAlgo(Game g) {
		s = new Solution(findShortest(g));

	}
	/**
	 * This is the algorithm to find the shortest Path 
	 * @param g input the game that you want to find the shortest paths between coordinates
	 * @return the game with the updated paths
	 */
	private Game findShortest(Game g) {

		ArrayList<Fruit> alf = g.getALF();
		ArrayList <Packman> alp = g.getALP();
		ArrayList <Fruit>	alfCopy = new ArrayList<Fruit>(alf);

		for(int i=0;i< alp.size();i++) {
			alp.get(i).setStartingPoint(alp.get(i).getP());
			if(alp.get(i).getPath().size()>0) {
				alp.get(i).getPath().clear();
			}
			alp.get(i).getPath().setTime(0);
		}

		Packman closestP = null;
		Fruit closestF = null;
		MyCoords mc = new MyCoords();
		int indexF=-1;
		int indexP=-1;
		//		if the size is greater than zero than set the first spot in the array list to be the closest
		if(alp.size()>0) {
			closestP = alp.get(0);
			indexP=0;
		}
		if(alfCopy.size()>0) {
			closestF = alfCopy.get(0);
			indexF = 0;
		}
		//		double closestTime = Double.MAX_VALUE;
		//	while alfCopy is greater than zero because the algorithm will remove Fruits when it finds the closest one to a Pacman
		while(alfCopy.size()>0) {
			// run through all the pacman
			double closestTime = Double.MAX_VALUE;
			for(int i=0; i<alp.size();i++) {
				Packman p1 = alp.get(i);
				//				double closestTime = Double.MAX_VALUE;

				for(int j=0; j<alfCopy.size();j++) {
					Fruit f1 = alfCopy.get(j);
					//					if closest time is greater than the time it to take to get to the next Fruit then update closest time 
					if(closestTime > ((mc.distance3d(p1.getP(), f1.getP())))/(p1.getSpeed()) + p1.getPath().getTime()) {
						closestTime = ((mc.distance3d(p1.getP(), f1.getP()))/p1.getSpeed()) + p1.getPath().getTime();
						closestP = p1;
						closestF = f1;
						indexF = j;
						indexP = i;

					}

				}
			}
			//			update the time of the closest packman and closest Fruit
			//				set the predecessor of the closest fruit, then add the closest fruit to the path of the closest pacman
			//				update the coordinates of the packman to be the coordinates of the fruit it just ate
			//			update the weight of the path
			//				then remove the fruit from the arraylist
			closestF.setTimeStamp((long)(closestP.getTimeStamp()+closestTime));
			closestP.getPath().setTime(closestTime);
			closestF.setPred(closestP);
			closestP.getPath().add(closestF);
			closestP.setP(closestF.getP());
			closestP.getPath().setWeight(closestP.getPath().getWeight() + closestF.getWeight());
			System.out.println(closestTime  +" seconds have passed when Packman "+ closestP.getId() +" finds Fruit " +closestF.getId() + " Weight: "+closestP.getPath().getWeight());

			if(indexF<alfCopy.size()) {
				alfCopy.remove(indexF);

			}
			//			}
			if(alp.size()>0) {
				closestP = alp.get(0);
				indexP=0;
			}
			if(alfCopy.size()>0) {
				closestF = alfCopy.get(0);
				indexF = 0;
			}
		}
//		find the amount of time it take for all the fruits to be eaten
		double topTime =0;
		for(int i=0;i < alp.size();i++) {
			if(topTime < alp.get(i).getPath().getTime()) {
				topTime =  alp.get(i).getPath().getTime();
			}
		}

		Game newG = new Game(alp , alf);
		newG.setTime(topTime);
		System.out.println("Time it takes to run algo in seconds is: " + topTime);
		return newG;

	}
	public Solution getSolution() {
		return s;
	}
//	public static void main(String args[] ) {
//		Packman p1 = new Packman(32.10452628971962,35.20409047113997,0.0,1,1);
//		Packman p2 = new Packman(32.104470411214955,35.21009834992785,0.0,1,1);
//		Fruit f1 = new Fruit(32.10423447975078,35.204555366522364,0.0 ,1);
//		Fruit f2 = new Fruit( 32.103688112149534,35.204362256132754,0.0,1);
//		Fruit f3 = new Fruit(32.10333421495327,35.203911665223664,0.0,1);
//		Fruit f4 = new Fruit( 32.10441453271028,35.20954047546898,0.0,1);
//		Fruit f5 = new Fruit(32.10385574766355,35.20964775901876,0.0,1);
//		Fruit f6 = new Fruit( 32.1033838847352,35.20973358585859,0.0,1);
//
//		ArrayList<Packman> alp = new ArrayList<Packman>();
//		ArrayList <Fruit> alf = new ArrayList<Fruit>();
//		alp.add(p1);
//		alp.add(p2);
//		alf.add(f1);
//		alf.add(f2);
//		alf.add(f3);
//		alf.add(f4);
//		alf.add(f5);
//		alf.add(f6);
//		Game g = new Game(alp,alf);
//		ShortestPathAlgo spa = new ShortestPathAlgo(g);
//				System.out.println(spa.getSolution());
//	}
}
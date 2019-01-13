package Algo;

import java.util.ArrayList;

import MyGIS.Path;
import Geom.Point3D;
import MyCoords.MyCoords;
import MyGIS.Block;
import MyGIS.Fruit;
import MyGIS.Game;
import MyGIS.Ghost;
import MyGIS.Map;
import MyGIS.Player;
import graph.Graph;
import graph.Graph_Algo;
import graph.Node;
/**
 * This is a class represents the Algorithm for the Automatic Mode
 * The main point of the Algorithm is to find the closest fruit from the Player
 * @author David Tover
 *
 */
public class GameAlgo {
	Game game = new Game();
/**
 * This is constructor for the Game Algorithm
 * @param g is the game you want to run the Algorithm with 
 */
	public GameAlgo(Game g) {
		this.game = g;
	}
	/**
	 * This is the Algorithm itself
	 * @param startingPoint is the point you want to find the closest fruit from
	 * @return The Game with the Player having a path of where it needs to go to next 
	 */
	public Game RunAlgo(Point3D startingPoint) {
		ArrayList<Point3D> points = cornersInBoxes();
		Graph graph = buildGraph(points);
		MyCoords mc = new MyCoords();
		ArrayList<Fruit> alfCopy = new ArrayList<Fruit>(game.getALF());
		Player playerCopy = new Player(game.getPlayer());
		playerCopy.setP(startingPoint);
		Path path = new Path();
		String source = "a";
		String target = "b";
		double distance = Double.MAX_VALUE;
		int index = -1;
		boolean isBlocked = false;
		for(int i=0;i<alfCopy.size();i++) {
			if(distance > mc.distance3d(playerCopy.getP(), alfCopy.get(i).getP())) {
				Point3D point1 = playerCopy.getP();
				Point3D point2 =alfCopy.get(i).getP();
				boolean los = LOS(point1,point2);
				if(los) {
					distance =  mc.distance3d(playerCopy.getP(),alfCopy.get(i).getP());
					index = i;
					isBlocked = false;
				}
				else {
					addEdges(graph,points,playerCopy.getP(),alfCopy.get(i).getP());
					Graph_Algo.dijkstra(graph,source);
					Node dest = graph.getNodeByName(target);

					if(distance> dest.getDist()) {
						distance = dest.getDist();
						index = i;
						isBlocked = true;
						Fruit f = new Fruit(points.get(Integer.parseInt(dest.getPath().get(1))),0.0);
						path.add(f);
					}
				}
			}
		}
		if(isBlocked) {
			playerCopy.getPath().add(path.getAL().get(0));
		}
		else {
			if(index!=-1) {
				playerCopy.getPath().add(alfCopy.get(index));
				alfCopy.remove(index);
			}
		}
		
		game.setPlayer(playerCopy);
		return game;
	}
	/**
	 * This is a private method to build a graph with the amount of nodes needed
	 * @param corners is the arraylist of points so you know how many nodes to have on the graph
	 * @return a graph with the amount of nodes as the corners arraylist
	 */
	private Graph buildGraph(ArrayList<Point3D> corners) {
		Graph graph = new Graph();
		String source = "a";
		
		String dest = "b";
		Node sour = new Node(source);
		sour.set_id(0);
		graph.add(sour);

		for(int i=1; i<=corners.size();i++) {
			Node d1 = new Node(""+i);

			d1.set_id(i);
			graph.add(d1);

		}
		Node des = new Node(dest);
		des.set_id(corners.size()+1);
		graph.add(des);
		return graph;
	}
	/**
	 * This is a private method add the edges between nodes in the graph
	 * @param gr is the graph you want to add edges to
	 * @param points is the ArrayList of points that are in the graph 
	 * @param source is the starting point of the graph
	 * @param dest is the destination of the graph
	 */
	private void addEdges(Graph gr,ArrayList<Point3D> points,Point3D source, Point3D dest) {
//		Map m = new Map(1386,642,"Ariel1.png");
		MyCoords mc = new MyCoords();

		points.add(0, source);
		points.add(dest);

		for(int i=0;i<points.size();i++) {
			Point3D point1 = points.get(i);
			for(int j=i;j<points.size();j++) {
				if(i!=j) {
					Point3D point2 = points.get(j);
					boolean hasLos = LOS(point1,point2);
					if(hasLos) {
						if(i==0) {
							gr.addEdge("a", ""+j,  mc.distance3d(point1, point2));
						}
						else if( j==points.size()-1) {
							gr.addEdge(""+i, "b", mc.distance3d(point1, point2));

						}
						else {
							gr.addEdge(""+i, ""+j,  mc.distance3d(point1, point2));
						}
					}
				}
			}
		}
	}
	/**
	 * This is a private method to remove all the corners in the Block that doesn't have neighbors or in another block
	 * @return an ArrayList without the Points that doesn't have neighbors or in another Block
	 */
	private ArrayList<Point3D> cornersInBoxes(){
		ArrayList<Point3D> points = new ArrayList<Point3D>();
		for(int i=0;i<game.getALB().size();i++) {
			points.addAll(game.getALB().get(i).getBlockCorners());
		}
		for(int i=0; i<game.getALB().size();i++) {
			for(int j=0;j<points.size();j++) {
				if(game.getALB().get(i).isInBlock(points.get(j))) {
					points.remove(j);
				}
			}
		}
		for(int i=0;i<points.size();i++) {
			boolean hasNei = hasNeighbors(points.get(i),points,i);
			if(!hasNei) {
				points.remove(i);
			}
		}
		return points;
	}
	/**
	 * This is a private method to check if a certain point has neighbors in the graph
	 * @param gps is the gps point you would like to check
	 * @param points is the ArrayList of points to see if they are neighbored to the gps point
	 * @param index is the index of that gps point in the Arraylist points
	 * @return true if it has neighbors, false if it doesnt
	 */
	private boolean hasNeighbors(Point3D gps, ArrayList<Point3D> points,int index) {
//		Map m = new Map(1386,642,"Ariel1.png");
		for(int i=0;i<points.size();i++) {
			if(i!=index) {
				boolean hasLos = LOS(gps,points.get(i));
				if(hasLos) {
					return true;
				}

			}
		}

		return false;
	}
	/**
	 * This is a method to check if there aren't any Blocks in between 2 Points
	 * @param gps1 First Point
	 * @param gps2 Second Point
	 * @return true if there is Line of sight, false if there isn't
	 */
	public boolean LOS(Point3D gps1,Point3D gps2) {
		MyCoords mc = new MyCoords();
		double aed[] = mc.azimuth_elevation_dist(gps1, gps2);
		double angle = aed[0];
		for(int i=0; i<game.getALB().size();i++) {
			Point3D temp = gps1;
			double dist = mc.distance3d(gps1,gps2);
				while(dist>1) {
					temp = pointInTime(temp,1, angle);
					 dist = mc.distance3d(temp,gps2);
				if(game.getALB().get(i).isInBlock(temp)) {
					return false;
				}
			}
			
		}
		return true;
	}
	private Point3D pointInTime(Point3D gps1, double dist,double angle) {
		MyCoords mc = new MyCoords();
		double y = dist*Math.cos(mc.toRad(angle));
		double x = dist*Math.sin(mc.toRad(angle));
		Point3D v = new Point3D(x,y);
		Point3D point = mc.add(gps1,v);
		return point;

	}
	
}

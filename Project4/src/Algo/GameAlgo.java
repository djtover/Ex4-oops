package Algo;

import java.util.ArrayList;

import Geom.Point3D;
import MyGIS.Block;
import MyGIS.Fruit;
import MyGIS.Game;
import MyGIS.Map;
import MyGIS.Player;
import graph.Graph;
import graph.Node;

public class GameAlgo {
	Game game = new Game();

	public GameAlgo(Game g) {
		this.game = g;
	}
	public Game RunAlgo(Point3D startingPoint) {
		ArrayList<Point3D> points = new ArrayList<Point3D>();
		for(int i=0;i<game.getALB().size();i++) {
			ArrayList<Point3D> corners = game.getALB().get(i).getBlockCorners();
			points.add(corners.get(0));
			points.add(corners.get(1));
			points.add(corners.get(2));
			points.add(corners.get(3));

		}
		Graph graph = new Graph();
		String source = "source";
		String dest = "dest";
		graph.add(new Node(source));
		for(int i=0; i<game.getALB().size();i++) {
			Node d1 = new Node(""+(i*4));
			Node d2= new Node(""+((i*4)+1));
			Node d3 = new Node(""+((i*4)+2));
			Node d4 = new Node(""+((i*4)+3));
			graph.add(d1);
			graph.add(d2);
			graph.add(d3);
			graph.add(d4);
		}
		graph.add(new Node(dest));
		
		
		return null;
	}
	private void addEdges(Graph gr) {
		for(int i=0;i<gr.size();i++) {
			for(int j=i;j<gr.size();j++) {
			
			}
		}
	}
	
	public boolean LOS(Segment seg) {
		LinearEquation le = new LinearEquation(seg);
		Map m = new Map(1386,642,"Ariel1.png");
		for(int i=0; i<game.getALB().size();i++) {
			for(int x = seg.getPoint1().ix();x<=seg.getPoint2().ix();x++) {
				int y = le.f(x);
				Point3D pointPixels = new Point3D(x,y);
				Point3D point = m.Pixels2Coords(pointPixels, m.getWidth(), m.getHeight());
				System.out.println(point);
				if(game.getALB().get(i).isInBlock(point)) {
					return false;
				}
			}
		}
		return true;
		
	}
	public static void main(String []args) {
		Map m = new Map(1386,642,"Ariel1.png");

		Point3D p1 = new Point3D(32.104685,35.207876);
		Point3D p2 = new Point3D(32.103229,35.203154);
		Segment s = new Segment (m.Coords2Pixels(p1),m.Coords2Pixels(p2));
		System.out.println(s);
		System.out.println(m.Pixels2Coords(s.getPoint1(), m.getWidth(), m.getHeight()));
		System.out.println(m.Pixels2Coords(s.getPoint2(), m.getWidth(), m.getHeight()));
System.out.println();
		Block b= new Block(32.105361,35.202029,0,32.106425,35.208025,0);
		Game g = new Game();
		g.addBlock(b);
		GameAlgo ga = new GameAlgo(g);
		System.out.println(ga.LOS(s));

	}
}

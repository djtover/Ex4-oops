package Algo;

import java.util.ArrayList;

import Geom.Point3D;
import MyGIS.Block;
import MyGIS.Fruit;
import MyGIS.Game;
import MyGIS.Player;
import graph.Graph;
import graph.Node;

public class GameAlgo {
	Game game = new Game();

	public GameAlgo(Game g) {
		this.game = g;
	}
	public Game RunAlgo() {
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
		
	}
	private boolean LOS(Point3D gps1 , Point3D gps2) {
		return true;
	}
}

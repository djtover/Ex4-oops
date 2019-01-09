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

public class GameAlgo {
	Game game = new Game();
	//	boolean isAdded;

	public GameAlgo(Game g) {
		this.game = g;
		//		isAdded = false;
	}
	public Game RunAlgo(Point3D startingPoint) {
		long start = System.currentTimeMillis();
		ArrayList<Point3D> points = cornersInBoxes();
		Graph graph = buildGraph(points);

		MyCoords mc = new MyCoords();
		ArrayList<Fruit> alfCopy = new ArrayList<Fruit>(game.getALF());
		Player playerCopy = new Player(game.getPlayer());
		playerCopy.setP(startingPoint);
//		Map m = new Map(1386,642,"Ariel1.png");
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
		long end = System.currentTimeMillis();
		System.out.println(end - start + " milliseconds");
		return game;
	}
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
	private void addEdges(Graph gr,ArrayList<Point3D> points,Point3D source, Point3D dest) {
		Map m = new Map(1386,642,"Ariel1.png");
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
	public boolean LOS(Point3D gps1,Point3D gps2) {
		Map m = new Map(1386,642,"Ariel1.png");
		MyCoords mc = new MyCoords();
		double angle = m.findAngle(gps1, gps2);
		
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
//	public boolean GhostinArea(Point3D gps1,ArrayList<Ghost> ghosts ) {
//		MyCoords mc = new MyCoords();
//		for(int i=0; i<ghosts.size();i++) {
//			double dist = mc.distance3d(gps1, ghosts.get(i).getP());
//			if(dist<5) {
//				return true;
//			}
//		}
//		return false;
//	}
	
	public static void main(String args[] ) {
//		Player player = new Player(32.10541924	,35.2086449
//				,0.0,1,1);
//		Fruit f1 = new Fruit(32.10538274,	35.20820765
//				,0.0 ,1);
//		Fruit f2 = new Fruit(32.10535666	,35.2097261
//				,0.0,1);
//		Fruit f3 = new Fruit(32.10535145	,35.2112684
//				,0.0,1);
//		Fruit f4 = new Fruit( 32.10331235	,35.211332
//				,0.0,1);
//		Fruit f5 = new Fruit(32.10282735	,35.21132405
//				,0.0,1);
//		Fruit f6 = new Fruit( 32.10282214,	35.21029055
//				,0.0,1);
//		Fruit f7 = new Fruit( 32.1033838847352,35.20973358585859,0.0,1);
//
//		Fruit f8 = new Fruit( 32.10307767	,35.2109186
//				,0.0,1);
//		Fruit f9 = new Fruit(32.10338536,	35.21040185
//				,0.0,1);
//		Fruit f10 = new Fruit( 32.10306724,	35.21017925
//				,0.0,1);
//		Fruit f11 = new Fruit(32.10358354,	35.2109345
//				,0.0,1);
//		Fruit f12 = new Fruit( 32.10458483,	35.21046545
//				,0.0,1);
//		Fruit f13 = new Fruit(32.10460569	,35.2097738
//				,0.0,1);
//		Fruit f14 = new Fruit( 32.1045744	,35.20386695
//				,0.0,1);
//
//
//
//		Game g = new Game();
//		g.setPlayer(player);
////				g.getALF().add(f1);
////				g.getALF().add(f2);
//		g.getALF().add(f3);
//		g.getALF().add(f4);
//		g.getALF().add(f5);
//		g.getALF().add(f6);
//		g.getALF().add(f7);
//		g.getALF().add(f8);
//		g.getALF().add(f9);
//		g.getALF().add(f10);
//		g.getALF().add(f11);
//		g.getALF().add(f12);
//		g.getALF().add(f13);
//		g.getALF().add(f14);
//		Block block1 = new Block(32.10214939,	35.20281755,	0,	32.10541403,	35.2033025,	0
//				);
//		Block block2= new Block(32.10488731,	35.2026347,	0,32.10521585	,35.2111889,	0
//				);
//		Block block3 = new Block(32.10225369,	35.2115387,	0	,32.10547139,	35.2116023,	0
//				);
//		Block block4 = new Block(32.10221719	,35.20243595,	0	,32.10251445	,35.2118567	,0
//				);
//		Block block5 = new Block(32.10417806	,35.20626785	,0	,32.10435015	,35.21191235	,0
//				);
//		Block block6 = new Block(32.10363569	,35.20494815,	0,	32.10541924	,35.2055603,	0
//				);
//		Block block7 = new Block(32.10217025	,35.2041929,	0,	32.10392773,	35.20461425,	0
//				);
//
//		g.getALB().add(block1);
//		g.getALB().add(block2);
//		g.getALB().add(block3);
//		g.getALB().add(block4);
//		g.getALB().add(block5);
//		g.getALB().add(block6);
//		g.getALB().add(block7);
		
		Map m = new Map(1386,642,"Ariel1.png");

		Game g = new Game();
		Player player = new Player(32.10377686792453,35.20544910218978,0.0,1,1);
		Fruit f = new Fruit(32.10302552377673,35.20699129989315,0,1);
		Block b1 = new Block(32.10270219	,35.2081838,	0,	32.10498118,	35.2085177,	0
);
		Block b2 = new Block(32.10354703,	35.2056557,	0,	32.10395902	,35.2106165,	0
);
		g.setPlayer(player);
		g.addFruit(f);
		g.addBlock(b1);
		g.addBlock(b2);
		Segment seg  = new Segment(m.Coords2Pixels(player.getP()),m.Coords2Pixels(f.getP()));
		GameAlgo ga = new GameAlgo(g);
		System.out.println(ga.LOS(player.getP(),f.getP()));
//		g = ga.RunAlgo(g.getPlayer().getP());
//		System.out.println(g);



	}
}






















//package graph;
//
//import java.util.ArrayList;
//
//public class Graph_Algo
//{
//  public Graph_Algo() {}
//  
//  public static double diameter(Graph g)
//  {
//    double ans = 0.0D;
//    for (int i = 0; i < g.size(); i++) {
//      double dist = dijkstra(g, i);
//      if (ans < dist) {
//        ans = dist;
//        System.out.println(i + ") " + ans);
//      }
//    }
//    return ans;
//  }
//  
//  public static double radius(Graph g) { double ans = Double.MAX_VALUE;
//    for (int i = 0; i < g.size(); i++) {
//      double dist = dijkstra(g, i);
//      if (ans > dist) {
//        ans = dist;
//        System.out.println(i + ") " + ans);
//      }
//    }
//    return ans;
//  }
//  
//  public static double dijkstra(Graph g, String source) { double ans = -1.0D;
//    int ind = g.getNodeIndexByName(source);
//    if (ind != -1) {
//      ans = dijkstra1(g, ind);
//    }
//    return ans;
//  }
//  
//  private static double dijkstra(Graph g, int source) { clearGraphData(g);
//    return dijkstra1(g, source);
//  }
//  
//  private static void dijkstra_with_BL(Graph g, int source, ArrayList<Integer> bl) { clearGraphData(g);
//    for (int i = 0; i < bl.size(); i++) {
//      int ind_bl = ((Integer)bl.get(i)).intValue();
//      Node c = g.getNodeByIndex(ind_bl);
//      get_info_color = 2;
//    }
//    dijkstra1(g, source);
//  }
//  
//  private static double dijkstra1(Graph g, int source) { Node src = g.getNodeByIndex(source);
//    get_info_color = 1;
//    ArrayList<Node> grays = new ArrayList();
//    grays.add(src);
//    int non_white = 0;
//    for (int i = 0; i < g.size(); i++) {
//      if (getNodeByIndexget_info_color != 0) {
//        non_white++;
//      }
//    }
//    double ans = -1.0D;
//    while ((!grays.isEmpty()) && (non_white < g.size())) {
//      double min = Double.MAX_VALUE;
//      Node min_node = null;Node source_node = null;
//      int min_ind = 0;
//      for (int i = 0; i < grays.size(); i++) {
//        Node cr = (Node)grays.get(i);
//        double dist_cr = get_info_dist;
//        ArrayList<Edge> ni = cr.get_ni();
//        for (int a = 0; a < ni.size(); a++) {
//          Edge e_cr = (Edge)ni.get(a);
//          Node wi = g.getNodeByIndex(e_cr.getInd());
//          if (get_info_color == 0) {
//            double d1 = dist_cr + e_cr.getW();
//            if (d1 < min) {
//              min_node = wi;
//              min = d1;
//              source_node = cr;
//              min_ind = i;
//            }
//          }
//        }
//      }
//      non_white++;
//      get_info_color = 1;
//      get_info_temp_path.addAll(get_info_temp_path);
//      get_info_temp_path.add(source_node.get_name());
//      get_info_dist = min;
//      ans = min;
//      grays.add(min_node);
//      get_info_count_ni += 1;
//      if (get_info_count_ni == source_node.degree()) {
//        get_info_color = 2;
//        grays.remove(min_ind);
//      }
//    }
//    return ans;
//  }
//  
//  public static void clearGraphData(Graph g) { g.clear_meta_data(); }
//}

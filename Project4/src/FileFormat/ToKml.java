package FileFormat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import MyGIS.Fruit;
import MyGIS.Packman;
/**
 * This is a class that represents converting the pacman game to a kml file
 * @author David Tover
 *
 */
public class ToKml {


	public ToKml(String fileName, ArrayList<Packman> alp , ArrayList<Fruit> alf ) {

		File f = new File(fileName);
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);

			String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" + 
					"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\r\n" + 
					"    <Document>\r\n"; 
					
			bw.write(header);

			for(int i=0; i<alp.size();i++) {
//				drawing the starting point of the pacman
				String id = ""+ alp.get(i).getId();
				String lat = ""+ alp.get(i).getStartingPoint().x();
				String lon = ""+ alp.get(i).getStartingPoint().y();
				String timeStamp = "" + convertTime(alp.get(i).getTimeStamp());

				String context = "<Folder>\r\n"+
						"<name>"+"Packman"+id+"</name>\r\n"
						+ "<Placemark>\r\n" + 
						"<name>"+"Packman"+id+"</name>\r\n" + 
						"<description>A Packman Point</description>\r\n"+
						"<Point>\r\n" + 
						"<coordinates>"+lon+","+lat+",0</coordinates></Point>\r\n" + 
						"<TimeStamp>\r\n <when> "+timeStamp+" </when>\r\n </TimeStamp>\r\n"+
						"</Placemark>\r\n" ;
				
				bw.write(context);
//				drawing all the other points on the pacmans path
				for(int j=0; j<alp.get(i).getPath().size();j++) {
					
					 id = ""+ alp.get(i).getPath().getAL().get(j).getId();
					 lat = ""+ alp.get(i).getPath().getAL().get(j).getP().x();
					 lon = ""+ alp.get(i).getPath().getAL().get(j).getP().y();
					 timeStamp = "" + convertTime(alp.get(i).getPath().getAL().get(j).getTimeStamp());

					 context = "<Placemark>\r\n" + 
							"<name>"+"Fruit"+id+"</name>\r\n" + 
							"<description>A Fruit Point on Packman "+ i +"'s path</description>\r\n"+
							"<Point>\r\n" + 
							"<coordinates>"+lon+","+lat+"</coordinates></Point>\r\n" + 
							"<TimeStamp>\r\n <when>"+ timeStamp+"</when>\r\n </TimeStamp>\r\n"+
							"</Placemark>\r\n" ;
					bw.write(context);
				}
				String endFolder = "</Folder>\r\n";
				bw.write(endFolder);
			}
			
			
			
			
			
			
			
			
//			drawing lines between all the fruit in the path
			for(int i=0;i<alp.size();i++) {
			String s="	<Placemark>\r\n "+
					 "<LineString>\r\n"+
			        "<extrude>1</extrude>\r\n"+
			        "<tessellate>1</tessellate>\r\n"+
			        " <coordinates>";
			if(alp.get(i).getPath().size()>0) {
				s+= ""+alp.get(i).getStartingPoint().y()+","+alp.get(i).getStartingPoint().x()+"\n";
			}
				for(int j=0;j<alp.get(i).getPath().size();j++) {
					s+= ""+alp.get(i).getPath().getAL().get(j).getP().y()+","+alp.get(i).getPath().getAL().get(j).getP().x()+"\n";
					
				}
				s+="</coordinates>\r\n "+
						"</LineString>\r\n"+
						"</Placemark>\r\n";
				bw.write(s);
			}
			
		
			
			String closer = "</Document>\r\n</kml>";//"</Folder>\r\n" + 
//					"</Document>\r\n</kml>";
			bw.write(closer);
			bw.close();


		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * This is a private method that converts the time from long to the date in a string
	 * @param time input in long
	 * @return A String of the date converted from the input
	 */
	private String convertTime(long time){
	    Date date = new Date(time*1000);
	    Format format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
	    String ans = format.format(date);
	    String end ="T"+ ans.substring(10);
	    ans = ans.substring(0, 10) +end;
	    
	    return ans;
	}
}

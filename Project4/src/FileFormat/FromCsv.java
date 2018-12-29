package FileFormat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import MyGIS.Block;
import MyGIS.Fruit;
import MyGIS.Packman;


/**
 * This is a class that represents a way to extract data from a csv file
 * @author David Tover
 *
 */
public class FromCsv {

	private ArrayList<Fruit> ALF;
	private ArrayList<Packman> ALP;
	private ArrayList<Block> ALB;


	/**
	 * This is the constructor for the way to extract data from a csv file
	 * @param csvFile input the name of the file you want to extract data from
	 */
	public FromCsv(String csvFile) {

		ALF = new ArrayList<Fruit>();
		ALP = new ArrayList<Packman>();
		ALB = new ArrayList<Block>();

		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			br.readLine();
			while ((line = br.readLine()) != null) 
			{
				String[] userInfo = line.split(cvsSplitBy);
				if(userInfo[0].equals("B")) {
					double lat1 = Double.parseDouble(userInfo[2]);
					double lon1 = Double.parseDouble(userInfo[3]);
					double alt1= Double.parseDouble(userInfo[4]);
					double lat2 = Double.parseDouble(userInfo[5]);
					double lon2 = Double.parseDouble(userInfo[6]);
					double alt2 = Double.parseDouble(userInfo[7]);
					Block bl = new Block(lat1, lon1, alt1, lat2, lon2, alt2);
					ALB.add(bl);
				}
				else {
					double lat = Double.parseDouble(userInfo[2]);
					double lon = Double.parseDouble(userInfo[3]);
					double alt = Double.parseDouble(userInfo[4]);

					if(userInfo[0].equals("P")) {
						double speed = Double.parseDouble(userInfo[5]);
//						int speed = Integer.parseInt(userInfo[5]);
						Packman p = new Packman(lat,lon, alt, speed,1);
						ALP.add(p);


					}
					else if(userInfo[0].equals("F")) {
						double weight = Double.parseDouble(userInfo[5]); 
						Fruit f = new Fruit(lat,lon,alt,weight);	
						ALF.add(f);
					}
				}		

			}
			br.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	public ArrayList<Fruit> getALF() {
		return ALF;
	}
	public ArrayList<Packman> getALP() {
		return ALP;
	}
	public ArrayList<Block> getALB(){
		return ALB;
	}
		public static void main (String [] args) {
			FromCsv newFile = new FromCsv("data/Ex4_OOP_example3.csv") ;
			for(int i=0; i<newFile.ALB.size();i++) {
				System.out.println(newFile.ALB.get(i));
			}
		}
}

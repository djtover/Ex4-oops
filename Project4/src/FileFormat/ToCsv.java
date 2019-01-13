package FileFormat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import MyGIS.Fruit;
import MyGIS.Packman;
/**
 * This class represents a way to create a Csv file from an Arraylist of Fruit and Packmen
 * @author djtov
 *
 */
public class ToCsv {
/**
 * This is a constructor for the ToCSv class
 * @param fileName the name of the file you want it to be saved as
 * @param alp the ArrayList of Packmen
 * @param alf The ArrayList of Fruit
 */
	public ToCsv (String fileName,ArrayList<Packman> alp , ArrayList<Fruit> alf) {
		PrintWriter pw = null;

		try 
		{
			pw = new PrintWriter(new File(fileName));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}

		StringBuilder sb = new StringBuilder();
		sb.append("Type");
		sb.append(",");
		sb.append("id");
		sb.append(",");
		sb.append("Lat");
		sb.append(",");
		sb.append("Lon");
		sb.append(",");
		sb.append("Alt");
		sb.append(",");
		sb.append("Speed/Weight");
		sb.append(",");
		sb.append("Radius");
		sb.append("\n");
		
		for(int i=0; i<alp.size();i++) {
			sb.append("P");
			sb.append(",");
			sb.append(alp.get(i).getId());
			sb.append(",");
			sb.append(alp.get(i).getP().x());
			sb.append(",");
			sb.append(alp.get(i).getP().y());
			sb.append(",");
			sb.append(alp.get(i).getP().z());
			sb.append(",");
			sb.append(alp.get(i).getSpeed());
			sb.append("\n");
		}

		for(int i=0; i<alf.size();i++) {
			sb.append("F");
			sb.append(",");
			sb.append(alf.get(i).getId());
			sb.append(",");
			sb.append(alf.get(i).getP().x());
			sb.append(",");
			sb.append(alf.get(i).getP().y());
			sb.append(",");
			sb.append(alf.get(i).getP().z());
			sb.append(",");
			sb.append(alf.get(i).getWeight());
			sb.append("\n");
			
		}
		pw.write(sb.toString());
		pw.close();
		
	}
}

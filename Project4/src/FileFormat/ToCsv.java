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
//	public static void main(String [] args) {
//		Packman p1 = new Packman(32.10452628971962,35.20409047113997,0.0,1,1);
//        Packman p2 = new Packman(32.104470411214955,35.21009834992785,0.0,1,1);
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
////		alf.add(f1);
//		alf.add(f3);
//		alf.add(f4);
//		alf.add(f5);
//		alf.add(f6);
//		ToCsv tc = new ToCsv("C:\\Users\\djtov\\Documents\\hello1.csv",alp,alf);
//	}
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
//			sb.append(",");
//			sb.append(alp.get(i).getRadius());
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

package de.hawhamburg.mi.control;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import de.hawhamburg.mi.model.common.SimObjects;

public class MapLoader {
	
	public void loadWorld(String filename) {
		FileInputStream fstream;
		DataInputStream in = null;
		
		try {
			fstream = new FileInputStream(filename);
			in = new DataInputStream(fstream);
		} catch (FileNotFoundException e1) {
			System.out.println("Error reading MAP file:");
			e1.printStackTrace();
			System.exit(1);
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		try {
			String strLine = br.readLine();
			String worldName = strLine.split(";")[0];
			System.out.println("Loading map with name \"" + worldName + "\"");
			int y = 0;
			int x;
			SimObjects type;
			while ((strLine = br.readLine()) != null)   {
				x = 0;
				for(String val : strLine.split(";")) {
					type = getObjectType(val.replace(" ",""));
					if(type == SimObjects.NOTHING) {
						System.out.println("Unbekanntest Objekt an Position: x=" + x + " y=" + y);
					}
					x += 1;
				}
				
				y += 1;
			}
		} catch (IOException e) {
			System.out.println("Error reading MAP file:");
			e.printStackTrace();
			System.exit(1);
		
		}
	}
	
	public SimObjects getObjectType(String name) {
		
		if(name.equals("L"))
				return SimObjects.LAWN;
		
		if(name.equals("SW"))
			return SimObjects.SIDEWALK;
		
		if(name.equals("B"))
			return SimObjects.BUILDING;
		
		if(name.equals("SOURCE"))
			return SimObjects.SOURCE;
		
		if(name.equals("TARGET"))
			return SimObjects.TARGET;
		
		if(name.equals("S"))
			return SimObjects.STREET;
		
		if(name.equals("T"))
			return SimObjects.TREE;
		
		return SimObjects.NOTHING;
		
	}
	
}

package edu.isi.dig.jsonparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import edu.isi.dig.jsonparser.AlignedAirports;

public class Main {

	
	List<AlignedAirports> airportList;
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws JsonSyntaxException 
	 * @throws JsonIOException 
	 * @throws URISyntaxException 
	 * @throws MalformedURLException 
	 */
	
	public static void main(String[] args) throws JsonIOException, JsonSyntaxException, FileNotFoundException, URISyntaxException, MalformedURLException {
		
		try
		{
			File testf = new File("src/main/x-airports-aligned.json" );
			
			List<AlignedAirports> airportList = new ArrayList<AlignedAirports>();
			
			airportList = ParserJSON(testf);
			
			Gson gs = new Gson();
			
			StringBuilder sbJSON = new StringBuilder();
			
			sbJSON.append("[");
			
			for (AlignedAirports alignedAirports : airportList) 
			{
				//alignedAirports.printAirports(alignedAirports);
				sbJSON.append(gs.toJsonTree(alignedAirports));
				sbJSON.append(",");
				
			}
			
			sbJSON.replace(sbJSON.lastIndexOf(","), sbJSON.lastIndexOf(",")+1, "");
			sbJSON.append("]");
			
			//System.out.print(sbJSON.toString());
			writeJSON(sbJSON.toString());
			
			System.out.print("Done!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

	}
	
	public static List<AlignedAirports> ParserJSON(File jFile) throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		List<AlignedAirports> airportList = new ArrayList<AlignedAirports>();
		
		JsonParser parser = new JsonParser();
		
		JsonElement je = parser.parse(new FileReader(jFile)); 
		
		JsonArray ja = je.getAsJsonArray();

		int i =0;
		
		for (JsonElement jsonElement : ja) 
		{
			i++;
			AlignedAirports aa = new AlignedAirports();
			aa.readJSONFile(jsonElement.getAsJsonObject());
			//System.out.println(jsonElement.isJsonObject());
			airportList.add(aa);
			//System.out.println(aa.getAddressCountryLabel());
			//System.out.println(aa.getAddressLocality());
		}
		
		return airportList;
		
		
		
		 
	}
	
	public static void writeJSON(String content)
	{
		

		 Writer writer = null;

		 try {
		     writer = new BufferedWriter(new OutputStreamWriter(
		           new FileOutputStream("src/main/x-alignedairports-solr.json"), "utf-8"));
		     writer.write(content);
		 } catch (IOException ex) {
		   // report
		 } finally {
		    try {writer.close();} catch (Exception ex) {}
		 }
	}

}

package edu.isi.dig.jsonparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class DigJSONParser {
	
	JsonParser parser;
	
	
	public DigJSONParser()
	{
		
		parser = new JsonParser();
	}
	
	public void ParserJSON(File jFile) throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		//File jFile = new File(fileName);
		
		JsonElement je = parser.parse(new FileReader(jFile)); 
		
		JsonArray ja = je.getAsJsonArray();
		int i =0;
		for (JsonElement jsonElement : ja) 
		{i++;
			readJSONFile(jsonElement.getAsJsonObject());
			//System.out.println(jsonElement.isJsonObject());
			if (i==1) break;
		}
		
		System.out.println(ja.size());
		
		//System.out.println(ja.get(0).getAsJsonObject().entrySet());
		
		//System.out.print(je.isJsonArray());
		
		//JsonObject jo = je.getAsJsonObject();
		
		//readJSONFile(jo);
		
		 
	}
	
	public void readJSONFile(JsonObject jo)
	{
		for (Entry<String, JsonElement> entry : jo.entrySet()) {

            String key = entry.getKey();
            JsonElement value = entry.getValue();
            
            
            //System.out.println(key);
            
            if (value.isJsonArray())
            {
            	JsonArray jsonArray = value.getAsJsonArray();
                
                if (jsonArray.size() == 1) {
                	System.out.println("ranjha");
                   readJSONFile((JsonObject) jsonArray.get(0));
               }
                else {
                   //prints json array name
                   System.out.println(key);
                   Iterator<JsonElement> msg = jsonArray.iterator();
                   while (msg.hasNext()) {
                       ////prints json array values
                	   System.out.println("heer");
                       System.out.println(msg.next());
                   }
               }
            }
            
            else if (value.isJsonObject())
            {
            	System.out.println(key);
            	readJSONFile(value.getAsJsonObject());
            }
            
            else {
                ////prints json object's keys and values
                System.out.println(key + " - " + value);
            }
        }
	}

}

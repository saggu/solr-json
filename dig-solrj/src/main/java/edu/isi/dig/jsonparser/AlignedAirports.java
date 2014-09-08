package edu.isi.dig.jsonparser;

import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class AlignedAirports {

	
	
	private String id;
	private String type;
	private String geoLongitude;
	private String geoLatitude;
	private String geoType;
	private String geoId;
	private String addressCountryLabel;
	private String addressCountryType;
	private String addressCountryId;
	private String addressLocality;
	private String addressLocalityType;
	private String addressLocalityId;
	
	
	public AlignedAirports()
	{
		
		this.id = null;
		this.type = null;
		this.geoLongitude = null;
		this.geoLatitude = null;
		this.geoType = null;
		this.geoId = null;
		this.addressCountryLabel = null;
		this.addressCountryType = null;
		this.addressCountryId = null;
		this.addressLocality = null;
		this.addressLocalityType = null;
		this.addressLocalityId = null;
	}
	public AlignedAirports(String id, String type, String geoLongitude,	String geoLatitude, String geoType, String geoId,String addressCountryLabel,
			String addressCountryType, String addressCountryId,	String addressLocality, String addressLocalityType,	String addressLocalityId) 
	{
		this.id = id;
		this.type = type;
		this.geoLongitude = geoLongitude;
		this.geoLatitude = geoLatitude;
		this.geoType = geoType;
		this.geoId = geoId;
		this.addressCountryLabel = addressCountryLabel;
		this.addressCountryType = addressCountryType;
		this.addressCountryId = addressCountryId;
		this.addressLocality = addressLocality;
		this.addressLocalityType = addressLocalityType;
		this.addressLocalityId = addressLocalityId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGeoLongitude() {
		return geoLongitude;
	}
	public void setGeoLongitude(String geoLongitude) {
		this.geoLongitude = geoLongitude;
	}
	public String getGeoLatitude() {
		return geoLatitude;
	}
	public void setGeoLatitude(String geoLatitude) {
		this.geoLatitude = geoLatitude;
	}
	public String getGeoType() {
		return geoType;
	}
	public void setGeoType(String geoType) {
		this.geoType = geoType;
	}
	public String getGeoId() {
		return geoId;
	}
	public void setGeoId(String geoId) {
		this.geoId = geoId;
	}
	public String getAddressCountryLabel() {
		return addressCountryLabel;
	}
	public void setAddressCountryLabel(String addressCountryLabel) {
		this.addressCountryLabel = addressCountryLabel;
	}
	
	public String getAddressCountryType() {
		return addressCountryType;
	}
	public void setAddressCountryType(String addressCountryType) {
		this.addressCountryType = addressCountryType;
	}
	public String getAddressCountryId() {
		return addressCountryId;
	}
	public void setAddressCountryId(String addressCountryId) {
		this.addressCountryId = addressCountryId;
	}
	public String getAddressLocality() {
		return addressLocality;
	}
	public void setAddressLocality(String addressLocality) {
		this.addressLocality = addressLocality;
	}
	public String getAddressLocalityType() {
		return addressLocalityType;
	}
	public void setAddressLocalityType(String addressLocalityType) {
		this.addressLocalityType = addressLocalityType;
	}
	public String getAddressLocalityId() {
		return addressLocalityId;
	}
	public void setAddressLocalityId(String addressLocalityId) {
		this.addressLocalityId = addressLocalityId;
	}
	
	
		
	public void readJSONFile(JsonObject jo)
	{

		for (Entry<String, JsonElement> entry : jo.entrySet()) {

            String key = entry.getKey();
            JsonElement value = entry.getValue();
            
            
            if (key.equals(OntologyFields.SCHEMA_GEO) && value.isJsonObject())
            {
            	processGeo(value.getAsJsonObject());
            	//readJSONFile(value.getAsJsonObject());
            }
            
            if (key.equals(OntologyFields.SCHEMA_ADDRESS) && value.isJsonObject())
            {
            	processAddress(value.getAsJsonObject());
            	readJSONFile(value.getAsJsonObject());
            }
            
            if (key.equals(OntologyFields.SCHEMA_ADDRESS_COUNTRY) && value.isJsonObject())
            {
            	processAddressCountry(value.getAsJsonObject());
            	//readJSONFile(value.getAsJsonObject());
            }
            
            else {
                
            	if(key.equals(OntologyFields.ID))
            	{
            		this.id = value.getAsString();
            	}
            	
            	if(key.equals(OntologyFields.TYPE))
            	{
            		this.type = value.getAsString();
            	}
            	
            }
        }
		
		
	}

	
	void processGeo(JsonObject jo)
	{
		for (Entry<String, JsonElement> entry : jo.entrySet()) 
		{
			if(entry.getKey().equals(OntologyFields.SCHEMA_LONGITUDE))
			{
				this.geoLongitude = entry.getValue().getAsString();
			}
			if(entry.getKey().equals(OntologyFields.SCHEMA_LATITUDE))
			{
				this.geoLatitude = entry.getValue().getAsString();
			}
			if(entry.getKey().equals(OntologyFields.TYPE))
			{
				this.geoType = entry.getValue().getAsString();
			}
			if(entry.getKey().equals(OntologyFields.ID))
			{
				this.geoId = entry.getValue().getAsString();
			}
		}
	}
	
	void processAddressCountry(JsonObject jo)
	{
		for (Entry<String, JsonElement> entry : jo.entrySet()) 
		{
			if(entry.getKey().equals(OntologyFields.RDFS_LABEL))
			{
				this.addressCountryLabel = entry.getValue().getAsString();
			}
			if(entry.getKey().equals(OntologyFields.TYPE))
			{
				this.addressCountryType = entry.getValue().getAsString();
			}
			if(entry.getKey().equals(OntologyFields.ID))
			{
				this.addressCountryId = entry.getValue().getAsString();
			}
		}
	}
	

	void processAddress(JsonObject jo)
	{
		for (Entry<String, JsonElement> entry : jo.entrySet()) 
		{
			if(entry.getKey().equals(OntologyFields.SCHEMA_ADDRESS_LOCALITY))
			{
				this.addressLocality = entry.getValue().getAsString();
			}
			if(entry.getKey().equals(OntologyFields.TYPE))
			{
				this.addressLocalityType = entry.getValue().getAsString();
			}
			if(entry.getKey().equals(OntologyFields.ID))
			{
				this.addressLocalityId = entry.getValue().getAsString();
			}
		}
	}
	
	public void printAirports(AlignedAirports aa)
	{
		System.out.println(OntologyFields.ID + " - " + aa.getId());
		System.out.println(OntologyFields.TYPE + " - " + aa.getType());
		System.out.println(OntologyFields.SCHEMA_LATITUDE + " - " + aa.getGeoLatitude());
		System.out.println(OntologyFields.SCHEMA_LONGITUDE + " - " + aa.getGeoLongitude());
		System.out.println(OntologyFields.SCHEMA_GEO_ID + " - " + aa.getGeoId());
		System.out.println(OntologyFields.SCHEMA_GEO_TYPE + " - " + aa.getGeoType());
		System.out.println(OntologyFields.SCHEMA_ADDRESS_COUNTRY + " - " + this.getAddressCountryLabel());
		System.out.println(OntologyFields.SCHEMA_ADDRESS_COUNTRY_ID + " - " + this.getAddressCountryId());
		System.out.println(OntologyFields.SCHEMA_ADDRESS_COUNTRY_TYPE + " - " + this.getAddressCountryType());
		System.out.println(OntologyFields.SCHEMA_ADDRESS_LOCALITY + " - " + this.getAddressLocality());
		System.out.println(OntologyFields.SCHEMA_ADDRESS_LOCALITY_ID+ " - " + this.getAddressLocalityId());
		System.out.println(OntologyFields.SCHEMA_ADDRESS_LOCALITY_TYPE + " - " + this.getAddressLocalityType());

	}
	
}

package restAPI;

/**
 * 
 * @author harora
 *
 */

public class ResourcesAPI {
	
	
	public static String getCountryByAlphaCode(){
		String countryByAlphaCode="/alpha/";
		return countryByAlphaCode;
	}
	
	public static String getAllCountries(){
		String allCountries="/all";
		return allCountries;
	}
	
	public static String getCountryByInvalidName(){
		String invalidCountry="/name/23424";
		return invalidCountry;
	}
	
	public static String postCountry(){
		String postCountry="/countryPost";
		return postCountry;
	}
}

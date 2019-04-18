
import java.net.*;
import java.util.List;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestAPI {
	public static void main(String[] args) {
	String apiId = "&appid=286d37efd2f53154bbe32653bfac5a00";
	String units = "&units=metric";
	String lang = "&lang=en";
	String country = "Yekaterinburg";
	String weather  =  "https://api.openweathermap.org/data/2.5/weather?q="+country+""+units+""+lang+""+apiId;
	HttpURLConnection connection = null;
	Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	try {
		connection = (HttpURLConnection) new URL(weather).openConnection();
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(250);
		connection.setReadTimeout(250);
		connection.connect();
		StringBuilder sb = new StringBuilder();
		if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) !=null) {
				sb.append(line);
				sb.append("\n");
			}
			String result = sb.toString();
			OpenWeather openweather = GSON.fromJson(result, OpenWeather.class);
			System.out.print(openweather.main.temp);
			//System.out.print(result);
		}else {
			System.out.print("fail:" + connection.getResponseCode() + ", " + connection.getResponseMessage());
		}
	} 
	catch (Throwable cause)
	{
		cause.printStackTrace();
	} finally { 
		if (connection != null)
			connection.disconnect();
	}
}
}
class OpenWeather{
	public Coord coord;
	public List<Weather> weather;
	public String base;
	public Main main;
	public int visibility;
	public Wind wind;
	public Clouds clouds;
	public int dt;
	public Sys sys;
	public int id;
	public String name;
	public int cod;
}
class Coord{
	public double lon;
	public double lat;
}
class Weather{
	public int id;
	public String main;
	public String description;
	public String icon;
}
class Main {
	public double temp;
	public int pressure;
	public int humidity;
	public double temp_min;
	public double temp_max;
}
class Wind {
	public double speed;
	public int deg;
}
class Clouds{
	public int all;
}
class Sys{
	public int type;
	public int id;
	public double massage;
	public String Country;
	public int sunrise;
	public int sunset;
}

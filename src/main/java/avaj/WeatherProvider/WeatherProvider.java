package avaj.WeatherProvider;

import avaj.Coordinates.Coordinates;
import java.util.Random;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = null;
    private String[] weather = { "RAIN", "FOG", "SUN", "SNOW" };

    private WeatherProvider() {

    }

    public static WeatherProvider getProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        Random generator = new Random(coordinates.getLongitude() * coordinates.getLatitude() * coordinates.getHeight());
        int randomIndex = generator.nextInt(weather.length);
        return weather[randomIndex];
    }
}

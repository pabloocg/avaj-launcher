package avaj.Tower;

import avaj.Coordinates.Coordinates;
import avaj.WeatherProvider.WeatherProvider;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        this.conditionsChanged();
    }

}
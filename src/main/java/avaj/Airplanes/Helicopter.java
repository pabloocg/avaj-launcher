package avaj.Airplanes;

import avaj.Tower.WeatherTower;
import avaj.Coordinates.Coordinates;
import avaj.Simulator.Log;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        Coordinates cord = this.coordinates;
        String weather = this.weatherTower.getWeather(this.coordinates);
        switch (weather) {
        case "SUN":
            this.coordinates = new Coordinates(cord.getLongitude() + 10, cord.getLatitude(), cord.getHeight() + 2);
            Log.getLog().write("Helicopter#" + this.name + "(" + this.id
                    + "): Let everyone say what they want: the best cooker is the sun.");
            break;
        case "RAIN":
            this.coordinates = new Coordinates(cord.getLongitude() + 5, cord.getLatitude(), cord.getHeight());
            Log.getLog().write("Helicopter#" + this.name + "(" + this.id
                    + "): I want to caress you in the rain... with a bare wire.");
            break;
        case "FOG":
            this.coordinates = new Coordinates(cord.getLongitude() + 1, cord.getLatitude(), cord.getHeight());
            Log.getLog().write("Helicopter#" + this.name + "(" + this.id
                    + "): I'm going to sleep for a while until the fog lifts.");
            break;
        case "SNOW":
            this.coordinates = new Coordinates(cord.getLongitude(), cord.getLatitude(), cord.getHeight() - 12);
            Log.getLog()
                    .write("Helicopter#" + this.name + "(" + this.id + "): Snow is the truth only when it is snowing.");
            break;
        }
        if (this.coordinates.getHeight() <= 0) {
            Log.getLog().write("Helicopter#" + this.name + "(" + this.id + ") landing.");
            this.weatherTower.unregister(this);
            Log.getLog()
                    .write("Helicopter#" + this.name + "(" + this.id + "): Longitude " + this.coordinates.getLongitude()
                            + " Latitude " + this.coordinates.getLatitude() + " Height "
                            + this.coordinates.getHeight());
        }
    }

    public void registerTower(WeatherTower weathertower) {
        this.weatherTower = weathertower;
        weathertower.register(this);
    }

}

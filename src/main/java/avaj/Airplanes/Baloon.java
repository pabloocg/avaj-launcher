package avaj.Airplanes;

import avaj.Tower.WeatherTower;
import avaj.Coordinates.Coordinates;
import avaj.Simulator.Log;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        Coordinates cord = this.coordinates;
        String weather = this.weatherTower.getWeather(this.coordinates);
        switch (weather) {
        case "SUN":
            this.coordinates = new Coordinates(cord.getLongitude() + 2, cord.getLatitude(), cord.getHeight() + 4);
            Log.getLog().write(
                    "Baloon#" + this.name + "(" + this.id + "): It is very strange that the sun rises on this side..");
            break;
        case "RAIN":
            this.coordinates = new Coordinates(cord.getLongitude(), cord.getLatitude(), cord.getHeight() - 5);
            Log.getLog().write("Baloon#" + this.name + "(" + this.id
                    + "): This rain invites you to make hot things... like coffee.");
            break;
        case "FOG":
            this.coordinates = new Coordinates(cord.getLongitude(), cord.getLatitude(), cord.getHeight() - 3);
            Log.getLog()
                    .write("Baloon#" + this.name + "(" + this.id + "): How thick the fog is. I can't see the path.");
            break;
        case "SNOW":
            this.coordinates = new Coordinates(cord.getLongitude(), cord.getLatitude(), cord.getHeight() - 15);
            Log.getLog().write("Baloon#" + this.name + "(" + this.id + "): Year of snow, year of goods.");
            break;
        }
        if (this.coordinates.getHeight() <= 0) {
            Log.getLog().write("Baloon#" + this.name + "(" + this.id + ") landing.");
            this.weatherTower.unregister(this);
            Log.getLog().write("Baloon#" + this.name + "(" + this.id + "): Longitude " + this.coordinates.getLongitude()
                    + " Latitude " + this.coordinates.getLatitude() + " Height " + this.coordinates.getHeight());
        }
    }

    public void registerTower(WeatherTower weathertower) {
        this.weatherTower = weathertower;
        weathertower.register(this);
    }

}

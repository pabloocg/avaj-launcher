package avaj.Airplanes;

import avaj.Tower.WeatherTower;
import avaj.Coordinates.Coordinates;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
        // this.print();
    }

    public void updateConditions() {
        Coordinates cord = this.coordinates;
        String weather = this.weatherTower.getWeather(this.coordinates);
        switch (weather) {
        case "SUN":
            this.coordinates = new Coordinates(cord.getLongitude() + 10, cord.getLatitude(), cord.getHeight() + 2);
            System.out.println("Helicopter#" + this.name + "(" + this.id + "): weather sun.");
            break;
        case "RAIN":
            this.coordinates = new Coordinates(cord.getLongitude() + 5, cord.getLatitude(), cord.getHeight());
            System.out.println("Helicopter#" + this.name + "(" + this.id + "): weather rain.");
            break;
        case "FOG":
            this.coordinates = new Coordinates(cord.getLongitude() + 1, cord.getLatitude(), cord.getHeight());
            System.out.println("Helicopter#" + this.name + "(" + this.id + "): weather fog.");
            break;
        case "SNOW":
            this.coordinates = new Coordinates(cord.getLongitude(), cord.getLatitude(), cord.getHeight() - 12);
            System.out.println("Helicopter#" + this.name + "(" + this.id + "): weather snow.");
            break;
        }
        if (this.coordinates.getHeight() <= 0) {
            System.out.println("Helicopter#" + this.name + "(" + this.id + ") landing.");
            this.weatherTower.unregister(this);
            System.out.println("Helicopter#" + this.name + "(" + this.id + "): Longitude "
                    + this.coordinates.getLongitude() + " Latitude " + this.coordinates.getLatitude() + " Height "
                    + this.coordinates.getHeight());
        }
    }

    public void registerTower(WeatherTower weathertower) {
        this.weatherTower = weathertower;
        weathertower.register(this);
    }

}

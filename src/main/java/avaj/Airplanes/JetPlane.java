package avaj.Airplanes;

import avaj.Tower.WeatherTower;
import avaj.Coordinates.Coordinates;
import avaj.Simulator.Log;

class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        Coordinates cord = this.coordinates;
        String weather = this.weatherTower.getWeather(this.coordinates);
        switch (weather) {
        case "SUN":
            this.coordinates = new Coordinates(cord.getLongitude(), cord.getLatitude() + 10, cord.getHeight() + 2);
            Log.getLog().write("JetPlane#" + this.name + "(" + this.id
                    + "): No matter how sunny it is, don't leave your cape at home.");
            break;
        case "RAIN":
            this.coordinates = new Coordinates(cord.getLongitude(), cord.getLatitude() + 5, cord.getHeight());
            Log.getLog().write("JetPlane#" + this.name + "(" + this.id
                    + "): Even if it is a rainy day it will be a wonderful day.");
            break;
        case "FOG":
            this.coordinates = new Coordinates(cord.getLongitude(), cord.getLatitude() + 1, cord.getHeight());
            Log.getLog().write("JetPlane#" + this.name + "(" + this.id + "): This fog is complicating the journey.");
            break;
        case "SNOW":
            this.coordinates = new Coordinates(cord.getLongitude(), cord.getLatitude(), cord.getHeight() - 7);
            Log.getLog().write("JetPlane#" + this.name + "(" + this.id + "): Year of acorns, snow up to the balls");
            break;
        }
        if (this.coordinates.getHeight() <= 0) {
            Log.getLog().write("JetPlane#" + this.name + "(" + this.id + ") landing.");
            this.weatherTower.unregister(this);
            Log.getLog()
                    .write("JetPlane#" + this.name + "(" + this.id + "): Longitude " + this.coordinates.getLongitude()
                            + " Latitude " + this.coordinates.getLatitude() + " Height "
                            + this.coordinates.getHeight());
        }
    }

    public void registerTower(WeatherTower weathertower) {
        this.weatherTower = weathertower;
        weathertower.register(this);
    }

}

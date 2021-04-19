package avaj.Airplanes;

import avaj.Tower.WeatherTower;
import avaj.Coordinates.Coordinates;

class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
        // this.print();
    }

    public void updateConditions() {
        Coordinates cord = this.coordinates;
        String weather = this.weatherTower.getWeather(this.coordinates);
        switch (weather) {
        case "SUN":
            this.coordinates = new Coordinates(cord.getLongitude(), cord.getLatitude() + 10, cord.getHeight() + 2);
            System.out.println("JetPlane#" + this.name + "(" + this.id + "): weather sun.");
            break;
        case "RAIN":
            this.coordinates = new Coordinates(cord.getLongitude(), cord.getLatitude() + 5, cord.getHeight());
            System.out.println("JetPlane#" + this.name + "(" + this.id + "): weather rain.");
            break;
        case "FOG":
            this.coordinates = new Coordinates(cord.getLongitude(), cord.getLatitude() + 1, cord.getHeight());
            System.out.println("JetPlane#" + this.name + "(" + this.id + "): weather fog.");
            break;
        case "SNOW":
            this.coordinates = new Coordinates(cord.getLongitude(), cord.getLatitude(), cord.getHeight() - 7);
            System.out.println("JetPlane#" + this.name + "(" + this.id + "): weather snow.");
            break;
        }
        if (this.coordinates.getHeight() <= 0) {
            System.out.println("JetPlane#" + this.name + "(" + this.id + ") landing.");
            this.weatherTower.unregister(this);
            System.out.println("JetPlane#" + this.name + "(" + this.id + "): Longitude "
                    + this.coordinates.getLongitude() + " Latitude " + this.coordinates.getLatitude() + " Height "
                    + this.coordinates.getHeight());
        }
    }

    public void registerTower(WeatherTower weathertower) {
        this.weatherTower = weathertower;
        weathertower.register(this);
    }

}
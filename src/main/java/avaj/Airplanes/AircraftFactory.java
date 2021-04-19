package avaj.Airplanes;

import avaj.Coordinates.Coordinates;

public class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates cord = new Coordinates(longitude, latitude, height);

        if (type.equals("Helicopter")) {
            return new Helicopter(name, cord);
        } else if (type.equals("JetPlane")) {
            return new JetPlane(name, cord);
        } else if (type.equals("Baloon")) {
            return new Baloon(name, cord);
        } else {
            return null;
        }
    }

}
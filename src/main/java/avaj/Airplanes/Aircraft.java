package avaj.Airplanes;

import avaj.Coordinates.Coordinates;

public class Aircraft {

    private static long idCounter = 0;
    protected final long id;
    protected final String name;
    protected Coordinates coordinates;

    protected Aircraft(String name, Coordinates coordinates) {
        this.id = nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    private static long nextId() {
        return ++idCounter;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

}
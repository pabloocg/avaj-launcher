package avaj.Coordinates;

public class Coordinates {

    private int longitude, latitude, height;

    public Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        if (this.height > 100) {
            this.height = 100;
        } else {
            this.height = height;
        }
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public void print() {
        System.out
                .println("Longitude: " + this.longitude + "\nLatitude: " + this.latitude + "\nHeight: " + this.height);
    }

}

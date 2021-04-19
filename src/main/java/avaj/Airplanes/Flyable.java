package avaj.Airplanes;

import avaj.Tower.WeatherTower;

public interface Flyable {

    public void updateConditions();

    public void registerTower(WeatherTower weathertower);

}

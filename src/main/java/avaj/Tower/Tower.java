package avaj.Tower;

import java.util.ArrayList;
import avaj.Airplanes.*;

public class Tower {

    private ArrayList<Flyable> observers = new ArrayList<>();
    private ArrayList<Flyable> to_delete = new ArrayList<>();

    public void register(Flyable flyable) {
        if (observers.contains(flyable)) {
            return;
        }
        observers.add(flyable);
        Aircraft airplane = (Aircraft) flyable;
        System.out.println("Tower says: " + airplane.getClass().getSimpleName() + "#" + airplane.getName() + "("
                + airplane.getId() + ") registered to weather tower.");
    }

    public void unregister(Flyable flyable) {
        if (to_delete.contains(flyable)) {
            return;
        }
        to_delete.add(flyable);
        Aircraft airplane = (Aircraft) flyable;
        System.out.println("Tower says: " + airplane.getClass().getSimpleName() + "#" + airplane.getName() + "("
                + airplane.getId() + ") unregistered from weather tower.");
    }

    protected void conditionsChanged() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
        observers.removeAll(to_delete);
        to_delete.clear();
    }
}
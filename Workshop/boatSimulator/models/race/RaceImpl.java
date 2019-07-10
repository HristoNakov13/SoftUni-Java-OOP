package boatSimulator.models.race;

import boatSimulator.models.ArgumentException;
import boatSimulator.models.boats.Boat;
import boatSimulator.models.boats.Inject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class RaceImpl implements Race {
    private int distance;
    private double windSpeed;
    private double oceanCurrent;
    private List<Boat> participants;
    private boolean motorBoatsAllowed;

    public RaceImpl(int distance, double windSpeed, double oceanCurrent, boolean motorBoatsAllowed) throws ArgumentException {
        this.setDistance(distance);
        this.windSpeed = windSpeed;
        this.oceanCurrent = oceanCurrent;
        this.motorBoatsAllowed = motorBoatsAllowed;
        this.participants = new ArrayList<>();
    }

    private int getDistance() {
        return distance;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setOceanCurrent(double oceanCurrent) {
        this.oceanCurrent = oceanCurrent;
    }

    public void setMotorBoatsAllowed(boolean motorBoatsAllowed) {
        this.motorBoatsAllowed = motorBoatsAllowed;
    }

    public void setDistance(int distance) throws ArgumentException {
        if (!RaceValidator.isValidDistance(distance)) {
            throw new ArgumentException("Distance must be a positive integer");
        }
        this.distance = distance;
    }

    private double getWindSpeed() {
        return windSpeed;
    }

    private double getOceanCurrent() {
        return oceanCurrent;
    }

    public boolean SignUpBoat(Boat boat) throws UnallowedBoatType {
        if (!this.motorBoatsAllowed()) {
            if (RaceValidator.isMotorBoat(boat)) {
                throw new UnallowedBoatType("The specified boat does not meet the race constraints.");
            }
        }
        this.participants.add(boat);
        return true;
    }

    public boolean motorBoatsAllowed() {
        return this.motorBoatsAllowed;
    }

    public String StartRace() {
        Map<Boat, Double> raceResults = new LinkedHashMap<>();

        for (Boat boat : this.participants) {

            if (!this.motorBoatsAllowed()) {
                if (RaceValidator.isMotorBoat(boat)) {
                    continue;
                }
            }
            if (RaceValidator.isSailBoat(boat)) {
                injectWindSpeed(boat);
            }
            double currentBoatSpeed = boat.calculateSpeed(this.getOceanCurrent());
            double time = this.getDistance() / currentBoatSpeed;
            raceResults.put(boat, time);
        }
        return getWinners(raceResults);
    }

    private String getWinners(Map<Boat, Double> racers) {
        StringBuilder scoreBoard = new StringBuilder();
        AtomicInteger counter = new AtomicInteger(0);

        racers.entrySet().stream().sorted((racer1, racer2) -> {
            int compare;
            if (racer1.getValue() < 0) {
                compare = 1;
            } else if (racer2.getValue() < 0) {
                compare = -1;
            } else {
                compare = Double.compare(racer1.getValue(), racer2.getValue());
            }
            return compare;
        }).limit(3).forEach(racer -> {
            DecimalFormat dFormat = new DecimalFormat("0.##");
            String time = dFormat.format(racer.getValue());
            if (racer.getValue() < 0) {
                time = "Did not finish!";
            }
            counter.getAndIncrement();
            String placing = "";
            if (counter.get() == 1) {
                placing = "First place";
            } else if (counter.get() == 2) {
                placing = "Second place";
            } else if (counter.get() == 3) {
                placing = "Third place";
            }
            scoreBoard
                    .append("#").append(placing).append(" ")
                    .append(racer.getKey().boatInformation())
                    .append(String.format(" Time: %s" , time))
                    .append(System.lineSeparator());
        });
        return scoreBoard.toString().trim();
    }

    private void injectWindSpeed(Boat boat) {
        Method[] methods = boat.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Inject.class)) {
                method.setAccessible(true);
                try {
                    method.invoke(boat, this.getWindSpeed());
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public boolean hasEnoughParticipants() {
        return this.participants.size() >= 3;
    }
}

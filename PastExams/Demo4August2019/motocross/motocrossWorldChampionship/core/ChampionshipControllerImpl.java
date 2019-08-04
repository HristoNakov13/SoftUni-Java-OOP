package motocrossWorldChampionship.core;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.common.OutputMessages;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.core.interfaces.Factory;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.repositories.MotorcycleRepository;
import motocrossWorldChampionship.repositories.RaceRepository;
import motocrossWorldChampionship.repositories.RiderRepository;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ChampionshipControllerImpl implements ChampionshipController {
    private static final String MOTORCYCLE_SUFFIX = "Motorcycle";
    private static final int MINIMUM_PARTICIPANTS_IN_RACE_NEEDED = 3;
    private Repository<Motorcycle> motorcycleRepository;
    private Repository<Race> raceRepository;
    private Repository<Rider> riderRepository;
    private Factory factory;

    public ChampionshipControllerImpl() {
        this.motorcycleRepository = new MotorcycleRepository<>();
        this.riderRepository = new RiderRepository();
        this.raceRepository = new RaceRepository();
        this.factory = new FactoryImpl();
    }

    @Override
    public String createRider(String riderName) {
        Rider rider = this.factory.createRider(riderName);
        this.riderRepository.add(rider);
        return String.format(OutputMessages.RIDER_CREATED, riderName);
    }

    @Override
    public String createMotorcycle(String type, String model, int horsePower) {
        Motorcycle motorcycle = this.factory.createMotorcycle(type, model, horsePower);
        this.motorcycleRepository.add(motorcycle);
        return String.format(OutputMessages.MOTORCYCLE_CREATED, type + MOTORCYCLE_SUFFIX, model);
    }

    @Override
    public String addMotorcycleToRider(String riderName, String motorcycleModel) {
        Rider rider = this.riderRepository.getByName(riderName);

        if (rider == null) {
            throw new NullPointerException(String.format(ExceptionMessages.RIDER_NOT_FOUND, riderName));
        }
        Motorcycle motorcycle = this.motorcycleRepository.getByName(motorcycleModel);

        if (motorcycle == null) {
            throw new NullPointerException(String.format(ExceptionMessages.MOTORCYCLE_NOT_FOUND, motorcycleModel));
        }
        rider.addMotorcycle(motorcycle);
        return String.format(OutputMessages.MOTORCYCLE_ADDED, riderName, motorcycleModel);
    }

    @Override
    public String addRiderToRace(String raceName, String riderName) {
        Race race = this.raceRepository.getByName(raceName);

        if (race == null) {
            throw new NullPointerException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }
        Rider rider = this.riderRepository.getByName(riderName);

        if (rider == null) {
            throw new NullPointerException(String.format(ExceptionMessages.RIDER_NOT_FOUND, riderName));
        }
        race.addRider(rider);
        return String.format(OutputMessages.RIDER_ADDED, riderName, raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = this.raceRepository.getByName(raceName);
        if (race == null) {
            throw new NullPointerException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }
        this.validateRace(race);
        int laps = race.getLaps();
        Collection<Rider> riders = race.getRiders();

        List<Rider> winners = riders.stream().sorted((rider1, rider2) -> {
            double rider2Points = rider2.getMotorcycle().calculateRacePoints(laps);
            double rider1Points = rider1.getMotorcycle().calculateRacePoints(laps);
            return Double.compare(rider2Points, rider1Points);
        }).collect(Collectors.toList());
        winners.get(0).winRace();
        this.raceRepository.remove(race);

        StringBuilder winnersInfo = new StringBuilder();
        winnersInfo
                .append(String.format(OutputMessages.RIDER_FIRST_POSITION, winners.get(0).getName(), raceName))
                .append(System.lineSeparator())
                .append(String.format(OutputMessages.RIDER_SECOND_POSITION, winners.get(1).getName(), raceName))
                .append(System.lineSeparator())
                .append(String.format(OutputMessages.RIDER_THIRD_POSITION,  winners.get(2).getName(), raceName));
        return winnersInfo.toString();
    }

    private void validateRace(Race race) {
        int raceParticipants = race.getRiders().size();
        if (raceParticipants < MINIMUM_PARTICIPANTS_IN_RACE_NEEDED) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID, race.getName(), MINIMUM_PARTICIPANTS_IN_RACE_NEEDED));
        }
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = this.factory.createRace(name, laps);
        this.raceRepository.add(race);
        return String.format(OutputMessages.RACE_CREATED, name);
    }
}

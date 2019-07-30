package core.interfaces;

import entities.interfaces.Fighter;
import entities.interfaces.Tank;

import java.util.ArrayList;
import java.util.List;

public interface MachineFactory {
    Tank createTank(String name, double attackPoints, double defensePoints);

    Fighter createFighter(String name, double attackPoints, double defensePoints);



}

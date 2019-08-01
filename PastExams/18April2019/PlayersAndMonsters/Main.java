import common.InputInterpreter;
import core.Engine;
import core.ManagerControllerImpl;
import core.factory.Factory;
import core.factory.FactoryImpl;
import core.interfaces.ManagerController;
import models.battleFields.BattleFieldImpl;
import models.battleFields.interfaces.Battlefield;
import repositories.CardRepositoryImpl;
import repositories.PlayerRepositoryImpl;
import repositories.interfaces.CardRepository;
import repositories.interfaces.PlayerRepository;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ManagerController managerController = new ManagerControllerImpl();
        InputInterpreter inputInterpreter = new InputInterpreter();

        Engine engine = new Engine(managerController, inputInterpreter);
        engine.run();
    }
}

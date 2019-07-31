package repositories;

import models.players.interfaces.Player;
import repositories.interfaces.PlayerRepository;

import java.util.List;

public class PlayerRepositoryImpl implements PlayerRepository {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public List<Player> getPlayers() {
        return null;
    }

    @Override
    public void add(Player player) {

    }

    @Override
    public boolean remove(Player player) {
        return false;
    }

    @Override
    public Player find(String name) {
        return null;
    }
}

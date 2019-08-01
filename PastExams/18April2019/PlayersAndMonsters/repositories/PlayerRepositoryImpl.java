package repositories;

import models.players.interfaces.Player;
import repositories.interfaces.PlayerRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlayerRepositoryImpl implements PlayerRepository {
    private Map<String, Player> players;

    public PlayerRepositoryImpl() {
        this.players = new LinkedHashMap<>();
    }

    @Override
    public int getCount() {
        return this.players.size();
    }

    @Override
    public List<Player> getPlayers() {
        return new ArrayList<>(this.players.values());
    }

    @Override
    public void add(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }

        if (this.players.containsKey(player.getUsername())) {
            throw new IllegalArgumentException(String.format("Player %s already exists!", player.getUsername()));
        }
        this.players.put(player.getUsername(), player);
    }

    @Override
    public boolean remove(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (players.containsKey(player.getUsername())) {
            this.players.remove(player.getUsername());
            return true;
        }
        return false;
    }

    @Override
    public Player find(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player's username cannot be null or an empty string.");
        }
        return this.players.get(name);
    }
}


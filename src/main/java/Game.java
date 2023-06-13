import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class Game {

    private HashMap<String, Player> players = new HashMap<>();

    public HashMap<String, Player> getPlayers() {
        return players;
    }
    public void register(Player player) {

        if (players.containsKey(player.getName())) {
            throw new AlreadyExistsException(
                    "Игрок с именем " + player.name + "уже существует."
            );
        }
        players.put(player.getName(), player);
    }

    public int round (String playerName1, String playerName2) {

        Player player1 = findByName(playerName1);
        Player player2 = findByName(playerName2);
        int player1Strength = player1.getStrength();
        int player2Strength = player2.getStrength();

        if (player1Strength > player2Strength) {
            return 1;
        } else if (player1Strength < player2Strength) {
            return 2;
        } else {
            return 0;
        }
    }

    public Player findByName(String name) {

        Player player = players.get(name);
        if (player == null) {
            throw new NotRegisteredException(
                    "Игрок с именем " + name + " не зарегистрирован."
            );
        }
        return player;
    }
}

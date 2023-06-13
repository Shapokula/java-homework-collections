import java.util.ArrayList;
import java.util.function.Consumer;

public class Game {

    private ArrayList<Player> players = new ArrayList<>();

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void register(Player player) {

        for (int i = 0; i < players.size(); i++) {
            Player currentPlayer = players.get(i);
            String currentPlayerName = currentPlayer.getName();
            String playerName = player.getName();
            if (currentPlayer.getId() == player.getId()) {
                throw new AlreadyExistsException(
                        "Игрок с id " + player.getId() + " уже существует."
                );
            }
            if (currentPlayerName.equals(playerName)) {
                throw new AlreadyExistsException(
                        "Игрок с именем " + player.getName() + " уже существует."
                );
            }
        }

        players.add(player);
    }

    public int round(String playerName1, String playerName2) {

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
        for (Player player :
                players) {
            String playerName = player.getName();
            if (playerName.equals(name)) {
                return player;
            }
        }
        throw new NotRegisteredException(
                "Игрок с именем " + name + " не зарегистрирован."
        );
    }
}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game game = new Game();

    Player player1 = new Player(1, "Monster", 2);
    Player player2 = new Player(2, "Huge", 1);
    Player player3 = new Player(3, "Vanya", 1);

    @BeforeEach
    public void setup() {
        game.register(player1);
        game.register(player2);
        game.register(player3);
    }

    @Test
    public void register() {

        HashMap<String, Player> list = new HashMap<>();
        list.put(player1.getName(), player1);
        list.put(player2.getName(), player2);
        list.put(player3.getName(), player3);

        boolean expected = true;
        boolean actual = list.equals(game.getPlayers());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotAddPlayerWithExistingName() {

        Player player4 = new Player(4, "Vanya", 3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            game.register(player4);
        });
    }

    @Test
    public void shouldFindRegisteredPlayerByName() {

        Player expected = player1;
        Player actual = game.findByName("Monster");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotRegisteredException() {

        Player player4 = new Player(4, "Kesha", 3);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.findByName("Kesha");
        });
    }

    @Test
    public void firstPlayerShouldWin() {

        int expected = 1;
        int actual = game.round("Monster", "Huge");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void secondPlayerShouldWin() {

        int expected = 2;
        int actual = game.round("Vanya", "Monster");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldTie() {

        int expected = 0;
        int actual = game.round("Vanya", "Huge");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotRegisteredException2() {

        Player player4 = new Player(4, "Denys", 3);

        Assertions.assertThrows(NotRegisteredException.class, () ->
                game.round("Denys", "Huge"));
    }
}
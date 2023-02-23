package ladder.dto;

import static java.util.stream.Collectors.*;

import ladder.domain.Player;
import ladder.domain.Players;

public class PlayersResponse {
    private final String players;

    public PlayersResponse(String players) {
        this.players = players;
    }

    public static PlayersResponse ofPlayers(Players players) {
        return players.getPlayers().stream()
                .map(Player::getName)
                .map(name -> String.format("%-5s", name))
                .collect(collectingAndThen(joining(" "), PlayersResponse::new));
    }

    public String getPlayers() {
        return players;
    }
}

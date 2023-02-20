package ladder.service;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import ladder.domain.Height;
import ladder.domain.Ladder;
import ladder.domain.Line;
import java.util.ArrayList;
import java.util.List;
import ladder.domain.Player;
import ladder.domain.PlayerResult;
import ladder.domain.PlayerResults;
import ladder.domain.Players;
import ladder.domain.Result;
import ladder.domain.Results;

public class LadderService {
    private static final String PLAYERS_STRING_DELIMITER = ",";
    private static final String RESULTS_STRING_DELIMITER = ",";

    private final LineStrategy lineStrategy;

    public LadderService(LineStrategy lineStrategy) {
        this.lineStrategy = lineStrategy;
    }

    public Ladder createLadder(Height height, int playerCount) {
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < height.getHeight(); i++) {
            lines.add(lineStrategy.generate(playerCount));
        }
        return new Ladder(lines, playerCount);
    }

    public Players createPlayers(String namesInput) {
        String[] playerNames = namesInput.split(PLAYERS_STRING_DELIMITER);
        List<Player> players = Arrays.stream(playerNames)
                .map(Player::new)
                .collect(toList());
        return new Players(players);
    }

    public Results createResults(String resultsInput, int playerCount) {
        String[] resultsStrings = resultsInput.split(RESULTS_STRING_DELIMITER);
        List<Result> results = Arrays.stream(resultsStrings)
                .map(Result::new)
                .collect(toList());
        return new Results(results, playerCount);
    }

    public PlayerResults createPlayerResults(Players players, Ladder ladder, Results results) {
        List<Result> playerResults = players.getPlayers().stream()
                .map(players::findIndexByPlayer)
                .map(ladder::getLadderIndexResult)
                .map(results::findResultByIndex)
                .collect(toList());
        return combinePlayerResults(players.getPlayers(), playerResults);
    }

    private PlayerResults combinePlayerResults(List<Player> players, List<Result> results) {
        List<PlayerResult> resultList = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            resultList.add(new PlayerResult(players.get(i), results.get(i)));
        }
        return new PlayerResults(resultList);
    }
}

package ladder.controller;

import ladder.domain.Height;
import ladder.domain.Ladder;
import ladder.domain.Players;
import ladder.dto.LadderResponse;
import ladder.dto.PlayersResponse;
import ladder.service.LadderService;
import ladder.view.LadderView;

public class LadderController {
    private final LadderService ladderService;
    private final LadderView ladderView;

    public LadderController(LadderService ladderService, LadderView ladderView) {
        this.ladderService = ladderService;
        this.ladderView = ladderView;
    }

    public void run() {
        String inputNames = ladderView.readPlayerNames();
        int inputHeight = ladderView.readLadderHeight();
        Players players = ladderService.createPlayers(inputNames);
        Height height = new Height(inputHeight);
        Ladder ladder = ladderService.createLadder(height, players.getPlayersCount());
        ladderView.printResult(PlayersResponse.ofPlayers(players), LadderResponse.ofLadder(ladder));
    }
}

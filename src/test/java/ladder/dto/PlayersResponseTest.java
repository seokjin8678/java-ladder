package ladder.dto;

import static ladder.Util.createPlayers;
import static org.assertj.core.api.Assertions.*;

import ladder.domain.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersResponseTest {

    @Test
    @DisplayName("정상적으로 문자열을 반환해야 한다.")
    void ofPlayers_success() {
        // given
        Players players = createPlayers("glen", "doggy", "man");

        // when
        PlayersResponse playersResponse = PlayersResponse.ofPlayers(players);

        // then
        assertThat(playersResponse.getPlayers())
                .isEqualTo("glen  doggy man  ");
    }
}

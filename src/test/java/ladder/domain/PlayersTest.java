package ladder.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayersTest {

    private static Stream<Arguments> generateValidNameSize() {
        return Stream.of(
                Arguments.of(List.of("pobi", "crong"), new String[]{"pobi", "crong"}),
                Arguments.of(List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"),
                        new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"})
        );
    }

    private static Stream<Arguments> generateInvalidNameSize() {
        return Stream.of(
                Arguments.of(List.of("pobi")),
                Arguments.of(List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"))
        );
    }

    @ParameterizedTest(name = "입력: {0}, 출력: {1}")
    @MethodSource("generateValidNameSize")
    @DisplayName("플레이어는 2명 이상, 10명 이하만 가능하다.")
    void should_valid_player_size(final List<String> names, final String[] expected) {
        final Players players = Players.from(names);

        assertThat(players.getPlayerNames()).containsExactly(expected);
    }

    @ParameterizedTest(name = "입력: {0}")
    @MethodSource("generateInvalidNameSize")
    @DisplayName("플레이어는 2명 미만, 10명 초과인 경우 예외를 던진다.")
    void throw_exception_invalid_player_size(final List<String> names) {
        assertThatThrownBy(() -> Players.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 2명 이상, 10명 이하만 가능합니다. 현재 입력한 플레이어 수는 " + names.size() + "명 입니다.");
    }

    @Test
    @DisplayName("플레이어의 이름에 중복이 있으면 예외를 던진다.")
    void throw_exception_duplicate_name() {
        final List<String> names = List.of("pobi", "crong", "eddy", "crong");

        assertThatThrownBy(() -> Players.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 중복되면 안됩니다.");
    }
}

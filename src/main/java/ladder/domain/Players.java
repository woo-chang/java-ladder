package ladder.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {

    private static final int MINIMUM_PLAYER_SIZE = 2;
    private static final int MAXIMUM_PLAYER_SIZE = 10;

    private final List<Player> players;

    public Players(final List<String> names) {
        final List<Player> players = generatePlayers(names);
        validate(players);
        this.players = players;
    }

    private List<Player> generatePlayers(final List<String> names) {
        return names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private void validate(final List<Player> players) {
        validatePlayerSize(players);
        validateDuplicatePlayer(players);
    }

    private void validatePlayerSize(final List<Player> players) {
        if (hasSmallSize(players) || hasLargeSize(players)) {
            throw new IllegalArgumentException(
                    "플레이어는 " + MINIMUM_PLAYER_SIZE + "명 이상, " + MAXIMUM_PLAYER_SIZE + "명 이하만 가능합니다."
                            + " 현재 입력한 플레이어 수는 " + players.size() + "명 입니다.");
        }
    }

    private boolean hasSmallSize(final List<Player> players) {
        return players.size() < MINIMUM_PLAYER_SIZE;
    }

    private boolean hasLargeSize(final List<Player> players) {
        return MAXIMUM_PLAYER_SIZE < players.size();
    }

    private void validateDuplicatePlayer(final List<Player> players) {
        final Set<Player> uniquePlayers = new HashSet<>(players);

        if (isDuplicate(players, uniquePlayers)) {
            throw new IllegalArgumentException("플레이어 이름은 중복되면 안됩니다.");
        }
    }

    private boolean isDuplicate(final List<Player> players, final Set<Player> uniquePlayers) {
        return players.size() != uniquePlayers.size();
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public int size() {
        return players.size();
    }

    public boolean exists(final Player player) {
        return players.contains(player);
    }
}

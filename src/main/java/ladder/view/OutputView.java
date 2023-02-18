package ladder.view;

import java.util.List;
import java.util.StringJoiner;
import ladder.domain.Direction;
import ladder.domain.Ladder;
import ladder.domain.Line;
import ladder.domain.Players;

public class OutputView {

    private static final String EXECUTION_RESULT = "실행결과";
    private static final String LINE_BREAK = "\n";
    private static final int FORMAT_LENGTH = 5;
    private static final String RIGHT_ALIGN = "%" + FORMAT_LENGTH + "s";
    private static final String BLANK = " ";
    private static final String EMPTY = "";
    private static final String LADDER = "|";
    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printLadderResult(final Players players, final Ladder ladder) {
        System.out.println(LINE_BREAK + EXECUTION_RESULT + LINE_BREAK);
        printPlayerNames(players);
        printLadder(ladder);
    }

    private void printPlayerNames(final Players players) {
        final List<String> playerNames = players.getPlayerNames();
        System.out.println(getFormattedPlayerNames(playerNames));
    }

    private String getFormattedPlayerNames(final List<String> playerNames) {
        final StringJoiner joiner = new StringJoiner(BLANK);

        for (final String playerName : playerNames) {
            joiner.add(String.format(RIGHT_ALIGN, playerName));
        }

        return joiner.toString();
    }

    private void printLadder(final Ladder ladder) {
        final List<Line> lines = ladder.getLines();

        for (final Line line : lines) {
            printLine(line);
        }
    }

    private void printLine(final Line line) {
        final List<Direction> directions = line.getDirections();
        final StringJoiner stringJoiner = new StringJoiner(LADDER, EMPTY, LADDER);

        for (final Direction direction : directions) {
            stringJoiner.add(direction.getFoothold());
        }

        System.out.println(stringJoiner);
    }

    public void printErrorMessage(final String errorMessage) {
        System.out.println(ERROR_PREFIX + errorMessage);
    }
}

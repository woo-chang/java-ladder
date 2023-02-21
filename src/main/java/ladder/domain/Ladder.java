package ladder.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ladder.domain.generator.DirectionGenerator;

public class Ladder {

    private final List<Line> lines;

    public Ladder(final List<Line> lines) {
        this.lines = lines;
    }

    public static Ladder of(final DirectionGenerator directionGenerator, final Players players, final Height height) {
        final List<Line> lines = new ArrayList<>();
        int count = height.getValue();

        while (count-- > 0) {
            lines.add(Line.of(directionGenerator, players.size()));
        }
        return new Ladder(lines);
    }

    public List<Line> getLines() {
        return Collections.unmodifiableList(lines);
    }
}


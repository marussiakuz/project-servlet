package com.tictactoe.model;

import java.util.*;
import java.util.stream.Collectors;

public class Field {
    private final Map<Integer, Sign> field;
    private static final List<List<Integer>> WIN_POSSIBILITIES;
    private static final List<List<Integer>> PRIORITY;

    public Field() {
        field = new HashMap<>();
        field.put(0, Sign.EMPTY);
        field.put(1, Sign.EMPTY);
        field.put(2, Sign.EMPTY);
        field.put(3, Sign.EMPTY);
        field.put(4, Sign.EMPTY);
        field.put(5, Sign.EMPTY);
        field.put(6, Sign.EMPTY);
        field.put(7, Sign.EMPTY);
        field.put(8, Sign.EMPTY);
    }

    static {
        WIN_POSSIBILITIES = List.of(
                List.of(0, 1, 2),
                List.of(3, 4, 5),
                List.of(6, 7, 8),
                List.of(0, 3, 6),
                List.of(1, 4, 7),
                List.of(2, 5, 8),
                List.of(0, 4, 8),
                List.of(2, 4, 6)
        );

        PRIORITY = List.of(
                List.of(4),
                List.of(0, 2, 6 ,8),
                List.of(1, 3, 5, 7)
        );
    }

    public Map<Integer, Sign> getField() {
        return field;
    }

    public int getEmptyFieldIndex() {
        return field.entrySet().stream()
                .filter(e -> e.getValue() == Sign.EMPTY)
                .map(Map.Entry::getKey)
                .findFirst().orElse(-1);
    }

    public int getTheBestFieldIndex() {
        int probabilityOfXWinning = checkProbabilityOfWinning(Sign.CROSS);
        if (probabilityOfXWinning >= 0) return probabilityOfXWinning;

        int probabilityOfYWinning = checkProbabilityOfWinning(Sign.NOUGHT);
        if (probabilityOfYWinning >= 0) return probabilityOfYWinning;

        return getTheBestEmptyField();
    }

    public List<Sign> getFieldData() {
        return field.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public Sign checkWin() {
        for (List<Integer> winPossibility : WIN_POSSIBILITIES) {
            Sign sign = field.get(winPossibility.get(0));
            if (sign != Sign.EMPTY && sign == field.get(winPossibility.get(1))
                    && sign == field.get(winPossibility.get(2))) {
                changeField(winPossibility, sign);
                return sign;
            }
        }

        return Sign.EMPTY;
    }

    private void changeField(List<Integer> list, Sign sign) {
        Sign strikethroughSign = getStrikethroughSign(sign, Math.abs(list.get(0) - list.get(1)));
        for (Integer integer : list) {
            field.put(integer, strikethroughSign);
        }
    }

    private Sign getStrikethroughSign(Sign sign, int delta) {
        StringBuilder builder = new StringBuilder(sign == Sign.CROSS ? "CROSS" : "NOUGHT");

        return switch (delta) {
            case 1 -> Sign.valueOf(builder.append("_CROSSED_OUT_HORIZONTALLY").toString());
            case 2 -> Sign.valueOf(builder.append("_DIAGONAL_STRIKETHROUGH_RIGHT").toString());
            case 3 -> Sign.valueOf(builder.append("_CROSSED_OUT_VERTICALLY").toString());
            default -> Sign.valueOf(builder.append("_DIAGONAL_STRIKETHROUGH_LEFT").toString());
        };
    }

    private int checkProbabilityOfWinning(Sign sign) {
        List<Integer> positions = getAllPositions(sign);

        for (List<Integer> winPossibility : WIN_POSSIBILITIES) {
            List<Integer> copy = new ArrayList<>(List.copyOf(winPossibility));
            if (copy.removeAll(positions) && copy.size() == 1 && field.get(copy.get(0)) == Sign.EMPTY)
                return copy.get(0);
        }

        return -1;
    }

    private List<Integer> getAllPositions(Sign sign) {
        return field.entrySet().stream()
                .filter(e -> e.getValue() == sign)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private int getTheBestEmptyField() {
        List<Integer> allEmptyPositions = getAllPositions(Sign.EMPTY);

        for (List<Integer> priorityIndexes : PRIORITY) {
            int chosen = getRandomIndex(allEmptyPositions, priorityIndexes);
            if (chosen >= 0) return chosen;
        }

        return getEmptyFieldIndex();
    }

    private int getRandomIndex(List<Integer> dest, List<Integer> src) {
        List<Integer> copy = new ArrayList<>(dest);
        copy.retainAll(src);

        if (!copy.isEmpty()) {
            return copy.size() == 1 ? copy.get(0) : copy.get(new Random().nextInt(copy.size()));
        }

        return -1;
    }
}
package com.tictactoe.model;

public enum Sign {
    EMPTY("img/empty.png"),
    CROSS("img/cross.png"),
    NOUGHT("img/nought.png"),
    CROSS_CROSSED_OUT_VERTICALLY("img/cross_crossed_out_vertically.png"),
    NOUGHT_CROSSED_OUT_VERTICALLY("img/nought_crossed_out_vertically.png"),
    CROSS_CROSSED_OUT_HORIZONTALLY("img/cross_crossed_out_horizontally.png"),
    NOUGHT_CROSSED_OUT_HORIZONTALLY("img/nought_crossed_out_horizontally.png"),
    CROSS_DIAGONAL_STRIKETHROUGH_LEFT("img/cross_diagonal_strikethrough_left.png"),
    NOUGHT_DIAGONAL_STRIKETHROUGH_LEFT("img/nought_diagonal_strikethrough_left.png"),
    CROSS_DIAGONAL_STRIKETHROUGH_RIGHT("img/cross_diagonal_strikethrough_right.png"),
    NOUGHT_DIAGONAL_STRIKETHROUGH_RIGHT("img/nought_diagonal_strikethrough_right.png");

    private final String sign;

    Sign(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
package main.java.Day2;

import lombok.Getter;

public class Game {
    public final Integer GameId;
    public final Integer Blue;
    public final Integer Green;
    public final Integer Red;

    public Game(final String gameInput) {
        this.GameId = parseGameId(gameInput);

        var inputs = computeColors(gameInput);
        this.Blue = inputs[0];
        this.Green = inputs[1];
        this.Red = inputs[2];
    }

    private Integer parseGameId(final String gameInput) {
        var split = gameInput.split(":");
        var numberString = split[0].split(" ")[1];
        return Integer.parseInt(numberString);
    }

    private Integer[] computeColors(final String gameInput) {
        var colors = gameInput.split(":")[1].split(",|;");
        var maxBlue = 0;
        var maxGreen = 0;
        var maxRed = 0;

        for (var color : colors) {
            var splitColor = color.trim().split(" ");

            switch (splitColor[1]) {
                case "blue" -> {
                    var parsed = Integer.parseInt(splitColor[0]);
                    if (parsed > maxBlue) { maxBlue = parsed; }
                }
                case "green" -> {
                    var parsed = Integer.parseInt(splitColor[0]);
                    if (parsed > maxGreen) { maxGreen = parsed; }
                }
                case "red" -> {
                    var parsed = Integer.parseInt(splitColor[0]);
                    if (parsed > maxRed) { maxRed = parsed; }
                }
                default -> throw new UnsupportedOperationException();
            }
        }

        return new Integer[] { maxBlue, maxGreen, maxRed };
    }

    public boolean isValid(final int maxBlue, final int maxGreen, final int maxRed) {
        if (this.Blue > maxBlue) { return false; }
        if (this.Green > maxGreen) { return false; }
        if (this.Red > maxRed) { return false; }

        return true;
    }

    @Override
    public String toString() {
        return "Game{" +
                "GameId=" + GameId +
                ", Blue=" + Blue +
                ", Green=" + Green +
                ", Red=" + Red +
                '}';
    }
}

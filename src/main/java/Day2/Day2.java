package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        var filename = "./src/main/java/Day2/input.txt";
        var file = new File(filename);
        var games = new ArrayList<Game>();

        try (var myReader = new Scanner(file)) {
            myReader.useDelimiter("\\n");
            while (myReader.hasNext()) {
                var current = myReader.next();
                games.add(new Game(current));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        var total = 0;
        var powerSum = 0;

        for (var game : games) {
            if (game.isValid(14, 13, 12)) {
                total += game.GameId;
            }
                // Calculate power sum for part 2
                powerSum += (game.Blue * game.Green * game.Red);
        }

        System.out.printf("Summed valid game indexes: %d\n", total);
        System.out.printf("Power sum of valid game indexes: %d\n", powerSum);
    }
}

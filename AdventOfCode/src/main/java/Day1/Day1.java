package main.java.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {
        var filename = "./src/main/java/Day1/input.txt";
        var file = new File(filename);
        var total = 0;

        try (var myReader = new Scanner(file)) {
            while (myReader.hasNext()) {
                var current = myReader.next();
                var front = 0;
                var back = current.length() - 1;

                while(!Character.isDigit(current.charAt(front)) || !Character.isDigit(current.charAt(back))) {
                    if (!Character.isDigit(current.charAt(front))) { front++; }
                    if (!Character.isDigit(current.charAt(back))) { back--; }
                }

                var numberString = String.format("%s%s", current.charAt(front), current.charAt(back));
                var intVal = Integer.parseInt(numberString);
                total += intVal;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.printf("Result: %d%n", total);
    }
}

package main.java.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {
        var filename = "./src/main/java/Day1/input.txt";
        var file = new File(filename);

        var partOneResult = calculatePartOne(file);
        var partTwoResult = calculatePartTwo(file);

        System.out.printf("Part One Result: %d%n", partOneResult);
        System.out.printf("Part Two Result: %d%n", partTwoResult);
    }

    private static int calculatePartOne(final File file) {
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

        return total;
    }

    private static int calculatePartTwo(final File file) {
        var total = 0;

        try (var myReader = new Scanner(file)) {
            while (myReader.hasNext()) {
                var current = myReader.next();
                var numbers = calculateNumbersInString(current);
                var first = numbers.get(0);
                var second = numbers.size() == 1 ? numbers.get(0) : numbers.get(numbers.size() - 1);

                var numberString = String.format("%s%s", first, second);
                var intVal = Integer.parseInt(numberString);
                total += intVal;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return total;
    }

    private static List<Integer> calculateNumbersInString(final String current) {
        var index = 0;
        var numbers = new ArrayList<Integer>();

        while (index < current.length()) {
            var currentChar = current.charAt(index);
            if (!Character.isDigit(currentChar)) {
                switch (currentChar) {
                    case 'o' -> {
                        var isOne = calculateIsNumber(index, "one", current);
                        if (isOne) {
                            numbers.add(1);
                            index += 2;
                            break;
                        }
                        index++;
                    }
                    case 't' -> {
                        var isTwo = calculateIsNumber(index, "two", current);
                        if (isTwo) {
                            numbers.add(2);
                            index += 2;
                            break;
                        }

                        var isThree = calculateIsNumber(index, "three", current);
                        if (isThree) {
                            numbers.add(3);
                            index += 4;
                            break;
                        }
                        index++;
                    }
                    case 'f' -> {
                        var isFour = calculateIsNumber(index, "four", current);
                        if (isFour) {
                            numbers.add(4);
                            index += 4;
                            break;
                        }

                        var isFive = calculateIsNumber(index, "five", current);
                        if (isFive) {
                            numbers.add(5);
                            index += 3;
                            break;
                        }
                        index++;
                    }
                    case 's' -> {
                        var isSix = calculateIsNumber(index, "six", current);
                        if (isSix) {
                            numbers.add(6);
                            index += 3;
                            break;
                        }

                        var isSeven = calculateIsNumber(index, "seven", current);
                        if (isSeven) {
                            numbers.add(7);
                            index += 5;
                            break;
                        }
                        index++;
                    }
                    case 'e' -> {
                        var isEight = calculateIsNumber(index, "eight", current);
                        if (isEight) {
                            numbers.add(8);
                            index += 4;
                            break;
                        }
                        index++;
                    }
                    case 'n' -> {
                        var isNine = calculateIsNumber(index, "nine", current);
                        if (isNine) {
                            numbers.add(9);
                            index += 3;
                            break;
                        }
                        index++;
                    }
                    default -> index++;
                }
            } else {
                numbers.add(Integer.parseInt(String.format("%s", currentChar)));
                index++;
            }
        }

        return numbers;
    }

    private static Boolean calculateIsNumber(
            final int index,
            final String expectedString,
            final String originalString
    ) {
        try {
            var substring = originalString.substring(index, (index + expectedString.length()));
            return substring.equals(expectedString);
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }
}

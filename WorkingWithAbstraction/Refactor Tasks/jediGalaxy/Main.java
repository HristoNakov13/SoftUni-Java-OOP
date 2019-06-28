package jediGalaxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        int[] dimestions = Arrays.stream(bfr.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int x = dimestions[0];
        int y = dimestions[1];

        int[][] matrix = new int[x][y];

        int value = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrix[i][j] = value++;
            }
        }

        String command = bfr.readLine();
        long sum = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] ivoS = Arrays.stream(command.split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] evil = Arrays.stream(bfr.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int xE = evil[0];
            int yE = evil[1];

            while (xE >= 0 && yE >= 0) {
                if (xE < matrix.length && yE < matrix[0].length) {
                    matrix[xE][yE] = 0;
                }
                xE--;
                yE--;
            }
            int xI = ivoS[0];
            int yI = ivoS[1];

            while (xI >= 0 && yI < matrix[1].length) {
                if (xI < matrix.length && yI >= 0 && yI < matrix[0].length) {
                    sum += matrix[xI][yI];
                }

                yI++;
                xI--;
            }
            command = bfr.readLine();
        }
        System.out.println(sum);
    }
}

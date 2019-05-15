package models;

import java.util.Random;

public class Game {
    private int[][] map;
    private int n;
    private static Random random = new Random();

    public Game(int n) {
        map = new int[n][n];
        this.n = n;
    }

    public void insert() {
        int x, y;
        do {
            x = random.nextInt(n);
            y = random.nextInt(n);
        } while(map[x][y] != 0);
        map[x][y] = random.nextInt(2) * 2 + 2;
    }

    private void applyGravity(Direction direction) {
        int deltaX = Direction.getDeltaX(direction);
        int deltaY = Direction.getDeltaY(direction);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                try {
                    if(map[i + deltaX][j + deltaY] == 0) {
                        map[i + deltaX][j + deltaY] = map[i][j];
                        map[i][j] = 0;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {}

            }
        }
    }

    public void move(Direction direction) {
        for(int i = 0; i < n; i++)
            applyGravity(direction);
        int deltaX = Direction.getDeltaX(direction);
        int deltaY = Direction.getDeltaY(direction);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                try {
                    if(map[i][j] == map[i + deltaX][j + deltaY]) {
                        map[i][j] = map[i][j] * 2;
                        map[i + deltaX][j + deltaY] = 0;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {}
            }
        }
        for(int i = 0; i < n; i++)
            applyGravity(direction);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                stringBuilder.append(String.format("%d\t", map[i][j]));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}

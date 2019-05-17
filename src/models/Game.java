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

    public int getN() {
        return n;
    }

    public int getCell(int x, int y) {
        return map[x][y];
    }

    public void insert() {
        if (isFull())
            return;
        int x, y;
        do {
            x = random.nextInt(n);
            y = random.nextInt(n);
        } while (map[x][y] != 0);
        map[x][y] = random.nextInt(2) * 2 + 2;
    }

    private void applyGravity(Direction direction) {
        int deltaX = Direction.getDeltaX(direction);
        int deltaY = Direction.getDeltaY(direction);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                try {
                    if (map[i + deltaX][j + deltaY] == 0) {
                        map[i + deltaX][j + deltaY] = map[i][j];
                        map[i][j] = 0;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }

            }
        }
    }

    public void move(Direction direction) {
        for (int i = 0; i < n; i++)
            applyGravity(direction);
        int deltaX = Direction.getDeltaX(direction);
        int deltaY = Direction.getDeltaY(direction);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                try {
                    if (map[i][j] == map[i + deltaX][j + deltaY]) {
                        map[i][j] = map[i][j] * 2;
                        map[i + deltaX][j + deltaY] = 0;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        for (int i = 0; i < n - 1; i++)
            applyGravity(direction);
    }

    public boolean isFull() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (map[i][j] == 0)
                    return false;
        return true;
    }

    private boolean isEqualsMap(int[][] map1, int[][] map2) {
        if (map1.length != map2.length)
            return false;
        for (int i = 0; i < map1.length; i++) {
            if (map1[i].length != map2[i].length)
                return false;
            for (int j = 0; j < map1[i].length; j++)
                if (map1[i][j] != map2[i][j])
                    return false;
        }
        return true;
    }

    public boolean canMove(Direction direction) {
        Game game = new Game(n);
        for (int i = 0; i < n; i++)
            System.arraycopy(map[i], 0, game.map[i], 0, n);
        game.move(direction);
        return !isEqualsMap(map, game.map);
    }

    public boolean isLosed() {
        return isFull() &&
                !canMove(Direction.UP) &&
                !canMove(Direction.LEFT) &&
                !canMove(Direction.DOWN) &&
                !canMove(Direction.RIGHT);
    }

    @Override
    public String toString() {
        return getString(map);
    }

    public String toString(int[][] map) {
        return getString(map);
    }

    private String getString(int[][] map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                stringBuilder.append(String.format("%d\t", map[i][j]));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}

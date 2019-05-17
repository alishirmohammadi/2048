package models;

public enum Direction {
    UP, RIGHT, LEFT, DOWN;

    public static int getDeltaY(Direction direction) {
        if(direction == UP)
            return -1;
        if(direction == DOWN)
            return 1;
        return 0;
    }

    public static int getDeltaX(Direction direction) {
        if(direction == LEFT)
            return -1;
        if(direction == RIGHT)
            return 1;
        return 0;
    }
}

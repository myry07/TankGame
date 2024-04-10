package TankGame.main;

/**
 * @author myry
 * @date 2024-09-29 23:09
 * Node节点 保存坦克信息 处理字符串的难度非常高
 */
public class Node {
    private int x;
    private int y;
    private int Direction;

    public Node(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        Direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return Direction;
    }

    public void setDirection(int direction) {
        Direction = direction;
    }
}

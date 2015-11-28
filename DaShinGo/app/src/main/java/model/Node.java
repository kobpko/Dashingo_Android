package model;

/**
 * Created by MSI on 2015/11/25.
 */
public class Node {
    private Point point;
    private Atn atn;

    public Atn getAtn() {
        return atn;
    }

    public void setAtn(Atn atn) {
        this.atn = atn;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Node() {
    }

    public Node(Atn atn, Point point) {
        this.atn = atn;
        this.point = point;
    }
}
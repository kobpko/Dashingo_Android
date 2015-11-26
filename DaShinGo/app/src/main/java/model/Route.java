package model;

import java.lang.String;import java.util.Vector;

/**
 * Created by MSI on 2015/11/25.
 */
public class Route {

    private String routeId;
    private User user;
    private Vector<Node> nodes;
    private String description;

    public Route() {
    }

    public Route(String description, Vector<Node> nodes, String routeId, User user) {
        this.description = description;
        this.nodes = nodes;
        this.routeId = routeId;
        this.user = user;
    }

    public String getRouteId() {

        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vector<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Vector<Node> nodes) {
        this.nodes = nodes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

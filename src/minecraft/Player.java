package minecraft;

import javafx.geometry.*;

/**
 * Created with IntelliJ IDEA.
 * User: Gabriel
 * Date: 11.06.13
 * Time: 10:37
 * To change this template use File | Settings | File Templates.
 */
public class Player {
    private int entityID = 0;
    private String name;
    private Point3D position;
    private short health;
    private short food;

    private Player() { }

    //Singleton
    private static Player instance;
    public static Player getPlayer() {
        if(instance == null) {
            instance = new Player();
        }
        return instance;
    }

    //Getter & Setter

    public int getEntityID() {
        return entityID;
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point3D getPosition() {
        return position;
    }

    public void setPosition(Point3D position) {
        this.position = position;
    }

    public short getHealth() {
        return health;
    }

    public void setHealth(short health) {
        this.health = health;
    }

    public short getFood() {
        return food;
    }

    public void setFood(short food) {
        this.food = food;
    }
}

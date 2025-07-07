package org.ahicode.objects;

public class ObjectsSetter {

    private final int tileSize;

    public ObjectsSetter(int tileSize) {
        this.tileSize = tileSize;
    }

    public GameObject[] setObject() {
        GameObject[] result = new GameObject[10];

        //result[0] = new Tent(tileSize * 1, tileSize * 1, tileSize);
        //result[0].setCollision(true);

        return result;
    }
}

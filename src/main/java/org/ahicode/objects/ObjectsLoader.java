package org.ahicode.objects;

import org.ahicode.application.core.GameSettings;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.*;
import java.util.List;

public class ObjectsLoader {

    private final static Set<String> bushNames = Set.of("bush", "bushType1", "bushType2", "bushType3", "bushType4");
    private final static Set<String> treeNames = Set.of("treeType1", "treeType2", "treeType3", "treeType4");
    private final static Set<String> fenceNames = Set.of("fenceSideLeft", "fenceSideRight");
    private final static String LEVEL_TMX_PATH = "/levels/Level1.tmx";
    private final static String OBJECTS_TSX_PATH = "/levels/full_basic_objects.tsx";
    private final static String TREES_TSX_PATH = "/levels/treesSpriteSheet.tsx";

    private final static int Y_OFFSET = GameSettings.TILE_SIZE * 2;

    public static GameObject[] loadObjects(Map<Integer, ObjectMetadata> objectMetadataMap) {
        try {
            List<GameObject> objectList = new ArrayList<>();

            InputStream is = ObjectsLoader.class.getResourceAsStream(LEVEL_TMX_PATH);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(is);
            document.getDocumentElement().normalize();

            NodeList objectGroupList = document.getElementsByTagName("objectgroup");

            for (int i = 0; i < objectGroupList.getLength(); i++) {
                Node objectGroupNode = objectGroupList.item(i);

                if (objectGroupNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element objectGroupElement = (Element) objectGroupNode;
                    String name = objectGroupElement.getAttribute("name");

                    if ("environment".equals(name)) {
                        NodeList objectsList = objectGroupElement.getElementsByTagName("object");

                        for (int j = 0; j < objectsList.getLength(); j++) {
                            Node objectNode = objectsList.item(j);

                            if (objectNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element objectElement = (Element) objectNode;

                                int objectGid = Integer.parseInt(objectElement.getAttribute("gid"));
                                int objectX = Integer.parseInt(objectElement.getAttribute("x")) * GameSettings.SCALE;
                                int objectY = Integer.parseInt(objectElement.getAttribute("y")) * GameSettings.SCALE - Y_OFFSET;
                                int objectWidth = Integer.parseInt(objectElement.getAttribute("width"));
                                int objectHeight = Integer.parseInt(objectElement.getAttribute("height"));

                                ObjectMetadata objectMeta = objectMetadataMap.get(objectGid);
                                GameObject gameObject = new GameObject(objectX, objectY, objectMeta.getOrder(), objectMeta.getSolidArea());

                                if (objectMeta != null) {
                                    gameObject.setImage(objectMeta.getImage());
                                    gameObject.setName(objectMeta.getName());
                                    gameObject.setCollision(objectMeta.isCollision());
                                    gameObject.setSpriteWidth(objectWidth);
                                    gameObject.setSpriteHeight(objectHeight);
                                    //gameObject.setSolidArea(objectMeta.getSolidArea());
                                }

                                objectList.add(gameObject);
                            }
                        }
                    }
                }
            }

            GameObject[] objects = objectList.toArray(new GameObject[0]);
            sortObjectArray(objects, 0, objects.length - 1);

            return objects;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<Integer, ObjectMetadata> getObjectsMetadata() {
        try {
            InputStream is = ObjectsLoader.class.getResourceAsStream(LEVEL_TMX_PATH);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(is);
            document.getDocumentElement().normalize();

            NodeList tilesetList = document.getElementsByTagName("tileset");
            int fullBasicObjectsGid = -1;
            int treesSpriteSheetGid = -1;

            for (int i = 0; i < tilesetList.getLength(); i++) {
                Node tilesetNode = tilesetList.item(i);

                if (tilesetNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element tilesetElement = (Element) tilesetNode;
                    String source = tilesetElement.getAttribute("source");

                    if ("full_basic_objects.tsx".equals(source)) {
                        fullBasicObjectsGid = Integer.parseInt(tilesetElement.getAttribute("firstgid"));
                    } else if ("treesSpriteSheet.tsx".equals(source)) {
                        treesSpriteSheetGid = Integer.parseInt(tilesetElement.getAttribute("firstgid"));
                    }
                }
            }

            Map<Integer, ObjectMetadata> objectsMeta = new HashMap<>();
            objectsMeta.putAll(parseTsxFile(OBJECTS_TSX_PATH, fullBasicObjectsGid));
            objectsMeta.putAll(parseTsxFile(TREES_TSX_PATH, treesSpriteSheetGid));

            return objectsMeta;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void sortObjectArray(GameObject[] objectsArray, int low, int high) {
        if (low < high) {
            int pivot = partition(objectsArray, low, high);

            sortObjectArray(objectsArray, low, pivot - 1);
            sortObjectArray(objectsArray, pivot + 1, high);
        }
    }

    private static int partition(GameObject[] objectsArray, int low, int high) {
        int pivot = objectsArray[high].getWorldY();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (objectsArray[j].getWorldY() < pivot) {
                i++;

                GameObject temp = objectsArray[i];
                objectsArray[i] = objectsArray[j];
                objectsArray[j] = temp;
            }
        }

        GameObject temp = objectsArray[i + 1];
        objectsArray[i + 1] = objectsArray[high];
        objectsArray[high] = temp;

        return i + 1;
    }

    private static Map<Integer, ObjectMetadata> parseTsxFile(String path, int tiledOffset) throws Exception {
        Map<Integer, ObjectMetadata> tileObjects = new HashMap<>();

        InputStream is = ObjectsLoader.class.getResourceAsStream(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(is);

        Element tileset = document.getDocumentElement();

        int tileWidth = Integer.parseInt(tileset.getAttribute("tilewidth"));
        int tileHeight = Integer.parseInt(tileset.getAttribute("tileheight"));

        NodeList imageNodes = tileset.getElementsByTagName("image");
        if (imageNodes.getLength() == 0) {
            throw new Exception("No image source found in TSX file");
        }

        Element imageElement = (Element) imageNodes.item(0);
        String imagePath = imageElement.getAttribute("source").replace("../objects/", "/objects/");
        int imageWidth = Integer.parseInt(imageElement.getAttribute("width"));
        int imageHeight = Integer.parseInt(imageElement.getAttribute("height"));

        InputStream imageStream = ObjectsLoader.class.getResourceAsStream(imagePath);
        BufferedImage tilesetImage = ImageIO.read(imageStream);

        int columns = Integer.parseInt(tileset.getAttribute("columns"));

        NodeList tileNodes = tileset.getElementsByTagName("tile");
        for (int i = 0; i < tileNodes.getLength(); i++) {
            Element tileElement = (Element) tileNodes.item(i);
            int tileId = Integer.parseInt(tileElement.getAttribute("id")) + tiledOffset;

            int row = i / columns;
            int col = i % columns;
            BufferedImage objectImage = tilesetImage.getSubimage(
                    col * tileWidth, row * tileHeight,
                    tileWidth, tileHeight
            );

            ObjectMetadata metadata = getObjectMetadata(tileElement, objectImage);

            tileObjects.put(tileId, metadata);
        }

        return tileObjects;
    }

    private static ObjectMetadata getObjectMetadata(Element tileElement, BufferedImage objectImage) {
        RenderingOrder renderingOrder = RenderingOrder.BACKGROUND;
        Rectangle solidArea = new Rectangle(0, 0, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE);
        boolean objectCollision = false;
        String objectName = null;

        NodeList propertyNodes = tileElement.getElementsByTagName("property");
        for (int j = 0; j < propertyNodes.getLength(); j++) {
            Element propertyElement = (Element) propertyNodes.item(j);
            String propertyName = propertyElement.getAttribute("name");
            String propertyValue = propertyElement.getAttribute("value");

            if ("name".equals(propertyName)) {
                objectName = propertyValue;

                if (bushNames.contains(objectName)) {
                    renderingOrder = RenderingOrder.FOREGROUND;
                }

                if (treeNames.contains(objectName)) {
                    solidArea = new Rectangle(0, 0, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE * 2);
                }

                if (fenceNames.contains(objectName)) {
                    if ("fenceSideRight".equals(objectName)) {
                        solidArea = new Rectangle(13 * GameSettings.SCALE, 0, 3 * GameSettings.SCALE, 16 * GameSettings.SCALE);
                    } else if ("fenceSideLeft".equals(objectName)) {
                        solidArea = new Rectangle(0, 0, 3 * GameSettings.SCALE, 16 * GameSettings.SCALE);
                    }
                }

            } else if ("collision".equals(propertyName)) {
                objectCollision = Boolean.parseBoolean(propertyValue);
            }
        }

        return new ObjectMetadata(objectName, objectImage, objectCollision, renderingOrder, solidArea);
    }
}

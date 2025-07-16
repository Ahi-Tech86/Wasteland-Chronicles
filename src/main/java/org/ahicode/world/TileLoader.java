package org.ahicode.world;

import org.ahicode.core.GameSettings;
import org.ahicode.utility.SpriteManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.image.BufferedImage;
import java.io.*;

public class TileLoader {
    private static final byte TILED_ID_OFFSET = 1;
    private static final String TILESET_TSX_PATH = "/levels/grass-tileset.tsx";
    private static final String LEVEL_PATH = "/levels/Level1.tmx";

    public static Tile[] loadTilesetFromTsx() {
        try {
            Element tileset = loadAndParseXml(TILESET_TSX_PATH).getDocumentElement();
            TilesetMetadata metadata = extractTilesetMetadata(tileset);

            BufferedImage tilesetImage = loadTilesetImage(tileset);
            return extractTilesFromImage(tilesetImage, metadata);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load tileset", e);
        }
    }

    public static int[][] loadMapFromTmx(int maxScreenCol, int maxScreenRow) {
        try {
            Document document = loadAndParseXml(LEVEL_PATH);
            NodeList layers = document.getElementsByTagName("layer");

            for (int i = 0; i < layers.getLength(); i++) {
                Element layer = (Element) layers.item(i);

                if ("Tilemap".equals(layer.getAttribute("name"))) {
                    return processTilemapLayer(layer, maxScreenCol, maxScreenRow);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load TMX map", e);
        }

        return new int[maxScreenCol][maxScreenRow];
    }

    private static Document loadAndParseXml(String path) throws Exception {
        InputStream stream = TileLoader.class.getResourceAsStream(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(stream);
        document.getDocumentElement().normalize();
        return document;
    }

    private static TilesetMetadata extractTilesetMetadata(Element tileset) {
        return new TilesetMetadata(
                Integer.parseInt(tileset.getAttribute("tilewidth")),
                Integer.parseInt(tileset.getAttribute("tileheight")),
                Integer.parseInt(tileset.getAttribute("tilecount")),
                Integer.parseInt(tileset.getAttribute("columns"))
        );
    }

    private static BufferedImage loadTilesetImage(Element tileset) throws IOException {
        Element imageElement = (Element) tileset.getElementsByTagName("image").item(0);
        String imagePath = imageElement.getAttribute("source").replace("../tileset/", "/tileset/");
        InputStream is = TileLoader.class.getResourceAsStream(imagePath);
        return ImageIO.read(is);
    }

    private static int[][] processTilemapLayer(Element layer, int maxCol, int maxRow) {
        int[][] map = new int[maxCol][maxRow];
        NodeList dataList = layer.getElementsByTagName("data");

        if (dataList.getLength() > 0) {
            String csvData = dataList.item(0).getTextContent().trim();
            String[] rows = csvData.split("\n");

            for (int row = 0; row < Math.min(rows.length, maxRow); row++) {
                String[] values = rows[row].trim().split(",");

                for (int col = 0; col < Math.min(values.length, maxCol); col++) {
                    try {
                        map[col][row] = Integer.parseInt(values[col].trim()) - TILED_ID_OFFSET;
                    } catch (NumberFormatException e) {
                        map[col][row] = 0;
                    }
                }
            }
        }
        return map;
    }

    private static Tile[] extractTilesFromImage(BufferedImage tilesetImage, TilesetMetadata metadata) {
        Tile[] tiles = new Tile[metadata.tileCount()];
        int columns = metadata.columns();

        for (int i = 0; i < metadata.tileCount(); i++) {
            int row = i / columns;
            int col = i % columns;

            BufferedImage tileImage = tilesetImage.getSubimage(
                    col * metadata.tileWidth(),
                    row * metadata.tileHeight(),
                    metadata.tileWidth(),
                    metadata.tileHeight()
            );
            BufferedImage scaledTileImage = SpriteManager.scaleImage(tileImage, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE);

            tiles[i] = new Tile(scaledTileImage);
        }

        return tiles;
    }

    private record TilesetMetadata(
            int tileWidth,
            int tileHeight,
            int tileCount,
            int columns
    ) {}
}

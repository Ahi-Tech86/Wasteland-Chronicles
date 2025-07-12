package org.ahicode.tile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TileLoader {
    private static final byte TILED_ID_OFFSET = 1;
    private static final String TILESET_TSX_PATH = "/levels/grass-tileset.tsx";
    private static final String LEVEL_PATH = "/levels/Level1.tmx";
    private static final String TILESET_PATH = "/tileset/cropped/";
    private static final String TILEMAP_PATH = "/map/start_loc";
    private static final String IMAGE_EXTENSION = ".png";

    public static Tile[] loadTilesetFromTsx() {
        try {
            InputStream tsxStream = TileLoader.class.getResourceAsStream(TILESET_TSX_PATH);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(tsxStream);
            doc.getDocumentElement().normalize();

            Element tileset = doc.getDocumentElement();
            int tileWidth = Integer.parseInt(tileset.getAttribute("tilewidth"));
            int tileHeight = Integer.parseInt(tileset.getAttribute("tileheight"));
            int tileCount = Integer.parseInt(tileset.getAttribute("tilecount"));
            int columns = Integer.parseInt(tileset.getAttribute("columns"));

            Element imageElement = (Element) tileset.getElementsByTagName("image").item(0);
            String imagePath = imageElement.getAttribute("source");
            imagePath = imagePath.replace("../tileset/", "/tileset/");
            System.out.println(imagePath);

            InputStream is = TileLoader.class.getResourceAsStream(imagePath);
            BufferedImage tilesetImage = ImageIO.read(is);

            Tile[] tiles = new Tile[tileCount];

            int rows = (int) Math.ceil((double) tileCount / columns);
            for (int i = 0; i < tileCount; i++) {
                int row = i / columns;
                int col = i % columns;

                BufferedImage tileImage = tilesetImage.getSubimage(
                        col * tileWidth,
                        row * tileHeight,
                        tileWidth,
                        tileHeight
                );

                tiles[i] = new Tile(tileImage);
            }

            return tiles;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load tileset", e);
        }
    }

    public static Tile[] loadTileset() {
        List<Tile> tiles = new ArrayList<>();

        List<String> resourceFiles = getResourceFiles(TILESET_PATH);

        List<String> imageFiles = resourceFiles.stream()
                .filter(file -> file.toLowerCase().endsWith(IMAGE_EXTENSION))
                .sorted()
                .toList();

        for (String fileName : imageFiles) {
            try (InputStream is = TileLoader.class.getResourceAsStream(TILESET_PATH + fileName)) {
                if (is == null) continue;

                BufferedImage image = ImageIO.read(is);
                if (image != null) {
                    Tile tile = new Tile(image);
                    tiles.add(tile);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return tiles.toArray(new Tile[0]);
    }

    public static int[][] loadMapFromTmx(int maxScreenCol, int maxScreenRow) {
        int[][] mapTileNum = new int[maxScreenCol][maxScreenRow];

        try {
            InputStream is = TileLoader.class.getResourceAsStream(LEVEL_PATH);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();

            NodeList layerList = doc.getElementsByTagName("layer");

            for (int i = 0; i < layerList.getLength(); i++) {
                Element layer = (Element) layerList.item(i);
                String layerName = layer.getAttribute("name");

                if (layerName.equals("Tilemap")) {
                    NodeList dataList = layer.getElementsByTagName("data");

                    if (dataList.getLength() > 0) {
                        Element data = (Element) dataList.item(0);
                        String csvData = data.getTextContent().trim();

                        String[] rows = csvData.split("\n");

                        for (int row = 0; row < Math.min(rows.length, maxScreenRow); row++) {
                            String[] values = rows[row].trim().split(",");

                            for (int col = 0; col < Math.min(values.length, maxScreenCol); col++) {
                                try {
                                    mapTileNum[col][row] = Integer.parseInt(values[col].trim()) - TILED_ID_OFFSET;
                                } catch (NumberFormatException e) {
                                    mapTileNum[col][row] = 0;
                                }
                            }
                        }
                    }
                    break;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return mapTileNum;
    }

    public static int[][] loadMapFromTxt(int maxScreenCol, int maxScreenRow) {
        int[][] mapTileNum = new int[maxScreenCol][maxScreenRow];

        try {
            InputStream inputStream = TileLoader.class.getResourceAsStream(TILEMAP_PATH);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while (col < maxScreenCol && row < maxScreenRow) {
                String line = bufferedReader.readLine();

                while (col < maxScreenCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == maxScreenCol) {
                    col = 0;
                    row++;
                }
            }

            bufferedReader.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return mapTileNum;
    }

    private static List<String> getResourceFiles(String path) {
        List<String> filenames = new ArrayList<>();

        try (
                InputStream in = TileLoader.class.getResourceAsStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(in)))
        ) {
            String resource;
            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return filenames;
    }
}

package org.ahicode.tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TileLoader {
    private static final String TILESET_PATH = "/tileset/cropped/";
    private static final String TILEMAP_PATH = "/map/start_loc";
    private static final String IMAGE_EXTENSION = ".png";

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

    public static int[][] loadMap(int maxScreenCol, int maxScreenRow) {
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

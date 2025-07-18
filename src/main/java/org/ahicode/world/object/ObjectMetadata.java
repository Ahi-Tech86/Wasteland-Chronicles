package org.ahicode.world.object;

import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferedImage;

public record ObjectMetadata(String name, BufferedImage image, boolean collision, Rectangle solidArea) {

}

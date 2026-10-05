package flyweight;

public class IconFlyweight implements GlyphFlyweight {

    private final String iconName;
    private final String imagePath;

    public IconFlyweight(String iconName, String imagePath) {
        this.iconName = iconName;
        this.imagePath = imagePath;
    }

    public String draw(int x, int y, String color, int scale) {
        return "Icon '" + iconName + "' | image=" + imagePath + " | x=" + x + " y=" + y + " | color=" + color + " | scale=" + scale;
    }

    public String getContent() {
        return "[" + iconName + "]";
    }
}

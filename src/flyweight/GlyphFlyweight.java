package flyweight;

public interface GlyphFlyweight {

    String draw(int x, int y, String color, int scale);

    String getContent();
}
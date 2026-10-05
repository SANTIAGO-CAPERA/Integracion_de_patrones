package flyweight;


public class PlacedGlyph {

    private final GlyphFlyweight glyph;
    private final int x;
    private final int y;
    private final String color;
    private final int scale;

    public PlacedGlyph(GlyphFlyweight glyph, int x, int y, String color, int scale) {
        this.glyph = glyph;
        this.x = x;
        this.y = y;
        this.color = color;
        this.scale = scale;
    }

    public String draw() {
        return glyph.draw(x, y, color, scale);
    }

    public String getContent() {
        return glyph.getContent();
    }

    public GlyphFlyweight getGlyph() {
        return glyph;
    }
}
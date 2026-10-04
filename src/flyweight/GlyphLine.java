package flyweight;

import java.util.ArrayList;

public class GlyphLine {

    private final GlyphFactory factory;
    private final ArrayList<PlacedGlyph> glyphs;

    public GlyphLine(GlyphFactory factory) {
        this.factory = factory;
        this.glyphs = new ArrayList<PlacedGlyph>();
    }

    public void addText(String text, String fontName, int startX, int y, String color, int scale) {
        int x = startX;
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            GlyphFlyweight flyweight = factory.getCharacter(symbol, fontName);
            glyphs.add(new PlacedGlyph(flyweight, x, y, color, scale));
            x = x + 8 * scale;
        }
    }

    public void addIcon(String iconName, String imagePath, int x, int y, String color, int scale) {
        GlyphFlyweight flyweight = factory.getIcon(iconName, imagePath);
        glyphs.add(new PlacedGlyph(flyweight, x, y, color, scale));
    }

    public String getPlainText() {
        String result = "";
        for (int i = 0; i < glyphs.size(); i++) {
            result = result + glyphs.get(i).getContent();
        }
        return result;
    }

    public ArrayList<PlacedGlyph> getGlyphs() {
        return glyphs;
    }

    public int size() {
        return glyphs.size();
    }
}
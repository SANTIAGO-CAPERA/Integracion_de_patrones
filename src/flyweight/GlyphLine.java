package flyweight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GlyphLine {

    private static final int CHAR_WIDTH = 8;

    private final GlyphFactory factory;
    private final List<PlacedGlyph> glyphs = new ArrayList<>();

    public GlyphLine(GlyphFactory factory) {
        this.factory = factory;
    }

    public void addText(String text, String fontName, int startX, int y, String color, int scale) {
        int x = startX;
        for (int i = 0; i < text.length(); i++) {
            GlyphFlyweight flyweight = factory.getCharacter(text.charAt(i), fontName);
            glyphs.add(new PlacedGlyph(flyweight, x, y, color, scale));
            x += CHAR_WIDTH * scale;
        }
    }

    public void addIcon(String iconName, String imagePath, int x, int y, String color, int scale) {
        GlyphFlyweight flyweight = factory.getIcon(iconName, imagePath);
        glyphs.add(new PlacedGlyph(flyweight, x, y, color, scale));
    }

    public String getPlainText() {
        StringBuilder sb = new StringBuilder();
        for (PlacedGlyph g : glyphs) {
            sb.append(g.getContent());
        }
        return sb.toString();
    }

    public List<PlacedGlyph> getGlyphs() {
        return Collections.unmodifiableList(glyphs);
    }

    public int size() {
        return glyphs.size();
    }
}
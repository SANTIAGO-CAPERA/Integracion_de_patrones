package flyweight;

import java.util.HashMap;

public class GlyphFactory {

    private final HashMap<String, GlyphFlyweight> pool;
    private int totalRequests;

    public GlyphFactory() {
        this.pool = new HashMap<String, GlyphFlyweight>();
        this.totalRequests = 0;
    }

    public GlyphFlyweight getCharacter(char symbol, String fontName) {
        totalRequests++;
        String key = "C:" + fontName + ":" + symbol;
        GlyphFlyweight glyph = pool.get(key);
        if (glyph == null) {
            glyph = new CharacterFlyweight(symbol, fontName);
            pool.put(key, glyph);
        }
        return glyph;
    }

    public GlyphFlyweight getIcon(String iconName, String imagePath) {
        totalRequests++;
        String key = "I:" + iconName;
        GlyphFlyweight glyph = pool.get(key);
        if (glyph == null) {
            glyph = new IconFlyweight(iconName, imagePath);
            pool.put(key, glyph);
        }
        return glyph;
    }

    public int getPoolSize() {
        return pool.size();
    }

    public int getTotalRequests() {
        return totalRequests;
    }
}

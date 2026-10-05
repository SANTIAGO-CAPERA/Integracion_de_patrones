package flyweight;

import java.util.HashMap;
import java.util.Map;

public class GlyphFactory {

    private final Map<String, GlyphFlyweight> pool = new HashMap<>();
    private int totalRequests = 0;

    public GlyphFlyweight getCharacter(char symbol, String fontName) {
        totalRequests++;
        String key = "C:" + fontName + ":" + symbol;
        return pool.computeIfAbsent(key, k -> new CharacterFlyweight(symbol, fontName));
    }

    public GlyphFlyweight getIcon(String iconName, String imagePath) {
        totalRequests++;
        // La llave incluye nombre y ruta (todo el estado intrinseco)
        String key = "I:" + iconName + ":" + imagePath;
        return pool.computeIfAbsent(key, k -> new IconFlyweight(iconName, imagePath));
    }

    public int getPoolSize() {
        return pool.size();
    }

    public int getTotalRequests() {
        return totalRequests;
    }


    public int getReusedCount() {
        return totalRequests - pool.size();
    }
}
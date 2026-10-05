package flyweight;

public class CharacterFlyweight implements GlyphFlyweight {

    private final char symbol;
    private final String fontName;

    public CharacterFlyweight(char symbol, String fontName) {
        this.symbol = symbol;
        this.fontName = fontName;
    }

    @Override
    public String draw(int x, int y, String color, int scale) {
        return "Character '" + symbol + "' | font=" + fontName
                + " | x=" + x + " y=" + y + " | color=" + color + " | scale=" + scale;
    }

    @Override
    public String getContent() {
        return String.valueOf(symbol);
    }
}
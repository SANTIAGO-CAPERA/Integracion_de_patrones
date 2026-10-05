package flyweight;

public class FlyweightDemo {

    public static void main(String[] args) {
        GlyphFactory factory = new GlyphFactory();

        GlyphLine title = new GlyphLine(factory);
        title.addIcon("logo", "icons/logo.png", 10, 10, "#000000", 2);
        title.addText("Factura de venta", "Arial", 40, 10, "#000000", 2);

        GlyphLine body = new GlyphLine(factory);
        body.addText("Total a pagar", "Arial", 10, 40, "#333333", 1);
        body.addText("Total a pagar", "Arial", 10, 60, "#FF0000", 1);

        for (int i = 0; i < title.size(); i++) {
            System.out.println(title.getGlyphs().get(i).draw());
        }
        for (int i = 0; i < body.size(); i++) {
            System.out.println(body.getGlyphs().get(i).draw());
        }

        System.out.println();
        System.out.println("Title text: " + title.getPlainText());
        System.out.println("Body text: " + body.getPlainText());
        System.out.println("Total glyphs placed: " + (title.size() + body.size()));
        System.out.println("Flyweight requests: " + factory.getTotalRequests());
        System.out.println("Flyweight objects created: " + factory.getPoolSize());
    }
}

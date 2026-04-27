import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class WallBlock extends Rectangle {

    public WallBlock(int x, int y) {
        super(x, y, 4, 4);
    }

    public void render(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect(x, y, width, height);
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
    }
}
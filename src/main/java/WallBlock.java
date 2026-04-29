import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class WallBlock extends Rectangle {

    public WallBlock(int x, int y) {
        super(x, y, 4, 4);
    }

    public void render(Graphics g) {
        g.setColor(new Color(255, 0, 255));
        g.fillRect(x, y, width, height);
        g.setColor(new Color(0, 0, 0));
        g.drawRect(x, y, width, height);
    }
}
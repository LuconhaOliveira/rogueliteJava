import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {

    public static List<WallBlock> wall = new ArrayList<WallBlock>();

    public World() {
        for(int yy = 0; yy < Game.WIDTH/4; yy++) {
            wall.add(new WallBlock(0,yy*4));
        }
        for(int yy = 0; yy < Game.WIDTH/4; yy++) {
            wall.add(new WallBlock(Game.WIDTH-4,yy*4));
        }

    }

    public static boolean isFree(int x, int y) {
        for (WallBlock currentBlock : wall) {
            if (currentBlock.intersects(new Rectangle(x, y, 32, 32))) return false;
        }
        return true;
    }

    public void render(Graphics g) {
        for (WallBlock wallBlock : wall) wallBlock.render(g);
    }
}
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {

    public static List<WallBlock> wall = new ArrayList<WallBlock>();

    public World() {
        for(int yy = 0; yy < 135; yy++) {
            wall.add(new WallBlock(0,yy*4));
        }
        for(int yy = 0; yy < 135; yy++) {
            wall.add(new WallBlock(960-4,yy*4));
        }

    }

    public static boolean isFree(int x, int y) {
        for(int i = 0; i < wall.size(); i++) {
            WallBlock blocoAtual = wall.get(i);
            if(blocoAtual.intersects(new Rectangle(x,y,32,32))) {
                return false;
            }
        }
        return true;
    }

    public void render(Graphics g) {
        for(int i = 0; i < wall.size(); i++) {
            wall.get(i).render(g);
        }
    }
}
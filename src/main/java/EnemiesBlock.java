import java.awt.*;
import java.util.Random;

public class EnemiesBlock extends Rectangle{

    int color;

    public EnemiesBlock(int x, int y){
        super(x,y,16,16);
        color=(new Random().nextInt(3))+1;
    }

    public void render(Graphics g){
        switch (color){
            case 1 -> g.setColor(new Color(255, 0, 0));
            case 2 -> g.setColor(new Color(0, 255, 0));
            case 3 -> g.setColor(new Color(255, 0, 255));
        }
        g.fillRect(x,y,width,height);
        g.setColor(new Color(0,255,255));
        g.drawRect(x,y,width,height);
    }

}

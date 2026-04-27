import java.awt.*;

public class EnemiesBlock extends Rectangle{

    public EnemiesBlock(int x, int y){
        super(x,y,16,16);
    }

    public void render(Graphics g){
        g.setColor(new Color(255, 0, 30));
        g.fillRect(x,y,width,height);
        g.setColor(new Color(255,255,255));
        g.drawRect(x,y,width,height);
    }

}

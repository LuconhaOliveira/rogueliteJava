import java.awt.*;

public class Player extends Rectangle {

    public boolean right,left,isShooting;
    public int speed=3;

    public Player(int x, int y){
        super(x,y,32,32);
    }

    public void tick(){
        if(right)x+=speed;
        if(left)x-=speed;
    }

    public void render(@org.jetbrains.annotations.NotNull Graphics g){
        g.setColor(new Color(0, 60, 255));
        g.fillRect(x,y,width,height);
        g.setColor(new Color(255,255,255));
        g.drawRect(x,y,width,height);
    }

}

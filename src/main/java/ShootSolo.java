import java.awt.*;

public class ShootSolo extends Rectangle {

    public int speed=10;

    public ShootSolo(int x,int y){
        super(x,y,6,16);
    }

    public void tick(){
        y-=speed;
    }

    public void render(Graphics g){
        g.setColor(new Color(255,255,0));
        g.fillRect(x,y,width,height);
        g.setColor(new Color(255,255,255));
        g.drawRect(x,y,width,height);
    }

}


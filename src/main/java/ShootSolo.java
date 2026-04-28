import java.awt.*;

public class ShootSolo extends Rectangle {

    public String direction;
    public int distance=0;
    public static int cont=10;
    int color;

    public ShootSolo(int x,int y, int color){
        super(x,y,6,16);
        this.color=color;
    }

    public void tick(){
        y-=10;
    }

    public void render(Graphics g){
        switch (color){
            case 1 -> g.setColor(new Color(255, 0, 0));
            case 2 -> g.setColor(new Color(0, 255, 0));
            case 3 -> g.setColor(new Color(255, 0, 255));
        }
        g.fillRect(x,y,width,height);
        g.setColor(new Color(255,255,0));
        g.drawRect(x,y,width,height);
    }

}


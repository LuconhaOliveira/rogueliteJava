import java.awt.*;

public class Player extends Rectangle {

    public boolean right,left,up,down,dash;
    public int dashCooldown=0;
    public int speed=3;
    public int limitX,limitY;

    public Player(int x, int y){
        super(x,y,32,32);
    }

    public void tick(){
        if(right)x+=speed;
        if(left)x-=speed;
        if(up)y-=speed;
        if(down)y+=speed;
        if(dash&&right&&dashCooldown<=0)speed=7;
        if(dash&&left&&dashCooldown<=0)speed=7;
        if(dash&&up&&dashCooldown<=0)speed=7;
        if(dash&&down&&dashCooldown<=0)speed=7;
        if(dash)dashCooldown=4;
        if(dashCooldown<=3){
            speed=3;
        }
    }

    public void render(@org.jetbrains.annotations.NotNull Graphics g){
        g.setColor(new Color(0, 60, 255));
        g.fillRect(x,y,width,height);
        g.setColor(new Color(255,255,255));
        g.drawRect(x,y,width,height);
    }

}

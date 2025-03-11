import java.awt.*;

public class PlayerShoot extends Rectangle {

    public String direction;
    public int distance=0;
    public boolean remove=false;

    public PlayerShoot(int x,int y){
        super(x,y,4,4);
    }

    public void tick(){
        if(distance<=300) {
            switch (direction) {
                case "left" -> x--;
                case "right" -> x++;
                case "up" -> y--;
                case "down" -> y++;
            }
            distance++;
        }else remove=true;
    }

    public void render(Graphics g){
        g.setColor(new Color(159, 81, 255));
        g.fillRect(x,y,width,height);
    }

}

import java.awt.*;

public class PlayerShoot extends Rectangle {

    public String direction;
    public int speed=4;
    public int distance=0;
    public boolean remove=false;

    public PlayerShoot(int x,int y, String direction){
        super(x,y,4,4);
        this.direction = direction;
    }

    public void tick(){
        if(distance<=300) {
            switch (direction) {
                case "left" -> x-=speed;
                case "right" -> x+=speed;
                case "up" -> y-=speed;
                case "down" -> y+=speed;
            }
            distance++;
        }else remove=true;
    }

    public void render(Graphics g){
        g.setColor(new Color(159, 81, 255));
        g.fillRect(x,y,width,height);
    }

}

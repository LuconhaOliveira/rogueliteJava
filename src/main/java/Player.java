import java.awt.*;

public class Player extends Rectangle {

    public boolean right,left,isShooting;
    public int speed=4;
    int shotCooldown=6;

    static PlayerShots playerShots;

    public Player(int x, int y){
        super(x,y,32,32);
        playerShots = new PlayerShots(y,Game.WIDTH, Game.HEIGHT -32);
    }

    public void tick(){
        playerShots.tick();
        if(right&&World.isFree(x+speed,y))x+=speed;
        if(left&&World.isFree(x-speed,y))x-=speed;
        if(shotCooldown<=0&&isShooting){
            playerShots.addShot(x+13);
            shotCooldown=6;
        }
        shotCooldown--;
    }

    public void render(@org.jetbrains.annotations.NotNull Graphics g){
        g.setColor(new Color(0, 60, 255));
        g.fillRect(x,y,width,height);
        g.setColor(new Color(255,255,255));
        g.drawRect(x,y,width,height);
        playerShots.render(g);
    }

}

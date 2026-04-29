import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayerShots extends Rectangle {

    public static ArrayList<ShootSolo> shots = new ArrayList<ShootSolo>();

    public PlayerShots(int y){
        super(0,y-16,Game.WIDTH, Game.HEIGHT -32);
    }

    public void tick(){
        for (ShootSolo shot : shots) shot.tick();
        if(!shots.isEmpty()) {
            for (int i = 0; i < shots.size(); i++) {
                ShootSolo currentShot = shots.get(i);
                if (currentShot.y < 32) {
                    shots.remove(i);
                    return;
                }
                if (Enemies.isHit(currentShot)) {
                    shots.remove(i);
                    return;
                }
            }
        }
    }

    public void render(Graphics g){
        for (ShootSolo shot : shots) shot.render(g);
    }

    public void addShot(int x){
        shots.add(new ShootSolo(x,y));
    }

}

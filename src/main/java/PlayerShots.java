import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayerShots extends Rectangle {

    public static ArrayList<ShootSolo> shots = new ArrayList<ShootSolo>();

    public PlayerShots(int y,int width, int height){
        super(0,y-26,width,height);
    }

    public void tick(){
        for (int i = 0; i < shots.size(); i++)shots.get(i).tick();
        if(!shots.isEmpty()) {
            for (int i = 0; i < shots.size(); i++) {
                if (shots.get(i).y < 32) {
                    shots.remove(i);
                    return;
                }
                if (Enemies.isHit(shots.get(i))) {
                    shots.remove(i);
                    return;
                }
            }
        }
    }

    public void render(Graphics g){
        for(int i=0;i<shots.size();i++){
            shots.get(i).render(g);
        }
    }

    public void addShot(int x,int color){
        ShootSolo shot = new ShootSolo(x,y,color);
        shots.add(shot);
    }

}

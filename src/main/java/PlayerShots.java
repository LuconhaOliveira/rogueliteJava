import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayerShots extends Rectangle {

    public static ArrayList<ShootSolo> shots = new ArrayList<ShootSolo>();

    public PlayerShots(int y,int width, int height){
        super(0,y,width,height);
    }

    public void tick(){
        for(int i=0;i<shots.size();i++){
            shots.get(i).tick();
            if(Enemies.isHit(shots.get(i).x,shots.get(i).y)){
                Enemies.killEnemy(shots.get(i).x);
                shots.remove(i);
                return;
            }
            if(shots.get(i).y<32){
                shots.remove(i);
            }
        }
    }

    public void render(Graphics g){
        for(int i=0;i<shots.size();i++){
            shots.get(i).render(g);
        }
    }

    public void addShot(int x){
        ShootSolo shot = new ShootSolo(x,y);
        shots.add(shot);
    }

}

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Enemies extends Rectangle{

    static int enemyX;
    public static ArrayList<EnemiesBlock> blocks = new ArrayList<EnemiesBlock>();

    public static int cont=90;

    public Enemies(int width,int height){
        super(0,0,width,height);
        enemyX = ((new Random().nextInt(14))*64)+24;
        EnemiesBlock block = new EnemiesBlock(enemyX,16);
        blocks.add(block);
    }

    public void tick(){
        if(cont<=0){
            enemyX = ((new Random().nextInt(14))*64)+24;
            EnemiesBlock block = new EnemiesBlock(enemyX,16);
            blocks.add(block);
            cont=90;
        }
        if(cont%10==0){
            for(int i=0;i<blocks.size();i++){
                blocks.get(i).y+=8;
            }
        }
        cont--;
    }

    public void render(Graphics g){
        for(int i=0;i<blocks.size();i++){
            blocks.get(i).render(g);
        }
    }

    public static void killEnemy(int x){
        for(int i=0;i<blocks.size();i++) {
            if (blocks.get(i).x + 5 <= x + 8 && blocks.get(i).x + 5 >= x - 8) {
                blocks.remove(i);
                return;
            }
        }
    }
    public static boolean isHit(int x, int y) {
        for(int i = 0; i < blocks.size(); i++) {
            EnemiesBlock blocoAtual = blocks.get(i);
            if(blocoAtual.intersects(new Rectangle(x,y,6,16))) {
                return false;
            }
        }
        return true;
    }


}

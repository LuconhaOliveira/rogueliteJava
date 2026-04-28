import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Enemies extends Rectangle{

    static int enemyX;
    public static ArrayList<EnemiesBlock> blocks = new ArrayList<EnemiesBlock>();

    public static int cont=48;

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
            cont=48;
        }
        if(cont%8==0){
            for(int i=0;i<blocks.size();i++){
                blocks.get(i).y+=8;
                if(blocks.get(i).y>=Game.HEIGHT-80){
                    Game.damage++;
                    blocks.remove(i);
                }
            }
        }
        cont--;
    }

    public void render(Graphics g){
        for(int i=0;i<blocks.size();i++){
            blocks.get(i).render(g);
        }
    }

    public static void killEnemy(ShootSolo shot){
        for(int i=0;i<blocks.size();i++) {
            EnemiesBlock blocoAtual = blocks.get(i);
            if(blocoAtual.intersects(shot)) {
                blocks.remove(i);
                Game.kills++;
                return;
            }
        }
    }
    public static boolean isHit(ShootSolo shot) {
        for(int i = 0; i < blocks.size(); i++) {
            EnemiesBlock blocoAtual = blocks.get(i);
            if(blocoAtual.intersects(shot)&&shot.color==blocoAtual.color) {
                killEnemy(shot);
                return true;
            }
        }
        return false;
    }


}

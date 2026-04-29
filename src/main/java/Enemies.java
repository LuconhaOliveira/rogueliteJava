import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Enemies extends Rectangle{

    Random random = new Random();
    static int randomX;
    public static ArrayList<EnemiesBlock> blocks = new ArrayList<EnemiesBlock>();

    public static int cont=48;

    public Enemies(int width,int height){
        super(0,0,width,height);
        randomX = random.nextInt(14)*64+24;
        blocks.add(new EnemiesBlock(randomX,16));
    }

    public void tick(){
        if(cont<=0){
            randomX = random.nextInt(14)*64+24;
            blocks.add(new EnemiesBlock(randomX,16));
            cont=60;
        }
        if(cont%10==0){
            for(int i=0;i<blocks.size();i++){
                EnemiesBlock currentEnemy = blocks.get(i);
                currentEnemy.y+=8;
                if(currentEnemy.y>=Game.HEIGHT-80){
                    Game.damage+=currentEnemy.life;
                    blocks.remove(i);
                }
            }
        }
        cont--;
    }

    public void render(Graphics g){
        for (EnemiesBlock block : blocks) block.render(g);
    }

    public static void killEnemy(int index){
        blocks.remove(index);
        Game.kills++;
    }
    public static boolean isHit(ShootSolo shot) {
        for (EnemiesBlock currentBlock : blocks) {
            if (currentBlock.intersects(shot)) {
                damageEnemy(shot);
                return true;
            }
        }
        return false;
    }

    public static void damageEnemy(ShootSolo shot){
        for(int i=0;i<blocks.size();i++) {
            EnemiesBlock currentBlock = blocks.get(i);
            if(currentBlock.intersects(shot)) {
                currentBlock.life--;
                if(currentBlock.life<=0){
                    killEnemy(i);
                }
                return;
            }
        }
    }
}

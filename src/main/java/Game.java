import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

    public JFrame frame;
    public Thread thread;
    public boolean isRunning = true;
    private final int WIDTH =600;
    private final int HEIGHT = 350;
    private final int SCALE = 2;
    private final BufferedImage image;

    static Player player;

    PlayerShoot[] shoots={new PlayerShoot(0,0,"up")};
    PlayerShoot[] newShoots;

    public Game() {
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        initFrame();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    private void initFrame() {
        frame = new JFrame("Lorem Ipsum");
        frame.add(this);
        frame.setResizable(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop() {
        isRunning=false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tick(){
        player.tick();
        for(int i=0;i<shoots.length;i++) {
            if (!shoots[i].remove) {
                shoots[i].tick();
            } else {
                newShoots = new PlayerShoot[shoots.length - 1];
                int p = 0;
                for (PlayerShoot shoot : shoots) {
                    if (!shoot.remove) {
                        newShoots[p] = shoot;
                        p++;
                    }
                }
                shoots = newShoots;
                if(shoots.length==0){
                    PlayerShoot[] shoots = {new PlayerShoot(0,0,"up")};
                }
            }
        }
    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs==null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = image.getGraphics();
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        player.render(g);
        for(PlayerShoot shoot:shoots) shoot.render(g);

        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0,0,WIDTH*SCALE,HEIGHT*SCALE,null);

        bs.show();
    }

    public static void main(String[] args){
        Game game = new Game();

        player = new Player(game.WIDTH / 2 - 16, game.HEIGHT / 2 - 16);

        game.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks=60.0;
        double ns = 1000000000/amountOfTicks;
        double delta=0;
        int frames = 0;
        double timer = System.currentTimeMillis();
        while(isRunning) {
            long now = System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime=now;
            if(delta>=1) {
                tick();
                render();
                frames++;
                delta--;
            }

            if(System.currentTimeMillis()-timer>=1000) {
                System.out.println("FPS: "+frames);
                frames = 0;
                timer+=1000;
                if (player.dashCooldown>0){
                    player.dashCooldown-=1;
                }
            }
        }

        stop();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W -> player.up=true;
            case KeyEvent.VK_S -> player.down=true;
            case KeyEvent.VK_A -> player.left=true;
            case KeyEvent.VK_D -> player.right=true;
        }
        if (e.getKeyCode()==KeyEvent.VK_SPACE&&player.dashCooldown<=0)player.dash=true;

        switch (e.getKeyCode()){
            case KeyEvent.VK_UP -> {
                newShoots = new PlayerShoot[shoots.length+1];

                for (int i=0;i<shoots.length;i++){
                    newShoots[i]=shoots[i];
                }
                newShoots[shoots.length+1]=new PlayerShoot(player.x,player.y,"up");
            }
            case KeyEvent.VK_DOWN -> {
                newShoots = new PlayerShoot[shoots.length+1];

                for (int i=0;i<shoots.length;i++){
                    newShoots[i]=shoots[i];
                }
                newShoots[shoots.length+1]=new PlayerShoot(player.x,player.y,"down");
            }
            case KeyEvent.VK_LEFT -> {
                newShoots = new PlayerShoot[shoots.length+1];

                for (int i=0;i<shoots.length;i++){
                    newShoots[i]=shoots[i];
                }
                newShoots[shoots.length+1]=new PlayerShoot(player.x,player.y,"left");
            }
            case KeyEvent.VK_RIGHT -> {
                newShoots = new PlayerShoot[shoots.length+1];

                for (int i=0;i<shoots.length;i++){
                    newShoots[i]=shoots[i];
                }
                newShoots[shoots.length+1]=new PlayerShoot(player.x,player.y,"right");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP, KeyEvent.VK_W  -> player.up=false;
            case KeyEvent.VK_DOWN, KeyEvent.VK_S  -> player.down=false;
            case KeyEvent.VK_LEFT, KeyEvent.VK_A  -> player.left=false;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D  -> player.right=false;
            case KeyEvent.VK_SPACE -> player.dash=false;
        }
    }
}

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

    public JFrame frame;
    public Thread thread;
    public static boolean isRunning = true;
    public static boolean gameOver=false;
    public static boolean pause=false;
    static final int WIDTH =960;
    static final int HEIGHT = 540;
    private final int SCALE = 1;
    private final BufferedImage image;
    public static int kills = 0;
    static int highScore=0;
    public static int life=5;

    static Player player;

    static Enemies enemies;

    public static World world;

    static Menu menu;

    public Game() {
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        initFrame();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    private void initFrame() {
        frame = new JFrame("Lorem Ipsum");
        frame.add(this);
        frame.setResizable(false);
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
        if(!pause){
            player.tick();
            enemies.tick();
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



        g.setColor(new Color(0, 255, 0));
        g.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        FontMetrics fm = g.getFontMetrics();

        String text="Life: "+life;
        g.drawString(text, 8, 22);

        /*int x = (fm.stringWidth(text))+8;
        text="";
        for(int i=1;i<=life;i++){
            text+=".";
        }
        g.drawString(text, x, 19);*/

        int x = (fm.stringWidth(text))+8+20;
        text="Points: "+kills;
        g.drawString(text, x, 22);


        x += (fm.stringWidth(text))+20;
        text="High Score: "+highScore;
        g.drawString(text, x, 22);


        player.render(g);
        enemies.render(g);
        world.render(g);

        if(gameOver){
            g.setColor(new Color(255,255,255));
            g.setFont(new Font(Font.SERIF, Font.BOLD, 24));
            fm = g.getFontMetrics();
            text="GAME OVER!!";
            x = (WIDTH - fm.stringWidth(text)) / 2;
            int y = (HEIGHT - fm.getHeight()) / 2 + fm.getAscent();
            g.drawString(text, x, y);

            text="press 'R' to restart.";
            g.setFont(new Font(Font.SERIF, Font.BOLD, 12));
            fm = g.getFontMetrics();
            x = (WIDTH - fm.stringWidth(text)) / 2;
            y = (HEIGHT - fm.getHeight()) / 2 + fm.getAscent() +18;
            g.drawString(text, x, y);
        }
        if(pause&&!gameOver){
            g.setColor(new Color(50,50,50));
            g.fillRect(0,(HEIGHT/2)-100,WIDTH,200);

            g.setColor(new Color(255,255,255));
            g.setFont(new Font(Font.SERIF, Font.BOLD, 24));
            fm = g.getFontMetrics();
            text="PAUSED";
            x = (WIDTH - fm.stringWidth(text)) / 2;
            int y = (HEIGHT - fm.getHeight()) / 2 + fm.getAscent()-70;
            g.drawString(text, x, y);

            menu.render(g);
        }

        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0,0,WIDTH*SCALE,HEIGHT*SCALE,null);

        bs.show();
    }

    public static void main(String[] args){
        Game game = new Game();

        player = new Player(WIDTH / 2 - 16, HEIGHT - 64);
        enemies = new Enemies(WIDTH, HEIGHT-64);
        world = new World();
        menu = new Menu(0,HEIGHT/2-100,WIDTH,200);

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
            }
        }

        stop();
    }

    public static void verifyEndGame(){
        if(life<1){
            gameOver=true;
            pause=true;
        }
    }

    public static void clearScreen(){
        enemies.clearEnemies();
        player.clearShots();
    }

    public static void resetGame(){
        clearScreen();
        if(highScore<kills)highScore=kills;
        life=5;
        kills=0;
        gameOver=false;
        pause=false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> player.left=true;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> player.right=true;
            case KeyEvent.VK_SPACE -> player.isShooting=true;
            case KeyEvent.VK_ESCAPE -> {
                if(!gameOver){
                    menu.selectedValue=0;
                    pause=!pause;
                }
            }
            case KeyEvent.VK_UP, KeyEvent.VK_W -> {
                if(pause&&!gameOver){
                    if(menu.selectedValue<=0){
                        menu.selectedValue=menu.options.length-1;
                    }
                    else{
                        menu.selectedValue--;
                    }
                }
            }
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> {
                if(pause&&!gameOver){
                    if(menu.selectedValue>=menu.options.length-1){
                        menu.selectedValue=0;
                    }
                    else{
                        menu.selectedValue++;
                    }
                }
            }
            case KeyEvent.VK_ENTER -> {if(pause&&!gameOver)menu.executeOption();}
            case KeyEvent.VK_R -> resetGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT, KeyEvent.VK_A  -> player.left=false;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D  -> player.right=false;
            case KeyEvent.VK_SPACE -> player.isShooting=false;
        }
    }
}


//TODO: REFATORAR CODIGO PELO AMOR DE DEUS QUE ESPAGUETE FEIO DA PORRA ARRUMA ESSA MERDA.
//TODO: Poderes.
//TODO: Beta-testing.
//TODO: Mudar linguas: inglês e portugues. russo?...
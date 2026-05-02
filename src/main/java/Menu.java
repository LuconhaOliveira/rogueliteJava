import java.awt.*;

public class Menu extends Rectangle{

    int selectedValue=0;
    String[] options ={"Continue","Restart","Exit"};

    public Menu(int x,int y,int width,int height){
        super(x,y,width,height);
    }

    public void render(Graphics g){
        g.setColor(new Color(255,255,255));
        g.setFont(new Font(Font.SERIF,Font.PLAIN,24));
        FontMetrics fm = g.getFontMetrics();
        for(int i=0;i<options.length;i++){
            int x = (width - fm.stringWidth(options[i])) / 2;
            int y = (Game.HEIGHT - fm.getHeight()) / 2 + fm.getAscent()-20 +30*i;
            g.drawString(options[i],x,y);
            if(i==selectedValue){
                //g.fillPolygon(new int[]{40, 24, 28, 24}, new int[]{20, 12, 20, 28},4);
                g.fillPolygon(
                        new int[]{x-fm.stringWidth(options[i])/2, x-fm.stringWidth(options[i])/2-16,
                                x-fm.stringWidth(options[i])/2-12, x-fm.stringWidth(options[i])/2-16},
                        new int[]{y-8, y-16, y-8, y},4);
            }
        }
    }

    public void executeOption(){
        switch (options[selectedValue]){
            case "Continue" -> Game.pause=false;
            case "Restart" -> Game.resetGame();
            case "Exit" -> System.exit(0);
        }
    }

}

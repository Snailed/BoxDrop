import java.awt.*;

/**
 * Created by Rasmus Hag Løvstad on 08-09-2016.
 */
public class Player extends SpilObject{
    public static Color currentPlayerColor = Color.GREEN;
    public Player(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);

    }
    public void tick() {
        x += velX;
        y+=velY;
        x=Spil.clamp(x,0,Spil.WIDTH-width);
        y=Spil.clamp(y,0,Spil.HEIGHT-height-25);

    }

    public void render(Graphics g) {
        g.setColor(currentPlayerColor);
        g.fillOval(x,y,width,height);

    }
}

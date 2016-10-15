import java.awt.*;

/**
 * Created by Rasmus Hag Løvstad on 08-09-2016.
 */
public class basalFjende extends SpilObject{
    private int i;

    public basalFjende(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
    }

    @Override
    public void tick() {

        y = y+velY;
        if (i%4 == 0) {
            velY++;
        }
        y++;
        i++;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect(x,y,60,60);
    }
}

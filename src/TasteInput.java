import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Rasmus Hag Løvstad on 08-09-2016.
 */
public class TasteInput extends KeyAdapter{



    private Handler handler = new Handler();

    public TasteInput(Handler handler) {
        System.out.println("TasteInput oprettet.");
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objektliste.size(); i++) {
            SpilObject temp = handler.objektliste.get(i);
            if (temp.getId() == ID.Player) {
                //Hvad skal player gøre når der bliver trykket på specifikke knapper?
                //TODO Fix den her ting!
                //if (temp.getX() < 0) temp.setVelX(5); else if (temp.getX() > 1220) temp.setVelX(-5); else if (temp.getY() < 0) temp.setVelY(5); else if (temp.getY() > 660) temp.setVelY(-5);

                if (key == KeyEvent.VK_UP) {
                    temp.setVelY(-7);
                } else if (key == KeyEvent.VK_LEFT) {
                    temp.setVelX(-7);
                } else if (key == KeyEvent.VK_RIGHT) {
                    temp.setVelX(7);
                } else if (key == KeyEvent.VK_DOWN) {
                    temp.setVelY(7);
                }

            }
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objektliste.size(); i++) {
            SpilObject temp = handler.objektliste.get(i);
            if (temp.getId() == ID.Player) {
                //Hvad skal player gøre når der bliver givet slip på specifikke knapper?
                if (key == KeyEvent.VK_UP) {
                    temp.setVelY(0);
                } else if (key == KeyEvent.VK_LEFT) {
                    temp.setVelX(0);
                } else if (key == KeyEvent.VK_RIGHT) {
                    temp.setVelX(0);
                } else if (key == KeyEvent.VK_DOWN) {
                    temp.setVelY(0);
                }

            }
        }
    }
}

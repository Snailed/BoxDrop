import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Rasmus Hag Løvstad on 09-09-2016.
 */
public class HUD {
    public static int HEALTH = 100;
    public static int currentHealth = HEALTH;
    public static boolean isInvincible = false;
    private int invincibilityMilliSeconds = 0;

    public void tick() {
    }
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(30,30,HEALTH*3,30);
        g.setColor(Color.RED);
        g.fillRect(30,30,currentHealth*3,30);
        g.setColor(Color.white);
        g.drawString("HP:",30,20);
    }

    public void looseHealth(int lostHealth) {
        if (!isInvincible) {
            currentHealth -= lostHealth;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isInvincible = true;
                    if (invincibilityMilliSeconds >= 500) {
                        invincibilityMilliSeconds = 0;
                        Player.currentPlayerColor = Color.GREEN;
                        isInvincible = false;
                        timer.cancel();
                    } else if (invincibilityMilliSeconds%50 == 0) {
                        if (Player.currentPlayerColor == Color.GREEN) {
                            Player.currentPlayerColor = Color.RED;
                        } else if (Player.currentPlayerColor == Color.RED) {
                            Player.currentPlayerColor = Color.GREEN;
                        }
                    }
                    invincibilityMilliSeconds++;
                }

            }, 0,1);
        }
        Spil.clamp(currentHealth,0,HEALTH);
    }
}

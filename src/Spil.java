import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 * Created by Rasmus Hag Løvstad on 07-09-2016.
 */


public class Spil extends Canvas implements Runnable{
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;
    public Thread tråd;
    public boolean kørerSpillet;
    private Handler handler;
    private int r = 0;
    public static long point = 0;
    private static int pointcounter = 0;
    public HUD hud;
    public static int difficulty = 0;
    public Spil() {


        handler = new Handler();
        this.addKeyListener(new TasteInput(handler));
        new Vindue(WIDTH, HEIGHT, "BoxDrop", this);
        hud = new HUD();
        //Her tilføjes objekter...
        handler.tilføjObjekt(new Player(640,600, 50, 50, ID.Player));
        handler.tilføjObjekt(new basalFjende((int)Math.floor(Math.random()*1280), 0, 60, 60, ID.Enemy));





    }

    public synchronized void start() {
        tråd = new Thread(this);
        tråd.start();
        kørerSpillet = true;
    }
    public synchronized void stop() {
        try {
            tråd.join();
            kørerSpillet = false;
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
    public static int clamp(int var, int min, int max) {
        if(var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }
    }

    public static void main(String[] args) {
        new Spil();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(kørerSpillet){
            long now = System.nanoTime();
            delta += (now-lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if(kørerSpillet) {
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: "+frames);
                frames = 0;

            }
        }
        stop();
    }
    private void tick() {
        handler.tick();
        hud.tick();

        if (r % (10-difficulty) == 0) {
            handler.tilføjObjekt(new basalFjende(r, -60, 60, 60, ID.Enemy));
        }
        r = (int)(Math.random()*1280);
        if (handler.ifCollide() == true) {
            //System.out.println("Du tabte!");
            System.out.println("Point: "+point);
            hud.looseHealth(20);

        }
        if (HUD.currentHealth <= 0) {
            render();
            stop();
        }
        if (pointcounter++==5) {
            pointcounter=0;
            point++;
        }
        if (point%200==0) {
            if (difficulty != 9) {
                difficulty++;
                System.out.println("Sværhedsgrad: " + difficulty);
                point++;
            }
        }


    }
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null ) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);
        handler.render(g);
        hud.render(g);
        g.dispose();
        bs.show();

    }
}

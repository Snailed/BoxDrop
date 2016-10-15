import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Rasmus Hag Løvstad on 08-09-2016.
 */
public class Handler {
    LinkedList<SpilObject> objektliste = new LinkedList<>();
    LinkedList<SpilObject> collision = new LinkedList<>();
    SpilObject player;

    public void tick() {
        for (int i = 0; i < objektliste.size(); i++) {
            SpilObject temp = objektliste.get(i);
            temp.tick();
        }

    }

    public void render(Graphics g) {
        for (int i = 0; i < objektliste.size(); i++) {
            SpilObject temp = objektliste.get(i);
            temp.render(g);
        }
    }

    public void tilføjObjekt(SpilObject object) {
        this.objektliste.add(object);
        if (object.getId()==ID.Enemy) {
            this.collision.add(object);
            //System.out.println("Tilføjede fjende til collision-listen!");
        }
    }
    public void removeObjekt(SpilObject object) {
        this.objektliste.remove(object);
        if (object.getId()==ID.Enemy) {
            this.collision.remove(object);
        }
    }
    public void findSpilleren() {
        for (int i = 0; i < objektliste.size(); i++) {
            if (objektliste.get(i).getId() == ID.Player) {
                player = objektliste.get(i);
            }
        }
    }
    public boolean ifCollide() {
        if (player == null) {
            findSpilleren();
            System.out.println("Fundet spilleren! Koordinater: X="+player.getX()+" Y="+player.getY()+ " Width="+player.getWidth()+" Height="+player.getHeight());

        }

        for (int i = 0; i < collision.size(); i++) {
            SpilObject temp = collision.get(i);
            int tempMidteX = temp.getX()+temp.getWidth()/2;
            int tempMidteY = temp.getY()+temp.getHeight()/2;
            int playerMidteX = player.getX()+player.getWidth()/2;
            int playerMidteY = player.getY()+player.getHeight()/2;


            if (Math.pow(Math.pow(tempMidteX-playerMidteX,2)+Math.pow(tempMidteY-playerMidteY,2),0.5)<50) {
                //System.out.println("Collide!");
                return true;
            }
        }
        return false;
    }


}

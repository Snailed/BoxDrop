import javax.swing.*;
import java.awt.*;

/**
 * Created by Rasmus Hag Løvstad on 07-09-2016.
 */
public class Vindue extends Canvas{


    public Vindue(int width, int height, String title, Spil spil){
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(spil);
        frame.setVisible(true);
        frame.setFocusable(true);
        spil.start();
    }
}

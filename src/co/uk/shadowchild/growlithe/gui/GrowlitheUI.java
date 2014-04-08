package co.uk.shadowchild.growlithe.gui;

import javax.swing.*;
import java.awt.*;


public class GrowlitheUI extends JFrame {

    public GrowlitheUI() {

        super("Growlithe IRC Bot");
        this.setSize(new Dimension(1000, 640));
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.add(new GrowlitheJPanel());
    }
}

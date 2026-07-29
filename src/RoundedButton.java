import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;

public class RoundedButton extends JButton {
    private int cornerRadius;

    public RoundedButton(String text, int radius) {
        super(text);
        this.cornerRadius = radius; // making button round
        setContentAreaFilled(false); // disables default background
        setFocusPainted(false);      // disables boarder when pressed
        setBorderPainted(false);    // disables default border
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        
        // Anti-aliasing to make rounding smooth
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //change color when button is pressed
        if (getModel().isArmed())
            g2.setColor(getBackground().darker());
         else 
            g2.setColor(getBackground());
        

        // make the shape smoothly rounded
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();
        super.paintComponent(g); //designs (X/O or Submit) on the button
    }
}
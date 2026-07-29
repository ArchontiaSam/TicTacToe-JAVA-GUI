import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;

public class RoundedButton extends JButton {
    private int cornerRadius;

    public RoundedButton(String text, int radius) {
        super(text);
        this.cornerRadius = radius; // Ακτίνα καμπυλότητας (π.χ. 15-20px)
        setContentAreaFilled(false); // Αφαιρεί το default ορθογώνιο background της Java
        setFocusPainted(false);      // Αφαιρεί το περίγραμμα εστίασης όταν πατιέται
        setBorderPainted(false);    // Αφαιρεί το default border
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        
        // Anti-aliasing για να φαίνονται οι καμπύλες πολύ λείες (Smooth)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Αλλαγή χρώματος αν το κουμπί είναι πατημένο ή όχι
        if (getModel().isArmed()) {
            g2.setColor(getBackground().darker());
        } else {
            g2.setColor(getBackground());
        }

        // Σχεδιασμός στρογγυλεμένου παραλληλογράμμου
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();
        super.paintComponent(g); // Σχεδιάζει το κείμενο (X/O ή Submit) πάνω από το background
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;

/**
 * Render the class quadric cross fractal, either in plane-filling or cross version,
 * the user switching between the two will click of mouse.
 */

public class QuadricCross extends JPanel {

    private boolean inner;

    public QuadricCross() {
        this.setPreferredSize(new Dimension(800, 800));
        this.setBackground(Color.WHITE);
        this.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    inner = !inner; repaint();
                }
            });
    }

    /**
     * Render this component as it currently looks like.
     * @param g The {@code Graphics} object provided by Swing for us to draw on.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(1f));
        g2.setColor(Color.BLACK);
        double w = this.getWidth();
        double h = this.getHeight();
        quadricBranch(g2, w / 2, h / 2, w / 4, 0);
        quadricBranch(g2, w / 2, h / 2, -w / 4, 0);
        quadricBranch(g2, w / 2, h / 2, 0, h / 4);
        quadricBranch(g2, w / 2, h / 2, 0, -h / 4);
    }

    private static final double mul = 0.5;
    private void quadricBranch(Graphics2D g2, double cx, double cy, double dx, double dy) {
        if(Math.abs(dx) + Math.abs(dy) < 1) { return; }
        g2.draw(new Line2D.Double(cx, cy, cx + dx, cy + dy));
        quadricBranch(g2, cx + dx, cy + dy, -mul * dy, mul * dx);
        quadricBranch(g2, cx + dx, cy + dy, mul * dx, mul * dy);
        quadricBranch(g2, cx + dx, cy + dy, mul * dy, -mul * dx);
        if(inner) {
            quadricBranch(g2, cx, cy, dx / 2, dy / 2);
            quadricBranch(g2, cx + dx / 2, cy + dy / 2, dx / 2, dy / 2);
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(new FlowLayout());
        f.add(new QuadricCross());
        f.pack();
        f.setVisible(true);
    }
}
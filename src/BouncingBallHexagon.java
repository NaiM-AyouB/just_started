import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import javax.swing.*;

//I didn't make this program i just liked it and copy it , maybe I will try to make my own in the future.
public class BouncingBallHexagon extends JPanel implements ActionListener {
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;
    private static final int HEXAGON_RADIUS = 200;
    private static final double ROTATION_SPEED = 0.02; // Rotation speed of the hexagon
    private static final int BALL_RADIUS = 20;
    private static final double GRAVITY = 0.5;
    private static final double FRICTION = 0.99;

    private double ballX = PANEL_WIDTH / 2.0;
    private double ballY = 100;
    private double ballVelocityX = 2;
    private double ballVelocityY = 0;

    private double hexagonRotation = 0; // Current rotation angle of the hexagon

    private Timer timer;

    public BouncingBallHexagon() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.BLACK);
        timer = new Timer(16, this); // Roughly 60 FPS
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw spinning hexagon
        g2d.translate(PANEL_WIDTH / 2.0, PANEL_HEIGHT / 2.0);
        g2d.rotate(hexagonRotation);

        Path2D hexagon = new Path2D.Double();
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            double x = HEXAGON_RADIUS * Math.cos(angle);
            double y = HEXAGON_RADIUS * Math.sin(angle);
            if (i == 0) {
                hexagon.moveTo(x, y);
            } else {
                hexagon.lineTo(x, y);
            }
        }
        hexagon.closePath();
        g2d.setColor(Color.WHITE);
        g2d.draw(hexagon);
        g2d.rotate(-hexagonRotation); // Reset rotation
        g2d.translate(-PANEL_WIDTH / 2.0, -PANEL_HEIGHT / 2.0);

        // Draw the ball
        g2d.setColor(Color.RED);
        Ellipse2D ball = new Ellipse2D.Double(ballX - BALL_RADIUS, ballY - BALL_RADIUS, BALL_RADIUS * 2, BALL_RADIUS * 2);
        g2d.fill(ball);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update hexagon rotation
        hexagonRotation += ROTATION_SPEED;

        // Update ball position
        ballX += ballVelocityX;
        ballY += ballVelocityY;
        ballVelocityY += GRAVITY;

        // Apply friction
        ballVelocityX *= FRICTION;
        ballVelocityY *= FRICTION;

        // Check for collisions with hexagon bounds
        double dx = ballX - PANEL_WIDTH / 2.0;
        double dy = ballY - PANEL_HEIGHT / 2.0;
        double distanceFromCenter = Math.sqrt(dx * dx + dy * dy);

        if (distanceFromCenter + BALL_RADIUS > HEXAGON_RADIUS) {
            double angle = Math.atan2(dy, dx);
            ballX = PANEL_WIDTH / 2.0 + (HEXAGON_RADIUS - BALL_RADIUS) * Math.cos(angle);
            ballY = PANEL_HEIGHT / 2.0 + (HEXAGON_RADIUS - BALL_RADIUS) * Math.sin(angle);

            // Reflect velocity based on hexagon rotation
            double normalAngle = angle + Math.PI / 2;
            double normalX = Math.cos(normalAngle);
            double normalY = Math.sin(normalAngle);
            double dotProduct = ballVelocityX * normalX + ballVelocityY * normalY;

            ballVelocityX -= 2 * dotProduct * normalX;
            ballVelocityY -= 2 * dotProduct * normalY;

            // Adjust velocities to match hexagon rotation effects
            double rotationEffectX = -ROTATION_SPEED * HEXAGON_RADIUS * Math.sin(hexagonRotation);
            double rotationEffectY = ROTATION_SPEED * HEXAGON_RADIUS * Math.cos(hexagonRotation);

            ballVelocityX += rotationEffectX * 0.1;
            ballVelocityY += rotationEffectY * 0.1;
        }

        // Ensure ball stays inside the panel bounds
        if (ballX - BALL_RADIUS < 0 || ballX + BALL_RADIUS > PANEL_WIDTH) {
            ballVelocityX = -ballVelocityX;
        }
        if (ballY - BALL_RADIUS < 0 || ballY + BALL_RADIUS > PANEL_HEIGHT) {
            ballVelocityY = -ballVelocityY;
        }

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Ball in Spinning Hexagon with Gravity and Friction");
        BouncingBallHexagon panel = new BouncingBallHexagon();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

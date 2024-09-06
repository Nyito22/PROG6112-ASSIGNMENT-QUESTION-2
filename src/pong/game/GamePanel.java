/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pong.game;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private int ballX = 400, ballY = 300;
    private int ballVelocityX = 2, ballVelocityY = 2;
    private int paddle1Y = 250, paddle2Y = 250;
    private final int paddleWidth = 15, paddleHeight = 100;
    private int score1 = 0, score2 = 0;

    public GamePanel() {
        setBackground(Color.BLACK);
        Timer timer = new Timer(5, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }

    public void startGame() {
        // Start the game loop, handled by the timer.
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw paddles
        g.setColor(Color.WHITE);
        g.fillRect(50, paddle1Y, paddleWidth, paddleHeight);
        g.fillRect(735, paddle2Y, paddleWidth, paddleHeight);

        // Draw ball
        g.fillOval(ballX, ballY, 20, 20);

        // Draw scores
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString(String.valueOf(score1), 350, 50);
        g.drawString(String.valueOf(score2), 420, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ballX += ballVelocityX;
        ballY += ballVelocityY;

        // Ball collision with top and bottom
        if (ballY <= 0 || ballY >= 560) {
            ballVelocityY *= -1;
        }

        // Ball collision with paddles
        if (ballX <= 65 && ballY + 20 >= paddle1Y && ballY <= paddle1Y + paddleHeight) {
            ballVelocityX *= -1;
        }

        if (ballX >= 715 && ballY + 20 >= paddle2Y && ballY <= paddle2Y + paddleHeight) {
            ballVelocityX *= -1;
        }

        // Ball goes out of bounds
        if (ballX <= 0) {
            score2++;
            resetBall();
        }

        if (ballX >= 780) {
            score1++;
            resetBall();
        }

        repaint();
    }

    public void resetBall() {
        ballX = 400;
        ballY = 300;
        ballVelocityX *= -1;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W && paddle1Y > 0) {
            paddle1Y -= 20;
        }

        if (key == KeyEvent.VK_S && paddle1Y < getHeight() - paddleHeight) {
            paddle1Y += 20;
        }

        if (key == KeyEvent.VK_UP && paddle2Y > 0) {
            paddle2Y -= 20;
        }

        if (key == KeyEvent.VK_DOWN && paddle2Y < getHeight() - paddleHeight) {
            paddle2Y += 20;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}

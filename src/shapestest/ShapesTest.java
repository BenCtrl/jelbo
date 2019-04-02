
package shapestest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author cartwrightb
 */
public class ShapesTest extends JPanel{
    public ShapesTest(){
       
    }
    
    Timer timer;
    
    final int moveSpeed = 6;
    int x = 10;
    int y = 10;
    
    int rotR = 0;
    int rotL = 0;
    final int anDelay = 10;
    final int ballDiam = 50;
    
    //Main power house for establishing instances and drawing components.
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D G2D = (Graphics2D) g;
        G2D.setColor(Color.RED);
        //G2D.rotate(Math.toRadians(0), x, y);
        G2D.fillRect(x,y,ballDiam,ballDiam);
        
        
//        G2D.setColor(Color.BLUE);
//        G2D.fillOval(x + (ballDiam/2)-2,y + (ballDiam/2)-2,5,5);
        
        drawPosition(G2D);
    }
    
    public void drawPosition(Graphics2D g2d){                                        //Use this to also draw  FPS
        g2d.setColor(Color.BLUE);
        g2d.drawString("X CENTER pos: " + (x + (ballDiam / 2)), 5, 10);
        g2d.drawString("Y CENTER pos: " + (y + (ballDiam / 2)), 5, 25);
        g2d.drawString("X TRUE pos: " + x, 5, 40);
        g2d.drawString("Y TRUE pos: " + y, 5, 55);
        g2d.drawString("X DIAMpos: " + (x + ballDiam), 5, 70);
        g2d.drawString("Y DIAM pos: " + (y + ballDiam), 5, 85);
    }
     
    public void borders(JPanel panel){                                                      //Consider passing in the JFrame as an object to reference borders.
        if(x < 0)
            x = 0;
        else if(y < 0)
            y = 0;
        else if(x > getWidth() - ballDiam)
            x = getWidth() - ballDiam;
        else if(y > getHeight() - ballDiam)
            y = getHeight() - ballDiam;
    }
    
    Timer tMoveUp = new Timer(anDelay, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            y = y - moveSpeed;
        }
        
    });
    Timer tMoveDown = new Timer(anDelay, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            y = y + moveSpeed;
        }
        
    });
    Timer tMoveLeft = new Timer(anDelay, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            x = x - moveSpeed;
        }
        
    });
    Timer tMoveRight = new Timer(anDelay, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            x = x + moveSpeed;
        }
        
    });
    
    public void KeyBindings(){
        ShapesKeyBindings keys = new ShapesKeyBindings();
        
        keys.addKeyBinding(this, KeyEvent.VK_DOWN, "Move Down", (evt) -> {          //Each four key bindings calls a custom method from before.
            tMoveDown.start();
        }); //anonymous function
        keys.addKeyBinding(this, KeyEvent.VK_UP, "Move Up", (evt) -> {
            tMoveUp.start();
        }); 
        keys.addKeyBinding(this, KeyEvent.VK_RIGHT, "Move Right", (evt) -> {
            tMoveRight.start();
        }); 
        keys.addKeyBinding(this, KeyEvent.VK_LEFT, "Move Left", (evt) -> {
            tMoveLeft.start();
        }); 
        
        
        keys.addReleaseBinding(this, KeyEvent.VK_DOWN, "Move Down Release", (evt) -> {          //Each four key bindings calls a custom method from before.
            tMoveDown.stop();
        }); 
        keys.addReleaseBinding(this, KeyEvent.VK_UP, "Move Up Release", (evt) -> {
            tMoveUp.stop();
        }); 
        keys.addReleaseBinding(this, KeyEvent.VK_RIGHT, "Move Right Release", (evt) -> {
            tMoveRight.stop();
        }); 
        keys.addReleaseBinding(this, KeyEvent.VK_LEFT, "Move Left Release", (evt) -> {
            tMoveLeft.stop();
        }); 
        
    }
    
    @Override
    public Dimension getPreferredSize(){                                        //So this is getting automatically called for whatever reason.
        return new Dimension(500,400);
    }
    
    public ImageIcon setIcon(){                                                 
        String iconPath = "img/helmetfav.png";
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(iconPath));
        return icon;
    }
    
    public static void main(String[] args) throws InterruptedException {
        //ImageIcon icon = new ImageIcon("resources/img/helmetfav.png");        
        JFrame frame = new JFrame("Balls"); /*Super 2000*/
        JPanel panel = new JPanel();
        ShapesTest instance = new ShapesTest();
        
        frame.add(instance);
        frame.pack();                                                           //Important as it calls and uses the "getPreferredSize" method from earlier.
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //frame.setIconImage(icon.getImage());
        frame.setIconImage(instance.setIcon().getImage());
        
        System.out.println("Frame Width: " + frame.getWidth());
        System.out.println("Frame Height: " + frame.getHeight());
        System.out.println("Ball Size: " + instance.ballDiam);
//        instance.timer = new Timer(10, new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //System.out.println("Tick");
//                instance.repaint();
//            }
//            
//        });
//        
//        instance.timer.start();
        instance.KeyBindings();
        
        while(true){
            instance.repaint();
            instance.borders(panel);     //pass in "frame"
        }
        
    }

}

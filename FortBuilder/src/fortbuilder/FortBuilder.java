
package fortbuilder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class FortBuilder extends JFrame implements Runnable , MouseListener
{
   boolean EndProgram = false;
   int mx,my; // Mouse x and y
    
   //Drawing related variables
   BufferedImage bi = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);
   Graphics2D g2;
   Graphics2D big;
   boolean firstTime = true;
    
   //Used for the fps calculation
   int fps = 60;
   public static int fpsToPrint;
   long lastFpsTime;
    
   public static void main(String[] args) 
   {
      FortBuilder frame = new FortBuilder();
   }
    
   FortBuilder()
   {
      setLayout(null);
      setSize(640,480);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setResizable(false);
      setTitle("FortBuilder - by Chris Adams");
      
      //Mouse movement events
      addMouseMotionListener(new MouseMotionAdapter()
      {
         public void mouseMoved(MouseEvent e)
         {
            mx = e.getX();
            my = e.getY();
         }
       });
       addMouseListener(this);
      
      Thread t = new Thread(this);
      t.start();
   }
    
   @Override
   public void paint(Graphics g)
   {
      update(g);
   }
   
   @Override
   public void update(Graphics g)
   {
      g2 = (Graphics2D) g;
       
      if(firstTime)
      {
         bi = (BufferedImage) createImage(640,480);
         // Big is the graphics context
         big = bi.createGraphics();
         firstTime = false; // Only runs once
      }
      big.setColor(Color.BLACK);
      big.fillRect(0, 0, 640, 480);

      // This is the Image that will be shown on the screen
      g2.drawImage(bi, 0, 0, this);
   }
   
   public void run()
   {
       long lastLoopTime = System.nanoTime();
       final int TargetFps = 45;
       final long OptimalTime = 1000000000 / TargetFps;
       
       while(!EndProgram)
       {
           /**
            * This will work out how long since the last update which will
            * be used to calculate the animation speed. This would vary 
            * from system to system.
            */
           
           long now = System.nanoTime();
           //How long it takes to do an entire loop of the program
           long updateLength = now - lastLoopTime;
           lastLoopTime = now;
           //double delta = updateLength / ((double)OptimalTime);
           
           //Updates the frame counter
           lastFpsTime += updateLength;
           fps++;
           
           //Update our FPS counter if a second has passed since
           // the last recording
           if(lastFpsTime >= 1000000000)
           {
               System.out.println("FPS = " + fps + ")");
               fpsToPrint = fps;
               lastFpsTime = 0;
               fps = 0;
           }
           
           try
           {
               gameUpdate(); // game logic
               repaint(); // drawings
               try
               {
                   Thread.sleep( (lastLoopTime-System.nanoTime() + OptimalTime)/1000000);
               }catch(Exception are){}
           }
           catch(Exception er)
           {
               System.out.println(er.getMessage());
           }
      }
   }
   
   public BufferedImage loadImage(String fnm)
   {
      try
      {   
         // Finds the location of the image
         BufferedImage im = ImageIO.read(getClass().getResource("Res/" + fnm));
         return im;
      }
      catch(Exception er)
      {
         er.printStackTrace();
         return null;
      }
   }
   
   public void gameUpdate()
   {
       
   }
   
   @Override
   public void mouseClicked(MouseEvent e) 
   {
       
   }

   @Override
   public void mousePressed(MouseEvent e) 
   {

   }

   @Override
   public void mouseReleased(MouseEvent e) 
   {

   }

   @Override
   public void mouseEntered(MouseEvent e) 
   {
       
   }

   @Override
   public void mouseExited(MouseEvent e) 
   {
       
   }
   
}


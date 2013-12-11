
package fortbuilder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
   int curType = 1; 
   boolean clickRelease = true;
   //Drawing related variables
   BufferedImage bi = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);
   Graphics2D g2;
   Graphics2D big;
   boolean firstTime = true;
   BufferedImage Menu;
   
   Block[][] blocks = new Block[60][40];
   Rectangle[] recButtons = new Rectangle[5];
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
      //setUndecorated(true);
      setSize(620,500);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      //setResizable(false);
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
      
      Menu = loadImage("Menu.png");
      recButtons[0] = new Rectangle();
      recButtons[0].setBounds(23, 440, 36, 28);
      
      recButtons[1] = new Rectangle();
      recButtons[1].setBounds(70, 440, 36, 28);
      
      recButtons[2] = new Rectangle();
      recButtons[2].setBounds(120, 440, 36, 28);
      
      recButtons[3] = new Rectangle();
      recButtons[3].setBounds(175, 440, 36, 28);
      
      recButtons[4] = new Rectangle();
      recButtons[4].setBounds(225, 440, 36, 28);
       
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
         bi = (BufferedImage) createImage(640,500);
         // Big is the graphics context
         big = bi.createGraphics();
         loadMap();
         firstTime = false; // Only runs once
      }
      big.drawImage(Menu, 10, 430,603,57,this);
      loadMap();
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
               //System.out.println(er.getMessage());
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
   
   //Loads all the Blocks with air
   public void loadMap()
   {
       int y = 30,x = 10;
       for(int i=0;i<40;i++)
       {
           for(int j=0;j<60;j++)
           {
              if(firstTime)
              {
                  blocks[j][i] = new Block();
                  blocks[j][i].x = x;
                  blocks[j][i].y = y;
              }

               big.setColor(blocks[j][i].colour);
               big.fillRect(blocks[j][i].x, blocks[j][i].y, Block.w, Block.h);
               x += 10;
           }
           y += 10;
           x = 10;
       }
       
   }
   
   public void gameUpdate()
   {
       int j = 0;
       
       for(int i=59;i>0;i--)
       {
           for(j=39;j>0;j--)
           {
               if(blocks[i][j].Type == 0)
               {
                   blocks[i][j].colour = Color.BLACK;
               }
               
               if(blocks[i][j].Type == 1)
               {
                   blocks[i][j].colour = Color.yellow;
                   
                   if(j != 39)
                   {
                      if(blocks[i][j+1].Type == 0)
                      {
                         blocks[i][j].Type = 0;
                         blocks[i][j+1].Type = 1;
                      }
                   }
               }
               
               if(blocks[i][j].Type == 2)
               {
                   blocks[i][j].colour = new Color(139,105,20);
               }
               if(blocks[i][j].Type == 3)
               {
                   if(j != 39)
                   {
                      blocks[i][j].Type = 0;
                      if(blocks[i][j+1].Type == 0)
                      {
                         blocks[i][j+1].colour = Color.BLUE; 
                         blocks[i][j+1].Type = 3;
                      }
                      else if(blocks[i-1][j].Type == 0 )
                      {
                         blocks[i-1][j].colour = Color.BLUE;
                         blocks[i-1][j].Type = 3;
                      }
                      else if(blocks[i+1][j].Type == 0)
                      {
                         blocks[i+1][j].colour = Color.BLUE;
                         blocks[i+1][j].Type = 3;
                      }
                      else
                      {
                          blocks[i][j].colour = Color.BLUE;
                          blocks[i][j].Type = 3;
                      }
                      
                   }
               }
               
           }
           j=0;
       }
   }
   
   @Override
   public void mouseClicked(MouseEvent e)
   {

   }

   @Override
   public void mousePressed(MouseEvent e)
   {
       try
       {
          if(recButtons[0].contains(mx, my))
          {
             curType = 1;
          }
          else if(recButtons[1].contains(mx, my))
          {
             curType = 2;
          }
          else if(recButtons[2].contains(mx, my))
          {
             curType = 3;
          }
          else if(recButtons[3].contains(mx, my))
          {
             curType = 4;
          }
          else if(recButtons[4].contains(mx, my))
          {
             curType = 0;
          }
          
          int j = 0;
          int blockx = -1;
          int blocky = -1;
          for(int i=0;i<60;i++)
          {
             for(j=0;j<40;j++)
             {
                if(blocks[i][j].x >= mx-10 && blocks[i][j].y >=my-10)
                {
                   blockx = i;
                   blocky = j;
                   i = 60;
                   j = 40;
                }
             }
             j=0;
           
          }
          blocks[blockx][blocky].Type = curType;
       }catch(Exception er){}
   }

   @Override
   public void mouseReleased(MouseEvent e)
   {
      clickRelease = true;
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
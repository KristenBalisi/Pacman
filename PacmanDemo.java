import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class PacmanDemo
{
  public static void main(String[] args)
  {
    JFrame j = new JFrame("BeingZero - Pacman by Kristen Balisi");
    j.setBounds(500,200,800,600);
    PacmanPanel pnl = new PacmanPanel();
    pnl.setBackground(Color.yellow);
    pnl.setFocusable(true);
    j.add(pnl);
    j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    j.setIconImage(new ImageIcon("PacmanIcon.png").getImage());
    j.setVisible(true);
    
    while(true)
    {
      pnl.animate();
      
      try{ 
        Thread.sleep(100);
      }
      catch(Exception e)
      {
        System.out.println("Exception in thread sleep: "+e.toString());
      }
    }
  }
}

class PacmanPanel extends JPanel implements KeyListener
{
  BufferedImage img;
  BufferedImage[][] subImages;
  final static String imgPath = "PacmanSprites.png";
  final static int ROWS = 4;
  final static int COLS = 3;
  final static int WIDTH = 50;
  final static int HEIGHT = 50;
  int direction;
  int x,y,xp,yp,d;
  
  PacmanPanel()
  {
    subImages = new BufferedImage[ROWS][COLS];
    direction = KeyEvent.VK_RIGHT;
    this.addKeyListener(this);
    x = 1;
    y = 0;
    xp = 1;
    d = 5;
    yp = 50;
    try
    {
      img = ImageIO.read(new File(imgPath));
      for(int i=0;i<ROWS;i++)
        for(int j=0;j<COLS;j++)
        subImages[i][j] = img.getSubimage(j*50,i*50,WIDTH,HEIGHT);
    }
    catch(Exception e)
    {
      System.out.println("Exception in Loading Image: " +e.toString());
    }
  }
  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    g.drawImage(subImages[x][y],xp,yp,null);
  }
  
  public void animate()
  {
    y =(y+1)%3;
    repaint();
    switch(direction)
    {
      case KeyEvent.VK_DOWN:
        x = 2;
        yp = yp + d;
        if(yp >= this.getHeight())
          yp = 0;
      break;
      case KeyEvent.VK_UP:
        x = 3;
        yp = yp - d;
        if(yp < 0)
          yp = this.getHeight();
      break;
      case KeyEvent.VK_LEFT:
        x = 0;
        xp = xp - d;
        if(xp < 0)
          xp = this.getWidth();
      break;
      case KeyEvent.VK_RIGHT:
        x = 1;
        xp = xp + d;
        if(xp >= this.getWidth())
          xp = 0;
      break;
    }
  }
  
  public void keyPressed(KeyEvent e){
    direction = e.getKeyCode();
  }
  public void keyReleased(KeyEvent e){}
  public void keyTyped(KeyEvent e){}
}


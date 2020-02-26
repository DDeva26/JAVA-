Memory game code:

package memorygame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
public class MemoryGame extends JFrame {
    static String files[] = {"images 1.jpg", "images 2.jpg",
        "images 3.jpg", "images 4.jpg",
        "images 5.jpg", "images 6.jpg" , "images 7.jpg" , "images 8.jpg" , "images 9.jpg"
            , "images 10.jpg" , "images 11.jpg" , "images 12.png" };
    static JButton buttons[];
    ImageIcon closedIcon;
    int points=0;
    int numButtons;
    ImageIcon icons[];
    Timer myTimer;
    int success;
    int numClicks = 0;
    int oddClickIndex = 0;
    int currentIndex = 0;
    long tStart;
    String name;
    public MemoryGame(String name) {
          setTitle(" Memory Game");
 this.name=name;
        // Specify an action for the close button.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// Create a BorderLayout manager.
        setLayout(new GridLayout(4, files.length));
        tStart=System.currentTimeMillis();
        closedIcon = new ImageIcon(getClass().getResource("closed.jpg"));
                numButtons = files.length * 2;
        buttons = new JButton[numButtons];      
    icons = new ImageIcon[numButtons];
        for (int i = 0, j = 0; i < files.length; i++) {
            icons[j] = new ImageIcon(getClass().getResource(files[i]));
            buttons[j] = new JButton("");
            buttons[j].addActionListener(new ImageButtonListener());
            buttons[j].setIcon(closedIcon);
            add(buttons[j++]);
            icons[j] = icons[j - 1];
            buttons[j] = new JButton("");
            buttons[j].addActionListener(new ImageButtonListener());
            buttons[j].setIcon(closedIcon);
            add(buttons[j++]);
        }
       // randomize icons arraiy
        Random gen = new Random();
        for (int i = 0; i < numButtons; i++) {
            int rand = gen.nextInt(numButtons);
            ImageIcon temp = icons[i];
            icons[i] = icons[rand];
            icons[rand] = temp;
        }
        // Pack and display the window.
        pack();
        setVisible(true);

        myTimer = new Timer(1000, new TimerListener());
    }
    private class TimerListener implements ActionListener 
    {
       public void actionPerformed(ActionEvent e) {
            buttons[currentIndex].setIcon(closedIcon);
            buttons[oddClickIndex].setIcon(closedIcon);
            myTimer.stop();
        }
    }
    private class ImageButtonListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
            String status="";
            // we are waiting for timer to pop - no user clicks accepted
            if (myTimer.isRunning())
                return;
            numClicks++;
            System.out.println(numClicks)
            // which button was clicked?
            for (int i = 0; i < numButtons; i++) {
                if (e.getSource() == buttons[i]) {
                    buttons[i].setIcon(icons[i]);
                    currentIndex = i;
                    success++; 
                    points = (240-((numClicks/2-24)*5));
                    System.out.println("points: "+points+"success: "+success);
                    if(points>280 && points <=300)
                    {
                      status="Your score is excellent "
                              + "your memory power very high"
                              + " Speed of Recollection Fast";
                    }
                else if(points<=280 && points>230)
                {
                    status="Your score is GOOD\n "
                            + "your memory power high\n"
                           +  "Speed of Recollection Moderate";
                }
                else if(points<=230 && points>190)
                {
                    status="Your score is AVERAGE\n" +"your memory power low\n"
                            +"Speed of Recollection slow";
                }
                else
                {
                   status="Your score is WORSE your memory very low"
                           +" Speed of Recollection Veryslow";
                }
                }
            }
            // check for even click
            if (numClicks % 2 == 0) {
                // check whether same position is clicked twice!
                if (currentIndex == oddClickIndex) 
                       numClicks--;
                    success=success-1;
                    return;
                }
                // are two images matching?
                if (icons[currentIndex] != icons[oddClickIndex]) {
                    // show images for 1 sec, before flipping back
                	success=success-2;
                    myTimer.start(); 
                }
            } else {
                // we just record index for odd clicks
                oddClickIndex = currentIndex;
            }
            if(success==24){
            	   try {
                    Properties prop = new Properties();
                    InputStream input = null;
                    input = new FileInputStream("config.properties");
		// load a properties file
                    prop.load(input);
                    long tEnd=System.currentTimeMillis();
                    Class.forName(prop.getProperty("classname"));
                    Connection con=DriverManager.getConnection(prop.getProperty("databasepath"),prop.getProperty("dbuser"),prop.getProperty("dbpassword"));
                    String sql="insert into "+prop.getProperty("tablename")+" values('"+name+"','"+points+"','"+numClicks/2+"','"+(double)((tEnd - tStart)/1000.0)+"sec"+"');";
                    PreparedStatement pstmt=con.prepareStatement(sql);
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Congrates you won the game\nYou got "+points+" points\n You took "+numClicks/2+" attempts to complete game"+"\nTotal time took: "+(double)((tEnd - tStart)/1000.0)+"sec\n"+status);
                    con.close();
                    new Listoutallat(name);
                    dispose();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MemoryGame.class.getName()).log(Level.SEVERE, null,ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MemoryGame.class.getName()).log(Level.SEVERE, null,ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MemoryGame.class.getName()).log(Level.SEVERE, null,ex);
                } catch (IOException ex) {
                    Logger.getLogger(MemoryGame.class.getName()).log(Level.SEVERE, null,ex);
                }finally{ 
                }
            }
        }
    }
    public static void main(String[] args) 
    {
      //  new MemoryGame();
    }}
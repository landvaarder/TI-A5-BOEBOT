import java.awt.*;   
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.EventQueue;
import java.lang.*;
import java.awt.EventQueue;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import java.util.*;

/**
 * @author: Tim Schijvenaars 
 * edited by: je moeder 
 * @version 1.7
 */

public class GUI {

    private JFrame frame;
    private ComWriter communicate;
    private String routeCode;
    private int lastEntry;
    private JLabel lblTextboef;
    private JTextArea txtArea;
    private String textRoute;
    private ArrayList<Integer> entryList;
    private ArrayList<Integer> entryTextList;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        GUI window = new GUI();
                        window.frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    /**
     * Create the application.
     */
    public GUI() {
        routeCode = "";
        textRoute = "";
        lastEntry = 0;
        entryList = new ArrayList<>();
        entryTextList = new ArrayList<>();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setEnabled(false);
        frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 13));
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setBounds(100, 100, 500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        //______________________________________________________________________________________________________________________
        JMenu menu;
        JMenuItem item;

        // create the File menu
        menu = new JMenu("System");
        menubar.add(menu);

        item = new JMenuItem("Connect");
        item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    connectBluetooth();
                }
            });
        menu.add(item);

        item = new JMenuItem("Disconnect");
        item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    disconnectBluetooth();
                }
            });
        menu.add(item);
        menu.addSeparator();

        item = new JMenuItem("Quit");
        item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    quit(); 
                }
            });
        menu.add(item);

        //Modus menu:
        menu = new JMenu("Modes");
        menubar.add(menu);

        item = new JMenuItem("Remotecontrol Mode");
        item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    showState("Remotecontrol");
                    communicate.writeString("2");
                }
            });
        menu.add(item);

        item = new JMenuItem("Linefollow Mode");
        item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    showState("Line followers");
                    communicate.writeString("1");
                }
            });
        menu.add(item);

        item = new JMenuItem("Collisiondetection Mode");
        item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    showState("Collision");
                    communicate.writeString("0");
                }
            });
        menu.add(item);        

        // create the Help menu
        menu = new JMenu("Help");
        menubar.add(menu);

        item = new JMenuItem("About BoefBot...");
        item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    showAbout(); 
                }
            });
        menu.add(item);

        //______________________________________________________________________________________________________________________

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));

        JLabel lblManualMovement = new JLabel("Manual Movement");
        lblManualMovement.setFont(new Font("Arial", Font.PLAIN, 13));
        GridBagLayout gbl_layeredPane = new GridBagLayout();
        gbl_layeredPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gbl_layeredPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gbl_layeredPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_layeredPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        layeredPane.setLayout(gbl_layeredPane);

        JLayeredPane layeredPane_1 = new JLayeredPane();
        layeredPane_1.setBorder(new LineBorder(new Color(0, 0, 0)));

        JLabel lblAddRoute = new JLabel("Add to route");
        lblAddRoute.setFont(new Font("Arial", Font.PLAIN, 13));

        JLabel lblQueue = new JLabel("Queue");
        lblQueue.setFont(new Font("Arial", Font.PLAIN, 13));

        JLabel lblCurrentMode = new JLabel("Current mode: "); //State label

        lblTextboef = new JLabel("RemoteMode");

        txtArea = new JTextArea();
        txtArea.setEditable(false);
        txtArea.setBackground(SystemColor.controlHighlight);
        txtArea.setText("");

        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(groupLayout.createSequentialGroup()
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(lblQueue)
                            .addComponent(txtArea, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
                        .addGap(11)
                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                            .addComponent(layeredPane_1, 0, 0, Short.MAX_VALUE)
                            .addComponent(lblAddRoute)
                            .addComponent(lblManualMovement)
                            .addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 252, Short.MAX_VALUE)))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(lblCurrentMode)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(lblTextboef, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(155, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.TRAILING)
            .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                .addGap(23)
                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(lblAddRoute)
                    .addComponent(lblQueue))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(layeredPane_1, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
                        .addGap(18)
                        .addComponent(lblManualMovement)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtArea, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE))
                .addGap(18)
                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(lblCurrentMode)
                    .addComponent(lblTextboef))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layeredPane.setLayout(null);

        JButton btnForward = new JButton("Forward"); //Naar voren rijden
        btnForward.setBounds(75, 11, 106, 34);
        layeredPane.add(btnForward);
        btnForward.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    communicate.writeString("F");
                }
            });

        JButton btnLeft = new JButton("Left"); //Naar links rijden
        btnLeft.setBounds(10, 61, 73, 43);
        layeredPane.add(btnLeft);
        btnLeft.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    communicate.writeString("L");
                }
            });

        JButton btnStop = new JButton("Stop"); //Stoppen
        btnStop.setBounds(86, 61, 80, 43);
        layeredPane.add(btnStop);
        btnStop.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    communicate.writeString("S");
                }
            });

        JButton btnRight = new JButton("Right"); //Naar rechts rijden
        btnRight.setBounds(169, 61, 73, 43);
        layeredPane.add(btnRight);
        btnRight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    communicate.writeString("R");
                }
            });

        JButton btnBackwards = new JButton("Backwards");//Naar achteren rijden
        btnBackwards.setBounds(75, 125, 106, 34);
        layeredPane.add(btnBackwards);
        btnBackwards.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    communicate.writeString("B");
                }
            });

        //______________________________________________________________________________________________________________________

        layeredPane_1.setLayout(null);

        JButton btnFirstLeft = new JButton("First left");//Eerste links
        btnFirstLeft.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addDirections(0,'L');
                }
            });
        btnFirstLeft.setBounds(10, 1, 112, 23);
        layeredPane_1.add(btnFirstLeft);

        JButton btnFirstRight = new JButton("First Right");//Eerste rechts
        btnFirstRight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addDirections(0,'R');
                }
            });
        btnFirstRight.setBounds(132, 1, 110, 23);
        layeredPane_1.add(btnFirstRight);

        JButton btnSecondLeft = new JButton("Second left");//Tweede links
        btnSecondLeft.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addDirections(1,'L');
                }
            });
        btnSecondLeft.setBounds(10, 29, 112, 23);
        layeredPane_1.add(btnSecondLeft);

        JButton button = new JButton("Second right");//Tweede rechts
        button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addDirections(1,'R');
                }
            });
        button.setBounds(132, 29, 110, 23);
        layeredPane_1.add(button);

        JButton btnThirdLeft = new JButton("Third left");//Derde links
        btnThirdLeft.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addDirections(2,'L');
                }
            });
        btnThirdLeft.setBounds(10, 57, 112, 23);
        layeredPane_1.add(btnThirdLeft);

        JButton btnThirdRight = new JButton("Third right");//Derde rechts
        btnThirdRight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addDirections(2,'R');
                }
            });
        btnThirdRight.setBounds(132, 57, 110, 23);
        layeredPane_1.add(btnThirdRight);

        JButton btnBackwards_1 = new JButton("Send");//Stuur route
        btnBackwards_1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    sendRouteCode();                   
                }
            });
        btnBackwards_1.setBounds(10, 115, 112, 23);
        layeredPane_1.add(btnBackwards_1);

        JButton btnForward_1 = new JButton("Forward"); //Vooruit
        btnForward_1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addDirections(0,'F');           
                }
            });
        btnForward_1.setBounds(132, 115, 110, 23);
        layeredPane_1.add(btnForward_1);

        JButton btnDeleteLast = new JButton("Delete last");//Verwijder laatste commando
        btnDeleteLast.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    deleteLastRouteEntry();
                }
            });
        btnDeleteLast.setBounds(10, 143, 112, 23);
        layeredPane_1.add(btnDeleteLast);

        JButton btnDeleteAll = new JButton("Delete all");//Verwijder alle commando's
        btnDeleteAll.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    delateAllRouteEntrys();
                }
            });
        btnDeleteAll.setBounds(132, 143, 110, 23);
        layeredPane_1.add(btnDeleteAll);
        frame.getContentPane().setLayout(groupLayout);
    }

    /**
     * Quit function: quit the application.
     */
    private void quit()
    {
        System.exit(0);
    }

    /**
     * 'About' function: show the 'about' box.
     */
    private void showAbout()
    {
        JOptionPane.showMessageDialog(frame, 
            "Gemaakt door TI-A5:\n\nWesley de Hek, Tim Schijvenaars\nMichaël Schruring en Bilel Bghiel\n\nPeriode 1.2 2014.",
            "About ImageViewer", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void connectBluetooth() {
        if(communicate == null)
            communicate = new ComWriter(); 
        else
            JOptionPane.showMessageDialog(frame,"Already connected","Error",JOptionPane.INFORMATION_MESSAGE); 
    }

    private void disconnectBluetooth() {
        if(communicate != null)
            communicate = null;
        else 
            JOptionPane.showMessageDialog(frame,"Not connected to the boebot","Error",JOptionPane.INFORMATION_MESSAGE); 
    }

    private void showState(String state){
        lblTextboef.setText(state);
    }

    private void addDirections(int turn, char direction) { //0 - direct, 1 - second, 2 - thirth
        for(int i = 0; i < turn; i++) {
            routeCode += "F";
        }
        routeCode += direction;
        String turnText = (turn+1)+"e afslag "+direction + "\n";
        entryTextList.add(turnText.length());
        textRoute += turnText;        
        txtArea.setText(textRoute);
        entryList.add(turn+1);
        lastEntry ++;
    }

    private void sendRouteCode() {
        if(routeCode != "") {
            communicate.writeString(routeCode);
            try {
                Thread.sleep(1000);                 
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            communicate.writeString("1");
            showState("Line Followers");
        }	
        else
            JOptionPane.showMessageDialog(frame,"No directions entered.\nPlease enter at least one direction.","Error",JOptionPane.INFORMATION_MESSAGE);
    }

    private void delateAllRouteEntrys() {
        if(lastEntry != 0) {
            routeCode = "";
            txtArea.setText("");
            textRoute = "";
            lastEntry = 0;
        }
        else
            JOptionPane.showMessageDialog(frame,"No entrys to remove.","Error",JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteLastRouteEntry() {        
        if(lastEntry != 0) {            
            routeCode = routeCode.substring(0,routeCode.length()-entryList.get(lastEntry-1));
            textRoute = textRoute.substring(0,textRoute.length()-entryTextList.get(lastEntry-1));
            txtArea.setText(textRoute);
            lastEntry--;
        }
        else
            JOptionPane.showMessageDialog(frame,"No entry to remove.","Error",JOptionPane.INFORMATION_MESSAGE);
    }
}

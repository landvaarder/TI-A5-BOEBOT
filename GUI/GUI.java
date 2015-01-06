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
    private int lastEntryLength;

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
        frame.setBounds(100, 100, 650, 605);
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
                    communicate.writeString("2");
                }
            });
        menu.add(item);

        item = new JMenuItem("Linefollow Mode");
        item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) { 
                    communicate.writeString("1");
                }
            });
        menu.add(item);

        item = new JMenuItem("Collisiondetection Mode");
        item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {  
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

        lblTextboef = new JLabel();

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

        JButton btnForward = new JButton("Forward"); //Naar voren rijden
        GridBagConstraints gbc_btnForward = new GridBagConstraints();
        gbc_btnForward.insets = new Insets(0, 0, 5, 5);
        gbc_btnForward.gridx = 3;
        gbc_btnForward.gridy = 1;
        layeredPane.add(btnForward, gbc_btnForward);
        btnForward.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    communicate.writeString("F");
                }
            });

        JButton btnLeft = new JButton("Left"); //Naar links rijden
        GridBagConstraints gbc_btnLeft = new GridBagConstraints();
        gbc_btnLeft.insets = new Insets(0, 0, 5, 5);
        gbc_btnLeft.gridx = 2;
        gbc_btnLeft.gridy = 3;
        layeredPane.add(btnLeft, gbc_btnLeft);
        btnLeft.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    communicate.writeString("L");
                }
            });

        JButton btnStop = new JButton("Stop"); //Stoppen
        GridBagConstraints gbc_btnStop = new GridBagConstraints();
        gbc_btnStop.insets = new Insets(0, 0, 5, 5);
        gbc_btnStop.gridx = 3;
        gbc_btnStop.gridy = 3;
        layeredPane.add(btnStop, gbc_btnStop);
        btnStop.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    communicate.writeString("S");
                }
            });

        JButton btnRight = new JButton("Right"); //Naar rechts rijden
        GridBagConstraints gbc_btnRight = new GridBagConstraints();
        gbc_btnRight.insets = new Insets(0, 0, 5, 5);
        gbc_btnRight.gridx = 4;
        gbc_btnRight.gridy = 3;
        layeredPane.add(btnRight, gbc_btnRight);
        btnRight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    communicate.writeString("R");
                }
            });

        JButton btnBackwards = new JButton("Backwards");//Naar achteren rijden
        GridBagConstraints gbc_btnBackwards = new GridBagConstraints();
        gbc_btnBackwards.insets = new Insets(0, 0, 0, 5);
        gbc_btnBackwards.gridx = 3;
        gbc_btnBackwards.gridy = 5;
        layeredPane.add(btnBackwards, gbc_btnBackwards);
        btnBackwards.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    communicate.writeString("B");
                }
            });

        //______________________________________________________________________________________________________________________

        GridBagLayout gbl_layeredPane_1 = new GridBagLayout();
        gbl_layeredPane_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gbl_layeredPane_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_layeredPane_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_layeredPane_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        layeredPane_1.setLayout(gbl_layeredPane_1);

        JButton btnFirstLeft = new JButton("First left");//Eerste links
        btnFirstLeft.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addDirections(0,'L');
                }
            });
        GridBagConstraints gbc_btnFirstLeft = new GridBagConstraints();
        gbc_btnFirstLeft.insets = new Insets(0, 0, 5, 5);
        gbc_btnFirstLeft.gridx = 2;
        gbc_btnFirstLeft.gridy = 0;
        layeredPane_1.add(btnFirstLeft, gbc_btnFirstLeft);

        JButton btnFirstRight = new JButton("First Right");//Eerste rechts
        btnFirstRight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addDirections(0,'R');
                }
            });
        GridBagConstraints gbc_btnFirstRight = new GridBagConstraints();
        gbc_btnFirstRight.insets = new Insets(0, 0, 5, 5);
        gbc_btnFirstRight.gridx = 4;
        gbc_btnFirstRight.gridy = 0;
        layeredPane_1.add(btnFirstRight, gbc_btnFirstRight);

        JButton btnSecondLeft = new JButton("Second left");//Tweede links
        btnSecondLeft.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addDirections(1,'L');
                }
            });
        GridBagConstraints gbc_btnSecondLeft = new GridBagConstraints();
        gbc_btnSecondLeft.insets = new Insets(0, 0, 5, 5);
        gbc_btnSecondLeft.gridx = 2;
        gbc_btnSecondLeft.gridy = 1;
        layeredPane_1.add(btnSecondLeft, gbc_btnSecondLeft);

        JButton button = new JButton("Second right");//Tweede rechts
        button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addDirections(1,'R');
                }
            });
        GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.insets = new Insets(0, 0, 5, 5);
        gbc_button.gridx = 4;
        gbc_button.gridy = 1;
        layeredPane_1.add(button, gbc_button);

        JButton btnThirdLeft = new JButton("Third left");//Derde links
        btnThirdLeft.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addDirections(2,'L');
                }
            });
        GridBagConstraints gbc_btnThirdLeft = new GridBagConstraints();
        gbc_btnThirdLeft.insets = new Insets(0, 0, 5, 5);
        gbc_btnThirdLeft.gridx = 2;
        gbc_btnThirdLeft.gridy = 2;
        layeredPane_1   .add(btnThirdLeft, gbc_btnThirdLeft);

        JButton btnThirdRight = new JButton("Third right");//Derde rechts
        btnThirdRight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addDirections(2,'R');
                }
            });
        GridBagConstraints gbc_btnThirdRight = new GridBagConstraints();
        gbc_btnThirdRight.insets = new Insets(0, 0, 5, 5);
        gbc_btnThirdRight.gridx = 4;
        gbc_btnThirdRight.gridy = 2;
        layeredPane_1.add(btnThirdRight, gbc_btnThirdRight);

        JButton btnBackwards_1 = new JButton("Send");//Stuur route
        btnBackwards_1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    sendRouteCode();                   
                }
            });
        GridBagConstraints gbc_btnBackwards_1 = new GridBagConstraints();
        gbc_btnBackwards_1.insets = new Insets(0, 0, 0, 5);
        gbc_btnBackwards_1.gridx = 2;
        gbc_btnBackwards_1.gridy = 4;
        layeredPane_1.add(btnBackwards_1, gbc_btnBackwards_1);

        JButton btnForward_1 = new JButton("Forward"); //Vooruit
        btnForward_1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addDirections(0,'F');           
                }
            });
        GridBagConstraints gbc_btnForward_1 = new GridBagConstraints();
        gbc_btnForward_1.insets = new Insets(0, 0, 5, 5);
        gbc_btnForward_1.gridx = 4;
        gbc_btnForward_1.gridy = 4;
        layeredPane_1.add(btnForward_1, gbc_btnForward_1);

        JButton btnDeleteLast = new JButton("Delete last");//Verwijder laatste commando
        btnDeleteLast.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    deleteLastRouteEntry();
                }
            });
        GridBagConstraints gbc_btnDeleteLast = new GridBagConstraints();
        gbc_btnDeleteLast.insets = new Insets(0, 0, 0, 5);
        gbc_btnDeleteLast.gridx = 2;
        gbc_btnDeleteLast.gridy = 5;
        layeredPane_1.add(btnDeleteLast, gbc_btnDeleteLast);

        JButton btnDeleteAll = new JButton("Delete all");//Verwijder alle commando's
        btnDeleteAll.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    delateAllRouteEntrys();
                }
            });
        GridBagConstraints gbc_btnDeleteAll = new GridBagConstraints();
        gbc_btnDeleteAll.insets = new Insets(0, 0, 0, 5);
        gbc_btnDeleteAll.gridx = 4;
        gbc_btnDeleteAll.gridy = 5;
        layeredPane_1.add(btnDeleteAll, gbc_btnDeleteAll);
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
            "Gemaakt door TI-A5:\n\nWesley de Hek, Tim Schijvenaars\nMichaël Schruring, Timothy Verdonck\nSven Withagen en Bilel Bghiel\n\nPeriode 1.2 2014.",
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
        lastEntryLength = turnText.length();
        textRoute += turnText;        
        txtArea.setText(textRoute);
        lastEntry = turn++;
    }

    private void sendRouteCode() {
        if(routeCode != "") {
            communicate.writeString(routeCode);
            try {
                Thread.sleep(1000);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            communicate.writeString("1");
        }	
        else
            JOptionPane.showMessageDialog(frame,"No directions entered.\nPlease enter at least one direction.","Error",JOptionPane.INFORMATION_MESSAGE);
    }

    private void delateAllRouteEntrys() {
        if(routeCode != "") {
            routeCode = "";
            txtArea.setText("");
            textRoute = "";
        }
        else
            JOptionPane.showMessageDialog(frame,"No entrys to remove.","Error",JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteLastRouteEntry() {
        if(lastEntry != 0 ) {
            routeCode = routeCode.substring(0,routeCode.length()-lastEntry-1);
            textRoute = textRoute.substring(0,textRoute.length()-lastEntryLength);
            txtArea.setText(textRoute);
        }
        else
            JOptionPane.showMessageDialog(frame,"No entry to remove.","Error",JOptionPane.INFORMATION_MESSAGE);
    }
}

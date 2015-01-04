import java.awt.*;   
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.EventQueue;
import java.lang.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;


/**
 * @author: Tim Schijvenaars
 * @version 1.0
 */

public class GUI {

	private JFrame frame;

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
		frame.setBounds(100, 100, 532, 513);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        //______________________________________________________________________________________________________________________
        JMenu menu;
        JMenuItem item;
        
        // create the File menu
        menu = new JMenu("System");
        menubar.add(menu);
        
        item = new JMenuItem("Remotecontrol Mode");
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { remoteControlState();
                                }
                           });
        menu.add(item);

        item = new JMenuItem("Linefollow Mode");
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { lineFollowState();
                                }
                           });
        menu.add(item);
        
        item = new JMenuItem("Collisiondetection Mode");
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {  collisionDetectionState();
                                }
                           });
        menu.add(item);
        menu.addSeparator();
        
        item = new JMenuItem("Quit");
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { quit(); }
                           });
        menu.add(item);
        
        // create the Help menu
        menu = new JMenu("Help");
        menubar.add(menu);
        
        item = new JMenuItem("About BoefBot...");
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { showAbout(); }
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
		
		JTextPane textPane = new JTextPane();
		
		JLabel lblQueue = new JLabel("Queue");
		lblQueue.setFont(new Font("Arial", Font.PLAIN, 13));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblQueue)
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblAddRoute)
							.addComponent(layeredPane_1, GroupLayout.PREFERRED_SIZE, 298, Short.MAX_VALUE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblManualMovement)
							.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 290, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddRoute)
						.addComponent(lblQueue))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(layeredPane_1, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblManualMovement)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 159, Short.MAX_VALUE))
						.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JButton btnForward = new JButton("Forward"); //Naar voren rijden
		GridBagConstraints gbc_btnForward = new GridBagConstraints();
		gbc_btnForward.insets = new Insets(0, 0, 5, 5);
		gbc_btnForward.gridx = 3;
		gbc_btnForward.gridy = 1;
		layeredPane.add(btnForward, gbc_btnForward);
		btnForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
			}
		});
		GridBagConstraints gbc_btnThirdLeft = new GridBagConstraints();
		gbc_btnThirdLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnThirdLeft.gridx = 2;
		gbc_btnThirdLeft.gridy = 2;
		layeredPane_1.add(btnThirdLeft, gbc_btnThirdLeft);
		
		JButton btnThirdRight = new JButton("Third right");//Derde rechts
		btnThirdRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnThirdRight = new GridBagConstraints();
		gbc_btnThirdRight.insets = new Insets(0, 0, 5, 5);
		gbc_btnThirdRight.gridx = 4;
		gbc_btnThirdRight.gridy = 2;
		layeredPane_1.add(btnThirdRight, gbc_btnThirdRight);
		
		JButton btnBackwards_1 = new JButton("Backwards");//Achteruit
		btnBackwards_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnBackwards_1 = new GridBagConstraints();
		gbc_btnBackwards_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnBackwards_1.gridx = 2;
		gbc_btnBackwards_1.gridy = 4;
		layeredPane_1.add(btnBackwards_1, gbc_btnBackwards_1);
		
		JButton btnForward_1 = new JButton("Forward");//Vooruit
		btnForward_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnForward_1 = new GridBagConstraints();
		gbc_btnForward_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnForward_1.gridx = 4;
		gbc_btnForward_1.gridy = 4;
		layeredPane_1.add(btnForward_1, gbc_btnForward_1);
		frame.getContentPane().setLayout(groupLayout);
		
		JButton btnDeleteLast = new JButton("Delete last");//Verwijder laatste commando
		btnDeleteLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
    private void remoteControlState()
    {

    }
    
    /**
     * Quit function: quit the application.
     */
    private void lineFollowState()
    {

    }
    
    /**
     * Quit function: quit the application.
     */
    private void collisionDetectionState()
    {

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
	
}

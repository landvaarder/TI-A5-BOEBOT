package org.eclipse.wb.swt;

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

public class GUI1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI1 window = new GUI1();
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
	public GUI1() {
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
		frame.setBounds(100, 100, 530, 513);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JLayeredPane layeredPane = new JLayeredPane();
		
		JLabel lblManualMovement = new JLabel("Manual Movement");
		lblManualMovement.setFont(new Font("Arial", Font.PLAIN, 13));
		GridBagLayout gbl_layeredPane = new GridBagLayout();
		gbl_layeredPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_layeredPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_layeredPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_layeredPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		layeredPane.setLayout(gbl_layeredPane);
		
		JButton btnForward = new JButton("Forward");
		GridBagConstraints gbc_btnForward = new GridBagConstraints();
		gbc_btnForward.insets = new Insets(0, 0, 5, 5);
		gbc_btnForward.gridx = 3;
		gbc_btnForward.gridy = 0;
		layeredPane.add(btnForward, gbc_btnForward);
		
		JButton btnLeft_1 = new JButton("Left");
		GridBagConstraints gbc_btnLeft_1 = new GridBagConstraints();
		gbc_btnLeft_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeft_1.gridx = 1;
		gbc_btnLeft_1.gridy = 2;
		layeredPane.add(btnLeft_1, gbc_btnLeft_1);
		
		JButton btnStop = new JButton("Stop");
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.insets = new Insets(0, 0, 5, 5);
		gbc_btnStop.gridx = 3;
		gbc_btnStop.gridy = 2;
		layeredPane.add(btnStop, gbc_btnStop);
		
		JButton btnLeft = new JButton("Right");
		GridBagConstraints gbc_btnLeft = new GridBagConstraints();
		gbc_btnLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnLeft.gridx = 5;
		gbc_btnLeft.gridy = 2;
		layeredPane.add(btnLeft, gbc_btnLeft);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnBackwards = new JButton("Backwards");
		GridBagConstraints gbc_btnBackwards = new GridBagConstraints();
		gbc_btnBackwards.insets = new Insets(0, 0, 0, 5);
		gbc_btnBackwards.gridx = 3;
		gbc_btnBackwards.gridy = 4;
		layeredPane.add(btnBackwards, gbc_btnBackwards);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		
		JLabel lblAddRoute = new JLabel("Add to route");
		lblAddRoute.setFont(new Font("Arial", Font.PLAIN, 13));
		
		JTextPane textPane = new JTextPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAddRoute)
						.addComponent(lblManualMovement)
						.addComponent(layeredPane_1, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
						.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE))
					.addGap(207))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(lblAddRoute)
					.addGap(18)
					.addComponent(layeredPane_1, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblManualMovement)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
						.addComponent(layeredPane))
					.addContainerGap())
		);
		GridBagLayout gbl_layeredPane_1 = new GridBagLayout();
		gbl_layeredPane_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_layeredPane_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_layeredPane_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_layeredPane_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		layeredPane_1.setLayout(gbl_layeredPane_1);
		
		JButton btnFirstLeft = new JButton("First left");
		GridBagConstraints gbc_btnFirstLeft = new GridBagConstraints();
		gbc_btnFirstLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnFirstLeft.gridx = 2;
		gbc_btnFirstLeft.gridy = 0;
		layeredPane_1.add(btnFirstLeft, gbc_btnFirstLeft);
		
		JButton btnFirstRight = new JButton("First Right");
		GridBagConstraints gbc_btnFirstRight = new GridBagConstraints();
		gbc_btnFirstRight.insets = new Insets(0, 0, 5, 5);
		gbc_btnFirstRight.gridx = 4;
		gbc_btnFirstRight.gridy = 0;
		layeredPane_1.add(btnFirstRight, gbc_btnFirstRight);
		
		JButton btnSecondLeft = new JButton("Second left");
		GridBagConstraints gbc_btnSecondLeft = new GridBagConstraints();
		gbc_btnSecondLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnSecondLeft.gridx = 2;
		gbc_btnSecondLeft.gridy = 1;
		layeredPane_1.add(btnSecondLeft, gbc_btnSecondLeft);
		
		JButton button = new JButton("Second right");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 4;
		gbc_button.gridy = 1;
		layeredPane_1.add(button, gbc_button);
		
		JButton btnThirdLeft = new JButton("Third left");
		GridBagConstraints gbc_btnThirdLeft = new GridBagConstraints();
		gbc_btnThirdLeft.insets = new Insets(0, 0, 5, 5);
		gbc_btnThirdLeft.gridx = 2;
		gbc_btnThirdLeft.gridy = 2;
		layeredPane_1.add(btnThirdLeft, gbc_btnThirdLeft);
		
		JButton btnThirdRight = new JButton("Third right");
		GridBagConstraints gbc_btnThirdRight = new GridBagConstraints();
		gbc_btnThirdRight.insets = new Insets(0, 0, 5, 5);
		gbc_btnThirdRight.gridx = 4;
		gbc_btnThirdRight.gridy = 2;
		layeredPane_1.add(btnThirdRight, gbc_btnThirdRight);
		
		JButton btnBackwards_1 = new JButton("Backwards");
		GridBagConstraints gbc_btnBackwards_1 = new GridBagConstraints();
		gbc_btnBackwards_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnBackwards_1.gridx = 2;
		gbc_btnBackwards_1.gridy = 4;
		layeredPane_1.add(btnBackwards_1, gbc_btnBackwards_1);
		
		JButton btnForward_1 = new JButton("Forward");
		GridBagConstraints gbc_btnForward_1 = new GridBagConstraints();
		gbc_btnForward_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnForward_1.gridx = 4;
		gbc_btnForward_1.gridy = 4;
		layeredPane_1.add(btnForward_1, gbc_btnForward_1);
		
		JButton btnDeleteLast = new JButton("Delete last");
		GridBagConstraints gbc_btnDeleteLast = new GridBagConstraints();
		gbc_btnDeleteLast.insets = new Insets(0, 0, 0, 5);
		gbc_btnDeleteLast.gridx = 2;
		gbc_btnDeleteLast.gridy = 5;
		layeredPane_1.add(btnDeleteLast, gbc_btnDeleteLast);
		
		JButton btnDeleteAll = new JButton("Delete all");
		GridBagConstraints gbc_btnDeleteAll = new GridBagConstraints();
		gbc_btnDeleteAll.insets = new Insets(0, 0, 0, 5);
		gbc_btnDeleteAll.gridx = 4;
		gbc_btnDeleteAll.gridy = 5;
		layeredPane_1.add(btnDeleteAll, gbc_btnDeleteAll);
		frame.getContentPane().setLayout(groupLayout);
	}
}

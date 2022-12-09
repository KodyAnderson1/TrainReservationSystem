package GUI.CustomerGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import constants.FilePaths;

@SuppressWarnings("serial")
public class HomepagePanel extends JPanel {

	public HomepagePanel() {
		setBackground(Color.WHITE);
		setBounds(173, 0, 661, 611);
		setLayout(null);

		ImageIcon trainJPG = new ImageIcon(FilePaths.TRAIN_PICTURE.toString());

		JLabel lblTrainPic = new JLabel(trainJPG);
		lblTrainPic.setBounds(20, 120, 625, 491);
		add(lblTrainPic);

		JLabel lblHmpgTxt = new JLabel("Welcome to Guardian Railways!");
		lblHmpgTxt.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblHmpgTxt.setHorizontalAlignment(SwingConstants.CENTER);
		lblHmpgTxt.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblHmpgTxt.setForeground(Color.BLACK);
		lblHmpgTxt.setBounds(10, 11, 641, 83);
		add(lblHmpgTxt);

		JLabel lblSmallHmpgTxt = new JLabel("Please Select a Menu Option");
		lblSmallHmpgTxt.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblSmallHmpgTxt.setHorizontalAlignment(SwingConstants.CENTER);
		lblSmallHmpgTxt.setBounds(37, 105, 586, 46);
		add(lblSmallHmpgTxt);

		JLabel lblCopyright = new JLabel(
				"\u00a9 Anthony Welter, Andrew Rabinowitz, Chase Sisson, Dillon Vaughan, Kody Anderson");
		lblCopyright.setBounds(90, 586, 481, 14);
		add(lblCopyright);
	}

}

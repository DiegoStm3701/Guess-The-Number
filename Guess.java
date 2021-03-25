import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class Guess{

	static int randomNumber;
	static JFrame frame;
	static JPanel background;
	static Border border = new LineBorder(Color.BLACK, 4, true);
	static int lastDifference;
	static JPanel userMessagePanel = new JPanel();
	static JLabel userMessage =  new JLabel();
	
	public static void main(String[] args) {
		
		createFrame();
		askGuess();
		makeGuess();

	}

	
	
	
	
	//function to create basic frame, as well as a background panel, which will be used to add all other panel on top of it and to change the background
	public static void createFrame() {
		frame = new JFrame("Guess");
		background = new JPanel();
		background.setLayout(null);
		background.setBackground(Color.white);
		
		frame.setSize(1400,800);
		frame.add(background);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	//function that adds panels with the questions to the background.
	public static void askGuess() {

		JPanel questionPanel = new JPanel();
		questionPanel.setBackground(Color.green);
		questionPanel.setLocation(frame.getWidth()/4, 100);
		questionPanel.setSize(700,200);
		questionPanel.setBorder(border);
		
		
		JLabel label =  new JLabel("<html>I have a number between 1 and 1000. Can you guess my number? <br/> Please enter your first guess.</html?");
		label.setFont(new Font("Calibri",Font.PLAIN,25));
		
		
		questionPanel.add(label);
		background.add(questionPanel);
		background.revalidate();
		background.validate();
		background.repaint();
		
		
	}
	
	//this functions takes the user input with jtextfield, changes the color and message accordingly
	public static void makeGuess() {
		Random rand = new Random();
		randomNumber = rand.nextInt(1000) +1;
		
		JPanel textFieldPanel = new JPanel();
		textFieldPanel.setBackground(Color.gray);
		textFieldPanel.setLocation(frame.getWidth()*3/8, frame.getHeight()-250);
		textFieldPanel.setSize(300,200);
		textFieldPanel.setBorder(border);
		
		JLabel label =  new JLabel("Please insert a number");
		label.setFont(new Font("Calibri",Font.BOLD,25));
		
		JTextField userNumber = new JTextField(4);
		userNumber.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String inputString = userNumber.getText();
						int inputNumber = Integer.parseInt(inputString);
						int currentDifference = inputNumber - randomNumber;
						if(currentDifference < 0) {
							currentDifference = currentDifference*-1;
						}
						userNumber.setText("");
						
						userMessagePanel.setBackground(Color.gray);
						userMessagePanel.setLocation(frame.getWidth()*3/8, frame.getHeight()/2);
						userMessagePanel.setSize(300,100);
						userMessagePanel.setBorder(border);
						userMessagePanel.setVisible(true);
						
						
						userMessage.setLayout(null);
						userMessage.setFont(new Font("Calibri",Font.BOLD,25));
						
						if(inputNumber < randomNumber) {
							userMessage.setText("Too Low!");		
						}
						else if(inputNumber > randomNumber) {
							userMessage.setText("Too High!");	
						}
						else if(inputNumber == randomNumber) {
							userMessage.setText("Correct!");
						}
						
						if(currentDifference == 0) {
							background.setBackground(Color.YELLOW);
							userNumber.setEditable(false);
							
							JPanel buttonPanel = new JPanel();
							buttonPanel.setLocation(600, frame.getHeight()*3/8);
							buttonPanel.setSize(100,50);
							buttonPanel.setBackground(Color.YELLOW);
							
							JButton button = new JButton("Play Again?");
							button.setSize(100,100);
							button.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									userNumber.setEditable(true);
									buttonPanel.setVisible(false);
									userMessagePanel.setVisible(false);
									background.setBackground(Color.WHITE);
									randomNumber = rand.nextInt(1000) +1;
									makeGuess();
								}
							});
							
							buttonPanel.add(button);
							background.add(buttonPanel);
							background.revalidate();
							background.validate();
							background.repaint();
						}
						else if(lastDifference == 0) {
							background.setBackground(Color.WHITE);
						}
						else if(currentDifference > lastDifference) {
							background.setBackground(Color.BLUE);
						}
						else if(currentDifference < lastDifference) {
							background.setBackground(Color.RED);
						}
						else if(currentDifference ==  lastDifference) {
							background.setBackground(Color.WHITE);
						}
						lastDifference = currentDifference;
						
						userMessagePanel.add(userMessage);
						
						background.add(userMessagePanel);
						
						background.revalidate();
						background.validate();
						background.repaint();
						
						
					}
				}
				
				);
		
		
		
		textFieldPanel.add(label);
		textFieldPanel.add(userNumber);
		
		background.add(textFieldPanel);
		background.revalidate();
		background.validate();
		background.repaint();
	}
	
	
	
}




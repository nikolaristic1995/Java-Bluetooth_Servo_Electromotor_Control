package bluetooth;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import javax.swing.text.PlainDocument;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;


public class Gui{
	
	private int intServo_electromotor_position_in_degrees_TX = 0;
	private int intServo_electromotor_position_in_degrees_RX = 0;
	
	private JFrame frame;
	
	private JPanel panel;
	
	private JTextField text_field; //za input pozicije servo motora
	
	private ImageIcon picture;
	
	private JLabel input_label;
	private JLabel position_label;
	private JLabel picture_label;

	private JButton button_increment;
	private JButton button_decrement;
	private JButton button_set_position;
	
	public Gui(){
			
		//shServo_electromotor_position_in_degrees = bluetoothGePositionIntDegrees();
		
		frame = new JFrame();
		frame.setSize(720, 470);		//velicina framea
		frame.setLocation(280, 140); //kad se upali aplikacija gde se stvara frame
		frame.setResizable(false);  //mala je aplikacija tako da ne mora imati opciju da se resizuje
		frame.setTitle("Servo electromotor MG996R"); //naslov aplikacije
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//kad iksiras, aplikacija se zavrsila
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nikola\\Desktop\\Java projekti\\Projekti za fakultet\\Bluetooth servo electromotor control\\MG996RIcon.png"));
		//gorenjom linijom koda sam podesio ikonu aplikacije
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); //napravio okvir oko aplikacije, plave boje i delbljine 2 piksela
		panel.setLayout(null); //postavio sam layout panela
		frame.add(panel, BorderLayout.CENTER); //panel sam stavio u centar frejma
		
		input_label = new JLabel("Input a whole number between 0 and 180:"); 
		input_label.setFont(new Font("Ariel", Font.BOLD, 22));
		input_label.setForeground(Color.BLACK);
		
		text_field = new JTextField();// ovde treba da stoji trenutna pozicija od motora pokupljena bluetoothom
		text_field.setFont(new Font("Ariel", Font.BOLD, 22));
		

	    PlainDocument doc = (PlainDocument) text_field.getDocument();
	    doc.setDocumentFilter(new MyIntFilter());
		
	    text_field.getDocument().addDocumentListener(new DocumentListener() { //ogranici na 3 broja i da se prvi brojproguta
	        @Override
	        public void changedUpdate(DocumentEvent e) {
	          
	        		try{
					
	        			input_label.setForeground(Color.GREEN);
	        			input_label.setText("Input is valid!");
	        			button_set_position.setVisible(true);
				
	        			int input = Integer.parseInt(text_field.getText());
	        			
	        			System.out.println("text_field.getText() == " + text_field.getText());
	        			System.out.println("parsed input  == " + input);
					
	        			if(input >= 0 && input <= 180){
						
	        				intServo_electromotor_position_in_degrees_TX = input;
	        			}
					
	        			else{
						
	        				intServo_electromotor_position_in_degrees_TX = 0; //kasnije ukloniti, tj staviti od feedbacka bluetootha
	        				input_label.setForeground(Color.RED);
	        				input_label.setText("Error: Input must be a whole number between 0 and 180!");
	        				button_set_position.setVisible(false);
					}
					
				}
				
				catch(NumberFormatException er) {
					
					intServo_electromotor_position_in_degrees_TX = 0; //kasnije ukloniti, tj staviti od feedbacka bluetootha
					input_label.setForeground(Color.RED);
					input_label.setText("Error: Input cannot contain non-number characters!");	
					button_set_position.setVisible(false);
				}
	        }

	        @Override
	        public void removeUpdate(DocumentEvent e) {
	            
	        		try{
					
	        			input_label.setForeground(Color.GREEN);
	        			input_label.setText("Input is valid!");
	        			button_set_position.setVisible(true);
				
	        			int input = Integer.parseInt(text_field.getText());
					
	        			System.out.println("text_field.getText() == " + text_field.getText());
	        			System.out.println("parsed input  == " + input);
					
	        			if(input >= 0 && input <= 180){
						
	        				intServo_electromotor_position_in_degrees_TX = input;
	        			}
					
	        			else{
						
	        				intServo_electromotor_position_in_degrees_TX = 0; //kasnije ukloniti, tj staviti od feedbacka bluetootha
	        				input_label.setForeground(Color.RED);
	        				input_label.setText("Error: Input must be a whole number between 0 and 180!");
	        				button_set_position.setVisible(false);
	        			}
	        			
	        		}
				
	        		catch(NumberFormatException er) {
					
	        			intServo_electromotor_position_in_degrees_TX = 0; //kasnije ukloniti, tj staviti od feedbacka bluetootha
	        			input_label.setForeground(Color.RED);
	        			input_label.setText("Error: Input cannot contain non-number characters!");	
	        			button_set_position.setVisible(false);
	        		}
	        	}
	        
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	            
	        		try{
					
	        			input_label.setForeground(Color.GREEN);
	        			input_label.setText("Input is valid!");
	        			button_set_position.setVisible(true);
				
	        			int input = Integer.parseInt(text_field.getText());
					
	        			System.out.println("text_field.getText() == " + text_field.getText());
	        			System.out.println("parsed input  == " + input);
					
	        			if(input >= 0 && input <= 180){
						
	        				intServo_electromotor_position_in_degrees_TX = input;
	        			}
					
	        			else{
						
	        				intServo_electromotor_position_in_degrees_TX = 0; //kasnije ukloniti, tj staviti od feedbacka bluetootha
	        				input_label.setForeground(Color.RED);
	        				input_label.setText("Error: Input must be a whole number between 0 and 180!");
	        				button_set_position.setVisible(false);
	        			}
					
	        		}
				
	        		catch(NumberFormatException er) {
					
	        			intServo_electromotor_position_in_degrees_TX = 0; //kasnije ukloniti, tj staviti od feedbacka bluetootha
	        			input_label.setForeground(Color.RED);
	        			input_label.setText("Error: Input cannot contain non-number characters!");	
	        			button_set_position.setVisible(false);
	        		}
	        	}
	    	});
		
		position_label = new JLabel();
		position_label.setFont(new Font("Ariel", Font.BOLD, 22));
		position_label.setForeground(Color.BLUE);
		position_label.setText("Servo electromotor current position is " + intServo_electromotor_position_in_degrees_RX + "°."); //od feedbacka bluetootha pokupiti
		
		picture = new ImageIcon("C:\\Users\\Nikola\\Desktop\\Java projekti\\Projekti za fakultet\\Bluetooth servo electromotor control\\MG996RImage.png");
		picture_label = new JLabel(picture);
		
		button_increment = new JButton("INCREMENT");
		button_decrement = new JButton("DECREMENT");
		button_set_position = new JButton("SET POSITION");
		
		button_increment.setFont(new Font("Ariel", Font.BOLD, 20));
		button_decrement.setFont(new Font("Ariel", Font.BOLD, 20));
		button_set_position.setFont(new Font("Ariel", Font.BOLD, 20));
		
		button_increment.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e){
				
				if(intServo_electromotor_position_in_degrees_TX < 180) {
					
					intServo_electromotor_position_in_degrees_TX++;
				}
				
				text_field.setText(Integer.toString(intServo_electromotor_position_in_degrees_TX));
				input_label.setForeground(Color.GREEN);
				input_label.setText("Input is valid!");
				button_set_position.setVisible(true);
			}
			
		});
		
		button_decrement.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e){
				
				if(intServo_electromotor_position_in_degrees_TX > 0) {
					
					intServo_electromotor_position_in_degrees_TX--;
				}
				
				text_field.setText(Integer.toString(intServo_electromotor_position_in_degrees_TX));
				input_label.setForeground(Color.GREEN);
				input_label.setText("Input is valid!");
				button_set_position.setVisible(true);
			}
			
		});
		
		button_set_position.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e){
				
				intServo_electromotor_position_in_degrees_RX = intServo_electromotor_position_in_degrees_TX;
				position_label.setText("Servo electromotor current position is " + intServo_electromotor_position_in_degrees_RX + "°.");
			}
		});
		

		input_label.setBounds(30, 10, 700, 50);
		panel.add(input_label); 
		
		text_field.setBounds(30, 70, 170, 50);
		panel.add(text_field);
		
		button_increment.setBounds(30, 130, 170, 50);
		panel.add(button_increment); 
		
		button_decrement.setBounds(30, 190, 170, 50);
		panel.add(button_decrement);
		
		button_set_position.setBounds(220, 70, 190, 50);
		panel.add(button_set_position); 
		
		position_label.setBounds(220, 130, 600, 50);
		panel.add(position_label); 
		
		picture_label.setBounds(80, 110, 600, 400);
		panel.add(picture_label); 

		frame.setVisible(true);
	}
}

/*BAGOVI: 
 * 
 * 1. input parsiranje je lose.
 * */

package bluetooth;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.PlainDocument;


public class Gui{
	
	private boolean boolSystem_initialized = false;
	private boolean enable = false;
	
	private int intServo_electromotor_previous_TX = 0;
	
	private int intServo_electromotor_position_in_degrees_TX = 0; //ono sto treba da se posalje bluetoothom
	private int intServo_electromotor_position_in_degrees_RX = 0; //ono sto se prima od bluetootha
	
	private JFrame frame;
	
	private JPanel panel;
	
	private JTextField text_field; //za input pozicije servo motora
	
	private ImageIcon picture;
	
	private JLabel input_label;
	private JLabel position_label;
	private JLabel picture_label;

	public JToggleButton button_set_position;
	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Gui(){
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		HC05 blue_tooth = new HC05();					//bluetooth bojekat
		blue_tooth.initialize_servo_electromotor();		//otvorio stream za input i output
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		frame = new JFrame();									//JFrame objekat
		frame.setSize(690, 470);								//velicina framea
		frame.setLocation(280, 140); 							//kad se upali aplikacija gde se stvara frame
		frame.setResizable(false);  							//mala je aplikacija tako da ne mora imati opciju da se resizuje
		frame.setTitle("Servo electromotor MG996R"); 			//naslov aplikacije
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//kad iksiras, aplikacija se zavrsila
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Nikola\\Desktop\\Java projekti\\Projekti za fakultet\\Bluetooth servo electromotor control\\MG996RIcon.png"));
																//gorenjom linijom koda sam podesio ikonu aplikacije

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		panel = new JPanel();											 //JPanel objekat
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); //napravio okvir oko aplikacije, crne boje i delbljine 1 piksela
		panel.setLayout(null); 											 //postavio sam layout panela da se sve zadaje koordinatama
		frame.add(panel, BorderLayout.CENTER); 							 //panel sam stavio u centar frejma
		
		input_label = new JLabel("Input a whole number between 0 and 180:"); //napravljena labela iznad text fileda
		input_label.setFont(new Font("Ariel", Font.BOLD, 22));				 //podesio sam joj font
		input_label.setForeground(Color.BLACK);								 //podesio sam boju labele
		
		text_field = new JTextField();						//ovde treba da stoji trenutna pozicija od motora pokupljena bluetoothom
		text_field.setFont(new Font("Ariel", Font.BOLD, 22));//podesio font inputa u text field
		

	    PlainDocument doc = (PlainDocument) text_field.getDocument(); //pravljenje filtera tako da input moze biti samo od 0 do 180 stepeni
	    doc.setDocumentFilter(new MyIntFilter());					  //isto kao i prethodni komentar
		
	    text_field.getDocument().addDocumentListener(new DocumentListener() { //ogranico na 3 broja ili nulu
	        @Override
	        public void changedUpdate(DocumentEvent e) { //ako se nesto inputovalo u labelu
	        	
	        		try{ //ako je input validan:
					
	        			input_label.setForeground(Color.GREEN); //labela iznad text fieda je zelena sad
	        			input_label.setText("Input is valid!");  //ovo sad pise na njoj
	        			button_set_position.setVisible(true);	//i prikazuje se
				
	        			int input = Integer.parseInt(text_field.getText());  //input je ono sto je ukucano u JTextField
	        			
	        			if(input >= 0 && input <= 180){ //ako je input izmedju 0 i 180
						
	        				intServo_electromotor_position_in_degrees_TX = input; //stavi ovo u tx bafer, posalji ga tek kada se dugme za send stisne
	        			}
					
	        			else{
						
	        				input_label.setForeground(Color.RED); //boja errora koji se ispisuje u slucaju da je pogresan input unet u text field
	        				input_label.setText("Error: Input must be a whole number between 0 and 180!");//eror koji se ispisuje u slucaju da je pogresan input unet u text field
	        				button_set_position.setVisible(false);//Uklanjanje dugmeta za slanje
	        			}
					
				}
				
				catch(NumberFormatException er) {
					
					input_label.setForeground(Color.RED);  //boja errora koji se ispisuje u slucaju da je pogresan input unet u text field
					input_label.setText("Error: Input cannot contain non-number characters!");	//eror koji se ispisuje u slucaju da je pogresan input unet u text field
        			intServo_electromotor_position_in_degrees_TX = 0;
					button_set_position.setVisible(false); //Uklanjanje dugmeta za slanje
				}
	        }

	        @Override
	        public void removeUpdate(DocumentEvent e) {
	            
	        		try{
					
	        			input_label.setForeground(Color.GREEN);
	        			input_label.setText("Input is valid!");
	        			button_set_position.setVisible(true);
				
	        			int input = Integer.parseInt(text_field.getText());
	        			
	        			if(input >= 0 && input <= 180){
						
	        				intServo_electromotor_position_in_degrees_TX = input;
	        			}
					
	        			else{

	        				input_label.setForeground(Color.RED);
	        				input_label.setText("Error: Input must be a whole number between 0 and 180!");
	        				button_set_position.setVisible(false);
	        			}
	        			
	        		}
				
	        		catch(NumberFormatException er) {

	        			input_label.setForeground(Color.RED);
	        			input_label.setText("Error: Input cannot contain non-number characters!");	
	        			intServo_electromotor_position_in_degrees_TX = 0;
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
					
	        			if(input >= 0 && input <= 180){
	        				
	        				intServo_electromotor_position_in_degrees_TX = input;
	        			}
					
	        			else{
						
	        				input_label.setForeground(Color.RED);
	        				input_label.setText("Error: Input must be a whole number between 0 and 180!");
	        				button_set_position.setVisible(false);
	        			}
					
	        		}
				
	        		catch(NumberFormatException er) {
					
	        			input_label.setForeground(Color.RED);
	        			input_label.setText("Error: Input cannot contain non-number characters!");
	        			intServo_electromotor_position_in_degrees_TX = 0;
	        			button_set_position.setVisible(false);
	        		}
	        	}
	    	});
		
		position_label = new JLabel();
		position_label.setFont(new Font("Ariel", Font.BOLD, 22));
		position_label.setForeground(Color.BLUE);
		
		picture = new ImageIcon("C:\\Users\\Nikola\\Desktop\\Java projekti\\Projekti za fakultet\\Bluetooth servo electromotor control\\MG996RImage.png");
		picture_label = new JLabel(picture);
	
		button_set_position = new JToggleButton("SET POSITION");
		button_set_position.setVisible(false);
		
		button_set_position.setFont(new Font("Ariel", Font.BOLD, 20));
		
		button_set_position.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e){
								
				if(button_set_position.isSelected()){
					
					
					if((intServo_electromotor_position_in_degrees_TX != intServo_electromotor_previous_TX) || !boolSystem_initialized) {
						
						//button_set_position.setSelected(false); //Ovo ne radi to je problem

						boolSystem_initialized = true;
						
						intServo_electromotor_previous_TX = intServo_electromotor_position_in_degrees_TX;
						
						String TX = Integer.toString(intServo_electromotor_position_in_degrees_TX);
						blue_tooth.set_servo_electromotor_position_in_degrees(TX);
					
						String RX = blue_tooth.get_servo_electromotor_position_in_degrees();
						intServo_electromotor_position_in_degrees_RX = Integer.parseInt(RX);
				
						position_label.setText("Servo electromotor current position is " + intServo_electromotor_position_in_degrees_RX + "°.");

					}	
					
					/*else {
						
							input_label.setForeground(Color.ORANGE); 
							input_label.setText("Warning: Position request same as previous!");		
					}*/
					
					button_set_position.setSelected(false);

				}
			}
		});

		frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
              
                blue_tooth.uninitialize_servo_electromotor();
            }
        });
		

		input_label.setBounds(30, 10, 700, 50);
		panel.add(input_label); 
		
		text_field.setBounds(30, 70, 170, 50);
		panel.add(text_field);
		
		button_set_position.setBounds(220, 70, 190, 50);
		panel.add(button_set_position); 
		
		position_label.setBounds(20, 130, 600, 50);
		panel.add(position_label); 
		
		picture_label.setBounds(1, 110, 600, 400);
		panel.add(picture_label); 

		frame.setVisible(true);
	}
}


/*	        			if(intServo_electromotor_position_in_degrees_TX == intServo_electromotor_previous_TX && !boolSystem_initialized){
	        				
	        				input_label.setForeground(Color.YELLOW); //boja errora koji se ispisuje u slucaju da je pogresan input unet u text field
	        				input_label.setText("Warrning: Servo electromotor is already at current position");//eror koji se ispisuje u slucaju da je pogresan input unet u text field
	        				//button_set_position.setVisible(false);//Uklanjanje dugmeta za slanje	
	        			}
	        			
	        			else {
	        				
	        				input_label.setForeground(Color.GREEN); //labela iznad text fieda je zelena sad
	        				input_label.setText("Input is valid!");  //ovo sad pise na njoj
	        			}*/
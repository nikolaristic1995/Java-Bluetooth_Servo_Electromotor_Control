
package bluetooth;

//import java.awt.Color;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

public class HC05{
	
	private String hc05Url = "btspp://98D331408009:1;authenticate=false;encrypt=false;master=false"; //Ovo je URL od BLUETOOTHa
	
	private StreamConnection streamConnection;
	private OutputStream outputStream;
	private InputStream inputStream;
	
	/*public static void main(String[] args){
			
		HC05 bt = new HC05();
		
		bt.initialize_servo_electromotor();
		
		bt.set_servo_electromotor_position_in_degrees("100");
		
		String angle = bt.get_servo_electromotor_position_in_degrees();
		System.out.println("The read data from the terminal = " + angle);
		
		bt.uninitialize_servo_electromotor();
		
	}*/
	
	public HC05(){
		
	}
	
	public void connect() throws Exception {
		
		 streamConnection = (StreamConnection)Connector.open(hc05Url); //konektovao se na bluetooth
		 outputStream = streamConnection.openOutputStream();
		 inputStream = streamConnection.openInputStream();
	}
	
	public void writeToTerminal(String input) throws Exception {
		
		 input = input + '\r'; 
		 outputStream.write(input.getBytes());
		 
		 Thread.sleep(200);
		 
	}
	
	public String ReadFromTerminal() throws Exception{
			
		byte data[] = new byte[7];
		
		Thread.sleep(1000); //ovo treba obrisati
		
		inputStream.read(data);
		
		String data_string = new String(data);
		String extracted_data = data_string.substring(data_string.indexOf("\r") + 1, data_string.indexOf("\n"));
		
		return extracted_data;
	}
	
	public void disconnect() throws Exception {
		
		streamConnection.close();
	}
	
	public boolean initialize_servo_electromotor() {
	
	boolean connected = false;
		
		try {
			
			connect();
			connected = true;
		}
		catch(Exception e) {
			
			System.out.println("Initialize servo exception = " + e);
			connected = false;
		}
		
		return connected;
	}
	
	public void uninitialize_servo_electromotor() {
		
		try {
			
			disconnect();
			outputStream.close();
			inputStream.close();
		}
		catch(Exception e) {
			
			System.out.println("Uninitialize servo exception = " + e);
		}
	}
	
	boolean set_servo_electromotor_position_in_degrees(String degrees){
		
		try {
			
			writeToTerminal(degrees);
			return true;
		}
		catch(Exception e) {
			
			System.out.println("set servo position exception = " + e);
			return false;
		}
	}
	
	public String get_servo_electromotor_position_in_degrees(){
		
		String angle = "Error";
		
		try {
		
			writeToTerminal("G");
				
			angle = ReadFromTerminal();
			angle = angle.replaceAll("\\D+","");
		}
		catch(Exception e) {
			
			System.out.println("get servo position exception = " + e);
		}
		
		return angle;
	}
}

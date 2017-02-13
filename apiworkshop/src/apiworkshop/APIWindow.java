package apiworkshop;
/**
 * @author Jacob Reed
 */
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class APIWindow {

	private JFrame frmWorkshop;
	private JTextField textField;
	private JTextArea textArea;
	private JButton btnNewButton;
	private JLabel getURL;
	private JScrollPane textWindowScrollPane;
	private final String USER_AGENT = "Mozilla/5.0";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					APIWindow window = new APIWindow();
					window.frmWorkshop.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public APIWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWorkshop = new JFrame();
		frmWorkshop.setTitle("Workshop #4");
		frmWorkshop.setBounds(100, 100, 450, 300);
		frmWorkshop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWorkshop.getContentPane().setLayout(null);
		
		// Create window elements
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		btnNewButton = new JButton("Send Request");
		getURL = new JLabel("URL:");
		textWindowScrollPane = new JScrollPane();
		
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					sendGET();
				} catch(Exception e){
					textArea.setText("ERROR:  Unable to send GET request!");
				}
			}
		});
		
		btnNewButton.setBounds(0, 190, 428, 54);
		frmWorkshop.getContentPane().add(btnNewButton);
		
		textWindowScrollPane.setBounds(0, 0, 428, 135);
		frmWorkshop.getContentPane().add(textWindowScrollPane);
		
		textArea.setEditable(false);
		textWindowScrollPane.setViewportView(textArea);
		
		getURL.setFont(new Font("Tahoma", Font.PLAIN, 23));
		getURL.setBounds(10, 136, 88, 54);
		frmWorkshop.getContentPane().add(getURL);
		
		textField = new JTextField();
		textField.setBounds(78, 151, 335, 26);
		frmWorkshop.getContentPane().add(textField);
		textField.setColumns(10);
	}
	
	// Send the GET req. to the API.  This will give us a JSON object to work with.
	private void sendGET() throws Exception{
		String url = textField.getText();
		
		// Create new URL object for creating get request
		URL getURL = new URL(url);
		
		// New HTTP connection
		HttpURLConnection connection = (HttpURLConnection) getURL.openConnection();
		
		// Configure connection
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		connection.setDoOutput(true);
		
		// Get the response code 
		int responseCode = connection.getResponseCode();
		
		// Read in the text from the connection
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		// Close reader
		in.close();

	    // Parse the JSON object
		try{
			textArea.setText(parseJSON(response.toString(), Integer.toString(responseCode)));
		} catch(JSONException e){
			textArea.setText("ERROR: Could not parse JSON!");
		}
	}
	
	// Parses the JSON from the connection.  This makes what we get from the API GET request readable.
	private String parseJSON(String obj, String respCode) throws JSONException{
		// Each part of the JSON object
		String bodyText, author, link;
		
		// Create JSON object to parse
		JSONObject jsonObj = new JSONObject(obj);
		
		// Get the important parts of the object
		bodyText = jsonObj.getString("quoteText");
		author = jsonObj.getString("quoteAuthor");
		link = jsonObj.getString("quoteLink");
		
		return "Response Code: "+respCode+"\n\n"
				+"Quote: \""+bodyText+"\"\n\n"
				+"Author: "+author+"\n\n"
				+"Link: "+link+"\n\n";
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHello implements Runnable {
	private Socket socket;

	public ServerHello(Socket accept) {
		// TODO Auto-generated constructor stub
		this.socket = accept;
	}

	//public dealerCurr dealer = new dealerCurr();
	PrintWriter out;
	OutputStream outputStream;
	BufferedReader in;
	Boolean connect = false;

	public void run() {
		System.out.println("Started from the bottom now we're here");
		try {
			// Connect Player to server
			out = new PrintWriter(socket.getOutputStream(), true);
			outputStream = socket.getOutputStream();
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeConnect() throws IOException {
		outputStream.close();
		out.close();
		in.close();
		socket.close();
	}

	String getString() throws NumberFormatException, IOException {
		char[] buffer = new char[1024];
		String ex = null;
		
		in.read(buffer);
			ex = new String(buffer);
			
		
		System.out.printf("Inside getString function\n");
		return ex;
	}

	public void sendString(String ex) {
		out.println(ex);
	}

}
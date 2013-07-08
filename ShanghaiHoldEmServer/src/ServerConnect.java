import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnect {
	public static void main(String[] args) throws IOException,
			InterruptedException {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(20001);
		} catch (IOException e) {
			System.err.println(e.getMessage());

			System.exit(-1);
		}
		int players = 2;
		ServerHello[] cArray = new ServerHello[players];
		Thread[] threadPool = new Thread[players];

		for (int i = 0; i < players; i++) {
			Socket clientServer = serverSocket.accept();
			System.out.println("Connection number " + (i + 1)
					+ " is connected on port 20001.");
			cArray[i] = new ServerHello(clientServer);
			threadPool[i] = new Thread(cArray[i]);
			threadPool[i].start();

		}

		for (int i = 0; i < players; i++)
			threadPool[i].join();

		System.out.printf("Blah blah blah\n");
		String inputString = cArray[0].getString();
		System.out.println(inputString);
		cArray[1].sendString(inputString);
		inputString = cArray[1].getString();
		System.out.println(inputString);
		cArray[0].sendString(inputString);
		/*inputString = cArray[0].getString();
		System.out.println(inputString);
		cArray[1].sendString(inputString);
		inputString = cArray[1].getString();
		System.out.println(inputString);
		cArray[0].sendString(inputString);*/

		for (int i = 0; i < players; i++) {
			cArray[i].closeConnect();
		}
		serverSocket.close();
	}
}
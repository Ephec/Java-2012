package RESEAU;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.UnknownHostException;

public class Client extends Socket {
	
	public Client(boolean[][] tab){
		
		try {
			Client client = new Client("127.0.0.1",2007);
			OutputStream os = client.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
			//oos.writeObject(tab);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public Client(Proxy proxy) {
		super(proxy);
		
	}

	public Client(SocketImpl impl) throws SocketException {
		super(impl);
		
	}

	public Client(String host, int port) throws UnknownHostException,
			IOException {
		super(host, port);
		
	}

	public Client(InetAddress address, int port) throws IOException {
		super(address, port);
		
	}

	public Client(String host, int port, InetAddress localAddr, int localPort)
			throws IOException {
		super(host, port, localAddr, localPort);
		
	}

	public Client(InetAddress address, int port, InetAddress localAddr,
			int localPort) throws IOException {
		super(address, port, localAddr, localPort);
		
	}

}

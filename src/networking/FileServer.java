package networking;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
public class FileServer extends Thread{

    ServerSocket server;
    int port = 8877;
  
  public  String FILE_TO_SEND = "c:/me.png";  // you may change this

 public FileServer(int SOCKET_PORT)  throws IOException {
      this.port=SOCKET_PORT;
     // this.FILE_TO_SEND=FILE_TO_SEND;
      
       try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
     
  }
  

	
	public void run() {
		while (true) {
			try {
				Socket clientSock = server.accept();
				saveFile(clientSock);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void saveFile(Socket clientSock) throws IOException {
		DataInputStream dis = new DataInputStream(clientSock.getInputStream());
		FileOutputStream fos = new FileOutputStream(dis.readUTF());
		byte[] buffer = new byte[4096];
		
		long filesize = dis.readLong(); // Send file size in separate msg
		int read = 0;
		int totalRead = 0;
		int remaining = (int) filesize;
		while((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
			totalRead += read;
			remaining -= read;
			System.out.println("read " + totalRead + " bytes.");
			fos.write(buffer, 0, read);
		}
		
		fos.close();
		dis.close();
	}
}
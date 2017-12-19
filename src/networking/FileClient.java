package networking;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author tuski-Revolve
 */
public class FileClient {
    
    
    
    	private Socket s;
        private String fileName;
	private long fileLength;
	public FileClient(String host, int port, String file,String fileName,long FileLength) {
		
            this.fileName=fileName;
            this.fileLength=FileLength;
            try {
			s = new Socket(host, port);
			sendFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void sendFile(String file) throws IOException {
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[4096];
		dos.writeUTF(fileName);
                dos.writeLong(fileLength);
		while (fis.read(buffer) > 0) {
			dos.write(buffer);
		}
		
		fis.close();
		dos.close();	
	}
    
    
}
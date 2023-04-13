import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import kr.or.team4.*;

public class FileInput{

	public void Input() {
		  FileInputStream fis = null;
		  BufferedInputStream bis = null;
		  ObjectInputStream in = null;

		  try {
		    fis = new FileInputStream("C:\\Team4\\JavaPrj\\Cinema\\movie2.txt");
		    in = new ObjectInputStream(bis);
		    ArrayList<String> readList = (ArrayList<String>) in.readObject();
		    
		    for (String movielist : readList) {
                System.out.println(movielist);
            }

		  } catch (Exception e) {
		    e.printStackTrace();
		  } finally {
		    try {
		      in.close();
		      fis.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		  }
		}
	}
	
	
	
	

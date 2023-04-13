import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FileIO {

		String path = "D:\\JavaPrj\\IOtest\\"; // 승환
//		String path = "C:\\Users\\KOSA\\Desktop\\JavaPrj-main\\IOtest"; // 나은
//		String path = "C:\\Team4\\IOtest"; // 석진
//		String path = "C:\\Users\\KOSA\\TeamProject\\IOtest\\"; // 창현
		
	// 데이터 불러오기 함수
		public ArrayList loadDataset(String fileName) {

//			String fileName =  (loadingDataset instanceof Theater) ? "Theater" :((loadingDataset instanceof Member) ? "Member" :(loadingDataset instanceof Reservation) ? "Reservation" : "noName");
			
			String filePath = path+fileName+".txt";
			System.out.println("불러오기 경로 : "+filePath);
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			ObjectInputStream in = null;
			
			try {
				fis = new FileInputStream(filePath);
				bis = new BufferedInputStream(fis);
				in = new ObjectInputStream(bis);
				
				ArrayList<Serializable> dataList = null;
				dataList = (ArrayList<Serializable>) in.readObject();
				return dataList;
				
				//원칙적인 처리 
			}catch (FileNotFoundException e) { //FileNotFoundException
				System.out.println("파일이 존재 않습니다.");
			}catch (EOFException e) {
				System.out.println("끝 " + e.getMessage());
			}catch (IOException e3) {
				e3.printStackTrace();
				System.out.println(fileName+"파일을 읽을 수 없습니다.");
			}catch (ClassNotFoundException e) { //ClassNotFoundException
				System.out.println("해당 객체가 존재하지 않습니다.");
			}catch (Exception e) {
				System.out.println("나머지 예외");
			}finally {
				try {
					in.close();
					bis.close();
					fis.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		      
		      return null;

	   }
		
		
		// 데이터 내보내기 함수
	   public void saveDataset(ArrayList savingDataset, String fileName) {
		   
	       FileOutputStream fos = null;
	       BufferedOutputStream bos = null;
	       ObjectOutputStream out=null;

	       try {
	    	   String filePath = path+fileName+".txt";
	    	   System.out.println("저장하기 경로 : "+filePath);
	           fos = new FileOutputStream(filePath);
	           bos = new BufferedOutputStream(fos);
	           out = new ObjectOutputStream(bos);
	         
	       //직렬화
	           for(int i=0; i<savingDataset.size(); i++) {
	        	   out.writeObject(savingDataset); 
	           }
	            
	      } catch (Exception e) {
	    	  System.out.println(e.getMessage());
	    	  
	      } finally {
			  try {
			     out.close();
			     bos.close();
			     fos.close();
			  } catch (Exception e2) {
			     e2.printStackTrace();
			  }
	      }
	      
	   }
	

	
}
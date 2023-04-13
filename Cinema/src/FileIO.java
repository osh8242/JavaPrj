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

	//입력 함수
		public ArrayList<Serializable> Input(String fileName) {
//			String fileName =  (loadingDataset instanceof Theater) ? "Theater" :((loadingDataset instanceof Member) ? "Member" :(loadingDataset instanceof Reservation) ? "Reservation" : "noName");
			
			String filename = "C:\\Users\\KOSA\\Desktop\\JavaPrj-main\\IOtest\\"+fileName+".txt";
			System.out.println("불러오기 경로 : "+filename);
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			ObjectInputStream in = null;
			
			try {
				fis = new FileInputStream(filename);
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
		
		
		// 출력 함수
	   public void Output(List<Serializable> savingDataset, String fileName) {
		   
	       FileOutputStream fos = null;
	       BufferedOutputStream bos = null;
	       ObjectOutputStream out=null;

	       try {
	    	   String filename = "C:\\Users\\KOSA\\Desktop\\JavaPrj-main\\IOtest\\"+fileName+".txt";
	    	   System.out.println("저장하기 경로 : "+filename);
	           fos = new FileOutputStream("C:\\Users\\KOSA\\Desktop\\JavaPrj-main\\IOtest\\"+fileName+".txt");
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
	

	//========================================================================================
	
	 /*

	// Theater입력 함수
	public ArrayList<Theater> Input() {
		String filename = "C:\\Users\\KOSA\\Desktop\\JavaPrj-main\\IOtest\\theaterList.txt";
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream in = null;
		
		try {
			fis = new FileInputStream(filename);
			bis = new BufferedInputStream(fis);
			in = new ObjectInputStream(bis);
			
			ArrayList<Theater> theaterList = null;
			theaterList = (ArrayList<Theater>) in.readObject();
			return theaterList;
			
			//원칙적인 처리 
		}catch (FileNotFoundException e) { //FileNotFoundException
			System.out.println("파일이 존재 않습니다.");
		}catch (EOFException e) {
			System.out.println("끝 " + e.getMessage());
		}catch (IOException e) {
			System.out.println("파일을 읽을 수 없습니다.");
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
	
	
	// 출력 함수
   public void Output(List<Theater> theaterList) {
	   
       FileOutputStream fos = null;
       BufferedOutputStream bos = null;
       ObjectOutputStream out=null;

       try {
    	   
           fos = new FileOutputStream("C:\\Users\\KOSA\\Desktop\\JavaPrj-main\\IOtest\\theaterList.txt");
           bos = new BufferedOutputStream(fos);
           out = new ObjectOutputStream(bos);
         
       //직렬화
           for(int i=0; i<theaterList.size(); i++) {
        	   out.writeObject(theaterList); 
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
   	  
	  */
	 
}
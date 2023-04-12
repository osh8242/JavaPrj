import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
/*
	private String movie;
	private String[][] seat;
	private boolean isImax;
	private int price;
 */

public class FileIO {
	
	// 입력 함수
	public ArrayList<Theater> Input() {
		String filename = "C:\\Users\\KOSA\\Desktop\\JavaPrj-main\\IOtest\\theaterList.txt";
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream in = null;
		
		try {
			fis = new FileInputStream(filename);
			bis = new BufferedInputStream(fis);
			in = new ObjectInputStream(bis);
			//in = new ObjectInputStream( new BufferedInputStream(new FileInputStream(filename)));
			
			//복원 (내가 객체 2개 write 사실을 알아야 이렇게 쓸수 있음)
			/*
			UserInfo r1 = (UserInfo)in.readObject();
			UserInfo r2 = (UserInfo)in.readObject();
			System.out.println(r1.toString());
			System.out.println(r2.toString());
			*/
			ArrayList<Theater> theaterList = null;
			theaterList = (ArrayList) in.readObject();
			for (int i =0; i<theaterList.size(); i++) {				
				String movieTitle = theaterList.get(i).getMovie();
				System.out.printf("%s\t", movieTitle);

			}
			return theaterList;
			
			//원칙적인 처리 
		}catch (FileNotFoundException e) { //FileNotFoundException
			System.out.println("파일이 존재하지 않아요");
		}catch (EOFException e) {
			System.out.println("끝 " + e.getMessage());
		}catch (IOException e) {
			System.out.println("파일을 읽을 수 없어요");
		}catch (ClassNotFoundException e) { //ClassNotFoundException
			System.out.println("해당 객체가 존재하지 않아요");
		}catch (Exception e) {
			System.out.println("나머지 예외");
		}finally {
			try {
				in.close();
				bis.close();
				fis.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	      
	      return null;
	      
	   
	   }
	
	
	// 출력 함수
   public void Output() {
      List<Theater> theaterList = new ArrayList<Theater>();
      
      
      FileOutputStream fos = null;
      BufferedOutputStream bos = null;
      ObjectOutputStream out=null;

		  System.out.println("함수시작");

      try {
          fos = new FileOutputStream("C:\\Users\\KOSA\\Desktop\\JavaPrj-main\\IOtest\\theaterList.txt");
          
          bos = new BufferedOutputStream(fos);
          out = new ObjectOutputStream(bos);
         
//          Theater t1 = new Theater("스즈메", new int[] {3,4}, true);
//          Theater t2 = new Theater("존윅", new int[] {10,10}, false);
         theaterList.add(new Theater("스즈메", new int[] {3,4}, true));
         theaterList.add(new Theater("존윅", new int[] {10,10}, false));
         
       //직렬화
         for(int i=0; i<theaterList.size(); i++) {
        	 out.writeObject(theaterList); 
 		}
		  
		  System.out.println("try끝");
            
      } catch (Exception e) {
    	  System.out.println(e.getMessage());
    	  
      } finally {
         try {
            out.close();
            bos.close();
            fos.close();
  		  System.out.println("finally-try끝");

         } catch (Exception e2) {
            e2.printStackTrace();
         }
      }
      
      
      
   
   }
}
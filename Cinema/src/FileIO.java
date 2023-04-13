import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class FileIO {
	public CinemaSystem cs;

		String path = "D:\\JavaPrj\\IOtest\\"; // 승환
//		String path = "C:\\Users\\KOSA\\Desktop\\JavaPrj-main\\IOtest"; // 나은
//		String path = "C:\\Team4\\IOtest"; // 석진
//		String path = "C:\\Users\\KOSA\\TeamProject\\IOtest\\"; // 창현

	public FileIO(CinemaSystem cinemaSystem) {
		this.cs = cinemaSystem;
	}
	
	// 초기 데이터 셋팅 함수
	public void setDatasets() {
		File file1 = new File(path+"Member.txt");
		File file2 = new File(path+"Reservation.txt");
		File file3 = new File(path+"Theater.txt");
		
		if(!file1.exists() || !file2.exists() || !file3.exists()) {
			System.out.println("데이터 초기화 중 ...");
			// default 데이터
			cs.theaters.add(new Theater("스즈메", new int[] {3,4}, true));
			cs.theaters.add(new Theater("존윅", new int[] {10,10}, false));
			cs.members.add(new Member("010-1234-123", "1234", "oshh","오승환", true));
			cs.members.add(new Member("010-123-4567", "5678", "sjsj","장석진"));

			Theater theater1 = (Theater) cs.theaters.get(0);
			Theater theater2 = (Theater) cs.theaters.get(1);
			User user1 = (User) cs.members.get(0);
			User user2 = (User) cs.members.get(1);
//			cs.reservations.add(new Reservation( user1, theater1, new int[]{3,4}));
//			cs.reservations.add(new Reservation( user2, theater2, new int[]{1,1}));

			// 임시 데이터 파일로 저장
			saveDataset(cs.theaters, "Theater");
			saveDataset(cs.members, "Member");
			saveDataset(cs.reservations, "Reservation");
		}
	}
	
		
	// 데이터 불러오기 함수
	public ArrayList loadDataset(String fileName) {

		String filePath = path + fileName + ".txt";
		System.out.println(" 경로: " + filePath);
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

			// 원칙적인 처리
		} catch (FileNotFoundException e) { // FileNotFoundException
			System.out.println("파일이 존재 않습니다.");
		} catch (EOFException e) {
			String msg = (e.getMessage() == null)? "파일을 모두 불러왔습니다.":"에러";
			System.out.println("... "+ msg);
		} catch (IOException e3) {
			e3.printStackTrace();
			System.out.println(fileName + "파일을 읽을 수 없습니다.");
		} catch (ClassNotFoundException e) { // ClassNotFoundException
			System.out.println("해당 객체가 존재하지 않습니다.");
		} catch (Exception e) {
			System.out.println("나머지 예외");
		} finally {
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
		ObjectOutputStream out = null;

		try {
			String filePath = path + fileName + ".txt";
			System.out.println("저장 경로 : " + filePath);
			fos = new FileOutputStream(filePath);
			bos = new BufferedOutputStream(fos);
			out = new ObjectOutputStream(bos);

			// 직렬화
			for (int i = 0; i < savingDataset.size(); i++) {
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
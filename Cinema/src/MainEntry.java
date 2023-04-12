import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainEntry {

	public static void main(String[] args) {

		CinemaSystem cs = new CinemaSystem();		
//		cs.run();
		
		// nana test code //////////////////////////////
		/* test 1
		FileIO fio = new FileIO();
		fio.Output();
		ArrayList<Theater> theaterList = fio.Input();
		System.out.println(theaterList.get(1).toString());
		 */
		
		/*
		// 임시데이터 생성
		List<Theater> theaterList = new ArrayList<Theater>();
		theaterList.add(new Theater("스즈메", new int[] {3,4}, true));
        theaterList.add(new Theater("존윅", new int[] {10,10}, false));
        // 출력
		FileIO fio = new FileIO();
		fio.Output(theaterList);
		// 입력
		theaterList = fio.Input();
		System.out.println(theaterList.get(0));//.toString());
		 */
		
		List<Serializable> theaterList = new ArrayList<Serializable>();
		theaterList.add(new Theater("스즈메", new int[] {3,4}, true));
        theaterList.add(new Theater("존윅", new int[] {10,10}, false));
        // 출력
		FileIO fio = new FileIO();
		fio.Output(theaterList, "Theater");
		// 입력 
		theaterList = fio.Input(theaterList, "Theater");
		System.out.println(theaterList.get(0));//.toString());

		
		List<Serializable> memberList = new ArrayList<Serializable>();
		//int userPhoneNumber, String userPassword, String userId, String userName
		memberList.add(new Member(0101234123, "1234", "1234","1234"));
		memberList.add(new Member(0101234567, "5678", "5678","5678"));
		// 출력
		fio.Output(memberList, "Member");
		// 입력 
		memberList = fio.Input(memberList, "Member");
		// error
//		System.out.println(memberList.get(0));
		
		
//		List<Serializable> reservationList = new ArrayList<Serializable>();
//		//int reservationNo, User user, Theater theater, int[] seat
//		User user1 = (User) memberList.get(0);
//		Theater theater1 = (Theater) theaterList.get(0);
//		reservationList.add(new Reservation(0101234123, user1, theater1,new int[]{3,4}));
//		reservationList.add(new Reservation(0101231111, user1, theater1,new int[]{3,4}));
//		// 출력
//		fio.Output(reservationList, "Teater");
//		// 입력 
//		reservationList = fio.Input(reservationList, "Teater");
//		System.out.println(reservationList.get(0));
		
		///////////////////////////////////////////////
		
		

	}

}

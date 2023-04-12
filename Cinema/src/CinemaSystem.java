import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CinemaSystem {
	
	private boolean isLoggedIn;
	private ReservationProcess reservationProcess;
	private ArrayList<Theater> theaters;
	private ArrayList<Reservation> reservations; 
	private ArrayList<Member> members;
	private FileIO fileIO;
	private BufferedReader br;	
	
	public void run() {
		
		outer: while(true) {
			firstDisplayPrint();			
			switch(getInputValue()) {
				case 1:{//1.회원 로그인
					//
					//
					//ReservationProcess reservationProcess = new ReservationProcess();
					//ReservationProcess.showReservationMenu(isLoggedIn);
				}
				case 2:{//2.비회원으로 예매
					//ReservationProcess reservationProcess = new ReservationProcess();
					//ReservationProcess.showReservationMenu(isLoggedIn);
					break outer;
					}
				case 3:{//3.회원가입
					//createUser();
					
				}
		
			
			}
		}
		
	}
	
	public int getInputValue() {
		this.br = new BufferedReader(new InputStreamReader(System.in));
		try {
			return Integer.parseInt(br.readLine());
		} catch (Exception e) {
			System.out.println("숫자를 입력해주세요");
			return getInputValue();
		} 	
	}
	
	public String getStringValue() {
		this.br = new BufferedReader(new InputStreamReader(System.in));
		try {
			return br.readLine();
		} catch (Exception e) {
			System.out.println("잘못된 입력입니다.");
			return getStringValue();
		} 	
	}
	
	public void firstDisplayPrint() {
	System.out.println("메뉴 선택 :");
	System.out.println("[1.회원 로그인]\t[2.비회원으로 예매]\t[3.회원가입]");
	System.out.println("입력> ");
	}
	
	public Member login() {
		Member user;
		System.out.print("아이디를 입력해주세요 : ");
		String id = getStringValue();
		for(Member m : members) {
			if(m.getUserId().equals(id)) {
				System.out.print("비밀번호를 입력해주세요 : ");
			}
		}
		return user;
	}
	
	public void showRevenue() {
		int revenueSum = 0;
		for(Reservation r : reservations) {
			//revenueSum += r.getTheater.getPrice();
		}
		System.out.println("총 판매액 : " + revenueSum);
	}
	
	public void showReservations() {
		for(Reservation r : reservations) {
			System.out.println(r.toString());
		}
	}
	
	public void showMembers() {
		for (Member m : members) {
			System.out.println(m.toString());
		}
	}
	
	

}

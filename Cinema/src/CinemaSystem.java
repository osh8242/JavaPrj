import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CinemaSystem {
	
	private boolean isLoggedIn;
	private ReservationProcess reservationProcess;
	private ArrayList<Theater> theaters;
	private ArrayList<Reservation> reservations; 
	private ArrayList<User> members;
	private FileIO fileIO;
	private BufferedReader br;
	public int inputValue;
	
	public void run() {
		
		outer: while(true) {
			firstDisplayPrint();
			getInputValue();
			switch(inputValue) {
				case 1:{//1.회원 로그인
					//login()					
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
	
	public void getInputValue() {
		this.br = new BufferedReader(new InputStreamReader(System.in));
		try {
			this.inputValue = Integer.parseInt(br.readLine());
		} catch (Exception e) {
			System.out.println("숫자를 입력해주세요");
		}		
	}
	
	public void firstDisplayPrint() {
	System.out.println("메뉴 선택 :");
	System.out.println("[1.회원 로그인]\t[2.비회원으로 예매]\t[3.회원가입]");
	System.out.println("입력> ");
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
			//System.out.println(r.toString());
		}
	}
	
	

}

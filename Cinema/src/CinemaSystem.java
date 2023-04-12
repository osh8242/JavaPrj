import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CinemaSystem {
	
	public boolean isLoggedIn;
	private ReservationProcess reservationProcess;
	private ArrayList<Theater> theaters;
	private ArrayList<Reservation> reservations; 
	private ArrayList<Member> members;
	private FileIO fileIO;
	private BufferedReader br;	
	private Member userLoggedIn;
	
	public void run() {		
		
		outer: while(true) {
			firstDisplayPrint();			
			switch(getInputValue()) {
				case 1:{//1.회원 로그인
					if( (userLoggedIn = login()) != null);{
						if(!userLoggedIn.isAdmin()) {
							//ReservationProcess reservationProcess = new ReservationProcess();
							//ReservationProcess.showReservationMenu(isLoggedIn, userLoggedIn);	
						} else {
							showAdminMenu();
						}
						
					} 
					break outer;
				}
				case 2:{//2.비회원으로 예매
					//ReservationProcess reservationProcess = new ReservationProcess();
					//ReservationProcess.showReservationMenu(isLoggedIn, null);
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
	System.out.print("입력> ");
	}
	
	public Member login() {
		System.out.print("아이디를 입력해주세요 : ");
		String id = getStringValue();
		for(Member m : members) {
			if(m.getUserId().equals(id)) {
				System.out.print("비밀번호를 입력해주세요 : ");
				if(m.getUserPassword().equals(getStringValue())) {
					System.out.println("로그인 성공!");
					System.out.println(m.getUserName()+" 님, 환영합니다^^*");
					return m;
				} else {
					System.out.println("비밀번호가 올바르지 않습니다.");
					System.out.println("초기화면으로 돌아가려면 exit을 입력하고,");
					System.out.println("다시 로그인 시도하려면 아무값이나 입력하세요");
					if(getStringValue().equals("exit")) return null;
					else return login();
				}
			}
		}
		System.out.println("해당 아이디가 존재하지 않습니다.");
		return null;
	}
	
	public void showRevenue() {
		int revenueSum = 0;
		for(Reservation r : reservations) {
			revenueSum += r.getTheater().getPrice();
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
	
	public void showAdminMenu() {
		System.out.println("관리자 메뉴 선택 :");
		System.out.println("[1.상영관 관리]\t[2.회원목록 조회]\t[3.회원목록 수정]\t[4.매출액 조회]");
		System.out.println("[5.예매목록 조회]\t[6.파일저장]");
		System.out.print("입력> ");		
	}
	
	public void showTheaterMenu() {
		System.out.println("상영관 관리 메뉴 선택 :");
		System.out.println("[1.상영관 목록 조회]\t[2.상영관 수정]");
		System.out.print("입력> ");
	}
	
	public void showTheaters() {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public ArrayList<Theater> getTheaters() {
		return theaters;
	}

	public void setTheaters(ArrayList<Theater> theaters) {
		this.theaters = theaters;
	}

	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}

	public ArrayList<Member> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<Member> members) {
		this.members = members;
	}
	
	

}

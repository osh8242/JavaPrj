import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CinemaSystem {
	
	public boolean isLoggedIn;
	private ReservationProcess reservationProcess;
	public ArrayList<Theater> theaters;
	public ArrayList<Reservation> reservations; 
	public ArrayList<Member> members;
	private FileIO fileIO;
	private BufferedReader br;	
	public Member userLoggedIn;
	private AdminOS AdminOs;

	
	

	
	public CinemaSystem() {

		this.isLoggedIn = false;
		this.reservationProcess = new ReservationProcess(this);
		
		this.theaters = new ArrayList<Theater>();
		this.members = new ArrayList<Member>();
		this.reservations = new ArrayList<Reservation>();
		
		this.fileIO = new FileIO();
		setDatasets();// 초기데이터 셋팅 : 기능 구현 후 삭제
		getDatasets();// 입력(데이터 불러오기)
//		System.out.println(this.theaters.get(0));
		System.out.println("완료.");
		
		this.br = br;
		this.userLoggedIn = userLoggedIn;
	}

	public void run() {		
		
		while(true) {

			firstDisplayPrint();

			switch(getInputValue()) {
				case 1:{//1.회원 로그인
					if( (userLoggedIn = login()) != null);{ //일반 회원이라면
						if(!userLoggedIn.isAdmin()) {
							//ReservationProcess reservationProcess = new ReservationProcess(this);
							//ReservationProcess.showReservationMenu(isLoggedIn, userLoggedIn);	
						} else { //관리자라면				 			
							 AdminOs.run();
						}
					} //일반회원이라면 
					break;
					} //로그인
				case 2:{//2.비회원으로 예매
					//ReservationProcess reservationProcess = new ReservationProcess(this);
					//ReservationProcess.showReservationMenu(isLoggedIn, null);
					break;
					}
				case 3:{//3.회원가입
					createUser();
					break;
					
				}
		
			
			}
		}
		
	}
	
	
	
	// 임시 데이터 셋팅 함수
	private void setDatasets() {
		System.out.println("초기 데이터를 셋팅중...");
		// default 데이터
		this.theaters.add(new Theater("스즈메", new int[] {3,4}, true));
		this.theaters.add(new Theater("존윅", new int[] {10,10}, false));
		this.members.add(new Member(0101234123, "1234", "oshh","오승환", true));
		this.members.add(new Member(0101234567, "5678", "sjsj","장석진"));
		Theater theater1 = (Theater) this.theaters.get(0);
		Theater theater2 = (Theater) this.theaters.get(1);
		User user1 = (User) this.members.get(0);
		User user2 = (User) this.members.get(1);
		this.reservations.add(new Reservation( user1, theater1, new int[]{3,4}));
		this.reservations.add(new Reservation( user2, theater2, new int[]{1,1}));
        // 임시 데이터 파일로 저장(출력)
		fileIO.saveDataset(this.theaters, "Theater");
		fileIO.saveDataset(this.members, "Member");
		fileIO.saveDataset(this.reservations, "Reservation");		
	}
	// 프로그램 졸료시 데이터 저장하는 함수 만들기
	
	private void getDatasets() {
		System.out.println("데이터를 불러옵니다.");
		// 입력(데이터 불러오기) 
		this.theaters = fileIO.loadDataset( "Theater");
		this.members = fileIO.loadDataset( "Member");
		this.reservations = fileIO.loadDataset( "Reservation");
		//System.out.println(this.theaters.get(0));
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
	
	public void createUser() {
		System.out.println("희망하는 회원ID를 입력하세요");
		String userId = getStringValue();
		System.out.println("비밀번호를 입력하세요");
		String userPassword = getStringValue();
		System.out.println("회원이름을 입력하세요");
		String userName = getStringValue();
		System.out.println("핸드폰번호를 입력하세요");
		int userPhoneNumber = getInputValue();
		members.add(new Member(userPhoneNumber, userPassword, userId, userName));
		System.out.println("회원가입이 완료되었습니다.");
		System.out.println("가입정보 : ");
		System.out.println("ID : "+userId + " / " + "회원이름 : "+userName + " / " + "핸드폰 번호 : " + userPhoneNumber);
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}	

}

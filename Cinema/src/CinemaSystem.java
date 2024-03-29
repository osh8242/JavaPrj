import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CinemaSystem {

	public boolean isLoggedIn;
	public ArrayList<Theater> theaters;
	public ArrayList<Reservation> reservations;
	public ArrayList<Member> members;
	private FileIO fileIO;
	private BufferedReader br;
	public Member userLoggedIn;
	private AdminOS AdminOs;
	public Validation validation;
	ReservationProcess reservationProcess;

	public CinemaSystem() {

		this.isLoggedIn = false;
		this.theaters = new ArrayList<>();
		this.members = new ArrayList<>();
		this.reservations = new ArrayList<>();
		this.AdminOs = new AdminOS(this);
		this.userLoggedIn = null;
		this.fileIO = new FileIO(this);
		this.reservationProcess = new ReservationProcess(this);
		this.validation = new Validation();

		fileIO.setDatasets();// 초기데이터 셋팅 : 데이터파일 최초 생성시에만 실행
		getDatasets();// 입력(데이터 불러오기)
		System.out.println("데이터 불러오기완료. ");
		System.out.println();
	}

	public void run() {
		while (true) {
			firstDisplayPrint();
			switch (getInputValue()) {
			case 1: {// 1.회원 로그인 / 회원예매하기
				userLoggedIn = login();
				if (userLoggedIn == null)
					break;
				if (!userLoggedIn.isAdmin()) {
					reservationProcess.showReservationMenu(isLoggedIn);
				} else { // 관리자라면
					AdminOs = new AdminOS(this);
					AdminOs.run();
				}
				isLoggedIn = !isLoggedIn;
				userLoggedIn = null;
				break;
			} // 로그인
			case 2: {// 2.비회원으로 예매
				reservationProcess.showReservationMenu(isLoggedIn);
				break;
			}
			case 3: {// 3.회원가입
				createUser();
				break;
			}
			}// swtich
		} // while
	}// run()

	// 데이터 저장하는 함수
	public void saveDatasets(ArrayList savingDataset, String fileName) {
		System.out.println(fileName + " 파일을 저장중...");
		fileIO.saveDataset(savingDataset, fileName);
		System.out.println("저장완료");
	}

	private void getDatasets() {
		System.out.println("파일을 불러옵니다.");
		this.theaters = fileIO.loadDataset("Theater");
		this.members = fileIO.loadDataset("Member");
		this.reservations = fileIO.loadDataset("Reservation");
		if (this.reservations != null) {
			String temp = this.reservations.get(this.reservations.size() - 1).getReservationNo();
			temp = temp.substring(temp.indexOf("-") + 1);
			this.reservations.get(0).setCount(Integer.parseInt(temp));
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

	private void firstDisplayPrint() {
		System.out.println("메뉴 선택 :");
		if (!isLoggedIn)
			System.out.println("[1.회원 로그인]   [2.비회원으로 예매]   [3.회원가입]");
		System.out.print("입력> ");
	}

	private Member login() {
		System.out.print("아이디를 입력해주세요 : ");
		String id = getStringValue();
		for (Member m : members) {
			if (m.getUserId().equals(id)) {
				System.out.print("비밀번호를 입력해주세요 : ");
				if (m.getUserPassword().equals(getStringValue())) {
					System.out.println("로그인 성공!");
					System.out.println(m.getUserName() + " 님, 환영합니다^^*");
					isLoggedIn = true;
					return m;
				} else {
					System.out.println("비밀번호가 올바르지 않습니다.");
					System.out.println("초기화면으로 돌아가려면 exit을 입력하고,");
					System.out.println("다시 로그인 시도하려면 아무값이나 입력하세요");
					if (getStringValue().equals("exit"))
						return null;
					else
						return login();
				}
			}
		}
		System.out.println("해당 아이디가 존재하지 않습니다.");
		return null;
	}

	private void createUser() {

		String userId;
		String userPassword;
		String userName;
		String userPhoneNumber;

		while (true) {
			System.out.println("희망하는 회원ID를 입력하세요.(시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5 ~ 12자 이하)");
			userId = getStringValue();
			if (validation.isValidId(userId)) {
				for (Member m : members) {
					if (userId.equals(m.getUserId())) {
						System.out.println("이미 사용중인 아이디입니다");
						createUser();
					}
				}
				break;
			} else {
				System.out.println("조건에 부합하지 않습니다.");
			}
		}

		while (true) {
			System.out.println("비밀번호를 입력하세요.(영문, 특수문자, 숫자 포함 8자 이상)");
			userPassword = getStringValue();
			if (validation.isValidPassword(userPassword)) {
				break;
			} else {
				System.out.println("잘못입력하셨습니다.");
			}
		}

		while (true) {
			System.out.println("회원이름을 입력하세요.(한글만)");
			userName = getStringValue();
			if (validation.isValidName(userName)) {
				break;
			} else {
				System.out.println("잘못입력하셨습니다.");
			}
		}

		while (true) {
			System.out.println("핸드폰번호를 입력하세요. (-입력하세요)");
			userPhoneNumber = getStringValue();
			if (validation.isValidPhonenumber(userPhoneNumber)) {
				break;
			} else {
				System.out.println("잘못입력하셨습니다.");
			}
		}
		members.add(new Member(userPhoneNumber, userPassword, userId, userName));
		System.out.println("회원가입이 완료되었습니다.");
		System.out.println("가입정보 : ");
		System.out.println("ID : " + userId + " / " + "회원이름 : " + userName + " / " + "핸드폰 번호 : " + userPhoneNumber);
	}

}

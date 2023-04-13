import java.util.Arrays;

public class ReservationProcess {

    private CinemaSystem cs;
    private Guest guest;
    private Validation validation = new Validation();


    public ReservationProcess(CinemaSystem cs) {
        this.cs = cs;
    }

    public void showReservationMenu(boolean isLoggedIn) {
        System.out.println();
        System.out.println("원하는 메뉴를 입력하세요.");
        System.out.println("0. 초기화면");
        System.out.println("1. 상영정보 조회");
        System.out.println("2. 영화예매");
        System.out.println("3. 예매취소");
        System.out.println("4. 예매확인");
        if (isLoggedIn) {
            System.out.println("5. 적립포인트 확인");
        }

        switch (cs.getInputValue())

        {
            case 0:
                return;
            case 1:
                showMovieList();
                showReservationMenu(isLoggedIn);
                break;
            case 2:
                createReservation(isLoggedIn);
                break;
            case 3:
                cancelReservation(isLoggedIn);
                break;
            case 4:
                confirmReservation();
                showReservationMenu(isLoggedIn);
                break;
            case 5:
                if (isLoggedIn) {
                    showUserPoint();
                    break;
                } else {
                    break;
                }

        }
    }

    private void showMovieList() {
        for (int i = 0; i < cs.theaters.size(); i++) {
            System.out.println("상영관 : "  +  (i + 1) + "관, 영화제목 : " + cs.theaters.get(i).getMovie() + ", 영화가격 : " + cs.theaters.get(i).getPrice());
        }
    }

    private void createReservation(boolean isLoggedIn) {
        System.out.println("현재 상영중인 영화입니다.");
        System.out.println();
        for (int i = 0; i < cs.theaters.size(); i++) {
            System.out.println("[" + (i + 1) + "] 영화제목 : " + cs.theaters.get(i).getMovie() + ", 영화가격 : "
                    + cs.theaters.get(i).getPrice());
        }
        System.out.println("예매하실 영화의 번호를 입력하세요.");
        int movieNum = cs.getInputValue();
        while (!isLoggedIn) {
            System.out.println("[비회원용] 설정하실 비밀번호를 입력해주세요.(숫자 6자리)");
            String password = cs.getStringValue();

            if (validation.isValidGuestPassword(password)) {
                System.out.println("[비회원용] 설정하실 비밀번호를 한번 더 입력해주세요.");
                String password2 = cs.getStringValue();

                if (!password.equals(password2)) {
                    System.out.println("[비회원용] 비밀번호가 다릅니다.");
                }

                if (password.equals(password2)) {
                    System.out.println("[비회원용] 비밀번호가 설정되었습니다.");
                    guest = new Guest(password);

                }
                break;
            } else {
                System.out.println("잘못된 입력입니다.");
            }

        }

        if (movieNum - 1 <= cs.theaters.size()) {
            System.out.println("선택하신 영화는 " + cs.theaters.get(movieNum - 1).getMovie() + "입니다.");
            for (int j = 0; j < cs.theaters.get(movieNum - 1).getSeat().length; j++) {
                for (int k = 0; k < cs.theaters.get(movieNum - 1).getSeat()[j].length; k++) {
                    int[] seat = new int[] { j + 1, k + 1 };

                    // 예약되지 않은 좌석 판별
                    boolean isReserved = false;
                    if (cs.reservations != null) {

                        for (Reservation reservation : cs.reservations) {
                            if (reservation.getTheater().getMovie().equals(cs.theaters.get(movieNum - 1).getMovie())
                                    && Arrays.equals(reservation.getSeat(), seat)) {
                                isReserved = true;
                                break;
                            }
                        }
                        if (isReserved) {
                            System.out.print("[예매불가]");
                        } else {
                            System.out.printf("[%d-%d]", seat[0], seat[1]);
                        }
                    }
                }
                System.out.println();
            }
            System.out.println("예매하실 좌석을 선택해주세요.");
            System.out.println("입력예시) 1-1");

            String seat = cs.getStringValue();
            String[] inputStrValue = seat.split("-");
            int[] inputIntValue = new int[] { Integer.parseInt(inputStrValue[0]), Integer.parseInt(inputStrValue[1]) };

            boolean isSeatAvailable = true;

            for (Reservation reservation : cs.reservations) {
                if (reservation.getTheater().getMovie().equals(cs.theaters.get(movieNum - 1).getMovie())
                        && Arrays.equals(reservation.getSeat(), inputIntValue)) {
                    System.out.println("해당 좌석은 예매가 불가능합니다");
                    isSeatAvailable = false;
                    break;
                }
            }

            if (isSeatAvailable) {
                if (isLoggedIn) {
                    Reservation arr = new Reservation(cs.userLoggedIn, cs.theaters.get(movieNum - 1), inputIntValue);
                    cs.reservations.add(arr);
                    cs.userLoggedIn.setUserPoint(
                            cs.userLoggedIn.getUserPoint() + (int) (cs.theaters.get(movieNum - 1).getPrice() * 0.1));
                    System.out.println("예매가 완료되었습니다.");
                    System.out.println("고객님의 예약번호는 "
                            + cs.reservations.get(cs.reservations.size() - 1).getReservationNo() + "입니다.");
                    System.out.println("적립된 포인트는 " + (int) (cs.theaters.get(movieNum - 1).getPrice() * 0.1) + ", 총 포인트는 "
                            + cs.userLoggedIn.getUserPoint() + " 입니다.");
                }
                if (!isLoggedIn) {
                    Reservation arr = new Reservation(guest, cs.theaters.get(movieNum - 1), inputIntValue);
                    cs.reservations.add(arr);
                    System.out.println("예매가 완료되었습니다.");
                    System.out.println("고객님의 예약번호는 "
                            + cs.reservations.get(cs.reservations.size() - 1).getReservationNo() + "입니다.");
                }
            }
        } else {
            System.out.println("올바른 영화를 입력하세요.");
        }
    }

    private void confirmReservation() {

        System.out.println("예약번호를 입력해주세요.");
        String reservationNo = cs.getStringValue();
        boolean match = false;
        for (int i = 0; i < cs.reservations.size(); i++) {
            if (reservationNo.equals(cs.reservations.get(i).getReservationNo())) {
                System.out.println("예매하신 영화는 " + cs.reservations.get(i).getTheater().getMovie() + "입니다.");
                System.out.println("좌석은 [" + cs.reservations.get(i).getSeat()[0] + " - "
                        + cs.reservations.get(i).getSeat()[1] + "] 입니다.");
                match = true;
                break;
            }
        }
        if (!match) {
            System.out.println("해당 예약 번호로 예매한 내역이 없습니다.");
        }
    }

    private void cancelReservation(boolean isLoggedIn) {

        System.out.println("예약번호를 입력해주세요.");
        String reservationNo = cs.getStringValue();
        boolean bb = false;
        for (int i = 0; i < cs.reservations.size(); i++) {
            if (reservationNo.equals(cs.reservations.get(i).getReservationNo())) {
                System.out.println("예약을 취소하시려면 비밀번호를 입력해주세요.");
                String password = cs.getStringValue();
                bb = true;
                if (isLoggedIn) {
                    if (password.equals(cs.userLoggedIn.getUserPassword())) {
                        cs.userLoggedIn.setUserPoint(cs.userLoggedIn.getUserPoint()
                                - (int) (cs.reservations.get(i).getTheater().getPrice() * 0.1));
                        cs.reservations.remove(i);

                        System.out.println("예매가 취소되었습니다.");
                    } else {
                        System.out.println("비밀번호가 틀렸습니다.");
                    }
                }
                if (!isLoggedIn) {
                    if (password.equals(cs.reservations.get(i).getUser().getUserPassword())) {
                        cs.reservations.remove(i);
                        System.out.println("예매가 취소되었습니다.");
                    } else {
                        System.out.println("비밀번호가 틀렸습니다.");
                    }
                }
            }

        }
        if (!bb) {
            System.out.println("예매하신 내역이 없습니다. 예약번호를 확인해주세요.");
        }
    }

    private void showUserPoint() {
        System.out.println("현재 포인트는 " + cs.userLoggedIn.getUserPoint() + "입니다.");
    }

}
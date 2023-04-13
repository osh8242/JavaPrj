import java.util.ArrayList;
import java.util.Arrays;

public class ReservationProcess {
   private CinemaSystem cinemaSystem;
   private Reservation reservation;
   private Member member;
   private static int num = 0;

   private ArrayList<Theater> theaters = cinemaSystem.getTheaters();
   private ArrayList<Reservation> reservations = cinemaSystem.getReservations();

   public ReservationProcess(CinemaSystem cinemaSystem, Reservation reservation, Member member) {
      this.cinemaSystem = cinemaSystem;
      this.reservation = reservation;
      this.member = member;
   }

   public void showReservationMenu(boolean isLoggedIn) {
      System.out.println("원하는 메뉴를 입력하세요.");
      System.out.println("1. 상영정보 조회");
      System.out.println("2. 영화예매");
      System.out.println("3. 예매취소");
      System.out.println("4. 예매확인");

      if (isLoggedIn) {
         System.out.println("5. 적립포인트 확인");
      }

      switch (cinemaSystem.getInputValue())

      {
      case 1:
         showMovieList();
         break;
      case 2:
         createReservation(isLoggedIn);
         break;
      case 3:
         confirmReservation();
         break;
      case 4:
         cancelReservation(isLoggedIn);
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
//      fileIO.Output();
   }

   private void createReservation(boolean isLoggedIn) {

      System.out.println("현재 상영중인 영화입니다.");
      System.out.println();
      for (int i = 0; i < theaters.size(); i++) {
         System.out.println("영화제목 : " + theaters.get(i).getMovie() + ", 영화가격 : " + theaters.get(i).getPrice());
      }

      System.out.println("예매하실 영화를 입력하세요.");
      String movieName = Integer.toString(cinemaSystem.getInputValue());

      for (int i = 0; i < theaters.size(); i++) {
         if (movieName.equals(theaters.get(i).getMovie())) {
            System.out.println("선택하신 영화는 " + theaters.get(i).getMovie() + "입니다.");
            for (int j = 0; j < theaters.get(i).getSeat().length; j++) {
               for (int k = 0; k < theaters.get(i).getSeat()[j].length; k++) {
                  int[] seat = new int[] { j + 1, k + 1 };

                  // 예약되지 않은 좌석 판별
                  boolean isReserved = false;
                  for (Reservation reservation : reservations) {
                     if (reservation.getTheater().getMovie().equals(theaters.get(i).getMovie())
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
               System.out.println();
            }
            System.out.println("예매하실 좌석을 선택해주세요.");
            System.out.println("입력예시) 1-1");

            String seat = Integer.toString(cinemaSystem.getInputValue());
            String[] inputStrValue = seat.split("-");
            int[] inputIntValue = new int[] { Integer.parseInt(inputStrValue[0]),
                  Integer.parseInt(inputStrValue[1]) };

            boolean isSeatAvailable = true;

            for (Reservation reservation : reservations) {
               if (reservation.getTheater().getMovie().equals(theaters.get(i).getMovie())
                     && Arrays.equals(reservation.getSeat(), inputIntValue)) {
                  System.out.println("해당 좌석은 예매가 불가능합니다");
                  isSeatAvailable = false;
                  break;
               }
            }

            if (isSeatAvailable) {
               num++;
               reservation.setReservationNo(num);
               reservation.setTheater(theaters.get(i));
               reservation.setUser(member);
               reservation.setSeat(inputIntValue);
               member.setUserPoint(member.getUserPoint() + (int) (theaters.get(i).getPrice() * 0.1));
               System.out.println("예매가 완료되었습니다.");
               System.out.println("고객님의 예약번호는 " + num + "입니다.");
               System.out.println("적립된 포인트는 " + (int) (theaters.get(i).getPrice() * 0.1) + ", 총 포인트는 "
                     + member.getUserPoint() + " 입니다.");

            }
         } else {
            System.out.println("올바른 영화를 입력하세요.");
         }
      }

   }

   private void confirmReservation() {

      System.out.println("예약번호를 입력해주세요.");
      int reservationNo = cinemaSystem.getInputValue();
      boolean match = false;
      for (int i = 0; i < reservations.size(); i++) {
         if (reservationNo == reservations.get(i).getReservationNo()) {
            System.out.println("예매하신 영화는 " + reservations.get(i).getTheater().getMovie() + "입니다.");
            System.out.println(
                  "좌석은 [" + reservations.get(i).getSeat()[0] + " - " + reservations.get(i).getSeat()[1] + "입니다.");
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
      int reservationNo = cinemaSystem.getInputValue();

      for (int i = 0; i < reservations.size(); i++) {
         if (reservationNo == reservations.get(i).getReservationNo()) {
            System.out.println("예약을 취소하시려면 비밀번호를 입력해주세요.");
            String userPassword = Integer.toString(cinemaSystem.getInputValue());
            if (userPassword == member.getUserPassword()) {
               reservations.remove(i);
               System.out.println("예매가 취소되었습니다.");
            } else {
               System.out.println("비밀번호가 틀렸습니다.");
            }
         } else {
            System.out.println("예매하신 내역이 없습니다. 예약번호를 확인해주세요.");
         }
      }
   }

   private void showUserPoint() {
      System.out.println("비밀번호를 입력해주세요.");
      String userPassword = Integer.toString(cinemaSystem.getInputValue());
      if (userPassword == member.getUserPassword()) {
         System.out.println("현재 포인트는 " + member.getUserPoint() + "입니다.");
      }

   }

}


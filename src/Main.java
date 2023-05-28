import model.entities.Reservation;
import model.exceptions.DomainException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        exceptions();
//        dates();
        files();

    }

    public static void exceptions() {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            System.out.print("Room number: ");
            int number = sc.nextInt();

            System.out.print("Check-in date (dd/MM/yyyy): ");
            Date checkIn = sdf.parse(sc.next());

            System.out.print("Check-out date (dd/MM/yyyy): ");
            Date checkOut = sdf.parse(sc.next());

            Reservation reservation = new Reservation(number, checkIn, checkOut);
            System.out.println("Reservation: " + reservation);

            System.out.println("Enter data to update the reservation");
            System.out.print("Check-in date (dd/MM/yyyy): ");
            checkIn = sdf.parse(sc.next());

            System.out.print("Check-out date (dd/MM/yyyy): ");
            checkOut = sdf.parse(sc.next());

            reservation.updateDates(checkIn, checkOut);
            System.out.println("Reservation: " + reservation);
        } catch (ParseException e) {
            System.out.println("Invalid date format");
        } catch (DomainException e) {
            System.out.println("Error in reservation: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Unexpected error");
        }

        sc.close();
    }

    public static void dates() {
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter fmt3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());

        // current date-time
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instant = Instant.now(); // GMT timezone

        // parsing from ISO string
        LocalDate localDate1 = LocalDate.parse("2022-07-20");
        LocalDateTime localDateTime1 = LocalDateTime.parse("2022-07-20T01:30:23");
        Instant instant1 = Instant.parse("2022-07-20T01:30:23Z");
        Instant instant2 = Instant.parse("2022-07-20T01:30:23-03:00");

        // parsing from custom format
        LocalDate localDate2 = LocalDate.parse("20/07/2022", fmt1);
        LocalDateTime localDateTime2 = LocalDateTime.parse("20/07/2022 01:30",fmt2);

        // from year, month and day
        LocalDate localDate3 = LocalDate.of(2022, 7, 20);
        LocalDateTime localDateTime3 = LocalDateTime.of(2022, 7, 20,1,30);

        // formatting to string
        String localDateToFmt1 = localDate3.format(fmt1);
        String localDateTimeToFmt2 = localDateTime3.format(fmt2);
        String instantToFmt3 = fmt3.format(instant1);

        // converting instant to LocalDate
        LocalDate instantToLocalDate = LocalDate.ofInstant(instant1, ZoneId.of("Portugal"));
        LocalDateTime instantToLocalDateTime = LocalDateTime.ofInstant(instant1, ZoneId.of("Portugal"));

        // getting info
        int dayOfMonth = instantToLocalDate.getDayOfMonth();
        int month = instantToLocalDate.getMonthValue();
        int year = instantToLocalDate.getYear();

        // changing date-time
        LocalDate pastWeekLocalDate = localDate3.minusDays(7);
        LocalDate nextWeekLocalDate = localDate3.plusDays(7);
        LocalDateTime nextHourLocalDateTime = localDateTime3.plusHours(1);
        Instant pastWeekInstant = instant1.minus(7, ChronoUnit.DAYS);

        // LocalDate to LocalDateTime
        LocalDateTime d1 = pastWeekLocalDate.atStartOfDay();
        LocalDateTime d2 = localDate3.atTime(1, 30);

        Duration t1 = Duration.between(localDateTime3, nextHourLocalDateTime);
        Duration t2 = Duration.between(d1, d2);
        Duration t3 = Duration.between(pastWeekInstant,instant1);

        System.out.println(t3.toDays());
    }

    public static void files() {
//        File file = new File("./in.txt");
//        Scanner sc = null;
//
//        try {
//            sc = new Scanner(file);
//            while (sc.hasNextLine()) {
//                System.out.println(sc.nextLine());
//            }
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        } finally {
//            if (sc != null) {
//                sc.close();
//            }
//        }

//        String path = "./in.txt";
//
//        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//            String line = br.readLine();
//            while (line != null) {
//                System.out.println(line);
//                line = br.readLine();
//            }
//
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }

//        String[] lines = new String[] {"Good morning", "Good afternoon","Good night"};
//
//        String path = "./out.txt";
//
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path,true))) {
//            for (String line : lines) {
//                bw.write(line);
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        File path = new File("./");

        File[] folders = path.listFiles(File::isDirectory);

        File[] files = path.listFiles(File::isFile);
        Arrays.stream(files).forEach(System.out::println);

//        new File("./" + "newDir").mkdir();


    }
}
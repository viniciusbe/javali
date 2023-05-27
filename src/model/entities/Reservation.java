package model.entities;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;

    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException {
        checkDatesValidity(checkIn,checkOut);

        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public long duration() {
        long diff = checkOut.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updateDates(Date checkIn, Date checkOut) throws DomainException {
        checkDatesValidity(checkIn,checkOut);

        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    private void checkDatesValidity(Date checkIn, Date checkOut) throws DomainException {
        Date now = new Date();

        if (checkIn.before(now) || checkOut.before(now)) {
            throw new DomainException("Reservation dates must be future");
        }
        if (checkIn.after(checkOut)) {
            throw new DomainException("Check-out must be after check-in date");
        }
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + ", check-in: " + sdf.format(checkIn) + ", check-out: " + sdf.format(checkOut) + ", " + duration() + " nights";
    }
}

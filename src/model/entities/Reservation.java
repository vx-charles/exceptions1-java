package model.entities;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // Usado o tratamento de exceção dentro do constructor, e quando fazer o "new", o constructor fará a tratativa antecipadamente.
    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException {
        // Programação defensiva - tratar as exceções no começo dos métodoss.
        if(!checkOut.after(checkIn)) {
            throw new DomainException("Error in reservation: Check-out date must be after check-in date");
        }

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
        long diff = checkOut.getTime() - checkIn.getTime(); // devolve a diferença de tempo em millisegundos.
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); // converte o milisegundos para dias.
    }

    // método agora lança uma exeção tratada pelo "throws DomainException".
    public void updateDates(Date checkIn, Date checkOut) throws DomainException {
        Date now = new Date();

        if(checkIn.before(now) || checkOut.before(now)) {
            // IllegalArgumentException - exceção do Java, usado quando os argumentos do método são inválidos, no caso o erro verificado nos valores do argumento passado no IF.
            throw new DomainException("Error in reservation: Reservation dates for update must be future dates");
        }

        if(!checkOut.after(checkIn)) {
            throw new DomainException("Error in reservation: Check-out date must be after check-in date");
        }

        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Room "
            + roomNumber
            + ", check-in: "
            + sdf.format(checkIn)
            + ", check-out: "
            + sdf.format(checkOut)
            + ", "
            + duration()
            + " nights";
    }
}

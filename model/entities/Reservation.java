package excecoes.exceptions1.model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	// Dado estático para não ser um objeto novo para cada objeto instanciado:
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
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
	
	// O cálculo com datas através do método "getTime()retorna um valor do tipo long, por isso o valor
	// de retorno deste método também ser do tipo long:
	public long duration() {
		// A diferença será trazida em milisegundos:
		long diff = checkOut.getTime() - checkIn.getTime();
		// TimeUnit é um Enum com operações, aqui, convertendo "diff" de milisegundos para dias:
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	// Tratamento de erro delegado à classe com retorno de String (ainda é ruim, mas melhor do que fazer
	// isso na aplicação principal:
	public String updateDates(Date checkIn, Date checkOut) {
		
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			return "Reservation dates for update must be future dates";
		}
		if (!checkOut.after(checkIn)) {
			return "Check-out date must be after check-in date";
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		
		// Retorna nulo se não houve erros:
		return null;
	}
	
	@Override
	public String toString() {
		return "Room "
				+ getRoomNumber()
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";
	}
	
}

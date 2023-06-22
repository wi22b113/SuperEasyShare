package SuperEasyShare.entities;

import SuperEasyShare.provided.Car;
import SuperEasyShare.provided.Customer;
import SuperEasyShare.provided.DateTime;
import SuperEasyShare.provided.TripStatus;

import java.util.Date;

/**
 * A trip in the car sharing system.<br>
 * 
 * A trip collects information about time, customer and car involved.
 * It also contains a rate responsible for calculation of the total amount
 * charged for
 * a trip.
 * 
 * Some operations' behavior depends on this trip's
 * {@link SuperEasyShare.provided.TripStatus}.
 * 
 */
public class Trip {

	private Car car;
	private double distance;
	private DateTime endTime;
	private Rate rate;
	private Customer renter;
	private DateTime startTime;
	private TripStatus status;

	public Trip(Car car, Customer renter, Rate rate) {
		this.car = car;
		this.rate = rate;
		this.renter = renter;
	}

	public Trip(Car car, Customer renter, Rate rate, DateTime startTime) {
		this.car = car;
		this.rate = rate;
		this.renter = renter;
		this.startTime = startTime;
		this.status = TripStatus.STARTED;
	}

	public Trip(Car car, Customer renter, Rate rate, DateTime startTime, DateTime endTime, double distance){
		this.car = car;
		this.renter = renter;
		this.rate = rate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.distance = distance;
	}

	public Trip(Car car, double distance, DateTime endTime, Rate rate, Customer renter, DateTime startTime, TripStatus status) {
		this.car = new Car(car);
		this.distance = distance;
		this.endTime = new DateTime(endTime);
		this.rate = rate;
		this.renter = new Customer(renter);
		this.startTime = new DateTime(startTime);
		this.status = status;
	}

	public Trip start(DateTime startTime){
		if (startTime != null && status != TripStatus.STARTED && status != TripStatus.COMPLETED){
			this.startTime = startTime;
			status = TripStatus.STARTED;
			return this;
		}
		return this;
	}

	public Trip end(DateTime endTime, double distance){
		if (endTime != null && status == TripStatus.STARTED){
			this.endTime = endTime;
			status = TripStatus.COMPLETED;
			return this;
		}else System.err.println("Status cannot be ended");
		return this;

	}

	public final int total(){
		return rate.total(this);
	}

	@Override
	public String toString() {
		return "Trip{" +
				"car=" + car +
				", distance=" + distance +
				", endTime=" + endTime +
				", rate=" + rate +
				", renter=" + renter +
				", startTime=" + startTime +
				", status=" + status +
				'}';
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public DateTime getEndTime() {
		return endTime;
	}

	public double getDistance() {
		return distance;
	}

	public TripStatus getStatus() {
		return status;
	}

	public Rate getRate() {
		return rate;
	}


	/**
	 * The duration of this trip in seconds.<br>
	 * 
	 * 
	 * 
	 * @ProgrammingProblem.Hint use {@link DateTime#diff(DateTime)}
	 * @return the difference in seconds if this trip is completed, zero otherwise
	 */
	public int duration() {
		if (status == TripStatus.COMPLETED)
			return (int) (startTime.diff(endTime));

		return 0;
	}
}

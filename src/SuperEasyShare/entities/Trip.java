package SuperEasyShare.entities;

import SuperEasyShare.provided.Car;
import SuperEasyShare.provided.Customer;
import SuperEasyShare.provided.DateTime;
import SuperEasyShare.provided.TripStatus;

import java.util.Date;

import static SuperEasyShare.provided.TripStatus.*;

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
	private TripStatus status = CREATED;


public Trip(Car car, Customer renter, Rate rate) {
	if(car!=null){
		this.car = car;
	}else{
		this.car = new Car();
	}
	if(renter!=null){
		this.renter = renter;
	}else{
		this.renter = new Customer("unknown");
	}
	if(rate!=null){
		this.rate = rate;
	}else{
		this.rate = new PerMinuteRate(1);
	}
}


	public Trip(Car car, Customer renter, Rate rate, DateTime startTime) {
		this(car, renter, rate);
		if(startTime.diff(new DateTime(2023,01,01,00,00))>=0){
			this.startTime = startTime;
			this.status = STARTED;
		}
	}

	public Trip(Car car, Customer renter, Rate rate, DateTime startTime, DateTime endTime, double distance) {
		this(car, renter, rate, startTime);

		if(endTime.diff(startTime)>0){
			this.endTime = endTime;
			this.status = COMPLETED;
		}
		if(distance>0){
			this.distance = distance;
		}
	}
	public Trip(Trip tr) {
		this.car = new Car(tr.car);
		this.renter = new Customer(tr.renter);
		this.rate = tr.rate;
		this.startTime = new DateTime(tr.startTime);
		this.endTime = new DateTime(tr.endTime);
		this.status = tr.status;
		this.distance = distance;
	}


	public Trip start(DateTime startTime){
		if (startTime != null && status != STARTED && status != COMPLETED){
			this.startTime = startTime;
			status = STARTED;
			return this;
		}
		return this;
	}

	public Trip end(DateTime endTime, double distance){
		if (endTime != null && status == TripStatus.STARTED){
			this.endTime = endTime;
			status = TripStatus.COMPLETED;
			this.distance = distance;
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
		if (status == COMPLETED)
			return (int) (startTime.diff(endTime));

		return 0;
	}
}

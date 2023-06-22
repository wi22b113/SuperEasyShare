package SuperEasyShare.app;

import SuperEasyShare.entities.*;
import SuperEasyShare.provided.Car;
import SuperEasyShare.provided.Customer;
import SuperEasyShare.provided.DateTime;


/**
 * A demo app.
 */
public class Main {

	/**
	 * Create some test data.
	 */
	private static Car[] cars =
			{new Car("Mercedes", "450 SEL 6.9", "W-I8NY"), //
					new Car("Volvo", "960", "W-I8LA"), //
					new Car("Volvo", "850", "W-ACAB"), //
					new Car("Volvo", "760", "W-LOL"), //
					new Car("Chevrolet", "G30", "W-JAVA RULEZ") //
};
	private static Customer[] customers = {
			new Customer("John Doe"), //
			new Customer("Jane Doe")//
	};

	private static Rate[] rates =
			{new LongTermRate(12000, 3000), //
					new LongTermRate(15000, 3000), //
					new LongTermRate(25000, 5000), //
					new LongTermRate(9000, 100), //
					new PerMinuteRate(40), //
					new PerMinuteRate(27), //
					new PerMinuteRate(75), //
					new PerMinuteRate(23) //
};
	private static Trip[] trips = {
			new Trip(cars[2], customers[0], rates[0]) //
			, //

			new Trip(cars[3], customers[0], rates[1]). //
					start( new DateTime(2021, 6, 23, 15, 17)) //
			, //

			new Trip(cars[0], customers[0], rates[2], //
					new DateTime(2021, 1, 1, 0, 0))//
			, //

			new Trip(cars[4], customers[0], rates[3]). //
					start(new DateTime(2021, 1, 1, 0, 0)). //
					end( new DateTime(2021, 1, 1, 0, 23), 10.2)//
			, //

			new Trip(cars[1], customers[0], rates[4], //
					new DateTime(2021, 1, 1, 15, 23), //
					new DateTime(2021, 1, 3, 15, 17), 5.2) //
			, //

			new Trip(cars[0], customers[1], rates[5], //
					new DateTime(2021, 05, 23, 11, 15)) //
			, //

			new Trip(cars[3], customers[0], rates[6]), //

			new Trip(cars[4], customers[0], rates[3]).start(new DateTime(2021, 1, 2, 3, 12)).end(new DateTime(2021, 1, 2, 3, 23), 10.2)
	};

	private static void tripTotal(Trip[] trips){
		for(Trip trip : trips){
			System.out.println(trip.toString() + " Total: " + trip.total() + " Duration: " + trip.duration());
		}
	}
	private static void tabularPrint(Trip[] trips){
		for(Trip trip : trips){
			System.out.println(trip.toString());
		}

	}


	/**
	 * A simple demo app which
	 *
	 * <ul>
	 * <li>prints the trips test data in tabularformat</li>
	 * <li>calculates and prints the total cost for all trips</li>
	 *
	 * </ul>
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		//tabularPrint(trips);
		tripTotal(trips);
	}


}

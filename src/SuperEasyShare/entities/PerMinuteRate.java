package SuperEasyShare.entities;

import SuperEasyShare.provided.TripStatus;

public class PerMinuteRate extends Rate{

    private int perMinute;

    public PerMinuteRate(int perMinute) {
        if (perMinute <=0){
            perMinute = 1;
        }else {
            this.perMinute = perMinute;
        }
    }

    public int total(Trip t){
        if (t.getStatus() == TripStatus.COMPLETED){
            return  perMinute * t.duration();
        }
        return 0;
    }

    @Override
    public String toString() {
        return "PerMinuteRate{" +
                "perMinute=" + perMinute +
                '}';
    }
}

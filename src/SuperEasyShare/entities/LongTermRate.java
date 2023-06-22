package SuperEasyShare.entities;

import SuperEasyShare.provided.TripStatus;

public class LongTermRate extends Rate{

    private int baseAmount = 25000;
    private int baseDuration = 432000;
    private int perDay = 7000;

    public LongTermRate(int baseAmount, int perDay) {
        this.baseAmount = baseAmount;
        this.perDay = perDay;
    }

    public int total(Trip t){
        if(t.getStatus() == TripStatus.COMPLETED){
            if (t.duration() < baseDuration) {
                return baseAmount;
            }else {
                int diff = t.duration() - baseDuration;
                return baseDuration +(diff*perDay);
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "LongTermRate{" +
                "baseAmount=" + baseAmount +
                ", baseDuration=" + baseDuration +
                ", perDay=" + perDay +
                '}';
    }
}

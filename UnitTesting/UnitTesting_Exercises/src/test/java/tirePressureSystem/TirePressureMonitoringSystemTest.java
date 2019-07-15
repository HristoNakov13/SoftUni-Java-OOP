package tirePressureSystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import p06_TirePressureMonitoringSystem.Alarm;

public class TirePressureMonitoringSystemTest {
    private Alarm alarm;

    @Before
    public void setUp() {
        this.alarm = new Alarm();
    }

    @Test
    public void alarmReturnsFalseForNoReading() {
        boolean actual = this.alarm.getAlarmOn();
        Assert.assertFalse(actual);
    }
//    Sensor yields a random pressure that may or may not be within
//    alarm pressure thresholds. How tf do you test that? Mockito?

    @Test
    public void alarmSesnorReading() {
    }

}

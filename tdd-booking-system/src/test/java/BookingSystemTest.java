import java.util.List;

import junitparams.JUnitParamsRunner;

import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.*;

import static org.junit.Assert.*;
@RunWith(JUnitParamsRunner.class)

public class BookingSystemTest {
    private BookingSystem bookingSystem = new BookingSystem();

    @Test
    public void bookedHoursListShouldBeEmptyAfterCreation(){
        List<Integer> bookedHours = bookingSystem.getBookedHoursList();
        assertThat(bookedHours).isEmpty();
    }

    @Test
    public void possibleToBookOneInterval(){
        assertThat(bookingSystem.book("user",12,14)).isTrue();
        List<Integer> bookedHours = bookingSystem.getBookedHoursList();
        assertThat(bookedHours).containsExactly(12,13);
    }


    @Test
    @Parameters({"4,7","20,22"})
    public void impossibleToBookIntervalBeyondBoundaries(int from, int till){
        assertThat(bookingSystem.book("user",from,till)).isFalse();
        assertThat(bookingSystem.getBookedHoursList()).isEmpty();

    }

}
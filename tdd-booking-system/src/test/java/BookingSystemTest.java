import java.util.List;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import static org.junit.Assert.*;

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
    public void impossibleToBookIntervalEarlierThan8am(){
        assertThat(bookingSystem.book("user",4,7)).isFalse();
        assertThat(bookingSystem.getBookedHoursList()).isEmpty();

    }

}
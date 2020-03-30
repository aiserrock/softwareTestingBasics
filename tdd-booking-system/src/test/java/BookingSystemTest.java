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

}
package ohtu;

import static org.junit.Assert.*;
import org.junit.Test;

public class MultiplierTest {

    @Test
    public void kertominenToimii() {
        Multiplier viisi = new Multiplier(2);

        assertEquals(2, viisi.multipliedBy(1));
        assertEquals(0, viisi.multipliedBy(0));
    }

}

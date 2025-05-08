import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DataReaderTest {

    @Test
    public void testThatDataReaderReturnsProperData() {
        try (DataReader reader = new DataReader()){
            Object[] data = reader.read();
            Assertions.assertArrayEquals(new Object[]{1, 2, 3, 4}, data);
        }
    }

    @Test
    public void close() {
        try (DataReader reader = new DataReader()) {
            reader.close();
            Assertions.assertEquals(0, reader.getData().length);
        }
    }
}
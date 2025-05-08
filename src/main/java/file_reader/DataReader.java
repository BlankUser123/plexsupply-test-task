package file_reader;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.Objects;

@Setter
@Getter
public class DataReader implements FileReader, Closeable {

    public static String DATA = "input.txt";

    private int[] data;

    public static void setData(String data) {
        DataReader.DATA = data;
    }

    @Override
    public Object[] read() {
        Object[] result;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(DataReader.class.getResourceAsStream(DATA))))) {
            Object[] data = reader
                    .lines()
                    .map(line -> {
                        if (line == null  || line.isEmpty()) {
                            return "";
                        }

                        try {
                           return Integer.parseInt(line);
                        } catch (NumberFormatException e) {
                            return "";
                        }
                    })
                    .toArray(Object[]::new);

            result = new Object[data.length];

            System.arraycopy(data, 0, result, 0, data.length);

            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        data = new int[0];
    }
}

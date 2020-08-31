package budget;

import java.io.IOException;

public interface ISave {
    void save(Purchases purchases, String fileName) throws IOException;
}

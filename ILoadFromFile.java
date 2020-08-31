package budget;

import java.io.IOException;

public interface ILoadFromFile {
    void loadData(Purchases purchaseLists, String fileName) throws IOException;
}

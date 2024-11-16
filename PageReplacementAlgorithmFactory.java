import java.util.Objects;

public class PageReplacementAlgorithmFactory {
    public static PageReplacementAlgorithmBase newPageReplacementAlgorithm(String algName) {
        if (algName.equals("FIFO")) {
            return new PageReplacementFIFO();
        } else if (algName.equals("LRU (matrix)")) {
            return new PageReplacementMatrixLRU();
        }

        return null;
    }
}

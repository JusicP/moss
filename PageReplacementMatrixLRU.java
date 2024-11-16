import java.util.BitSet;
import java.util.Vector;

public class PageReplacementMatrixLRU extends PageReplacementAlgorithmBase {
    private BitSet[] referenceBitMatrix;

    private void init(int pageNum) {
        referenceBitMatrix = new BitSet[pageNum];
        for (int i = 0; i < pageNum; i++) {
            referenceBitMatrix[i] = new BitSet(pageNum);
        }
    }

    public void onMemReference(Vector mem, int virtPageNum) {
        if (referenceBitMatrix == null) {
            init(mem.size());
        }

        // if page k is referenced, set all bits of k row to 1, all bits of k column to 0
    }

    public int getPageToReplace(Vector mem, int virtPageNum) {
        // select page with the lowest row value
        return -1;
    }
}

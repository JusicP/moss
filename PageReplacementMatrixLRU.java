import java.util.BitSet;
import java.util.Vector;

public class PageReplacementMatrixLRU extends PageReplacementAlgorithmBase {
    private BitSet[] referenceBitMatrix;

    public void init(int pageFrameNum) {
        referenceBitMatrix = new BitSet[pageFrameNum];
        for (int i = 0; i < pageFrameNum; i++) {
            referenceBitMatrix[i] = new BitSet(pageFrameNum);
        }
    }

    public void onMemReference(int pageFrameNum, int pageFrameId) {
        if (referenceBitMatrix == null) {
            init(pageFrameNum);
        }

        // if page k is referenced, set all bits of k row to 1
        for (int i = 0; i < referenceBitMatrix.length; i++) {
            referenceBitMatrix[pageFrameId].set(i);
        }

        // set all bits of k column to 0
        for (int i = 0; i < referenceBitMatrix.length; i++) {
            referenceBitMatrix[i].clear(pageFrameId);
        }
    }

    public int getPageToReplace(Vector mem, int virtPageNum) {
        // select page frame with the lowest row value
        int minPageRowValue = referenceBitMatrix[0].length();
        int minPageRowValueIdx = 0;
        for (int i = 0; i < referenceBitMatrix.length; i++) {
            if (referenceBitMatrix[i].length() < minPageRowValue) {
                minPageRowValue = referenceBitMatrix[i].length();
                minPageRowValueIdx = i;
            }
        }

        // search for virtual page with a given phys page id
        for (int i = 0; i < mem.size(); i++) {
            Page page = (Page)mem.get(i);
            if (page.physical == minPageRowValueIdx) {
                return page.id;
            }
        }

        // can we get here?
        assert(false);

        return -1;
    }
}

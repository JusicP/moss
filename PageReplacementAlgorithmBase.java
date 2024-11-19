import java.util.Vector;

public abstract class PageReplacementAlgorithmBase {
    public void init(int pageFrameNum) {}
    public void onMemReference(int pageFrameNum, int pageFrameId) {}
    public abstract int getPageToReplace(Vector mem, int virtPageNum);
}

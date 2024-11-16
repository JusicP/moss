import java.util.Vector;

public abstract class PageReplacementAlgorithmBase {
    public void onMemReference(Vector mem, int virtPageNum) {}
    public abstract int getPageToReplace(Vector mem, int virtPageNum);
}

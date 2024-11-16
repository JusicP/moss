import java.util.Vector;

public interface PageReplacementAlgorithmBase {
    public abstract int getPageToReplace(Vector mem, int virtPageNum);
}

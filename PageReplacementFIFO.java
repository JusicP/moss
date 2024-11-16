import java.util.Vector;

public class PageReplacementFIFO extends PageReplacementAlgorithmBase {
    public int getPageToReplace(Vector mem, int virtPageNum) {
        int count = 0;
        int oldestPage = -1;
        int oldestTime = 0;
        int firstPage = -1;
        boolean mapped = false;

        while ( ! (mapped) || count != virtPageNum ) {
            Page page = ( Page ) mem.elementAt( count );
            if ( page.physical != -1 ) {
                if (firstPage == -1) {
                    firstPage = count;
                }
                if (page.inMemTime > oldestTime) {
                    oldestTime = page.inMemTime;
                    oldestPage = count;
                    mapped = true;
                }
            }
            count++;
            if ( count == virtPageNum ) {
                mapped = true;
            }
        }
        if (oldestPage == -1) {
            oldestPage = firstPage;
        }

        return oldestPage;
    }
}

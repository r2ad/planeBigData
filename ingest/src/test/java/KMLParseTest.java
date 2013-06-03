import java.io.File;
/**
 * User: vlad
 * Created : 6/2/13 2:49 PM
 */
public abstract class KMLParseTest {
    protected File findTestResource(String name) {
        return new File(locateInClassPath(name));
    }

    protected String locateInClassPath(String name) {
        return this.getClass().getClassLoader().getResource(name).getFile();
    }
}

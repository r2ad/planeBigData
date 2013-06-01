import org.junit.Before;
import org.junit.Test;
import org.planebigdata.KML2TSV;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * User: vlad
 * Created : 6/1/13 2:23 PM
 */
public class LoadFileTest {

    private SAXParser sp;

    @Before
    public void setup() throws SAXException, ParserConfigurationException {
        final File indir = new File("../data/IN-kml");
        final File outdir = new File("../data/OUT-json");
        final SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        sp = spf.newSAXParser();
    }

    @Test
    public void doesEverythingWork() throws IOException, SAXException {
        KML2TSV me = new KML2TSV(new File("."),new File( "."), sp, true);
        me.workFiles();

    }

}

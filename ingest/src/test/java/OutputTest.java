import org.junit.Before;
import org.junit.Test;
import org.planebigdata.KML2TSV;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.PrintWriter;

import static org.mockito.Mockito.mock;

/**
 * User: vlad
 * Created : 6/2/13 8:02 AM
 */
public class OutputTest {


    private SAXParser sp;
    private PrintWriter output;

    @Before
    public void setup() throws SAXException, ParserConfigurationException {
        final SAXParserFactory spf = SAXParserFactory.newInstance();
        output = mock(PrintWriter.class);
        spf.setNamespaceAware(true);
        sp = spf.newSAXParser();
    }

    @Test
    public void checkSingleRecordOutput()
    {
        KML2TSV me = new KML2TSV(sp,output);
    }



}

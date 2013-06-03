import org.junit.Before;
import org.junit.Test;
import org.planebigdata.Convert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

/**
 * User: vlad
 * Created : 6/2/13 8:23 PM
 */
public class ConversionTest extends KMLParseTest {

    private PrintStream mockSout;

    @Before
    public void setUp() throws ClassNotFoundException {
        mockSout = mock(PrintStream.class);
     }


    @Test
    public void conversionWorkingOkTest() throws SAXException, ParserConfigurationException, IOException {
        final String file = locateInClassPath("twoRecords.kml");
        Convert.ConvertFile(file, mockSout);
        verify(mockSout,times(1)).close();
    }


}

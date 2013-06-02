import org.junit.Before;
import org.junit.Test;
import org.planebigdata.KML2TSV;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * User: vlad
 * Created : 6/1/13 2:23 PM
 */
public class LoadFileTest extends KMLParseTest {

    private SAXParser sp;
    private PrintWriter output;

    @Before
    public void setup() throws SAXException, ParserConfigurationException {
        final SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        sp = spf.newSAXParser();
        output = mock(PrintWriter.class);
    }


    @Test
    public void processHalfRecordFile() throws IOException, SAXException {
        KML2TSV me = new KML2TSV(sp, output);
        final File file = findTestResource("plane.kml");
        me.acceptInputFile(file);

        assertEquals(me.getFlightNum(), "JBU1732");
        assertEquals(me.getHeading(), 201);
        assertEquals(me.getDepartTime(), "06/01/2013 10:16 AM EDT (1416Z)");
        assertEquals(me.getCoordinates(0).lattitude , 40.66, 0.1);
        assertEquals(me.getCoordinates(0).longitude, -73.75, .01);
        assertEquals(me.getCoordinates(0).altitude, 122.0, .01);
    }

    @Test
    public void processFullRecordFile() throws IOException, SAXException {
        KML2TSV me = new KML2TSV(sp, output);
        final File file = findTestResource("wholeSingleRecord.kml");
        me.acceptInputFile(file);

        // Make sure fields are clear afterwards
        assertEquals(me.getFlightNum(), null);
        assertEquals(me.getHeading(), 0);
        assertEquals(me.getDepartTime(), null);

        verify(output,times(1)).print("FlightNum\t");
        verify(output,times(1)).print("DepartureTime\t");
        verify(output,times(1)).print("Heading\t");
        verify(output,times(1)).print("Lat\t");
        verify(output,times(1)).print("Long\t");
        verify(output,times(1)).print("Alt");


        verify(output,times(7)).print("DAL1311\t");
        verify(output,times(1)).print("261\t");
        verify(output,times(6)).print("\t\t");
        verify(output,times(7)).print("06/01/2013 07:24 AM CDT (1224Z)\t");

        verify(output,times(2)).print("33.9352777777778\t");
        verify(output,times(2)).print("-118.393611111111\t");
        verify(output,times(2)).print(30.0);

        verify(output,times(1)).print("-118.2975\t");
        verify(output,times(1)).print("33.9461111111111\t");
        verify(output,times(1)).print(467.0);


    }

}

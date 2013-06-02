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

/**
 * User: vlad
 * Created : 6/1/13 2:23 PM
 */
public class LoadFileTest {

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

        assertEquals(me.getFlightNum(), "DAL1311");
        assertEquals(me.getHeading(), 261);
        assertEquals(me.getDepartTime(), "06/01/2013 07:24 AM CDT (1224Z)");
        assertEquals(me.getCoordinatesCount(),7);
        assertEquals(me.getCoordinates(0).lattitude , 33.931, .1);
        assertEquals(me.getCoordinates(0).longitude, -118.33, .1);
        assertEquals(me.getCoordinates(0).altitude, 30.0, .1);

        assertEquals(me.getCoordinates(5).lattitude , 33.951, .1);
        assertEquals(me.getCoordinates(5).longitude, -118.25, .1);
        assertEquals(me.getCoordinates(5).altitude, 733.0, .1);

    }


    private File findTestResource(String name) {
        return new File(this.getClass().getClassLoader().getResource(name).getFile());
    }


}

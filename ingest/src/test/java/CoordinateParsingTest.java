import org.junit.Before;
import org.junit.Test;
import org.planebigdata.KML2TSV;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.PrintWriter;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * User: vlad
 * Created : 6/2/13 6:34 AM
 */
public class CoordinateParsingTest {

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
    public void parseSingleCoordNegativeLong() throws SAXException, ParserConfigurationException {
        KML2TSV me = new KML2TSV(output);

        me.parseCoordinates("-73.75,40.6666666666667,122");
        assertEquals(me.getCoordinatesCount(), 1);
        assertEquals(me.getCoordinates(0).lattitude , 40.66, 0.1);
        assertEquals(me.getCoordinates(0).longitude, -73.75, .01);
        assertEquals(me.getCoordinates(0).altitude, 122.0, .01);

    }

    @Test
    public void parseSingleCoordNegativeLat() throws SAXException, ParserConfigurationException {
        KML2TSV me = new KML2TSV(output);

        me.parseCoordinates("73.75,-40.6666666666667,122");
        assertEquals(me.getCoordinatesCount(), 1);
        assertEquals(me.getCoordinates(0).lattitude , -40.66, 0.1);
        assertEquals(me.getCoordinates(0).longitude, 73.75, .01);
        assertEquals(me.getCoordinates(0).altitude, 122.0, .01);
    }

    @Test
    public void parseDoubleCoordinates() throws SAXException, ParserConfigurationException {
        KML2TSV me = new KML2TSV(output);

        me.parseCoordinates("-118.347222222222,33.9413888888889,200 93.316666666667,13.95,9033");
        assertEquals(me.getCoordinatesCount(), 2);
        assertEquals(me.getCoordinates(0).lattitude , 33.9 , .1);
        assertEquals(me.getCoordinates(0).longitude, -118.347, .1);
        assertEquals(me.getCoordinates(0).altitude, 200.01, .1);

        assertEquals(me.getCoordinates(1).lattitude , 13.95 , 0.1);
        assertEquals(me.getCoordinates(1).longitude,  93.31, .1);
        assertEquals(me.getCoordinates(1).altitude, 9033.01, .1);
    }


    @Test
    public void testCoordinatesWithoutDecimalPeriodInLong() throws SAXException, ParserConfigurationException {
        KML2TSV me = new KML2TSV(output);
        me.parseCoordinates("-117.983333333333,34,2067");
    }

    @Test
    public void testCoordinatesWithoutDecimalPeriodInLat() throws SAXException, ParserConfigurationException {
        KML2TSV me = new KML2TSV(output);
        me.parseCoordinates("-117,34.22,2067");
    }


}

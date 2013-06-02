import org.junit.Before;
import org.junit.Test;
import org.planebigdata.KML2TSV;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static junit.framework.Assert.assertEquals;

/**
 * User: vlad
 * Created : 6/2/13 6:34 AM
 */
public class CoordinateParsingTest {

    private SAXParser sp;


    @Before
    public void setup() throws SAXException, ParserConfigurationException {
        final SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        sp = spf.newSAXParser();
    }

    @Test
    public void parseSingleCoordNegativeLong() throws SAXException {
        KML2TSV me = new KML2TSV(sp);

        me.parseCoordinates("-73.75,40.6666666666667,122");
        assertEquals(me.coordinatesCount(), 1);
        assertEquals(me.getCoordinates(0).lattitude , 40.66, 0.1);
        assertEquals(me.getCoordinates(0).longitude, -73.75, .01);
        assertEquals(me.getCoordinates(0).altitude, 122.0, .01);

    }

    @Test
    public void parseSingleCoordNegativeLat() throws SAXException {
        KML2TSV me = new KML2TSV(sp);

        me.parseCoordinates("73.75,-40.6666666666667,122");
        assertEquals(me.coordinatesCount(), 1);
        assertEquals(me.getCoordinates(0).lattitude , -40.66, 0.1);
        assertEquals(me.getCoordinates(0).longitude, 73.75, .01);
        assertEquals(me.getCoordinates(0).altitude, 122.0, .01);
    }

    @Test
    public void parseDoubleCoordinates() throws SAXException {
        KML2TSV me = new KML2TSV(sp);

        me.parseCoordinates("-118.347222222222,33.9413888888889,200 93.316666666667,13.95,9033");
        assertEquals(me.coordinatesCount(), 2);
        assertEquals(me.getCoordinates(0).lattitude , 33.9 , .1);
        assertEquals(me.getCoordinates(0).longitude, -118.347, .1);
        assertEquals(me.getCoordinates(0).altitude, 200.01, .1);

        assertEquals(me.getCoordinates(1).lattitude , 13.95 , 0.1);
        assertEquals(me.getCoordinates(1).longitude,  93.31, .1);
        assertEquals(me.getCoordinates(1).altitude, 9033.01, .1);
    }



}

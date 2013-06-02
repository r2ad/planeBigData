import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.planebigdata.KML2TSV;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

import static junit.framework.Assert.assertEquals;

/**
 * User: vlad
 * Created : 6/1/13 2:23 PM
 */
public class LoadFileTest {

    private SAXParser sp;

    @Before
    public void setup() throws SAXException, ParserConfigurationException {
        final SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        sp = spf.newSAXParser();
    }


    @Test
    public void processHalfRecordFile() throws IOException, SAXException {
        KML2TSV me = new KML2TSV(sp);
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
    @Ignore
    public void processFullRecordFile() throws IOException, SAXException {
        KML2TSV me = new KML2TSV(sp);
        final File file = findTestResource("wholeSingleRecord.kml");
        me.acceptInputFile(file);

        assertEquals(me.getFlightNum(), "JBU1732");
        assertEquals(me.getHeading(), 201);
        assertEquals(me.getDepartTime(), "06/01/2013 10:16 AM EDT (1416Z)");
        assertEquals(me.getCoordinates(0).lattitude , 40.66, 0.1);
        assertEquals(me.getCoordinates(0).longitude, -73.75, .01);
        assertEquals(me.getCoordinates(0).altitude, 122.0, .01);
    }


    private File findTestResource(String name) {
        return new File(this.getClass().getClassLoader().getResource(name).getFile());
    }


}

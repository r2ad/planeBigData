import org.junit.Before;
import org.junit.Test;
import org.planebigdata.KML2TSV;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

/**
 * User: vlad
 * Created : 6/2/13 8:02 AM
 */
public class OutputTest extends KMLParseTest  {


    private PrintWriter output;

    @Before
    public void setup() throws SAXException, ParserConfigurationException {
        output = mock(PrintWriter.class);

    }



    @Test
    public void checkSingleRecordProducesOneHeaderAndSevenDataRows() throws IOException, SAXException, ParserConfigurationException {
        KML2TSV me = new KML2TSV(output);
        final File file = findTestResource("wholeSingleRecord.kml");
        me.acceptInputFile(file);

        verify(output,times(1)).print("FlightNum\t");
        verify(output,times(1)).print("DepartureTime\t");
        verify(output,times(1)).print("Heading\t");
        verify(output,times(1)).print("Lat\t");
        verify(output,times(1)).print("Long\t");
        verify(output,times(1)).print("Alt");
        verify(output,times(8)).println("");
        verify(output,times(7)).print("DAL1311\t");
        verify(output,times(7)).print("06/01/2013 07:24 AM CDT (1224Z)\t");
        verify(output,times(1)).print("261\t");

        verify(output,times(2)).print("-118.393611111111\t");
        verify(output,times(2)).print("33.9352777777778\t");
        verify(output,times(2)).print(30.0);

        verify(output,times(1)).print("-118.347222222222\t");
        verify(output,times(1)).print("33.9413888888889\t");


        verify(output,times(2)).print("33.95\t");
        verify(output,times(6)).print("\t\t");
        verify(output,times(1)).print("-118.316666666667\t");
        verify(output,times(1)).print("33.9461111111111\t");
        verify(output,times(1)).print("-118.2975\t");
        verify(output,times(1)).print("-118.25\t");
        verify(output,times(1)).print("33.9530555555556\t");
        verify(output,times(1)).print("-118.316666666667\t");
        verify(output,times(1)).print("-118.229722222222\t");


    }

    @Test
    public void checkTwoRecordFile () throws IOException, SAXException, ParserConfigurationException {

        KML2TSV me = new KML2TSV(output);
        final File file = findTestResource("twoRecords.kml");
        me.acceptInputFile(file);

        verify(output,times(1)).print("FlightNum\t");
        verify(output,times(1)).print("DepartureTime\t");
        verify(output,times(1)).print("Heading\t");
        verify(output,times(1)).print("Lat\t");
        verify(output,times(1)).print("Long\t");
        verify(output,times(1)).print("Alt");
        verify(output,times(15)).println(""); // 7 + 7 records + 1 header

        verify(output,times(7)).print("DAL1311\t");
        verify(output,times(7)).print("UAL848\t");


    }



}

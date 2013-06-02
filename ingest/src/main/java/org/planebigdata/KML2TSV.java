package org.planebigdata;
/*
 * Store KML ouput to a tab separated file:
 * FlightNum	DepartTime	Heading	Lat Long	Alt	
 * Note: cdata includes Departed: 06/01/2013 10:16 AM EDT (1416Z)
 */

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonGenerator;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KML2TSV extends DefaultHandler {

    private final SAXParser parser;

    private Writer output;
    private JsonGenerator jsonGen;

    private static final String HEADING_ELM = "heading";
    private ArrayList<Coordinates> coordList = new ArrayList<Coordinates>(10);

    public KML2TSV(final SAXParser sp) {
        parser = sp;
    }


    final public void acceptInputFile(final File srcFile) throws SAXException, IOException {
        System.out.println(String.format("starting on file %s", srcFile.getAbsolutePath()));
        try {
            parser.parse(srcFile, this);
        } finally {
            doneOutput();
        }
    }


    private void doneOutput() throws IOException {
        try {
            //TODO: Do output here..
        } finally {
            IOUtils.closeQuietly(output);
            output = null;
            jsonGen = null;
        }
    }

    // For flight data:
    /*
    * ref: Lat/Lon ref: http://code.google.com/p/simplelatlng/source/browse/src/main/java/com/javadocmd/simplelatlng/LatLng.java
    */

    final public String getFlightNum() {
        return FlightNum;
    }

    private String FlightNum = "";

    final public int getHeading() {
        return heading;
    }


    final public String getDepartTime() {
        return DepartTime;
    }

    private String DepartTime = "";
    private int heading = 0;

    final public Coordinates getCoordinates(final int idx) {
        return coordList.get(idx);
    }

    final public int getCoordinatesCount() {
        return coordList.size();
    }

    public class Coordinates {
        public double longitude = 0.0;
        public double lattitude = 0.0;
        public double altitude = 0.0;
    }

    boolean parsingFirstHalf = true;

    private static final String KML_NS = "http://earth.google.com/kml/2.2";
    private static final String NAME_ELM = "name";
    private static final String DESCR_ELM = "description";
    private static final String COORD_ELM = "coordinates";

    private StringBuilder chars;

    protected String type;

    final private void newChars() {
        chars = new StringBuilder();
    }

    final private String doneChars() {
        if (chars == null) {
            return null;
        }

        final String c = chars.toString();
        chars = null;
        return c;
    }

    @Override
    final public void characters(final char[] ch,final  int start,final  int length) throws SAXException {
        if (chars != null) {
            chars.append(ch, start, length);
        }
    }

    @Override
    final public void startElement(String uri, String lName, String qName, Attributes atts)
            throws SAXException {
        if (!KML_NS.equals(uri)) {
            return;
        }


        newChars();


    }

    @Override
    final public void endElement(final String uri,final  String lName,final  String qName) throws SAXException {
        final String content = doneChars();


        if (HEADING_ELM.equals(lName)) {
            heading = Integer.parseInt(content);
        } else if (DESCR_ELM.equals(lName)) {
            DepartTime = extractDepartTime(content);
        } else if (COORD_ELM.equals(lName)) {
            parseCoordinates(content);
        } else if (NAME_ELM.equals(lName)) {
            FlightNum = content;
        }


    }

    final public void parseCoordinates(final String content) throws SAXException {
        String[] coords = COORD_SPLIT_REGEX.split(content);
        for (String coord : coords) {
            if (coord.trim().length() == 0) {
                continue;
            }
            extractCoordinates(coord);
        }
    }

    final private String extractDepartTime(final String content) throws SAXException {
        final Matcher matcher = DEPART_TIME_REGEX.matcher(content.trim());
        if (!matcher.matches()) {
            throw new SAXException("Problem parsing departure time string " + content + " doesn't match");
        }
        return matcher.group(1);
    }

    private static final Pattern COORD_SPLIT_REGEX = Pattern.compile("[^0-9-,\\.]",Pattern.DOTALL);
    private static final Pattern LONGLAT_REGEX = Pattern.compile("^(-?\\d+\\.\\d+),(-?\\d+\\.\\d+),(\\d+) ?$", Pattern.DOTALL);
    private static final Pattern DEPART_TIME_REGEX = Pattern.compile("^.*<tr><td>Departed: (.+Z\\))</td>.*$");




    final public void extractCoordinates(final String longLat) throws SAXException {
        final Matcher matcher = LONGLAT_REGEX.matcher(longLat);
        //ignore non matching and return null >> this erases all polygon shapes for the moment
        if (!matcher.matches()) {
            throw new SAXException("Problem parsing coordinates string " + longLat + " doesn't match");
        }

        Coordinates coord = new Coordinates();

        coord.lattitude = Double.parseDouble(matcher.group(2));
        coord.longitude = Double.parseDouble(matcher.group(1));
        coord.altitude = Double.parseDouble(matcher.group(3));

        coordList.add(coord);

    }

}



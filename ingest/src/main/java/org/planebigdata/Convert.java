package org.planebigdata;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * User: vlad
 * Created : 6/1/13 1:57 PM
 */
public class Convert {


    public static  void main ( String args [] ) throws SAXException, ParserConfigurationException, IOException {

        if ( args.length == 1 )
        {
            ConvertFile(args[0],System.out);
        }  else if ( args.length == 2 )
        {
            ConvertFile(args[0],args[1]);
        }

    }

    private static void ConvertFile(String inFile, String outFile) throws IOException, SAXException, ParserConfigurationException {
        ConvertFile(inFile,
                new PrintStream(
                new FileOutputStream(outFile)));
    }

    public static void ConvertFile(String inFile, PrintStream out) throws IOException, SAXException, ParserConfigurationException {
        InputStream in = new FileInputStream(inFile);
        ConvertFile(in,out);
    }

    public static void ConvertFile(InputStream file, PrintStream out) throws SAXException, ParserConfigurationException, IOException {
        KML2TSV me = new KML2TSV(new PrintWriter(out));
        me.acceptInputFile(file);


    }


}

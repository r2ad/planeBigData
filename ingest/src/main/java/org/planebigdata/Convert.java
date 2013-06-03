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
        }

    }

    public static void ConvertFile(String file, PrintStream out) throws SAXException, ParserConfigurationException, IOException {

        KML2TSV me = new KML2TSV(new PrintWriter(out));
        me.acceptInputFile(new FileInputStream(file));


    }


}

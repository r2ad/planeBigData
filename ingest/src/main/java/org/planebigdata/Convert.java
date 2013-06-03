package org.planebigdata;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * Driver class, kicks off conversion process.
 */
public class Convert {


    public static final void main(final String[] args)
            throws SAXException, ParserConfigurationException, IOException {

        if (args.length == 1) {
            convertFile(args[0], System.out);
        } else if (args.length == 2) {
            convertFile(args[0], args[1]);
        }

    }

    public static final void convertFile(final String inFile, final String outFile)
            throws IOException, SAXException, ParserConfigurationException {
        convertFile(inFile,
                new PrintStream(
                        new FileOutputStream(outFile),true,"UTF-8"));
    }

    public static final void convertFile(final String inFile, final PrintStream out)
            throws IOException, SAXException, ParserConfigurationException {
        InputStream in = new FileInputStream(inFile);
        convertFile(in, out);
    }

    public static final void convertFile(final InputStream file, final PrintStream out)
            throws SAXException, ParserConfigurationException, IOException {
        KML2TSV me = new KML2TSV(new PrintWriter(out));
        me.acceptInputFile(file);
    }


}

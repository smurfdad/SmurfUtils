package es.smurfdad.utils.xml.digester;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

import javax.xml.parsers.SAXParser;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * Sobrecarga y abstraccion de {@link org.apache.commons.digester.Digester} La implementacion de esta clase obliga a
 * definir un metodo que contenga las reglas de parseo.
 */
public abstract class Digester extends org.apache.commons.digester.Digester {

    public Digester() {
        super();
    }

    public Digester(SAXParser parser) {
        super(parser);
    }

    public Digester(XMLReader reader) {
        super(reader);
    }

    @Override
    public Object parse(File file) throws IOException, SAXException {
        rules();
        return super.parse(file);
    }

    @Override
    public Object parse(InputSource input) throws IOException, SAXException {
        rules();
        return super.parse(input);
    }

    @Override
    public Object parse(InputStream input) throws IOException, SAXException {
        rules();
        return super.parse(input);
    }

    @Override
    public Object parse(Reader reader) throws IOException, SAXException {
        rules();
        return super.parse(reader);
    }

    @Override
    public Object parse(String uri) throws IOException, SAXException {
        rules();
        return super.parse(uri);
    }

    @Override
    public Object parse(URL url) throws IOException, SAXException {
        rules();
        return super.parse(url);
    }

    /**
     * Metodo abstracto donde se definen las transformaciones a realizar entre xml y el modelo de datos que deseemos.
     */
    abstract public void rules();

}
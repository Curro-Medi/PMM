package com.example.practica3;

import android.os.DropBoxManager;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class StackOverflowXmlParser {

    //tres parametros autor titulo y enlace

    private String autor;
    private String titulo;
    private String enlace;


    public DropBoxManager.Entry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        String ns = null;
        parser.require(XmlPullParser.START_TAG, null, "entry");
        String title = null;
        long summary = 0;
        String link = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("title")) {
                title = readTitle(parser);
            }else if (name.equals("link")) {
                link = readLink(parser);
            } else {
                skip(parser);
            }
        }
        return new DropBoxManager.Entry(title, summary, link);
    }

    private void skip(XmlPullParser parser) {
    }

    private String readLink(XmlPullParser parser) {
        return null;
    }

    private String readSummary(XmlPullParser parser) {
        return null;
    }

    private String readTitle(XmlPullParser parser) {
        return null;
    }


    public int getTitulo() {
        return 0;
    }

    public String getAutor() {
        return null;
    }
}

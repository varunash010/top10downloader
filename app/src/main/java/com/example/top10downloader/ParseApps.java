package com.example.top10downloader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ParseApps {
    private static final String TAG = "ParseApps";
    private List<FeedEntry> apps;

    public ParseApps() {
        this.apps = new ArrayList<>();
    }

    public List<FeedEntry> getApps() {
        return apps;
    }

    public boolean parseXmlData(String xmlData)
    {
        boolean status = true;
        boolean isEntry = false;
        FeedEntry currentApp;
        String text = "";

        try{
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParserFactory.setNamespaceAware(true);
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));

        }
        catch(Exception e)
        {
            status = false;
        }
        return status;
    }
}

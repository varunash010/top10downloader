package com.example.top10downloader;

import android.util.Log;

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
        boolean isCollection = false;
        FeedEntry currentApp = null;
        String text = "";

        try{
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParserFactory.setNamespaceAware(true);
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT)
            {
                String tagName = xmlPullParser.getName();
                switch(eventType)
                {
                    case XmlPullParser.START_TAG:
                        Log.d(TAG, "parseXmlData: Starting tag --> " + tagName);
                        if("entry".equalsIgnoreCase(tagName))
                        {
                            isEntry = true;
                            currentApp = new FeedEntry();
                        }
                        else if("collection".equalsIgnoreCase(tagName))
                        {
                            isCollection = true;
                        }
                        else if("category".equalsIgnoreCase(tagName))
                        {
                            text = xmlPullParser.getAttributeValue(null,"label");
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if(!isCollection)
                        {
                            text = xmlPullParser.getText();
//                            Log.d(TAG, "parseXmlData: text for " + tagName + " is : " + text);
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "parseXmlData: End tag for --> " + tagName);
                        if(isEntry)
                        {
                            if("entry".equalsIgnoreCase(tagName))
                            {
                                apps.add(currentApp);
                                isEntry = false;
                            }
                            if("collection".equalsIgnoreCase(tagName))
                            {
                                isCollection = false;
                            }
                            else if("name".equalsIgnoreCase(tagName) && !isCollection)
                            {
                                currentApp.setName(text);
                            }
                            else if("title".equalsIgnoreCase(tagName))
                            {
                                currentApp.setTitle(text);
                            }
                            else if("artist".equalsIgnoreCase(tagName))
                            {
                                currentApp.setArtist(text);
                            }
                            else if("category".equalsIgnoreCase(tagName))
                            {
                                currentApp.setCategory(text);
                            }
                            else if("price".equalsIgnoreCase(tagName))
                            {
                                currentApp.setPrice(text);
                            }
                            else if("image".equalsIgnoreCase(tagName))
                            {
                                currentApp.setImageUrl(text);
                            }
                        }
                }
                eventType = xmlPullParser.next();
            }

            for(FeedEntry f : apps)
            {
                Log.d(TAG, "*******************\n" +
                        "parseXmlData: \n" + f.toString());
            }
        }
        catch(Exception e)
        {
            status = false;
        }
        return status;
    }
}
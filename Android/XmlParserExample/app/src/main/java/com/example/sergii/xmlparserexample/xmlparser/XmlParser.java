package com.example.sergii.xmlparserexample.xmlparser;

import android.content.Context;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sergii on 23.11.16.
 */
public class XmlParser {

    private static final String TAG = XmlParser.class.getSimpleName();

    public void parse(Context context, int resID, BaseTagParser baseTagParser) {

        XmlPullParser xpp = context.getResources().getXml(resID);

        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xpp.getEventType()) {
                    case XmlPullParser.START_DOCUMENT:
                        Log.d(TAG, "START_DOCUMENT");
                        break;
                    case XmlPullParser.START_TAG:
                        Log.d(TAG, "START_TAG name: "  + xpp.getName());
                        baseTagParser.parse(xpp);
                        break;
                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "END_TAG: name: " + xpp.getName());
                        break;
                    default:
                        break;
                }
                xpp.next();
            }
        } catch (XmlPullParserException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Log.d(TAG, "END_DOCUMENT");


    }
}
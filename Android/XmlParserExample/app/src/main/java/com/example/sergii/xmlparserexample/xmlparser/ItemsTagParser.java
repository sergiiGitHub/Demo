package com.example.sergii.xmlparserexample.xmlparser;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by sergii on 23.11.16.
 */
public class ItemsTagParser extends BaseTagParser {

    private static final String TAG = ItemsTagParser.class.getSimpleName();

    public ItemsTagParser(  ){
        super("items");
        addChildren(new BaseTagParser("item"){
            @Override
            protected void extractInfo(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException  {
                super.extractInfo(xmlPullParser);
                extractInfoLocal(xmlPullParser);
            }
        });
    }

    private void extractInfoLocal(XmlPullParser xmlPullParser) {
        String name = xmlPullParser.getAttributeValue(null, "name");
        String property = xmlPullParser.getAttributeValue(null, "property");
        Log.d(TAG, "extractInfoLocal: name: " + name + "; property: " + property );
    }

}

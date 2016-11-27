package com.example.sergii.xmlparserexample.xmlparser;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sergii on 23.11.16.
 */
public class BaseTagParser {

    private static final String TAG = BaseTagParser.class.getSimpleName();
    private final String tag;
    private List<BaseTagParser> children;

    public BaseTagParser( String tag ){
        this.tag = tag;
    }

    public boolean parse(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        Log.d(TAG, "parse: name" + xmlPullParser.getName() + "; event: " + xmlPullParser.getEventType());
        if (!isAccept(xmlPullParser.getName()) || XmlPullParser.START_TAG != xmlPullParser.getEventType()) {
            return false;
        }
        extractInfo(xmlPullParser);
        while (parseChildren(xmlPullParser)){ }
        xmlPullParser.next();
        if ( xmlPullParser.getEventType() != XmlPullParser.END_TAG  && isAccept(xmlPullParser.getName())){
            throw new IllegalArgumentException("xmlPullParser name: " + xmlPullParser.getName() + "; event: " + xmlPullParser.getEventType());
        }
        return true;
    }

    private boolean parseChildren(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException  {

        if ( children == null ){
            return false;
        }
        xmlPullParser.next();
        for ( BaseTagParser base : children ){
            return base.parse(xmlPullParser);
        }
        return false;
    }

    public void addChildren( BaseTagParser baseTagParser ){
        if ( children == null ){
            children = new LinkedList<>();
        }
        children.add(baseTagParser);
    }

    protected void extractInfo(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        Log.d(TAG, "extractInfo: ");
    }

    private boolean isAccept(String name) {
        Log.d(TAG, "isAccept: " + name);
        return tag.equals(name);
    }

    public void destroy(){
        if( children != null ){
            children.clear();
            children = null;
        }
    }
}

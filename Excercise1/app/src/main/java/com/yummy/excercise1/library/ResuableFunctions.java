package com.yummy.excercise1.library;

/**
 * Created by Tri Nguyen on 6/3/2016.
 */
public class ResuableFunctions {
    public static  String removeHtmlTag (String inputString)
    {
        StringBuffer newString = new StringBuffer(inputString);
        while(newString.indexOf("<") != -1)
        {
            int beginTag = newString.indexOf("<");
            int endTag = newString.indexOf(">",beginTag)+1;
            newString = newString.delete(beginTag,endTag);
        }
        return newString.toString();
    }
}

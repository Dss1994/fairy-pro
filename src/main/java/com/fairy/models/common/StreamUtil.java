package com.fairy.models.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

public class StreamUtil {
    public static String readBytes(InputStream inStream)  
            throws IOException { 
    	return CharStreams.toString(new InputStreamReader(inStream,Charsets.UTF_8));
    }  
    public static String readBytes(BufferedReader bufferedReader) throws IOException {
    	return CharStreams.toString(bufferedReader);
	}
}

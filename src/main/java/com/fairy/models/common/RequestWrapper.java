package com.fairy.models.common;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.google.common.base.Charsets;



public class RequestWrapper extends HttpServletRequestWrapper{
	    private String body;
	    
	    public RequestWrapper(HttpServletRequest request) throws IOException {
	        super(request);
	        body = StreamUtil.readBytes(request.getReader());
	    }

	    @Override
	    public BufferedReader getReader() throws IOException {
	        return new BufferedReader(new InputStreamReader(getInputStream()));
	    }

	    /**
	     * 在使用@RequestBody注解的时候，其实框架是调用了getInputStream()方法，所以我们要重写这个方法
	     * @return
	     * @throws IOException
	     */
	    @Override
	    public ServletInputStream getInputStream() throws IOException {
	        final ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes(Charsets.UTF_8));

	        return new ServletInputStream() {
	            @Override
	            public boolean isFinished() {
	                return false;
	            }

	            @Override
	            public boolean isReady() {
	                return false;
	            }

	            @Override
	            public void setReadListener(ReadListener readListener) {
	            }

	            @Override
	            public int read() throws IOException {
	                return bais.read();
	            }
	        };
	    }
	    @Override
	    public String toString() {
	    	return body;
	    }
}

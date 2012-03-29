package com.rushdevo.gitdroid.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.easymock.EasyMock;

/**
 * @author jasonrush
 * Helper methods for mocking and stubbing
 */
public class MockHelper {
	public static void setupHttpClientMock(HttpClient client, String expectedBody) throws IllegalStateException, IOException {
		HttpResponse response = EasyMock.createMock(HttpResponse.class);
		HttpEntity entity = EasyMock.createMock(HttpEntity.class);
		StatusLine statusLine = EasyMock.createMock(StatusLine.class);
		
		InputStream stream = new ByteArrayInputStream(expectedBody.getBytes());
		
		EasyMock.expect(statusLine.getStatusCode()).andStubReturn(new Integer(200));
		EasyMock.expect(entity.getContent()).andStubReturn(stream);
		
		EasyMock.expect(response.getStatusLine()).andStubReturn(statusLine);
		EasyMock.expect(response.getEntity()).andStubReturn(entity);
		
		EasyMock.expect(client.execute((HttpUriRequest)EasyMock.anyObject())).andStubReturn(response);
		
		EasyMock.replay(client);
		EasyMock.replay(response);
		EasyMock.replay(entity);
		EasyMock.replay(statusLine);
	}
}

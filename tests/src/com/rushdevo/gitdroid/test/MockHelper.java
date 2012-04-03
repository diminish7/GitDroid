package com.rushdevo.gitdroid.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

/**
 * @author jasonrush
 * Helper methods for mocking and stubbing
 */
public class MockHelper {
	public static void setupHttpClientMock(HttpClient client, String expectedBody) throws IllegalStateException, IOException {
		HttpResponse response = createMock(HttpResponse.class);
		HttpEntity entity = createMock(HttpEntity.class);
		StatusLine statusLine = createMock(StatusLine.class);
		
		InputStream stream = new ByteArrayInputStream(expectedBody.getBytes());
		
		expect(statusLine.getStatusCode()).andStubReturn(new Integer(200));
		expect(entity.getContent()).andStubReturn(stream);
		
		expect(response.getStatusLine()).andStubReturn(statusLine);
		expect(response.getEntity()).andStubReturn(entity);
		
		expect(client.execute((HttpUriRequest)anyObject())).andStubReturn(response);
		
		replay(client);
		replay(response);
		replay(entity);
		replay(statusLine);
	}
}

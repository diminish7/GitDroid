package com.rushdevo.gitdroid.github.v3.models.event_payloads;

import com.rushdevo.gitdroid.github.v3.models.Event;

/**
 * @author jasonrush
 * All the different types of payload that can be returned by an event must implement this
 */
public interface EventPayload {
	/**
	 * @return The full description of the event
	 */
	public String getFullDescription(Event event);
	/**
	 * @return The content of the event (comment, description, etc)
	 */
	public String getContent();
}

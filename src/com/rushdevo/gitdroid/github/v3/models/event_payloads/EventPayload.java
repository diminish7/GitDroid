package com.rushdevo.gitdroid.github.v3.models.event_payloads;

/**
 * @author jasonrush
 * All the different types of payload that can be returned by an event must implement this
 */
public interface EventPayload {
	/**
	 * @return The action verb for the event (commented, opened, closed, etc)
	 */
	public String getActionVerb();
	/**
	 * @return The action subject for the event (pull request, commit sha, etc)
	 */
	public String getActionSubject();
	/**
	 * @return The content of the event (comment, description, etc)
	 */
	public String getContent();
}

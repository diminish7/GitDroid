/**
 * 
 */
package com.rushdevo.gitdroid.gson;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rushdevo.gitdroid.github.v3.models.Event;
import com.rushdevo.gitdroid.github.v3.models.Organization;
import com.rushdevo.gitdroid.github.v3.models.Repository;
import com.rushdevo.gitdroid.github.v3.models.User;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.CommitComment;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.CreateEvent;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.DeleteEvent;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.DownloadEvent;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.EventPayload;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.FollowEvent;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.ForkApplyEvent;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.ForkEvent;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.GistEvent;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.GollumEvent;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.IssueCommentEvent;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.IssuesEvent;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.MemberEvent;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.PublicEvent;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.PullRequestEvent;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.PullRequestReviewCommentEvent;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.PushEvent;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.TeamAddEvent;
import com.rushdevo.gitdroid.github.v3.models.event_payloads.WatchEvent;
import com.rushdevo.gitdroid.utils.ErrorDisplay;

/**
 * @author jasonrush
 * JSON Deserializer for Github Events
 * Deals with the polymorphic nature of the payload property 
 */
public class EventDeserializer implements JsonDeserializer<Event> {

	@Override
	public Event deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx) throws JsonParseException {
		Event event = new Event();
		JsonObject obj = json.getAsJsonObject();
		JsonElement el = obj.get("type");
		if (!GsonUtils.isNull(el)) event.setType(el.getAsString());
		el = obj.get("public");
		event.setIsPublic((GsonUtils.isNull(el)) ? false : el.getAsBoolean());
		el = obj.get("created_at");
		if (!GsonUtils.isNull(el)) event.setCreatedAt((Date)ctx.deserialize(el, Date.class));
		el = obj.get("repo");
		if (!GsonUtils.isNull(el)) event.setRepo((Repository)ctx.deserialize(el, Repository.class));
		el = obj.get("actor");
		if (!GsonUtils.isNull(el)) event.setActor((User)ctx.deserialize(el, User.class));
		el = obj.get("org");
		if (!GsonUtils.isNull(el)) event.setOrg((Organization)ctx.deserialize(el, Organization.class));
		el = obj.get("payload");
		if (!GsonUtils.isNull(el) && event.getType() != null) event.setPayload(getPayload(el, ctx, event.getType()));
		return event;
	}
	
	public EventPayload getPayload(JsonElement json, JsonDeserializationContext ctx, String type) {
		if (type.equals("CommitCommentEvent")) {
			return (CommitComment)ctx.deserialize(json, CommitComment.class);
		} else if (type.equals("CreateEvent")) {
			return (CreateEvent)ctx.deserialize(json, CreateEvent.class);
		} else if (type.equals("DeleteEvent")) {
			return (DeleteEvent)ctx.deserialize(json, DeleteEvent.class);
		} else if (type.equals("DownloadEvent")) {
			return (DownloadEvent)ctx.deserialize(json, DownloadEvent.class);
		} else if (type.equals("FollowEvent")) {
			return (FollowEvent)ctx.deserialize(json, FollowEvent.class);
		} else if (type.equals("ForkEvent")) {
			return (ForkEvent)ctx.deserialize(json, ForkEvent.class);
		} else if (type.equals("ForkApplyEvent")) {
			return (ForkApplyEvent)ctx.deserialize(json, ForkApplyEvent.class);
		} else if (type.equals("GistEvent")) {
			return (GistEvent)ctx.deserialize(json, GistEvent.class);
		} else if (type.equals("GollumEvent")) {
			return (GollumEvent)ctx.deserialize(json, GollumEvent.class);
		} else if (type.equals("IssueCommentEvent")) {
			return (IssueCommentEvent)ctx.deserialize(json, IssueCommentEvent.class);
		} else if (type.equals("IssuesEvent")) {
			return (IssuesEvent)ctx.deserialize(json, IssuesEvent.class);
		} else if (type.equals("MemberEvent")) {
			return (MemberEvent)ctx.deserialize(json, MemberEvent.class);
		} else if (type.equals("PublicEvent")) {
			return (PublicEvent)ctx.deserialize(json, PublicEvent.class);
		} else if (type.equals("PullRequestEvent")) {
			return (PullRequestEvent)ctx.deserialize(json, PullRequestEvent.class);
		} else if (type.equals("PullRequestReviewCommentEvent")) {
			return (PullRequestReviewCommentEvent)ctx.deserialize(json, PullRequestReviewCommentEvent.class);
		} else if (type.equals("PushEvent")) {
			return (PushEvent)ctx.deserialize(json, PushEvent.class);
		} else if (type.equals("TeamAddEvent")) {
			return (TeamAddEvent)ctx.deserialize(json, TeamAddEvent.class);
		} else if (type.equals("WatchEvent")) {
			return (WatchEvent)ctx.deserialize(json, WatchEvent.class);
		} else {
			// Invalid type, can't parse the payload
			ErrorDisplay.debug(this, "Invalid event payload type: " + type);
			return null;
		}
	}
}
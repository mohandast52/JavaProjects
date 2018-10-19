package org.mohan.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mohan.messenger.database.DatabaseClass;
import org.mohan.messenger.exception.DataNotFoundException;
import org.mohan.messenger.model.Comment;
import org.mohan.messenger.model.Message;

public class MessageService {

	// get messages by accessing database
	private static Map<Long, Message> messages = DatabaseClass.getMessages();

	/*
	 * because of jersey life cycle, the below initialization have been shifted from
	 * default constructor to static block
	 */
	static {

		Map<Long, Comment> comment1 = new HashMap<>();
		comment1.put(1L, new Comment(1, "Comment 1 hahaha", "Mohan"));
		comment1.put(1L, new Comment(1, "Comment 2 hahaha", "Mohan"));

		Map<Long, Comment> comment2 = new HashMap<>();
		comment2.put(1L, new Comment(1, "Comment 3 hahaha", "Das"));
		comment2.put(1L, new Comment(1, "Comment 4 hahaha", "Das"));

		messages.put(1L, new Message(1, "Hello XYZ", "Mohan"));
		messages.put(2L, new Message(2, "Hello ABC", "Das"));
		messages.put(3L, new Message(3, "Hello LMN", "Soundara"));
		messages.put(4L, new Message(4, "Hello OOO", "Rajan"));
	}

	public MessageService() {

	}

	public List<Message> getAllMessage() {
		return (new ArrayList<Message>(messages.values()));
	}

	// using YEAR filter
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messageForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}

	// using pagination
	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if (start + size > list.size())
			return new ArrayList<Message>();
		return list.subList(start, start + size);
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		// if no messages exits, then return null
		if (message.getId() <= 0) {
			return null;
		}
		// else update the message in database!
		messages.put(message.getId(), message);
		return message;
	}

	public Message getMessage(long id) {
		Message message = messages.get(id);
		if (message == null) {
			throw new DataNotFoundException("Message with id " + id + " not found"); // custom exception
		}
		return message;
	}

	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}

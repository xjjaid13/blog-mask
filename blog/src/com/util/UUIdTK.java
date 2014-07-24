package com.util;

import java.util.UUID;

public class UUIdTK {
	private static final String _URN_PREFIX = "urn:uuid:";

	private UUIdTK() {
	}

	public static UUID generateNextId() {
		return UUID.randomUUID();
	}

	public static String nextId(boolean format) {
		return nextId(format, false);
	}

	public static String nextId(boolean format, boolean prefix) {
		UUID uuid = generateNextId();
		String uuidResult = null;
		if (format) {
			uuidResult = uuid.toString();
		} else {
			uuidResult = uuid.toString().toUpperCase();
		}
		if (prefix)
			uuidResult = _URN_PREFIX + uuidResult;
		return uuidResult.toUpperCase();
	}

}
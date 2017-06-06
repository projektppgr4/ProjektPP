package com.taskmgr.model.serializer;

/**
 * Created by Akai on 2017-06-04.
 */

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.taskmgr.model.TaskStatus;

import java.io.IOException;

/**
 * Created by Akai on 2017-06-04.
 */
public class TaskStatusSerializer extends StdSerializer<TaskStatus> {
	public TaskStatusSerializer() {
		this(null);
	}

	public TaskStatusSerializer(Class<TaskStatus> t) {
		super(t);
	}

	@Override
	public void serialize(
			TaskStatus taskStatus, JsonGenerator jgen, SerializerProvider provider)
			throws IOException {

		jgen.writeStartObject();
		jgen.writeStringField("Status", taskStatus.getName());
		jgen.writeEndObject();
	}
}

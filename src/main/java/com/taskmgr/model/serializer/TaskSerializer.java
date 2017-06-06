package com.taskmgr.model.serializer;

/**
 * Created by Akai on 2017-06-04.
 */

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.taskmgr.model.Task;

import java.io.IOException;

/**
 * Created by Akai on 2017-06-04.
 */

/**
 * Created by Akai on 2017-06-04.
 */
public class TaskSerializer extends StdSerializer<Task> {
	public TaskSerializer() {
		this(null);
	}

	public TaskSerializer(Class<Task> t) {
		super(t);
	}

	@Override
	public void serialize(
			Task task, JsonGenerator jgen, SerializerProvider provider)
			throws IOException {

		jgen.writeStartObject();
		jgen.writeNumberField("id", task.getId());
		jgen.writeStringField("name", task.getName());
		jgen.writeNumberField("duration", task.getDuration());
		if (task.getTaskStatus() != null) {
			jgen.writeStringField("status", task.getTaskStatus().getName());
		} else {
			jgen.writeStringField("status", "undefined");
		}
		jgen.writeEndObject();
	}
}

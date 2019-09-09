package com.example.serialize;

import com.example.enums.EnumType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class EnumListJsonSerializer extends JsonSerializer<List<EnumType>> {

	@Override
	public void serialize(List<EnumType> values, JsonGenerator jsonGenerator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jsonGenerator.writeStartArray();
		for (int i = 0; i < values.size(); i++) {
			EnumType enumType = values.get(i);
			jsonGenerator.writeStartObject();
			jsonGenerator.writeFieldName("code");
			jsonGenerator.writeNumber(enumType.code());
			jsonGenerator.writeFieldName("name");
			jsonGenerator.writeString(enumType.name());
			jsonGenerator.writeFieldName("text");
			jsonGenerator.writeString(enumType.text());
			jsonGenerator.writeEndObject();
		}
		jsonGenerator.writeEndArray();
	}

}

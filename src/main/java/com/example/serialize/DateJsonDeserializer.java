package com.example.serialize;

import com.example.utils.DateUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Date;

public class DateJsonDeserializer extends JsonDeserializer<Date> {
	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String str = jp.getText();
		if (StringUtils.isEmpty(str)) {
			return null;
		}

		if (str.indexOf('-') > -1) {
			return DateUtil.parseDate(jp.getText(), DateUtil.DatePattern.isoDateTime);
		} else {
			return DateUtil.parseDate(jp.getText(), DateUtil.DatePattern.slashTime);
		}
	}
}
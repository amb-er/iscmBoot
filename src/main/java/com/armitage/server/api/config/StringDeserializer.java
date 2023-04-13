package com.armitage.server.api.config;

import java.io.IOException;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@Configuration
public class StringDeserializer extends JsonDeserializer<String> {
    
	/**
	 * 自定义反序列化，清除字符串的空格
	 */
	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String value = p.getValueAsString();
		if(value==null) {
			return value;
		}
		return value.trim();
	}

}
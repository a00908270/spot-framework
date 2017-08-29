package at.spot.core.infrastructure.serialization;

import java.lang.reflect.Type;

import org.springframework.http.ResponseEntity;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * De-/Serializes java classes.
 */
public class ResponseEntitySerializer
		implements JsonSerializer<ResponseEntity<?>>, JsonDeserializer<ResponseEntity<?>> {

	@Override
	public JsonElement serialize(final ResponseEntity responseEntity, final Type typeOfSrc,
			final JsonSerializationContext context) {
		final JsonObject root = new JsonObject();

		root.addProperty("statusCode", responseEntity.getStatusCode().toString());
		// root.addProperty("package", javaClass.getHeaders());

		// final JsonElement body = new JsonObject().
		// body.set

		// root.add("body", body);

		return root;
	}

	@Override
	public ResponseEntity deserialize(final JsonElement json, final Type typeOfT,
			final JsonDeserializationContext context) throws JsonParseException {

		final String fullName = json.getAsJsonObject().get("name").getAsString();

		return null;
	}
}
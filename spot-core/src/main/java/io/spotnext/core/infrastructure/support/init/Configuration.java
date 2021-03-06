package io.spotnext.core.infrastructure.support.init;

import java.util.List;
import java.util.Properties;

public interface Configuration {

	/**
	 * Return an ordered list of {@link Properties} application configuration
	 * objects.
	 * 
	 */
	List<Properties> getConfiguration();
}

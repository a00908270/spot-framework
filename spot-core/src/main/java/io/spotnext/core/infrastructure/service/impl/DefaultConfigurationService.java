
package io.spotnext.core.infrastructure.service.impl;

import javax.annotation.Resource;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import io.spotnext.core.infrastructure.service.ConfigurationService;
import io.spotnext.core.infrastructure.service.LoggingService;

@Service
public class DefaultConfigurationService extends BeanAware implements ConfigurationService, EnvironmentAware {

	@Resource
	protected LoggingService loggingService;

	@Resource
	protected Environment environment;

	@Override
	public String getString(final String key) {
		return getProperty(key);
	}

	@Override
	public Integer getInteger(final String key) {
		Integer value = null;

		try {
			final String v = getProperty(key);

			if (v != null) {
				value = Integer.parseInt(v);
			}
		} catch (final NumberFormatException e) {
			loggingService.exception(String.format("Can't load config key %s", key), e);
		}

		return value;
	}

	@Override
	public Double getDouble(final String key) {
		Double value = null;

		try {
			value = Double.parseDouble(getProperty(key));
		} catch (final NumberFormatException e) {
			loggingService.exception(String.format("Can't load config key %s", key), e);
		}

		return value;
	}

	@Override
	public String getString(final String key, final String defaultValue) {
		final String val = getString(key);

		return val != null ? val : defaultValue;
	}

	@Override
	public Integer getInteger(final String key, final Integer defaultValue) {
		Integer val = getInteger(key);

		if (val == null) {
			val = defaultValue;
		}

		return val != null ? val : defaultValue;
	}

	@Override
	public Double getDouble(final String key, final Double defaultValue) {
		Double val = getDouble(key);

		if (val == null) {
			val = defaultValue;
		}

		return val != null ? val : defaultValue;
	}

	/**
	 * Iterates over all registered properties files.
	 * 
	 * @param key
	 */
	protected String getProperty(final String key) {
		final String value = environment.getProperty(key);

		// for (final Properties prop :
		// Registry.getAppConfiguration().getConfiguration()) {
		// value = prop.getProperty(key);
		//
		// if (StringUtils.isNotBlank(value)) {
		// break;
		// }
		// }

		return value;
	}

	@Override
	public Boolean getBoolean(final String key) {
		Boolean value = null;

		try {
			final String v = getProperty(key);

			// null is treated as false
			if (v != null) {
				value = Boolean.parseBoolean(v);
			}
		} catch (final NumberFormatException e) {
			loggingService.exception(String.format("Can't load config key %s", key), e);
		}

		return value;
	}

	@Override
	public boolean getBoolean(final String key, final boolean defaultValue) {
		Boolean b = getBoolean(key);

		if (b == null) {
			b = defaultValue;
		}

		return b;
	}

	@Override
	public void setEnvironment(final Environment environment) {
		this.environment = environment;
	}
}

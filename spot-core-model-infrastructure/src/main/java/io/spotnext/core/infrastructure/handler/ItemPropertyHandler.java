package io.spotnext.core.infrastructure.handler;

import io.spotnext.core.types.Item;

/**
 * Handles property access to {@link Item} objects.
 */
public interface ItemPropertyHandler {
	/**
	 * @see #getProperty(Item, String, Class)
	 * 
	 * @param item
	 * @param propertyName
	 */
	<T extends Item> Object getProperty(T item, String propertyName);

	/**
	 * Returns the property value for the given item.
	 * 
	 * @param item
	 * @param propertyName
	 */
	<T extends Item, R> R getProperty(T item, String propertyName, Class<R> returnType);

	/**
	 * Sets a property for the given item.
	 * 
	 * @param item
	 * @param propertyName
	 * @param value
	 */
	<T extends Item> void setProperty(T item, String propertyName, Object value);
}

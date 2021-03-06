package io.spotnext.core.infrastructure.service;

import io.spotnext.core.types.Item;
import io.spotnext.itemtype.core.user.User;

/**
 * This service uses the {@link User} registered assigned to the current session
 * and evaluates access permissions for {@link Item} types and instances.
 */
public interface AccessControlService {

	/**
	 * Returns true if the current session user is allowed to access the given
	 * {@link Item} type.
	 * 
	 * @param type
	 */
	<T extends Item> boolean accessAllowed(Class<T> type);

	/**
	 * Returns true if the current session user is allowed to access the given
	 * property of the given {@link Item} type.
	 * 
	 * @param type
	 */
	<T extends Item> boolean accessAllowed(Class<T> type, String property);

	/**
	 * Returns true if the current session user is allowed to access the given
	 * {@link Item} instance.
	 * 
	 * @param type
	 */
	<T extends Item> boolean accessAllowed(T type);

	/**
	 * Returns true if the current session user is allowed to access the given
	 * property of the given {@link Item} instance.
	 * 
	 * @param type
	 */
	<T extends Item> boolean accessAllowed(T type, String property);
}

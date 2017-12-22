package at.spot.core.persistence.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import at.spot.core.infrastructure.exception.ModelNotFoundException;
import at.spot.core.infrastructure.exception.ModelSaveException;
import at.spot.core.infrastructure.exception.ModelValidationException;
import at.spot.core.model.Item;
import at.spot.core.persistence.exception.ModelNotUniqueException;

@Service
public interface PersistenceService {
	/**
	 * Saves the given models and all of its dependent models.
	 * 
	 * @param type
	 * @return
	 */
	<T extends Item> void save(T... models) throws ModelSaveException, ModelNotUniqueException;

	/**
	 * Saves the given models and all of its dependent models.
	 * 
	 * @param type
	 * @return
	 */
	<T extends Item> void save(List<T> models) throws ModelSaveException, ModelNotUniqueException;

	/**
	 * Returns an object based on its PK.
	 * 
	 * @param pk
	 * @return
	 */
	<T extends Item> T load(Class<T> type, long pk) throws ModelNotFoundException;

	/**
	 * Refreshes the given model's properties.
	 * 
	 * @param pk
	 * @return
	 * @throws ModelNotFoundException
	 */
	<T extends Item> void refresh(T item) throws ModelNotFoundException;

	/**
	 * Returns an object based on the given search parameters (key = property
	 * name, value = property value).
	 * 
	 * @param type
	 * @param searchParameters
	 *            if empty or null, all items of the given type will be
	 *            returned.
	 */
	<T extends Item> List<T> load(Class<T> type, Map<String, Object> searchParameters);

	/**
	 * Returns a result stream for the given query.
	 * 
	 * @param queryString
	 *            the JPQL query string
	 * @param resultClass
	 *            the mapped type of the results. If this is a JPA entity, it
	 *            will be mapped directly. If it is a regular POJO, its
	 *            properties will be mapped based on the result column names.
	 */
	<T extends Item> Stream<T> query(String query, Class<T> resultClass);

	/**
	 * Returns the paginated result stream for the given query.
	 * 
	 * @param queryString
	 *            the JPQL query string
	 * @param resultClass
	 *            the mapped type of the results. If this is a JPA entity, it
	 *            will be mapped directly. If it is a regular POJO, its
	 *            properties will be mapped based on the result column names.
	 * @param page
	 *            will only be set if it is >= 0
	 * @param pageSize
	 *            will only be set if it is > 0
	 */
	<T extends Item> Stream<T> query(String queryString, Class<T> resultClass, int page, int pageSize);

	/**
	 * Returns an object based on the given search parameters (key = property
	 * name, value = property value).
	 * 
	 * @param type
	 * @param searchParameters
	 *            if empty or null, all items of the given type will be
	 *            returned.
	 * @param start
	 *            defines the amount of items that are being skipped.
	 * @param amount
	 *            starting from the start param this is the amount of items that
	 *            will be returned. the items will be just proxies that are
	 *            lazy-loaded.
	 */
	<T extends Item> List<T> load(final Class<T> type, final Map<String, Object> searchParameters, final Integer page,
			final Integer pageSize);

	/**
	 * Removes the given item.
	 * 
	 * @param item
	 */
	@SuppressWarnings("unchecked")
	<T extends Item> void remove(T... items);

	/**
	 * Removes the item of the given type with the given PK.
	 * 
	 * @param type
	 * @param pk
	 */
	<T extends Item> void remove(Class<T> type, long pk);

	/**
	 * Saves the database to disk. This has to be done before the application
	 * quits to prevent data corruption.
	 */
	void saveDataStorage();

	/**
	 * Clears all stored data from the storage.
	 */
	void clearDataStorage();

	/**
	 * Initialize a new item.
	 * 
	 * @param joinPoint
	 * @param rel
	 * @return
	 * @throws Throwable
	 */
	<T extends Item> void initItem(T item);

	/**
	 * Detaches a given item model from the underlying persistence
	 * implementation. This is useful if serializing the item causes problems.
	 * The effect can be different depending on the persistence service
	 * implementation, but in general lazy-loading properties will not work
	 * anymore afterwards.
	 * 
	 * @param items
	 */
	<T extends Item> void detach(T... items);

	/**
	 * Converts the given item to a map.
	 * 
	 * @param item
	 * @return
	 * @throws ModelValidationException
	 */
	<T extends Item> Map<String, Object> convertItemToMap(T item);
}

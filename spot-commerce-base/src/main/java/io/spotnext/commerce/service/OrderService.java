package io.spotnext.commerce.service;

import java.util.List;

import io.spotnext.commerce.exception.OrderCancellationException;
import io.spotnext.itemtype.commerce.customer.Customer;
import io.spotnext.itemtype.commerce.enumeration.OrderStatus;
import io.spotnext.itemtype.commerce.order.Order;

/**
 * This service provides functionality around the order object.
 */
public interface OrderService {

	/**
	 * Creates a new order order for the given customer.
	 */
	Order createNewOrder(Customer customer);

	/**
	 * Returns all orders for the given status that fit the filter criteria.
	 */
	List<Order> getOrders(Customer customer, OrderStatus... filter);

	/**
	 * Gets an order by its ID.
	 */
	Order getOrderById(String orderId);

	/**
	 * Creates a new order based on the given one. A new order will be created,
	 * but the order entries, and settings will be the same. <br>
	 * Prices and other conditions may change though, depending on the service
	 * implementation.
	 */
	Order reorder(Order sourceOrder);

	/**
	 * Cancels the given order:
	 */
	void cancelOrder(Order order) throws OrderCancellationException;
}

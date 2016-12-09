package at.spot.core.model.user;

import at.spot.core.infrastructure.annotation.ItemType;
import at.spot.core.infrastructure.annotation.Property;
import at.spot.core.model.Item;

@ItemType
public abstract class Principal extends Item {

	private static final long serialVersionUID = 1L;

	@Property(unique = true)
	public String uid;

	@Property
	public String name;
}

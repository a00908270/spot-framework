/**
 * This file is auto-generated. All changes will be overwritten.
 */
package io.spotnext.itemtype.cms.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import io.spotnext.core.infrastructure.annotation.Accessor;
import io.spotnext.core.infrastructure.annotation.ItemType;
import io.spotnext.core.infrastructure.annotation.Property;

import io.spotnext.itemtype.cms.model.CmsComponent;

import java.io.Serializable;

import java.lang.String;


@SuppressWarnings("unchecked")
@SuppressFBWarnings({"MF_CLASS_MASKS_FIELD",
    "EI_EXPOSE_REP",
    "EI_EXPOSE_REP2"
})
@ItemType(persistable = true, typeCode = "abstractnavigationentry")
public abstract class AbstractNavigationEntry extends CmsComponent {
    /** Default serialVersionUID value. */
    private static final long serialVersionUID = 1L;
    public static final String TYPECODE = "abstractnavigationentry";
    public static final String PROPERTY_NAME = "name";

    /**
     * The navigation entry name.
     */
    @Property(readable = true, writable = true)
    protected String name;

    /**
     * The navigation entry name.
     */
    @Accessor(propertyName = "name", type = io.spotnext.core.infrastructure.type.AccessorType.set)
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The navigation entry name.
     */
    @Accessor(propertyName = "name", type = io.spotnext.core.infrastructure.type.AccessorType.get)
    public String getName() {
        return this.name;
    }
}
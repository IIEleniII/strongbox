package org.carlspring.strongbox.data.domain;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orientechnologies.orient.core.annotation.OId;
import com.orientechnologies.orient.core.annotation.OVersion;

/**
 * Base class for all entities that have to be stored in OrientDB.
 * <p>
 * <b>Implementation notice</b>: don't declare variables with the same names as it's in this class ({@link #objectId},
 * {@link #detachAll} etc.) It will hide that variables and change behaviour of persistence subsystem to unpredictable.
 *
 * @see {@link GenericEntityHook}
 *
 * @author Alex Oreshkevich
 * @author Sergey Bespalov
 */
@MappedSuperclass
@Inheritance
public abstract class GenericEntity
        implements Serializable
{
    /**
     * This is internal `OrientDB` object identifier which may differ's because of internal OrientDB layout. At the first
     * time it will be something like #-1:-2 and then this object will be placed in some cluster in async way and it
     * will have different objectId.
     */
    @Id
    @OId
    @JsonIgnore
    @XmlTransient
    protected String objectId;
    
    protected String uuid;
    
    @OVersion
    @JsonIgnore
    protected Long entityVersion;

    public GenericEntity()
    {
    }

    @XmlTransient
    public final String getObjectId()
    {
        return objectId;
    }

    public final void setObjectId(String objectId)
    {
        this.objectId = objectId;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

}

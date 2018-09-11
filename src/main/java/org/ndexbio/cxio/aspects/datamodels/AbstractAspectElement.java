package org.ndexbio.cxio.aspects.datamodels;


import org.ndexbio.cxio.core.interfaces.AspectElement;

public abstract class AbstractAspectElement implements AspectElement {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public int compareTo(final AspectElement o) {
        if ((o != null) && (o.getAspectName() != null) && (getAspectName() != null)) {
            return getAspectName().compareTo(o.getAspectName());
        }
        return 0;
    }
    
}

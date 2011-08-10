package org.rodrigoramalho;

import org.exoplatform.container.ExoContainerContext;

public class ExoUtils {
	/**
	 * Get a service from the portal container
	 * @param type : component type
	 * @return the concrete instance retrieved in the container using the type as key
	 */
	public static <T>T getService(Class<T> type) {
		return (T)ExoContainerContext.getCurrentContainer().getComponentInstanceOfType(type);
	}

}

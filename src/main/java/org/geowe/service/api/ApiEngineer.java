package org.geowe.service.api;

import java.util.Collection;

import org.geowe.service.model.ResourceDef;

/**
 * This class represents the API expert. It is responsible for build and
 * retrieve the rest resources info.
 * 
 * @author lotor
 *
 */
public interface ApiEngineer {

	Collection<ResourceDef> getAll();

}

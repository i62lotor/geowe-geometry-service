/*******************************************************************************
 * Copyright 2017 geowe.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.geowe.service.constraints;

import org.geowe.service.constraints.group.SimplicityGroup;
import org.geowe.service.constraints.group.TopologyGroup;
import org.geowe.service.constraints.group.VertexOrientationGroup;

/**
 * Type of geometry validation available.
 * @author rafa@geowe.org
 *
 */
public enum GeometryValidationGroupDef {

	TOPOLOGY("topology", TopologyGroup.class),
	SIMPLICITY("simplicity", SimplicityGroup.class),
	ORIENTATION("orientation",VertexOrientationGroup.class);
	
	private String name;
	private Class<?> clazz;
	
	private GeometryValidationGroupDef(String name, Class<?> clazz) {
		this.name = name;
		this.clazz = clazz;
	}

	public String getName() {
		return name;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	
	
}

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
package org.geowe.service.geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.geowe.service.geometry.engine.GeoEngineer;
import org.geowe.service.geometry.engine.JTSGeoEngineer;
import org.geowe.service.geometry.engine.JTSGeoEngineerHelper;
import org.geowe.service.geometry.test.DivisionTest;
import org.geowe.service.model.FlatGeometry;
import org.geowe.service.model.OperationData;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.GZIP;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.util.LineStringExtracter;
import com.vividsolutions.jts.operation.polygonize.Polygonizer;

/**
 * Rest end point for JTS Division operantions resources
 * 
 * @author rafa@geowe.org
 *
 */
@Path("/jts/division")
public class JtsDivideService {

	private final Logger log = Logger.getLogger(JtsDivideService.class);
	
	@Path("/polygons")
	@POST
	@GZIP
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response divide(@NotNull @Valid OperationData operationData) {
		GeoEngineer geoEngineer = new JTSGeoEngineer();
		
		return Response.status(Status.CREATED).entity(geoEngineer.dividePolygons(operationData)).build();
	}
	
}


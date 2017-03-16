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
package org.geowe.service.geometry.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.geowe.service.geometry.FlatGeometryBuilder;
import org.geowe.service.geometry.JtsDivideService;
import org.geowe.service.geometry.engine.GeometryExtractor;
import org.geowe.service.geometry.engine.JTSGeoEngineerHelper;
import org.geowe.service.model.DivisionData;
import org.geowe.service.model.FlatGeometry;
import org.geowe.service.model.OperationData;
import org.geowe.service.model.error.ErrorEntity;
import org.jboss.logging.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.util.Assert;

public class DivisionTest {

	private final Logger log = Logger.getLogger(DivisionTest.class);

	private static final String SERVICE_URL = "http://127.0.0.1:8080/ggs/operations/jts/division";
	private ResteasyClient restClient;
	private ResteasyWebTarget target;
	private static final String POLYGON_WKT = "POLYGON ((-5.9685606135835565 38.333782907228816,-3.9141172545765723 38.31654526867596,-3.9360899108226595 37.52790590365409,-5.81475201986113 37.40582726607118,-5.9685606135835565 38.333782907228816))";
	private static final String LINE_WKT = "LINESTRING (-6.210259817917375 38.259416305016615,-5.188531302475292 37.679136788498276,-4.370049857309488 37.87018194277469,-3.463677787159337 38.272355099999935)";
	private static final String DIVISION_LINE_WKT = "LINESTRING(-4.85070171987867 37.840905948919264,-4.757317930832915 37.66935417624159)";
	@Before
	
	
	public void setUp() throws Exception {
		restClient = new ResteasyClientBuilder().build();
		target = restClient.target(SERVICE_URL);
	}


	private FlatGeometry getFlatGeometry(String wkt) {
		return new FlatGeometryBuilder().id("id-1").crs("WGS84").wkt(wkt).build();
	}
	

	private void printGC(List<FlatGeometry> fgeoms) {
		Geometry geom = new GeometryExtractor()
				.createGeometryCollection(new JTSGeoEngineerHelper().toGeometries(fgeoms));
		log.info("GEOMETRY COLLECTION: " + geom);
	}

	
	
	@Test
	public void divideLine(){
		DivisionData divisionData = new DivisionData();
		divisionData.setWktDivisionLine(DIVISION_LINE_WKT);
		divisionData.setWktToDivide(LINE_WKT);
		
		target = restClient.target(SERVICE_URL + "/line");
		Response response = target.request()
				.post(Entity.entity(divisionData, "application/json;charset=UTF-8"));
		
		List<String> dividedGeometries = response
				.readEntity(new GenericType<List<String>>() {});
		response.close();
		
		Assert.isTrue(response.getStatus() == Status.CREATED.getStatusCode());
		Assert.isTrue(dividedGeometries.size() == 2);
	}
	
	@Test
	public void divideLineBadDivisionLine(){
		DivisionData divisionData = new DivisionData();
		divisionData.setWktDivisionLine(POLYGON_WKT);
		divisionData.setWktToDivide(LINE_WKT);
		
		target = restClient.target(SERVICE_URL + "/line");
		Response response = target.request()
				.post(Entity.entity(divisionData, "application/json;charset=UTF-8"));
		
		ErrorEntity error= response
				.readEntity(ErrorEntity.class);
		response.close();
		log.info(error);
		Assert.isTrue(response.getStatus() == Status.BAD_REQUEST.getStatusCode());
	}
	
	@Test
	public void divideLineBadLineToDivide(){
		DivisionData divisionData = new DivisionData();
		divisionData.setWktDivisionLine(DIVISION_LINE_WKT);
		divisionData.setWktToDivide(POLYGON_WKT);
		
		target = restClient.target(SERVICE_URL + "/line");
		Response response = target.request()
				.post(Entity.entity(divisionData, "application/json;charset=UTF-8"));
		
		ErrorEntity error= response
				.readEntity(ErrorEntity.class);
		response.close();
		log.info(error);
		Assert.isTrue(response.getStatus() == Status.BAD_REQUEST.getStatusCode());
	}
	
	@Test
	public void dividePolygon(){
		DivisionData divisionData = new DivisionData();
		divisionData.setWktDivisionLine(LINE_WKT);
		divisionData.setWktToDivide(POLYGON_WKT);
		
		target = restClient.target(SERVICE_URL + "/polygon");
		Response response = target.request()
				.post(Entity.entity(divisionData, "application/json;charset=UTF-8"));
		
		List<String> dividedGeometries = response
				.readEntity(new GenericType<List<String>>() {});
		response.close();
		
		Assert.isTrue(response.getStatus() == Status.CREATED.getStatusCode());
		Assert.isTrue(dividedGeometries.size() == 2);
	}
	
	@Test
	public void divideBadPolygonToDivide(){
		DivisionData divisionData = new DivisionData();
		divisionData.setWktDivisionLine(LINE_WKT);
		divisionData.setWktToDivide(DIVISION_LINE_WKT);
		
		target = restClient.target(SERVICE_URL + "/polygon");
		Response response = target.request()
				.post(Entity.entity(divisionData, "application/json;charset=UTF-8"));
		
		ErrorEntity error= response
				.readEntity(ErrorEntity.class);
		response.close();
		log.info(error);
		Assert.isTrue(response.getStatus() == Status.BAD_REQUEST.getStatusCode());
	}

	

}

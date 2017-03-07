/*******************************************************************************
 * Copyright 2016 lotor
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
package org.geowe.service.geometry.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.geowe.service.model.FlatGeometry;
import org.jboss.logging.Logger;
import org.jboss.resteasy.spi.ReaderException;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

/**
 * This class represents the JTSGeoEngineer assistant. It is responsible to
 * perform simple geometries conversions
 * 
 * @author lotor
 *
 */
public class JTSGeoEngineerHelper {

	private final Logger log = Logger.getLogger(JTSGeoEngineerHelper.class);

	public Geometry getGeom(String wkt) {
		Geometry geom;
		try {
			WKTReader wktReader = new WKTReader();
			geom = wktReader.read(wkt);
		} catch (ParseException e) {
			throw new ReaderException("Error Reading wkt");
		}
		return geom;
	}

	public List<Geometry> toGeometries(Collection<FlatGeometry> entities) {
		List<Geometry> geometries = new ArrayList<Geometry>();
		for (FlatGeometry flatGeometry : entities) {
			geometries.add(getGeom(flatGeometry.getWkt()));
		}

		return geometries;
	}

	// TODO: Consider working with FlatGeometry instead of strings
	public List<String> getBasicGeometries(String wkt) {
		final List<String> elementsWkt = new ArrayList<String>();

		Geometry geomContorno = getGeom(wkt);
		if (geomContorno != null) {
			elementsWkt.addAll(getPolygons(geomContorno));
			elementsWkt.addAll(getLineStrings(geomContorno));
			elementsWkt.addAll(getPoints(geomContorno));
			elementsWkt.addAll(getMultiGeometries(geomContorno));
		}
		return elementsWkt;
	}

	public List<String> getPolygons(Geometry geom) {
		final List<String> polygonsWkt = new ArrayList<String>();
		if (geom instanceof Polygon) {
			polygonsWkt.add(geom.toText());
		} else if (geom instanceof MultiPolygon) {

			final MultiPolygon multiPolygon = (MultiPolygon) geom;
			for (int i = 0; i < multiPolygon.getNumGeometries(); i++) {
				final Polygon pol = (Polygon) multiPolygon.getGeometryN(i);
				polygonsWkt.add(pol.toText());
			}
		}
		return polygonsWkt;
	}

	public List<String> getLineStrings(Geometry geom) {
		final List<String> lineStringsWkt = new ArrayList<String>();
		if (geom instanceof LineString) {
			lineStringsWkt.add(geom.toText());
		} else if (geom instanceof MultiLineString) {
			final MultiLineString multiLine = (MultiLineString) geom;
			for (int i = 0; i < multiLine.getNumGeometries(); i++) {
				final LineString pol = (LineString) multiLine.getGeometryN(i);
				lineStringsWkt.add(pol.toText());
			}
		}
		return lineStringsWkt;
	}

	public List<String> getPoints(Geometry geom) {
		final List<String> pointsWkt = new ArrayList<String>();

		if (geom instanceof Point) {
			pointsWkt.add(geom.toText());
		} else if (geom instanceof MultiPoint) {
			final MultiPoint multiPoint = (MultiPoint) geom;
			for (int i = 0; i < multiPoint.getNumGeometries(); i++) {
				final Point pol = (Point) multiPoint.getGeometryN(i);
				pointsWkt.add(pol.toText());
			}
		}
		return pointsWkt;
	}
	
	public List<String> getMultiGeometries(Geometry geom){
		final List<String> geomsWkt = new ArrayList<String>();
		if (geom instanceof GeometryCollection) {
			
			final GeometryCollection gc = (GeometryCollection) geom;
			for (int i = 0; i < gc.getNumGeometries(); i++) {
				final Geometry geometry = gc.getGeometryN(i);
				geomsWkt.add(geometry.toText());
			}
		}
		return geomsWkt;
	}
	
}

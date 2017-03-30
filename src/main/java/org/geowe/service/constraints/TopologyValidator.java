package org.geowe.service.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.geowe.service.geometry.engine.JTSGeoEngineerHelper;

import com.vividsolutions.jts.geom.Geometry;

public class TopologyValidator implements ConstraintValidator<CheckTopology, String> {

	private JTSGeoEngineerHelper helper;
	
	@Override
	public void initialize(CheckTopology constraintAnnotation) {
		helper = new JTSGeoEngineerHelper();
	}

	@Override
	public boolean isValid(String wkt, ConstraintValidatorContext context) {
		Geometry geom = helper.getGeom(wkt);
		return geom.isValid();
	}

}

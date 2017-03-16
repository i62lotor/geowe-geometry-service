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
package org.geowe.service.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * This class represents Division DAta. It is responsible for storing the data
 * to be operated
 * 
 * @author rafa@geowe.org
 *
 */
public class DivisionData implements Serializable {

	private static final long serialVersionUID = 4419249969903477601L;

	@NotNull @NotEmpty
	private String wktToDivide;
	@NotNull @NotEmpty
	private String wktDivisionLine;

	public String getWktToDivide() {
		return wktToDivide;
	}

	public void setWktToDivide(String wktToDivide) {
		this.wktToDivide = wktToDivide;
	}

	public String getWktDivisionLine() {
		return wktDivisionLine;
	}

	public void setWktDivisionLine(String wktDivisionLine) {
		this.wktDivisionLine = wktDivisionLine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((wktDivisionLine == null) ? 0 : wktDivisionLine.hashCode());
		result = prime * result + ((wktToDivide == null) ? 0 : wktToDivide.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DivisionData other = (DivisionData) obj;
		if (wktDivisionLine == null) {
			if (other.wktDivisionLine != null)
				return false;
		} else if (!wktDivisionLine.equals(other.wktDivisionLine))
			return false;
		if (wktToDivide == null) {
			if (other.wktToDivide != null)
				return false;
		} else if (!wktToDivide.equals(other.wktToDivide))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DivisionData [wktToDivide=" + wktToDivide + ", wktDivisionLine=" + wktDivisionLine + "]";
	}
}

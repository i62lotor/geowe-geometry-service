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
import java.util.ArrayList;
import java.util.List;

/**
 * Represent the validation result
 * @author rafa@geowe.org
 *
 */
public class ValidationResult implements Serializable {


	private static final long serialVersionUID = 7523296227095064779L;
	private boolean isValid;
	private FlatGeometry validatedFlatGeometry;
	private List<ValidationError> errors = new ArrayList<ValidationResult.ValidationError>();

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public FlatGeometry getValidatedFlatGeometry() {
		return validatedFlatGeometry;
	}

	public void setValidatedFlatGeometry(FlatGeometry validatedFlatGeometry) {
		this.validatedFlatGeometry = validatedFlatGeometry;
	}

	public List<ValidationError> getErrors() {
		return errors;
	}

	public void setErrors(List<ValidationError> errors) {
		this.errors = errors;
	}
	
	public void addValidationError(ValidationError error){
		errors.add(error);
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errors == null) ? 0 : errors.hashCode());
		result = prime * result + (isValid ? 1231 : 1237);
		result = prime * result + ((validatedFlatGeometry == null) ? 0 : validatedFlatGeometry.hashCode());
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
		ValidationResult other = (ValidationResult) obj;
		if (errors == null) {
			if (other.errors != null)
				return false;
		} else if (!errors.equals(other.errors))
			return false;
		if (isValid != other.isValid)
			return false;
		if (validatedFlatGeometry == null) {
			if (other.validatedFlatGeometry != null)
				return false;
		} else if (!validatedFlatGeometry.equals(other.validatedFlatGeometry))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "ValidationResult [isValid=" + isValid + ", validatedFlatGeometry=" + validatedFlatGeometry + ", errors="
				+ errors + "]";
	}


	/**
	 * Represents an error in validation
	 * @author rafa@geowe.org
	 *
	 */
	public class ValidationError {
		private int id;
		private String description;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public String toString() {
			return "ValidationError [id=" + id + ", description=" + description + "]";
		}
		
	}
}

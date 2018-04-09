package br.com.itau.model;

import java.io.Serializable;

public class DistanceRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6527653332982139033L;
	
	private String source;
	private String destination;
	private String metricSystem;
	private String returnType;
		

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getMetricSystem() {
		return metricSystem;
	}

	public void setMetricSystem(String metricSystem) {
		this.metricSystem = metricSystem;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}


	@Override
	public String toString() {
		return "DistanceRequest [source=" + source + ", destination=" + destination + ", metricSystem=" + metricSystem
				+ ", returnType=" + returnType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((metricSystem == null) ? 0 : metricSystem.hashCode());
		result = prime * result + ((returnType == null) ? 0 : returnType.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
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
		DistanceRequest other = (DistanceRequest) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (metricSystem == null) {
			if (other.metricSystem != null)
				return false;
		} else if (!metricSystem.equals(other.metricSystem))
			return false;
		if (returnType == null) {
			if (other.returnType != null)
				return false;
		} else if (!returnType.equals(other.returnType))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}

}

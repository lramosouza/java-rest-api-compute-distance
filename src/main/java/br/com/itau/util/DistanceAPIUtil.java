package br.com.itau.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ResourceBundle;

import br.com.itau.model.DistanceRequest;

public class DistanceAPIUtil {

	public static DistanceRequest buildDistanceRequest(String source, String destination, String metricSystem,
			String returnType) {

		DistanceRequest distanceRequest = new DistanceRequest();

		distanceRequest.setDestination(destination);
		distanceRequest.setSource(source);
		distanceRequest.setMetricSystem(metricSystem);
		distanceRequest.setReturnType(returnType);

		return distanceRequest;
	}

	public static void validateRequest(DistanceRequest distanceRequest) throws Exception {
		validateSourceAndDestination(distanceRequest);
		validateMetricSystem(distanceRequest);
		validateReturnType(distanceRequest);
	}

	private static void validateReturnType(DistanceRequest distanceRequest) throws Exception {
		if (distanceRequest.getReturnType() != null) {
			if (!"xml".equalsIgnoreCase(distanceRequest.getReturnType())
					&& !"json".equalsIgnoreCase(distanceRequest.getReturnType())) {
				throw new Exception(loadErrorMessage("invalidReturnType"));
			}
		} else {
			throw new Exception(loadErrorMessage("invalidReturnType"));
		}
	}

	private static void validateMetricSystem(DistanceRequest distanceRequest) throws Exception {
		if (distanceRequest.getMetricSystem() != null) {
			if (!"km".equalsIgnoreCase(distanceRequest.getMetricSystem())
					&& !"mi".equalsIgnoreCase(distanceRequest.getMetricSystem())) {
				throw new Exception(loadErrorMessage("invalidUnit"));
			}
		} else {
			throw new Exception(loadErrorMessage("invalidUnit"));
		}
	}

	private static void validateSourceAndDestination(DistanceRequest distanceRequest) throws Exception {
		if (distanceRequest.getDestination() != null && distanceRequest.getSource() != null) {
			if (distanceRequest.getDestination().equalsIgnoreCase(distanceRequest.getSource())) {
				throw new Exception(loadErrorMessage("sameSourceAndDestination"));
			}
		} else {
			throw new Exception(loadErrorMessage("invalidSourceOrDestination"));
		}
	}

	public static String roundDoubleValue(Double doubleValue) {
		return new BigDecimal(doubleValue).setScale(3, RoundingMode.HALF_EVEN).toString();
	}

	public static String loadErrorMessage(String property) {
		String message = ResourceBundle.getBundle("Messages").getString(property);
		if (message != null) {
			return message;
		} else {
			return "An Error Occurred. Message not found";
		}

	}

	public static boolean isXMLRequest(String returnType) {
		return returnType != null && returnType.equalsIgnoreCase("xml");
	}

	public static boolean isRequestByKilometer(String metricSystem) {
		return metricSystem.equalsIgnoreCase("km");
	}

}

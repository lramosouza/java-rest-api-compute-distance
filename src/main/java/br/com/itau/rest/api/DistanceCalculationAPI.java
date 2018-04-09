package br.com.itau.rest.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.itau.dao.DAOFactory;
import br.com.itau.dao.DestinationDAO;
import br.com.itau.model.City;
import br.com.itau.model.DistanceRequest;
import br.com.itau.rest.business.DistanceCalculatorBusiness;
import br.com.itau.util.DistanceAPIUtil;

@Path("/distanceCalculator")
public class DistanceCalculationAPI {

	DistanceCalculatorBusiness business = new DistanceCalculatorBusiness();

	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("{source}/{destination}/{metricSystem}/{returnType}")
	public Response getUserHistory(@PathParam("source") String source, @PathParam("destination") String destination,
			@PathParam("metricSystem") String metricSystem, @PathParam("returnType") String returnType) throws Exception {

		try {

			DistanceRequest distanceRequest = DistanceAPIUtil.buildDistanceRequest(source, destination,
					metricSystem, returnType);

			DistanceAPIUtil.validateRequest(distanceRequest);

			DestinationDAO destinationDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL).getDestinationDAO();

			List<City> citiesList = destinationDAO.getCoordinatesByCityName(distanceRequest);
			
			Double distance = business.computeDistance(citiesList.get(0).getLatitude(),
					citiesList.get(0).getLongitude(), citiesList.get(1).getLatitude(), citiesList.get(1).getLongitude(),
					metricSystem);
			
			return buildResponse(returnType, distance);

		} catch (Exception e) {
			return Response.status(500).type(MediaType.APPLICATION_JSON).entity(e.getMessage()).build();
		}
	}

	private Response buildResponse(String returnType, Double distance) {
		if (DistanceAPIUtil.isXMLRequest(returnType)) {
			returnType = MediaType.APPLICATION_XML;
			return Response.status(200).type(MediaType.APPLICATION_XML)
					.entity("<distance>" + DistanceAPIUtil.roundDoubleValue(distance) + "</distance>")
					.build();
		}else {
			returnType = MediaType.APPLICATION_JSON;
			return Response.ok(DistanceAPIUtil.roundDoubleValue(distance), returnType)
					.build();
		}
	}

}
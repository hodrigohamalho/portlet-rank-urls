package org.rodrigoramalho;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.google.gdata.data.analytics.DataEntry;
import com.google.gdata.util.ServiceException;

public class UrlsMaisAcessadasServlet extends GenericPortlet{
	
	private List<String> errors = new ArrayList<String>();
	
	@Override
	/**
	 * Metodo chamado antes de carregar o modo VIEW do portlet.
	 */
	protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		
		try {
			// Objeto utilizado para persistir informacoes dos portlets
			PortletPreferences portletPreferences = request.getPreferences();
			Integer maxUrls = Integer.valueOf(portletPreferences.getValue("maxUrls", "5"));
			
			List<DataEntry> dataEntry = AnalyticsClient.getUrlsMaisAcessadas(maxUrls);
			request.setAttribute("entries", dataEntry);
			
		} catch (ServiceException e) {
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher("/jsp/index.jsp");
		rd.include(request, response);
	}
	
	/**
	 * Metodo chamado antes de carregar o modo EDIT do portlet.
	 */
	@Override
	protected void doEdit(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		PortletPreferences portletPreferences = request.getPreferences();
		Integer maxUrls = Integer.valueOf(portletPreferences.getValue("maxUrls", "5"));
		
		request.setAttribute("maxUrls", maxUrls);
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher("/jsp/edit.jsp");
		rd.include(request, response);
	}
	
	/**
	 * Metodo chamado ao se fazer um submit usando o <portlet:actionURL/>
	 */
	@Override
	public void processAction(ActionRequest request, ActionResponse response)
			throws PortletException, IOException {
		
		// 5 eh o default.
		Integer maxUrls = Integer.valueOf(5);
		
		try{
			maxUrls = Integer.valueOf(request.getParameter("maxUrls"));
		}catch (Exception e) {
			errors.add(e.getMessage());
			request.setAttribute("errors", errors);
		}
		
		// Objeto utilizado para persistir informacoes dos portlets
		PortletPreferences portletPreferences = request.getPreferences();
		portletPreferences.setValue("maxUrls", maxUrls.toString());
		portletPreferences.store();
		
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher("/jsp/index.jsp");
		response.setPortletMode(PortletMode.VIEW);
		rd.include(request, response);
	}
	
}

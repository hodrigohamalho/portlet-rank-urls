package org.rodrigoramalho;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gdata.client.analytics.AnalyticsService;
import com.google.gdata.client.analytics.DataQuery;
import com.google.gdata.data.analytics.AccountEntry;
import com.google.gdata.data.analytics.AccountFeed;
import com.google.gdata.data.analytics.DataEntry;
import com.google.gdata.data.analytics.DataFeed;
import com.google.gdata.util.ServiceException;

/**
 * 
 * @author rodrigo ramalho
 *         hodrigohamalho@gmail.com
 *
 */
public class AnalyticsClient {

  public static final PropertiesUtil properties = new PropertiesUtil("dados.properties");
  public static final String ACCOUNTS_URL= properties.getValor("account.url");
  public static final String DATA_URL = properties.getValor("data.url");
  public static final String ACCOUNT_NAME = properties.getValor("account.name");
  public static final String USERNAME = properties.getValor("username");
  public static final String PASSWORD = properties.getValor("password");
  

  /**
   * Returns a data feed containing the accounts that the user logged in to the
   * given AnalyticsService has access to.
   *
   * @param myService The AnalyticsService to request accounts from
   * @return An AccountFeed containing an entry for each profile the logged-in
   *     user has access to
   * @throws IOException If an error occurs while trying to communicate with
   *     the Analytics server
   * @throws ServiceException If the API cannot fulfill the user request for
   *     any reason
   */
  public static AccountFeed getAvailableAccounts(AnalyticsService myService)
      throws IOException, ServiceException {

    URL feedUrl = new URL(ACCOUNTS_URL);
    return myService.getFeed(feedUrl, AccountFeed.class);
  }

  /**
   * Runs through all the examples using the given GoogleService instance.
   *
   * @param service An unauthenticated AnalyticsService object
   * @throws ServiceException If the service is unable to handle the request
   * @throws IOException If there is an error communicating with the server
   */
  public static List<DataEntry> getUrlsMaisAcessadas(Integer maxUrls)
      throws ServiceException, IOException {

    // Authenticate using ClientLogin
	AnalyticsService service = new AnalyticsService("url_mais_acessadas");
    service.setUserCredentials(USERNAME, PASSWORD);

    // Print a list of all accessible accounts
    AccountFeed accountFeed = getAvailableAccounts(service);
    if (accountFeed.getEntries().isEmpty()) {
      throw new ServiceException("Não existem accounts para esse perfil");
    }

    String tableId = "";
    
    // Extrai o tableId da conta (ACCOUNT_NAME)
    for (AccountEntry profile : accountFeed.getEntries()){
    	if (profile.getTitle().getPlainText().equals(ACCOUNT_NAME)){
    		tableId = profile.getTableId().getValue();
    		break;
    	}
    }
    
    // Print the results of a basic request
    DataQuery urlsMaisAcessadasQuery = getUrlsMaisAcessadas(service, tableId, maxUrls);
    DataFeed urlsMaisAcessadas = service.getFeed(urlsMaisAcessadasQuery, DataFeed.class);
    
    return urlsMaisAcessadas.getEntries();
  }

  /**
   * Request the top pages paths, page titles and pageviews, 
   * in descending order by pageviews, for a particular profile.
   * @param {AnalyticsService} Google Analytics service object that
   *     is authorized through Client Login.
   */
  private static DataQuery getUrlsMaisAcessadas(AnalyticsService analyticsService, String tableId, Integer maxUrls)
      throws IOException, MalformedURLException, ServiceException {

	DataQuery query = new DataQuery(new URL(DATA_URL));
	
	// Pega data atual e subtrai 1 mes.
	Calendar calendar = Calendar.getInstance();
	calendar.add(Calendar.MONTH, -1);
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String startDate = dateFormat.format(calendar.getTime());
	String endDate = dateFormat.format(new Date());
	
    query.setStartDate(startDate);
    query.setEndDate(endDate);
    query.setDimensions("ga:pageTitle,ga:pagePath");
    query.setMetrics("ga:pageviews");
    query.setSort("-ga:pageviews");
    query.setMaxResults(maxUrls);
    query.setIds(tableId);
    
    return query;
  }
}

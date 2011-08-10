package org.rodrigoramalho;

import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author rodrigo ramalho
 *         hodrigohamalho@gmail.com
 *
 */
public class PropertiesUtil {
	private Properties props;

	public PropertiesUtil(String propertiesName) {
		props = new Properties();

		try{
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesName);
			props.load(in);
			in.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getValor(String chave) {
		return (String) props.getProperty(chave);
	}
}

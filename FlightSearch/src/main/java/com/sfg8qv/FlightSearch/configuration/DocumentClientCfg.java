package com.sfg8qv.FlightSearch.configuration;

import org.apache.http.HttpHost;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.DocumentClient;

@Configuration
public class DocumentClientCfg {
	
	@Bean
	public ConnectionPolicy getConnectionPolicy()
	{
		ConnectionPolicy objConnectionPolicy = new ConnectionPolicy();
		HttpHost objHttpHost = new HttpHost("pun-proxy", 3128);
		objConnectionPolicy.setProxy(objHttpHost);
		
		return objConnectionPolicy;
	}
	
	@Bean
	public DocumentClient getDocumentClient()
	{
		DocumentClient objDocumentClient = new DocumentClient("https://rjcosmosdb.documents.azure.com:443/",
                "UKHcSrtLL4QgsjxtdPrKaW2WFQclj8YJB3QY8b52LkmWqjEBrOyxoWiM8P3WBUmi9lwTVOZNyrp3qenq2E3Qgw==", 
                getConnectionPolicy(),
                ConsistencyLevel.Session);
		
		return objDocumentClient;
	}

}

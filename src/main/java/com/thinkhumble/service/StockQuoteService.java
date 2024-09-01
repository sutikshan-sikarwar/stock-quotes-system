package com.thinkhumble.service;

import com.thinkhumble.models.StockQuote;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class StockQuoteService {

    @Value("${alphavantage.apikey}")
    private String apiKey;

    private final WebClient webClient;

    public StockQuoteService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://www.alphavantage.co").build();
    }
    
    private Map<String, String> getCompanyOverview(String symbol) {
        String url = String.format("/query?function=OVERVIEW&symbol=%s&apikey=%s", symbol, apiKey);

        return this.webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(Map.class)
            .block();
    }

    public StockQuote getQuoteBySymbol(String symbol) {
        String url = String.format("/query?function=TIME_SERIES_INTRADAY&symbol=%s&interval=5min&apikey=%s", symbol, apiKey);

        Map<String, Object> response = this.webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(Map.class)
            .block();
        StockQuote stockQuote = new StockQuote();

        if (response.containsKey("Time Series (5min)")) {
            Map<String, Object> timeSeries = (Map<String, Object>) response.get("Time Series (5min)");
            Map.Entry<String, Object> latestEntry = timeSeries.entrySet().iterator().next();
            Map<String, String> latestData = (Map<String, String>) latestEntry.getValue();
            Map<String, String> overviewResponse = getCompanyOverview(symbol);
            
            
            stockQuote.setSymbol(symbol);
            stockQuote.setCompanyName((String) overviewResponse.get("Name"));
            stockQuote.setPrice(Double.parseDouble(latestData.get("1. open")));
            stockQuote.setOpeningPrice(Double.parseDouble(latestData.get("1. open")));
            stockQuote.setMarketCap((String) overviewResponse.get("MarketCapitalization"));
            stockQuote.setChange(Double.parseDouble(latestData.get("4. close")) - stockQuote.getPrice());
            stockQuote.setPercentageChange((stockQuote.getChange() / stockQuote.getPrice()) * 100);
            stockQuote.setWeek52High(Double.parseDouble((String) overviewResponse.get("52WeekHigh")));
            stockQuote.setWeek52Low(Double.parseDouble((String) overviewResponse.get("52WeekLow")));
            stockQuote.setDividendRate(Double.parseDouble((String) overviewResponse.get("DividendYield")));
            stockQuote.setTimestamp(latestEntry.getKey());

            
        }
        else {
        	System.out.println("time stamp sahi nahi h bhaiii....");
        	
        }
        

        return stockQuote;
    }
}

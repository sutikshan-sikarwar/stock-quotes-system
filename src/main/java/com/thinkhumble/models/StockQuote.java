package com.thinkhumble.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockQuote {
    private String symbol;
    private String companyName;
    private double price;
    private String marketCap;
    private double openingPrice;
    private double change;
    private double percentageChange;
    private double dividendRate;
    private double week52High;
    private double week52Low;
    private String timestamp;
}

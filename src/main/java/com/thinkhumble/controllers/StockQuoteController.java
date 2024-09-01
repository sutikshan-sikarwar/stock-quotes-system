package com.thinkhumble.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinkhumble.models.StockQuote;
import com.thinkhumble.service.StockQuoteService;

@RestController
@RequestMapping("/api/stocks")
public class StockQuoteController {

    @Autowired
    private StockQuoteService stockQuoteService;

    @GetMapping("/quote")
    public StockQuote getQuoteBySymbol(@RequestParam String symbol, Authentication authentication) {
        return stockQuoteService.getQuoteBySymbol(symbol);
    }
}

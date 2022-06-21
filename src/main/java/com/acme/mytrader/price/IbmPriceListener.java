package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;

import java.util.Objects;

public class IbmPriceListener implements PriceListener {
    private static final String IBM_SECURITY = "IBM";
    private static final double TRIGGER_PRICE = 50L;
    private static final int VOLUME = 100;

    private final ExecutionService executionService;

    public IbmPriceListener(ExecutionService executionService) {
        Objects.requireNonNull(executionService);
        this.executionService = executionService;
    }

    @Override
    public void priceUpdate(String security, double price) {
        if (IBM_SECURITY.equals(security) && TRIGGER_PRICE > price) {
            this.executionService.buy(IBM_SECURITY, price, VOLUME);
        }
    }

}

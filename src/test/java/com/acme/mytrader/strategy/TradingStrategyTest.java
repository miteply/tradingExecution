package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.IbmPriceListener;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;	

public class TradingStrategyTest {

    @Test
    public void shouldBuyOnlyTwoTimes() {
        ExecutionService executionService = mock(ExecutionService.class);
        PriceSource priceSource = mock(PriceSource.class);
        PriceListener ibmPriceListener = new IbmPriceListener(executionService);

        TradingStrategy tradeStrategy = new TradingStrategy(priceSource);
        tradeStrategy.registerListener(ibmPriceListener);

        // Data
        ibmPriceListener.priceUpdate("IBM", 49L);
        ibmPriceListener.priceUpdate("IBM", 48L);
        ibmPriceListener.priceUpdate("IBM", 48L);
        ibmPriceListener.priceUpdate("CSTEAM", 60L);
        ibmPriceListener.priceUpdate("APPLE", 70L);
        ibmPriceListener.priceUpdate("IBM", 55L);
        ibmPriceListener.priceUpdate("GOOGLE", 120L);

        // We expect to buy only once at 49
        verify(executionService, times(1)).buy("IBM", 49L, 100);

        // We expect to buy only twice at 48
        verify(executionService, times(2)).buy("IBM", 48L, 100);

        verify(executionService, atMost(3)).buy(any(), anyLong(), anyInt());
    }

}

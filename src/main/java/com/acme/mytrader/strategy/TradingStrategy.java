package com.acme.mytrader.strategy;

import java.util.Objects;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {

	private final PriceSource priceSource;

	public TradingStrategy(PriceSource priceSource) {
		Objects.requireNonNull(priceSource);
		this.priceSource = priceSource;
	}

	public void registerListener(PriceListener listener) {
		this.priceSource.addPriceListener(listener);
	}
}

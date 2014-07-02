package com.vincent.trade;

import java.io.IOException;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeFactory;
import com.xeiam.xchange.btcchina.BTCChinaExchange;
import com.xeiam.xchange.btcchina.dto.marketdata.BTCChinaTicker;
import com.xeiam.xchange.btcchina.service.polling.BTCChinaMarketDataServiceRaw;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.marketdata.Ticker;
import com.xeiam.xchange.service.polling.PollingMarketDataService;
 
/**
 * Demonstrate requesting Ticker at BTCChina. You can access both the raw data from BTCChina or the XChange generic DTO data format.
 */
public class TickerDemo {
 
  public static void main(String[] args) throws IOException {
 
    // Use the factory to get BTCChina exchange API using default settings
    Exchange btcChina = ExchangeFactory.INSTANCE.createExchange(BTCChinaExchange.class.getName());
 
    // Interested in the public polling market data feed (no authentication)
    PollingMarketDataService marketDataService = btcChina.getPollingMarketDataService();
 
    generic(marketDataService);
//    raw((BTCChinaMarketDataServiceRaw) marketDataService);
  }

	private static void generic(PollingMarketDataService marketDataService)
			throws IOException {

		while (true) {
			Ticker ticker = marketDataService.getTicker(CurrencyPair.BTC_CNY);

			System.out.println(ticker.toString());

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
 
//  private static void raw(BTCChinaMarketDataServiceRaw marketDataService) throws IOException {
// 
//    BTCChinaTicker BTCChinaTicker = marketDataService.getBTCChinaTicker();
// 
//    System.out.println(BTCChinaTicker.toString());
//  }
 
}
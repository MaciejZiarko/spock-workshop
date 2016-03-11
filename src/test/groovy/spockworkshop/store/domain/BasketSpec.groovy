package spockworkshop.store.domain

import org.joda.money.CurrencyUnit
import org.joda.money.Money
import spock.lang.Specification

class BasketSpec extends Specification {

    private static final CurrencyUnit currency = CurrencyUnit.GBP


    private BasketPosition basketPosition(int numberOfItems, BigDecimal price) {
        return new BasketPosition(numberOfItems, productWithPrice(price))
    }

    private Product productWithPrice(BigDecimal price) {
        return new Product("Sample id", "Sample product", Money.of(currency, price));
    }

}

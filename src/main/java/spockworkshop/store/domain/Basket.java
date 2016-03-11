package spockworkshop.store.domain;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class Basket {

    private final CurrencyUnit supportedCurrency;
    private final List<BasketPosition> basketPositions = new ArrayList<>();

    public Basket(CurrencyUnit supportedCurrency) {
        checkArgument(supportedCurrency != null, "Supported currency is required");
        this.supportedCurrency = supportedCurrency;
    }

    public Basket removeAllPositions() {
        basketPositions.clear();
        return this;
    }

    public int getNumberOfPositions() {
        return basketPositions.size();
    }

    public Basket addPosition(BasketPosition basketPosition) {
        checkArgument(basketPosition != null, "Basket position must be provided");
        verifyCurrency(basketPosition.getCurrency());
        basketPositions.add(basketPosition);
        return this;
    }

    public Basket addAllPositions(List<BasketPosition> positions) {
        checkArgument(positions.size() > 0, "At least one basket position must be provided");
        positions.forEach(this::addPosition);
        return this;
    }

    public Money getTotalPrice() {
        return basketPositions.stream()
                .map(BasketPosition::getTotalPriceForBasketPosition)
                .reduce(Money.zero(supportedCurrency), Money::plus);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "supportedCurrency=" + supportedCurrency +
                ", basketPositions=" + basketPositions +
                '}';
    }

    private void verifyCurrency(CurrencyUnit currency) {
        if (!currency.equals(supportedCurrency)) {
            throw new IllegalArgumentException(currency + " currency is not supported!");
        }
    }
}

package practice3;

import java.math.BigDecimal;
import java.util.List;

public class PriceCaculator{

    public List<OrderLineItem> orderLineItemList;
    public List<BigDecimal> discounts;
    public BigDecimal tax;

    public PriceCaculator(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts){
        this.orderLineItemList = orderLineItemList;
        this.discounts = discounts;
        this.tax = new BigDecimal(0.1);
    }

    public BigDecimal calculateall() {
        BigDecimal subTotal = new BigDecimal(0);

        // Total up line items
        for (OrderLineItem lineItem : orderLineItemList) {
            subTotal = subTotal.add(lineItem.getPrice());
        }

        // Subtract discounts
        for (BigDecimal discount : discounts) {
            subTotal = subTotal.subtract(discount);
        }

        // calculate tax
        BigDecimal tax = subTotal.multiply(this.tax);

        // calculate GrandTotal
        BigDecimal grandTotal = subTotal.add(tax);

        return grandTotal;
    }
}

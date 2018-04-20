package practice2;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

    private BigDecimal tax;

    public Receipt() {
        tax = new BigDecimal(0.1);
        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = new BigDecimal(0.0);

        for (Product product : products) {

            subTotal = calculateEachSub(product, items, subTotal);

            subTotal = subTotal.subtract(calculateEachReduce(product, items));
        }
        BigDecimal taxTotal = subTotal.multiply(tax);
        BigDecimal grandTotal = subTotal.add(taxTotal);

        return grandTotal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private BigDecimal calculateEachReduce(Product product, List<OrderItem> items){

        OrderItem curItem = findOrderItemByProduct(items, product);

        BigDecimal reducedPrice = product.getPrice()
                .multiply(product.getDiscountRate())
                .multiply(new BigDecimal(curItem.getCount()));

        return reducedPrice;
    }

    private OrderItem findOrderItemByProduct(List<OrderItem> items, Product product) {
        OrderItem curItem = null;
        for (OrderItem item : items) {
            if (item.getCode() == product.getCode()) {
                curItem = item;
                break;
            }
        }
        return curItem;
    }

    private BigDecimal calculateEachSub(Product product, List<OrderItem> items, BigDecimal subTotal) {

            OrderItem item = findOrderItemByProduct(items, product);
            BigDecimal itemTotal = product.getPrice().multiply(new BigDecimal(item.getCount()));
            subTotal = subTotal.add(itemTotal);

        return subTotal;
    }
}

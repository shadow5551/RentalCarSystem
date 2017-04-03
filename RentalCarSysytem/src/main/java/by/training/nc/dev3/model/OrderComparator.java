package main.java.by.training.nc.dev3.model;

import java.util.Comparator;

/**
 * Created by dima on 3.4.17.
 */
class OrderComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        return o1.getUser().compareTo(o2.getUser());
    }
}

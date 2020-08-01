package com.plakadee.sellice.Spiner;

import com.plakadee.sellice.DataObj.Car;
import com.plakadee.sellice.DataObj.Line;

public interface SelectCallback {
    public void selected(Car car);
    public void selected(Line line);
}

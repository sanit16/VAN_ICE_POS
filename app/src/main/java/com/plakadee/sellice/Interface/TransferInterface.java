package com.plakadee.sellice.Interface;

import com.plakadee.sellice.DataObj.Car;
import com.plakadee.sellice.DataObj.Line;

public interface TransferInterface {
    public void SelectLine(Line data);
    public void SelectCar(Car data);
    public void MoveCarCenter(int position);

}

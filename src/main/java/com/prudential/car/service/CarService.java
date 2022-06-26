package com.prudential.car.service;

import com.prudential.car.vo.CarVO;

import java.util.List;

/**
 * @Author:qutingting
 * @Description: 车辆service接口
 */
public interface CarService {
	/**
	 * 查询可租车辆
	 * @return
	 */
	List<CarVO> listCar();

	/**
	 * 扣车辆库存
	 * @param id
	 * @return
	 */
	int reduceStock(Long id);

	/**
	 * 加车辆库存
	 * @param id
	 * @return
	 */
	int addStock(Long id);
}

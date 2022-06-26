package com.prudential.car.mapper;

import com.prudential.car.entity.Car;
import com.prudential.car.vo.CarVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:qutingting
 * @Description: CarMapper
 */
@Mapper
public interface CarMapper {
	/**
	 * 查询可租车辆
	 *
	 * @return
	 */
	List<CarVO> listCar();

	/**
	 * 扣车辆库存
	 *
	 * @param id
	 */
	int reduceStock(Long id);

	/**
	 * 加车辆库存
	 *
	 * @param id
	 */
	int addStock(Long id);
}
package com.prudential.car.service.impl;

import com.prudential.car.mapper.CarMapper;
import com.prudential.car.service.CarService;
import com.prudential.car.vo.CarVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:qutingting
 * @Description: 车辆service实现类
 */
@Service
@Slf4j
public class CarServiceImpl implements CarService {
    @Resource
    private CarMapper carMapper;

    @Override
    public List<CarVO> listCar() {
        return carMapper.listCar();
    }

    @Override
    public int reduceStock(Long id) {
        return carMapper.reduceStock(id);
    }

    @Override
    public int addStock(Long id) {
        return carMapper.addStock(id);
    }
}

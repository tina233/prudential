package com.prudential.car.controller;

import com.prudential.car.common.RestResult;
import com.prudential.car.service.CarService;
import com.prudential.car.vo.CarVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:qutingting
 * @Description: 车辆controller
 */
@RestController
@RequestMapping("/car")
@Api(value = "CarController")
public class CarController {
    @Resource
    private CarService carService;

    @ApiOperation(value = "查询可租车辆")
    @GetMapping(value = "/list")
    public RestResult<List<CarVO>> listCar() {
        return RestResult.success(carService.listCar());
    }
}

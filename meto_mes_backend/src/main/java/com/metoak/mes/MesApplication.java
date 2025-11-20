package com.metoak.mes;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.metoak.mes.entity.Calibresult;
import com.metoak.mes.mapper.CalibresultMapper;
import com.metoak.mes.service.impl.CalibresultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "com.metoak.mes")
public class MesApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MesApplication.class, args);
	}

}

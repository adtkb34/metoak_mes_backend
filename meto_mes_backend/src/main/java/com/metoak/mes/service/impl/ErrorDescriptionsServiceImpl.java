package com.metoak.mes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metoak.mes.entity.ErrorDescriptions;
import com.metoak.mes.mapper.ErrorDescriptionsMapper;
import com.metoak.mes.service.IErrorDescriptionsService;
import org.springframework.stereotype.Service;

@Service
public class ErrorDescriptionsServiceImpl  extends ServiceImpl<ErrorDescriptionsMapper, ErrorDescriptions> implements IErrorDescriptionsService {
}

package com.lemon.admin.dept.service.impl;

import com.lemon.admin.dept.entity.Dept;
import com.lemon.admin.dept.mapper.DeptMapper;
import com.lemon.admin.dept.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author xubb
 * @since 2020-11-04
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

}

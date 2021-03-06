package com.idatrix.unisecurity.auditLog.controller;

import com.github.pagehelper.PageInfo;
import com.idatrix.unisecurity.auditLog.service.AuditLogService;
import com.idatrix.unisecurity.auditLog.vo.LogSearchVO;
import com.idatrix.unisecurity.common.domain.AuditLog;
import com.idatrix.unisecurity.common.utils.ResultVoUtils;
import com.idatrix.unisecurity.common.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AuditLogController
 * @Description
 * @Author ouyang
 * @Date 2018/8/28 13:36
 * @Version 1.0
 **/
@SuppressWarnings("all")
@Slf4j
@Validated
@RestController
@RequestMapping("/auditLog")
@Api(value = "/AuditLogController", tags = "安全管理-日志操作处理接口")
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;

    @ApiOperation(value = "获取当前租户下的日志列表", notes = "暂不提供关键字查询功能")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVo list(LogSearchVO search){
        PageInfo<AuditLog> pageInfo = auditLogService.findPage(search);
        return ResultVoUtils.ok(pageInfo);
    }

    @ApiOperation(value = "清除三个月前的日志信息", notes = "这里是不分租户的清理")
    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public ResultVo clear(){
        auditLogService.clearLog();
        return ResultVoUtils.ok();
    }

}

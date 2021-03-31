package com.lemon.pay;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.config.ali.AliPayConfig;
import com.lemon.print.attachInfo.entity.AttachInfo;
import com.lemon.print.attachInfo.service.AttachInfoService;
import com.lemon.print.orderInfo.entity.OrderInfo;
import com.lemon.print.orderInfo.service.OrderInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xubb
 * @Description
 * @create 2021-02-22 22:46
 */
@RestController
@Slf4j
@RequestMapping("/alipay")
public class AliPayController {

    @Resource
    private AttachInfoService attachInfoService;
    @Resource
    AliPayService aliPayService;


    @Resource
    private OrderInfoService orderInfoService;

    @ApiOperation("支付接口")
    @GetMapping(value = "/pay", produces = "application/xml")
    public void alipay(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        DecimalFormat df = new DecimalFormat("0.00");

        QueryWrapper<AttachInfo> qw = new QueryWrapper<>();
        qw.eq("order_guid", id);
        List<AttachInfo> attachGuidList = attachInfoService.list(qw);

        double sum = 0;
        for (AttachInfo attach : attachGuidList) {
//单面
            if ("1".equals(attach.getSides())) {
                sum = sum + 0.2 * Double.valueOf(attach.getPageNum()) * Double.valueOf(attach.getCopies());

            } else {
//                双面
                sum = sum + 0.3 * Double.valueOf(attach.getPageNum()) * Double.valueOf(attach.getCopies());
            }

        }


        String format = df.format(sum);
        String form = aliPayService.aliPay("打印付款", id,format, id);

        System.out.println(form);

        response.setContentType("text/html;charset=" + AliPayConfig.charset);
        response.getWriter().write(form);
        response.getWriter().flush();
        response.getWriter().close();


    }


    @ApiOperation("支付接口2测试")
    @GetMapping(value = "/pay2", produces = "application/xml")
    public void alipay2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String form = aliPayService.aliPay("打印付款", "11111110001","100.00", "xhfd0001");
        System.out.println(form);
        response.setContentType("text/html;charset=" + AliPayConfig.charset);
        response.getWriter().write(form);
        response.getWriter().flush();
        response.getWriter().close();

    }



    @ApiOperation("跳转前的处理")
    @PostMapping("/notify_url")
    public void notifyAlipay(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> params = convertRequestParamsToMap(request); // 将异步通知中收到的待验证所有参数都存放到map中
        String paramsJson = JSON.toJSONString(params);
        log.info("notify_url支付宝回调，{}", paramsJson);
        String out_trade_no = params.get("out_trade_no");
        log.info(out_trade_no);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUnitguid(out_trade_no);
        orderInfo.setOrderPayStatus("1");
        int i = RandomUtil.randomInt(1000, 9999);
        orderInfo.setDeliveryCode(String.valueOf(i));
        orderInfoService.updateById(orderInfo);
    }

    @ApiOperation("支付成功后最终跳转到哪个页面")
    @GetMapping("/return_url")
    public void returnAlipay(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> params = convertRequestParamsToMap(request); // 将异步通知中收到的待验证所有参数都存放到map中
        String paramsJson = JSON.toJSONString(params);
        log.info("return_url支付宝回调，{}", paramsJson);
        try {
            String out_trade_no = params.get("out_trade_no");
            log.info(out_trade_no);
            OrderInfo order = orderInfoService.getById(out_trade_no);
            response.sendRedirect("http://iprint.ink/js/website/order_check.html?delivery_code="+order.getDeliveryCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<String, String>();

        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }

        return retMap;
    }


}

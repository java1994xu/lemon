package com.lemon.pay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lemon.config.ali.AliPayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description：支付宝业务逻辑接口
 */
@Slf4j
@Service
public class AliPayService {

    /**
     * 支付服务(推荐传入订单号和金额,其余的从其他地方获取)
     */
    public String aliPay(String subject, String orderNo, String total, String code) {

        try {

            //2、构建客户端
            DefaultAlipayClient defaultAlipayClient = new DefaultAlipayClient(AliPayConfig.url,
                    AliPayConfig.app_id, AliPayConfig.merchant_private_key, "json",
                    AliPayConfig.charset, AliPayConfig.alipay_public_key, AliPayConfig.sign_type);
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();


            Map<String,Object> params  = new HashMap<>();
            params.put("out_trade_no",orderNo);
            params.put("total_amount",total);
            params.put("subject",subject);
            params.put("body",code);
            params.put("timeout_express","90m");
            params.put("product_code","FAST_INSTANT_TRADE_PAY");
            request.setBizContent(JSONObject.toJSONString(params));

            log.info("调用支付接口：请求参数为："+JSONObject.toJSONString(params));

/*            request.setBizContent("{\"out_trade_no\":\"" + orderNo + "\","
                    + "\"total_amount\":\"" + total + "\","
                    + "\"subject\":\"" + subject + "\","
                    + "\"body\":\"" + code + "\","
                    + "\"timeout_express\":\"90m\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");*/

            //异步通知后回调
            request.setNotifyUrl(AliPayConfig.notify_url);

            //支付成功后回调
            request.setReturnUrl(AliPayConfig.return_url);
            String response = defaultAlipayClient.pageExecute(request).getBody();
            return response;
        } catch (AlipayApiException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

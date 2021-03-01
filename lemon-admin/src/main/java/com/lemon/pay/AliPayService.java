package com.lemon.pay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lemon.config.ali.AliPayConfig;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description：支付宝业务逻辑接口
 */
@Service
public class AliPayService {

    /**
     * 支付服务(推荐传入订单号和金额,其余的从其他地方获取)
     */
    public String aliPay(String subject, String orderNo, String total, String code) {

        try {
//            //1、构建支付数据信息
//            AlipayTradePagePayModel model = new AlipayTradePagePayModel();
//            //订单标题
//            model.setSubject(subject);
//            //订单号
//            model.setOutTradeNo(orderNo);
//            //订单付款金额
//            model.setTotalAmount(total);
//            //订单付款时间
//            model.setTimeoutExpress("");
//            //销售产品码(必填)
//            model.setProductCode(code);
            //2、构建客户端
            DefaultAlipayClient defaultAlipayClient = new DefaultAlipayClient(AliPayConfig.url,
                    AliPayConfig.app_id, AliPayConfig.merchant_private_key, "json",
                    AliPayConfig.charset, AliPayConfig.alipay_public_key, AliPayConfig.sign_type);
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();

            request.setBizContent("{\"out_trade_no\":\"" + orderNo + "\","
                    + "\"total_amount\":\"" + total + "\","
                    + "\"subject\":\"" + subject + "\","
                    + "\"body\":\"" + code + "\","
                    + "\"timeout_express\":\"90m\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

            //异步通知后回调
            request.setNotifyUrl(AliPayConfig.notify_url);

            //支付成功后回调
            request.setReturnUrl(AliPayConfig.return_url);
//            request.setBizModel(model);
            String response = defaultAlipayClient.pageExecute(request).getBody();
            return response;
        } catch (AlipayApiException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

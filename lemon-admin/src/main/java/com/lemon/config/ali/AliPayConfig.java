package com.lemon.config.ali;

import org.springframework.context.annotation.Configuration;

/**
 * @author xubb
 * @Description 支付接口
 * @create 2021-02-22 22:37
 */
@Configuration
public class AliPayConfig {

    /**
     * netApp映射地址
     */
    private static String neturl = "http://122.51.59.149:8080/lemon";
    /**
     * 应用的APPID
     * 沙箱的appid
     * 2021002128609652
     * 2021000117613535
     */
    public static String app_id = "2021002129623280";
    /**
     * 商户私钥，您的PKCS8格式RSA2私钥  刚刚生成的私钥直接复制填写(填写自己利用第三方工具生成的私钥)
     */
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCi0qKJspXKzW316Ib2pnuhTsLW9Edkk9LNYEqCD+yUi//YoebwTY8LzgOdUKNj/bsKSFi8CxyvIm7QSqLNBy+wV0DORYj9G6w66urI27as5PBI93FQD+42k6CEqmyPcF0j/xNJPTgS14y1pIIT3nw6X98cwWhx5hZF7c6zDDRIaib5U6J7XO4uJQbA6FOVe7fR1yBkUU9P0KQXbvUpXFcOx5jg6RuJxwxLhzqKAPJhp7PMrxUt5t/ld6shaURw4XbjGUowkfec/D+HTv68Iwuf6QPKVB5/AHit5atgC0Vhp3pVUMKGThv91Z1aQrc1RTxipGz6XgWJ+mRrTs5E/m+xAgMBAAECggEAdjbz54GPm+S2t1mR07ZgAGfMsoA87VseLBqDPhcNr3RN1x0FLOdCulFi8m6/kjN//yEzu6NduhIH3paxyKY1C5Tt6lU19n6G92fj+tz6rmCxzGhLDtPiIiHg4zFRDHuKRD5H5F7cPWHeOWstPfvEuiUtOhV6HWfuN+tGrVxpAUFXvYcZGnIVYwscIFDM/Tb4JzpFk5TNYyfgnIuXM1+/fdw1yKf5Z5BKl13b9nAn7ltf56CO5U18XscBmGCVq5J752RvoPMb8iQRJ0TKLmLZiQ5bcGy/e9hlrofPbvr43Ny7I1fLQbq7Y7ZAuojX8SbyBQm38XQRXxgS64XshjRemQKBgQDbURJWsGwUvpAoshTtCIyITStgEz1qdkrJGSCCK+b9xBNJpWbocakkgmMB/qM3Z9IusZK3rxEY960LRtxFRy5xxDZwpCWOYZPU23TAzV7xQWNTxu6M2xCppMsj/+ssOEyba38OnjAol9ed8Ok4FliXhqn0jUSsQhrKaqEW7iQq2wKBgQC+Doylxf7T9qofpT1yomQGbyBYpu7D9JmUmMU+u4ZFLAn0BdvSiHpu2gsp4FyWeVZifsKWsATHtoTR5IvS1w8OaFLnHuPCoh2VBFXZaem1/zqcGjUuFQzoRj8tx8ch45vBe7xMEln81TabC5jGl1mFmbprXhaW8/TPaJZCIDenYwKBgQCl1/p8H1C7pxawD2oPjEVo1KIWNrZciTMoOp4AkV5NSzYinLbi0wBKoWZpHbsirJmdwZ3m1LWjIbdzLZRnttoCIZPhPd9Aeb0mWwVAvowq93063tWJIibJgdozUNTvTWtwo6sizLAyIhBkWMajKRK5EpX2XdUuRZaotlU2V8J1vQKBgQCkvifcakwBoAbHd4xUGb9qdFktf0XoRdX6oJIZVqOEwi++fnWVgQffntGecwTOyk6/AfHafKkoFGX9KoapNBGxA20ZOgVjaPMLgL6Jvuz+/UXomi8OyuBtVrbdEBbkoU8Z4A/QQPgP2SGENAEx42+OBzs4Zk5eewjx4E6e8x5K0QKBgQCynrKsAFai17vPgHnlgWwtB0aGvM55jJw+/dYukjxYZ8BBdmRpM3I+kbrX9FqImRzGFaQYWRm+qMkrFRmZbqHg41SWk0XKt8xNWPfaiYF3Zev+6RAgLYQPw9iiyJjySTwRgm0HK3bi0MEGgIcvWxw/gV8zRVo/Dw8J7jbqrPu51g==";
    /**
     * 支付宝公钥,对应APPID下的支付宝公钥(填写沙箱环境中的支付宝公钥)
     */
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApG1aHvJ9qtP6YtWGTmQm2ttPtkbZ/Y2TDtLW1cG/Uqp+5RGlJwmswPBUnvIVFOrQGWzfRTHjcrpUoKFAIUeErLMW7/HAZRisojsbz8/f2h1/qv9rGyyK/BcMNkfFbaB0mlYsm4kWB+6h9F3yqxgbpQ3nlc1h/3xfbGeFXO8ubdGguC5C9V6GEPrnRP+y06QmsRubvEfUkyETa3ci5qeYNyETIrzypKhWiJr6+X8giQld0fncegaDKoW5JC2w8oyCRqc6lEAgSLMw1rAfr533vi2L9/tqssjWcDtj/Zxf3VX5VX3EKaFfRQw/Ux+fwTnDL0cUYigg25+wVaxlk6ZhgQIDAQAB";
    /**
     * 服务器异步通知页面路径 需http://格式的完整路径(支付宝完成后返回的页面)
     */
    public static String notify_url = neturl+"/alipay/notify_url";
    /**
     * 页面跳转同步通知页面路径 需http://格式的完整路径(支付宝完成后返回的页面)
     */
    public static String return_url = neturl+"/alipay/return_url";
    /**
     * 签名方式
     */
    public static String sign_type = "RSA2";
    /**
     * 字符编码方式
     */
    public static String charset = "utf-8";
    /**
     * 支付宝网关
     */
    public static String url = "https://openapi.alipaydev.com/gateway.do";
}

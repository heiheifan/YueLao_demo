package com.heifan.demo.controller;

import com.alipay.api.internal.util.file.ByteArrayOutputStream;
import com.egzosn.pay.ali.api.AliPayConfigStorage;
import com.egzosn.pay.ali.api.AliPayService;
import com.egzosn.pay.ali.bean.AliTransactionType;
import com.egzosn.pay.common.api.PayService;
import com.egzosn.pay.common.bean.MethodType;
import com.egzosn.pay.common.bean.PayOrder;
import com.egzosn.pay.common.http.HttpConfigStorage;
import com.heifan.demo.domain.alipay.AliPayMessageHandler;
import com.heifan.demo.domain.alipay.AliPayMessageInterceptor;
import com.sun.javafx.fxml.BeanAdapter;
import com.sun.javafx.fxml.BeanAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
@RestController
public class PayController {
    private PayService service = null;

    @PostConstruct
    public void init() {
        AliPayConfigStorage aliPayConfigStorage = new AliPayConfigStorage();
        aliPayConfigStorage.setPid("2088621957040301");
        aliPayConfigStorage.setAppId("2021000118644670");
        aliPayConfigStorage.setKeyPublic("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmbxsEViQnluxP7Q7J6hpN3gvy4t9km7I35hPohosfmvmnD2Vd1OQKxBX79tb0HUUmNrH5oE5bGyr5r4kbO6uGy1O4Ck7XqbVwMHNJEIvFUmjU89qVuS2SPWYn/Tj8aVkUFh9W1gQ5Dc87TyMxnnD5ti7T3Tqi8N9WsNTtqaBjxnMLuDBlNro2b24MQtVCjBaYq1GcfY+8fEWcHikeB/fnxIMLADBq4NdcQRYfsyII5AoXHH6yHPVVue+RH8XJ1SmkatJ1vrgxQK6NnJZcwTB3tyW9AbCZ+X+zz9FhfG9qfj4x1siItQgZ+FYrI+ABWbeELFL9XsDyiR/OBfYZg4CbQIDAQAB");
        aliPayConfigStorage.setKeyPrivate("MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCM5Uq85+WPebWXRq84rDXMzwP2XlUxk3+N2hhAX94WegrJwXmXpVyVmBWVa3jsz5v+44BVQp6e1Rhz1xqZSlC4P06RyMdjuRJezZeKUVFbtYoJrsbJlL5HaYjYDusXiiK6xwMZB416F3MjZenNB9Z4XJ/ZChqGWIpOvhy7cD4MetD5RWMumuxEn/3MxjCYDIT1ArrF60n7y2h9VLg6C27GpmTZyBiLkVB1xcMWnGFGxONnkZQL9KCLfSik1OpdtNAUgwQAE63W/IC9QcyffNF/VK6rNhhj3Hi95Jz9NASTyByU/DhR0iiIlI37Ly5nP9TUUs1o3tZ2VCExWzPOGfpFAgMBAAECggEAbvwPK07gHWyaBle/nrzIhFRtopwcL7koOO0bPVpG7x2jcdndlf7EN3JdTh4iWygOkXFTndWkbSke4eEHKsjHXNtBYMTvizY1qkf4nxP/KmaOHaJ3JELuwAH5sXfO5/P/93Dcl9LDNvFR2QhmawNJhfYwVB8JVdpY2D4ZE+0Oc3T0u+6U+McwkY27Vua6QH7XdCJam3J81Nwru7mXos+nzTrdMbM7r7uGLZ1wrg/mbbt/ZaBKYwVwKUnjylkkcUvN6UNfIi/atJqA4sISM9RxRULNqzdolyX+Wyo0xy82LkfR6DVdlc6X4zW0NIwj+eZPTU/g4b0ePV7SyJfAqhL6AQKBgQD5tmAKIHA1bkrXFbhM+8LFYv2HvpuQ5c+1snLuC82aduPxcCrnuBplPp5uFNg8SUKfl/gW0xlIrpWv60gwTmiI9t5OcjWwnEmFNXRJqjiqEQPFrz0nf7Mr93Lg0+pNX1hzECQP5t79xBu4yjNryuIR01jED8EpG+cEy897P2/00QKBgQCQcX5RC3z8dPy9KnzsgDH8QMOg7e+jJpm/IX+BwiPpMbcNl8ocnlks8gcqvUpUr+bizwGMH0UDIYaRk+qswfofQrCwIZvVI5WjWrio0Kf7WPPdsA5D7kjZ4X9XtMrwTfRxEPe1KAsFY1QZkuQS4DuZQGAzExDnLpBq8vZFswZbNQKBgAhHmMWdL7tFzet1x+edjWa4Eon2svfJ9uoiYZctAwM1H/e4bWPDgfSLb/tca5MMJs5d3pb7JuJ3D3WGypz3bJXmAZb+SHNzqyhkjleG8lwJNmPOnKt7m3hAuyljPWiVWbxu85MnZ4tofjbRVYUkc/x9UnslvpTbXPyWuWlvwAmRAoGAGhIyH4S4UYT4HViu5a0Z2JMD/GcV6tfLxmDQd+ZHvw53IN/13gaCyAcDXiZ+ZRy+ys9cUyNNvIvvkxUktXvpnnzWEWRvkR/0IWD9lZLbzxU2Jxxg5sf6pED8UCNUwd0AMuxCGZOZsaZFBrypp/KLL1UcR/u4AFI6ROOHqfrvOcUCgYASrVjWS+i7sFenhnhot3UNEp4dXH4CjQqSqeo12wFiDxpQQlkB2w2VtJ0adgtoClQHYpafHiLuxODnxTXkiTH8eLwXeOe7Z0aQVaAaex6HLn4yPGWt7K+CNv3F9xdbe+pVxtCJL3zMBbuic8hGT0arJvHhK4SrwhktQnkCnxFVYA==");
        aliPayConfigStorage.setNotifyUrl("http://baidu.com");
        aliPayConfigStorage.setReturnUrl("http://baidu.com");
//        aliPayConfigStorage.setSignType("签名方式");
//        aliPayConfigStorage.setSeller("收款账号");
        aliPayConfigStorage.setInputCharset("utf-8");
        //是否为测试账号，沙箱环境
        aliPayConfigStorage.setTest(true);

        // service = new AliPayService(aliPayConfigStorage);
        //请求连接池配置
        HttpConfigStorage httpConfigStorage = new HttpConfigStorage();
        //最大连接数
        httpConfigStorage.setMaxTotal(20);
        //默认的每个路由的最大连接数
        httpConfigStorage.setDefaultMaxPerRoute(10);
        service =  new AliPayService(aliPayConfigStorage, httpConfigStorage);
        //以下拦截器与处理器用于支付与业务隔离简化版回调
        //增加支付回调消息拦截器
        service.addPayMessageInterceptor(new AliPayMessageInterceptor());
        //设置回调消息处理
//        service.setPayMessageHandler(spring.getBean(AliPayMessageHandler.class));

    }

    /**
     * 跳到支付页面
     * 针对实时支付,即时付款
     * @return
     */
    @RequestMapping(value = "toPay.html", produces = "text/html;charset=UTF-8")
    public String toPay() {

        PayOrder order = new PayOrder("订单title", "摘要", new BigDecimal(0.01) , UUID.randomUUID().toString().replace("-", ""));
        //网页支付
        order.setTransactionType(AliTransactionType.PAGE);
        //获取支付订单信息
        Map orderInfo = service.orderInfo(order);
        //组装成html表单信息
        return  service.buildRequest(orderInfo, MethodType.POST);
    }


    /**
     * 获取二维码图像
     * 二维码支付
     * @return
     */
    @RequestMapping(value = "toQrPay.jpg", produces = "image/jpeg;charset=UTF-8")
    public byte[] toWxQrPay( ) throws IOException {
        PayOrder order = new PayOrder("订单title", "摘要", new BigDecimal(0.01) , UUID.randomUUID().toString().replace("-", ""));
        //扫码付
        order.setTransactionType(AliTransactionType.SWEEPPAY);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(service.genQrPay(order), "JPEG", baos);
        return baos.toByteArray();
    }

    /**
     * 支付回调地址 方式一
     *
     * 方式二，{@link #payBack(HttpServletRequest)} 是属于简化方式， 试用与简单的业务场景
     *
     *
     * @param request 请求
     *
     * @return 返回对应的响应码
     * @see #payBack(HttpServletRequest)
     */
    @Deprecated
    @RequestMapping(value = "payBackBefore.json")
    public String payBackBefore(HttpServletRequest request) throws IOException {

        //获取支付方返回的对应参数
        Map<String, Object> params = service.getParameter2Map(request.getParameterMap(), request.getInputStream());
        if (null == params) {
            return service.getPayOutMessage("fail", "失败").toMessage();
        }

        //校验
        if (service.verify(params)) {
            //这里处理业务逻辑
            //......业务逻辑处理块........
            return service.getPayOutMessage("success", "成功").toMessage();
        }

        return service.getPayOutMessage("fail", "失败").toMessage();
    }
    /**
     * 支付回调地址
     *
     * @param request 请求
     *
     * @return 返回对应的响应码
     *
     * 业务处理在对应的PayMessageHandler里面处理，在哪里设置PayMessageHandler，详情查看{@link com.egzosn.pay.common.api.PayService#setPayMessageHandler(com.egzosn.pay.common.api.PayMessageHandler)}
     *
     * 如果未设置 {@link com.egzosn.pay.common.api.PayMessageHandler} 那么会使用默认的 {@link com.egzosn.pay.common.api.DefaultPayMessageHandler}
     *
     */
    @RequestMapping(value = "payBack.json")
    public String payBack(HttpServletRequest request) throws IOException {
        //业务处理在对应的PayMessageHandler里面处理，在哪里设置PayMessageHandler，详情查看com.egzosn.pay.common.api.PayService.setPayMessageHandler()
        return service.payBack(request.getParameterMap(), request.getInputStream()).toMessage();
    }

}

package com.wang.alipay.controller;

import cn.hutool.core.util.IdUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.wang.alipay.properties.AliPayProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @description: 阿里支付 网站页面支付 沙箱
 * @author: wei·man cui
 * @date: 2020/11/20 10:54
 */
@Slf4j
@Controller
@RequestMapping("/webPay")
public class AliPayWebPayController {

    @Resource
    private AlipayClient alipayClient;

    @Resource
    private AliPayProperties aliPayProperties;

    @GetMapping("/toPay")
    public void aliPayWebPay(HttpServletResponse response) throws IOException, AlipayApiException {
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(IdUtil.simpleUUID());
        model.setSubject("沙箱测试订单商品名称");
        model.setTotalAmount("10.00");
        model.setBody("这个商品是沙箱测试订单，这里是商品描述");
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        AlipayTradePagePayRequest payRequest = new AlipayTradePagePayRequest();
        payRequest.setReturnUrl(aliPayProperties.getReturnUrl());
        payRequest.setNotifyUrl(aliPayProperties.getNotifyUrl());
        payRequest.setBizModel(model);

        String form = alipayClient.pageExecute(payRequest).getBody();
        response.setContentType("text/html;charset=" + aliPayProperties.getCharset());
        response.getWriter().write(form);
        response.getWriter().flush();
        response.getWriter().close();
    }

    @RequestMapping("/returnUrl")
    public String returnUrl(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws UnsupportedEncodingException, AlipayApiException {
        response.setContentType("text/html;charset=" + aliPayProperties.getCharset());

        boolean verifyResult = this.rsaCheckV1(request);
        if (verifyResult) {
            // 商户订单号
            String outTradeNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 支付宝交易号
            String tradeNo = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 支付总金额
            String totalAmount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            map.put("outTradeNo", outTradeNo);
            map.put("tradeNo", tradeNo);
            map.put("totalAmount", totalAmount);
            return "aliPayWebPaySuccess";
        }
        return "aliPayWebPayFail";
    }

}

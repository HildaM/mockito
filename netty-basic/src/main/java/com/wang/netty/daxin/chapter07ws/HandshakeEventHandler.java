package com.wang.netty.daxin.chapter07ws;

import com.wang.netty.daxin.chapter07ws.responsehandler.WsContext;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 同时配置成 无状态的 单例 共享 Handler
 *
 * @description: WebSocket 握手成功事件
 * @author: wei·man cui
 * @date: 2021/4/28 10:04
 */
@Slf4j
@ChannelHandler.Sharable
public class HandshakeEventHandler extends ChannelInboundHandlerAdapter {

    public static final HandshakeEventHandler INSTANCE = new HandshakeEventHandler();

    /**
     * 监听 客户端 握手成功 的事件
     *
     * @param ctx 通道
     * @param evt 事件
     * @throws Exception 异常
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            // WebSocket 握手成功事件
            WebSocketServerProtocolHandler.HandshakeComplete event = (WebSocketServerProtocolHandler.HandshakeComplete) evt;
            /*log.info("握手成功事件 URI={}", event.requestUri());
            log.info("握手成功事件，头部信息={}", event.requestHeaders().toString());*/

            // 握手成功，将 channel 存储到 ChannelGroup 中
            WsContext.CHANNEL_GROUP.add(ctx.channel());
        } else {
            // 其他事件 交由父类 处理
            super.userEventTriggered(ctx, evt);
        }
    }

    /**
     * 客户端连接断开时，从 ChannelGroup 中去除 死链接。
     * 实际上 不需要手动操作，ChannelGroup 在 {@link DefaultChannelGroup#add(Channel)}
     * 中，直接添加了 监听器，当 Channel 失效时，会清除 过期的 Channel。
     *
     * @param ctx ChannelHandlerContext
     * @throws Exception 异常
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // WsContext.CHANNEL_GROUP.remove(ctx.channel());
        super.channelInactive(ctx);
    }
}

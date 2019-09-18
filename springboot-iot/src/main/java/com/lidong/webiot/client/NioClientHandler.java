package com.lidong.webiot.client;

import com.lidong.webiot.utils.CoderUtils;
import com.lidong.webiot.utils.DateUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class NioClientHandler extends SimpleChannelInboundHandler<Object> {

    public NioClientHandler() { }


    /**
     *客户端接收消息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        String msgT = msg.toString();
        new Thread(new Runnable() {
                @Override
                public void run() {
                    //接收到服务端发来的数据,业务处理
                    log.info(msgT);
                    String[] split = msgT.split("\\|");
                    String body = split[5];
                }}) .start();
    }


    /*
     * 读取完毕 服务端发送过来的数据之后的操作
     */
 /*   @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端接收数据完毕..");
        //  ctx.channel().write("");
        //  ctx.channel().flush();
    }*/

    /**
     * 连接关闭!
     * */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.error(" {}连接关闭！",ctx.channel().localAddress().toString() );
        ctx.close();
    }

    /**
     * 客户端主动连接服务端
     * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       // logger.info("RemoteAddress"+ ctx.channel().remoteAddress() + " active !");
        // logger.info("msg"+ ctx.m + " active !");
        //  ctx.writeAndFlush("连接成功！");
        super.channelActive(ctx);
    }

    /**
     * 发生异常处理
     * */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        log.error("连接异常,连接异常："+ DateUtils.dateToString(new Date())+cause.getMessage(), cause);
        ctx.fireExceptionCaught(cause);
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.READER_IDLE)) {
                ctx.close();
                //标志该链接已经close 了 
            }
        }
    }

    public static void main(String[] args) {


    }

}

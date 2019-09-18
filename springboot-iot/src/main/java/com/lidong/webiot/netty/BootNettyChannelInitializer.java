package com.lidong.webiot.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.channel.socket.SocketChannel;

/**
 * 通道初始化
 * @param
 */
public class BootNettyChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
	    // ChannelOutboundHandler，依照逆序执行
        pipeline.addLast("encoder", new StringEncoder());
        // 属于ChannelInboundHandler，依照顺序执行
        pipeline.addLast("decoder", new StringDecoder());
        /**
         * 自定义ChannelInboundHandlerAdapter
         */
        ByteBuf buf = Unpooled.copiedBuffer("@E#@".getBytes());
        //这里设置的是mc为包与包之间的分隔符，所以每出现一次mc就切割为一个包，注意的是设置为分隔符的mc将不会出现在最后的信息里。
        //并且这里设置了最大长度为1024个字节，如果超过了1024个字节还没有出现分隔符，会抛出异常
        DelimiterBasedFrameDecoder decoder = new DelimiterBasedFrameDecoder(1536,buf);
        pipeline.addLast(decoder);
        pipeline.addLast(new BootNettyChannelInboundHandlerAdapter());

	}

}

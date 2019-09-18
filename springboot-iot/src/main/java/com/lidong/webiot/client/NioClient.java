package com.lidong.webiot.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class NioClient implements  Runnable{


    private String host;
    private int port;

    private SocketChannel socketChannel;
    private  EventLoopGroup group;


   private volatile boolean isChannelPrepared =false;
    private volatile boolean isClose=false;

    private  int reConnectCount=0;
    private int delay=5;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public NioClient(String host ,int port){
        this.host=host;
        this.port=port;
    }


    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public EventLoopGroup getGroup() {
        return group;
    }

    public  void connect(String host, Integer port) throws Exception {
       // synchronized (NioClient.class){
            if (!isChannelPrepared) {
                //配置服务端的NIO线程组
                group = new NioEventLoopGroup();
                try {
                    Bootstrap b = new Bootstrap();
                    b.group(group) // 绑定线程池
                            .channel(NioSocketChannel.class)
                            //.option(ChannelOption.SO_BACKLOG, 1024)
                            //.option(ChannelOption.SO_KEEPALIVE, true) // 2小时无数据激活心跳机制
                            .option(ChannelOption.TCP_NODELAY, true)
                            .handler(new ClientChannelInitializer());
                    ChannelFuture f = b.connect(host, port).sync();
                    f.addListener(new GenericFutureListener<Future<? super Void>>() {
                        @Override
                        public void operationComplete(Future<? super Void> future) {
                            if (future.isSuccess()) {
                                isChannelPrepared = true;
                                reConnectCount = 0;
                                socketChannel = (SocketChannel) f.channel();
                                log.info("与服务器{}:{}连接建立成功...", host, port);
                            } else {
                                isChannelPrepared = false;
                                log.error("与服务器{}:{}连接建立失败...", host, port);
                                //  throw new NullPointerException();
                            }
                        }
                    });
                    // 关闭服务器通道
                    f.channel().closeFuture().sync();
                } catch (Exception e) {
                    isChannelPrepared = false;
                    //  e.printStackTrace();
                    log.error("与服务器{}:{}连接出现异常...", host, port);
                } finally {
              //      isChannelPrepared=false;
                    group.shutdownGracefully();
                    if (!isClose) {
                        System.gc();
                        reConnect(host, port);
                    }
                }
            }
       // }
    }

    // 断线重连
    private   void reConnect(String host, int port) {

            try {
                reConnectCount = reConnectCount > 23 ? 23 : reConnectCount;
                int _delay = ++reConnectCount * delay;
               // isChannelPrepared=false;
                log.info("与服务器{}:{}连接已断开, {}秒后重连...", host, port, _delay);
                Thread.sleep(_delay*1000);
               // SleepUtils.sleepSecond(_delay);
                // Thread.sleep(delay * 1000); //delay 秒后重连
                if(!isChannelPrepared) {
                    connect(host, port);
                }
            } catch (Exception e) {
                isChannelPrepared=false;
                e.printStackTrace();
            }
    }

    public   void  restart(){
        log.info("客户端重启与服务器{}:{}的连接...", host, port);
        isChannelPrepared=false;
        isClose=false;
        group.shutdownGracefully();
        System.out.println("重启!");
    }

    public   void close(){
        log.info("客户端关闭与服务器{}:{}的连接...", host, port);
        isChannelPrepared=false;
        this.isClose=true;
        group.shutdownGracefully();
        group=null;
        socketChannel=null;
        log.info("客户端关闭与服务器{}:{}的连接!", host, port);
    }

    public  boolean writeAndFlush(String data){
        log.trace("向服务器{}:{}发送数据:{}", host, port,data);
        boolean b = false;
        if( isChannelPrepared) {
                socketChannel.writeAndFlush(data);
                b = true;
        }else{
            log.error("与服务器{}:{}连接断开,无法发送数据[{}]...", host, port,data);
         //   throw  new RuntimeException();
        }
        return b;
    }

    @Override
    public void run() {
        try {
            connect(host,port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        NioClient nc=      new NioClient("127.0.0.1",9000);
        Thread t=      new Thread(nc);
        t.start();
        int j=0;
        while(j<5){
            j++;
            System.out.println(j);
            if(j%2==0){
                nc.writeAndFlush("test"+j+"\r\n");

            }
        }
        nc.close();
    }

}

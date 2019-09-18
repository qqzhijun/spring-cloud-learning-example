package com.lidong.webiot.netty;

import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 使用map来存储客户端
 */
public class ClientCache {
    /**
     *
     */
    public static ConcurrentMap<String, ChannelHandlerContext> clientMap = new ConcurrentHashMap<String, ChannelHandlerContext>();

    /**
     *删除map中ChannelHandlerContext
     *  */
    public static void removeChannnelMap(ChannelHandlerContext ctx){
        for( String key :ClientCache.clientMap.keySet()){
            if( ClientCache.clientMap.get(key)!=null &&  ClientCache.clientMap.get(key).equals( ctx)){
                ClientCache.clientMap.remove(key);
            }
        }
    }
}

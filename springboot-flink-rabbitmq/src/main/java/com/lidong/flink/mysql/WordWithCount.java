//package com.lidong.flink.mysql;
//import org.apache.flink.streaming.api.scala._
//import org.apache.flink.streaming.api.windowing.time.Time
//object SocketWindowWordCount {
//  def main(args: Array[String]) : Unit = {
//    //设置连接的主机名和端口号
//    var hostname: String = "192.168.147.133"
//    var port: Int = 6666
//    // 获取执行环境
//    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
//    // 获取连接数据
//    val text: DataStream[String] = env.socketTextStream(hostname, port, '\n')
//    // 数据处理，每5秒计算打印一次
//    val windowCounts = text
//      .flatMap { w => w.split("\\s") }
//      .map { w => WordWithCount(w, 1) }
//      .keyBy("word")
//      .timeWindow(Time.seconds(5))
//      .sum("count")
//    // 用单个线程打印结果，而不是并行打印结果
//    windowCounts.print().setParallelism(1)
//    env.execute("Socket Window WordCount")
//  }
//  /** 记录的数据类型 */
//  case class WordWithCount(word: String, count: Long)
//}

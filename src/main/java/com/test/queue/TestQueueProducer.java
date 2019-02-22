package com.test.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 测试点对点模式,   生产者(发送方)
 */
public class TestQueueProducer {
    public static void main(String[] args) throws Exception {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.200.128:61616");
        //2.获取连接
        Connection conn = connectionFactory.createConnection();
        //3.启动连接
        conn.start();
        //4.获取session  (参数1：是否启动事务,参数2：消息确认模式)
        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建队列对象
        Queue queue = session.createQueue("test-queue");
        //6.创建消息生产者
        MessageProducer producer = session.createProducer(queue);
        //7.创建消息
        TextMessage message = session.createTextMessage("欢迎光临");
        //8.发送消息
        producer.send(message);
        //9.关闭资源
        producer.close();
        session.close();
        conn.close();
    }

}

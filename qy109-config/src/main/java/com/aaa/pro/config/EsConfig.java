//package com.aaa.pro.config;
//
//import com.aaa.pro.properties.EsProperties;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//import static com.aaa.pro.staticproperties.EsStaticProperties.*;
//
//
///**
// * @Author zyb
// * @Date Create in 2020/5/12 9:52
// * @Description 这个类就是springboot整合ES的配置类
// **/
//@Configuration
//public class EsConfig {
//
//    @Autowired
//    private EsProperties esProperties;
//
//    /**
//     * @author zyb
//     * @description
//     *      配置transportClient:
//     *          也就是说这个类就是ES给java所提供的API的一个类(让java可以通过这个类中的方法对ES实现增删改查)
//     *
//     *      client.transport.sniff:(非常核心的一个配置)
//     *          在ES的服务器集群中，当有新的节点重新添加进来的时候，java项目会自动感知到，不需要在application.properties
//     *          中添加新的节点，java项目就会把这个新的节点自动添加进来
//     *          eg:
//     *              目前ES集群中只有一个节点(node-1)
//     *              随着用户量增加，搜索的数据也会增加，导致ES的一个节点就撑不住了
//     *              需要新增加一个节点(node-2)
//     *              这个时候application.properties中是没有node-2数据的
//     *              如果没有配置sniff，node-2不会自动的添加到项目中
//     *
//     *          什么时候用枚举，什么时候用静态常量呢？
//     *              只要是有一系列值的时候就用枚举比较合适
//     *              如果只是单个的值使用静态常量比较合适
//     *              eg:
//     *                  比如用户登录:
//     *                      用户名已存在
//     *                      密码错误
//     *                      用户被锁定
//     *                      ...
//     *
//     * @param []
//     * @date 2020/5/12
//     * @return org.elasticsearch.client.transport.TransportClient
//     * @throws
//     **/
//    @Bean
//    public TransportClient transportClient() {
//        // 1.创建TransportClient对象
//        TransportClient transportClient = null;
//        try {
//            // 2.配置ES(写法是固定的)
//            // builder():这个对象就是一个Map类型(final修饰的Map类型)
//            Settings settings = Settings.builder()
//                    .put(CLUSTER_NAME, esProperties.getClusterName())
//                    .put(NODE_NAME, esProperties.getNodeName())
//                    .put(THREAD_SEARCH_SIZE, esProperties.getPool())
//                    .put(CLIENT_TRANSPORT_SNIFF, true).build();
//            // 3.配置ES的连接信息
//            TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(esProperties.getIpAddr()), esProperties.getPort());
//            // 4.初始化TransportClient对象
//            transportClient = new PreBuiltTransportClient(settings);
//            // 5.把ES的连接信息放到transportClient对象中
//            transportClient.addTransportAddress(transportAddress);
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        return transportClient;
//    }
//
//}

//package com.aaa.pro.utils;
//
//import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
//import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
//import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
//import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetRequestBuilder;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequestBuilder;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.text.Text;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.elasticsearch.search.sort.SortOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @Company AAA软件教育
// * @Author Seven Lee
// * @Date Create in 2020/5/12 10:45
// * @Description
// *      Java对ES的增删改查工具类
// *      因为ES给java提供的API就是TransportClient对象
// **/
//@Component
//public class EsUtils {
//
//    /**
//     * @author Seven Lee
//     * @description
//     *      并不是为了实现单例，只是为了实现静态方法直接使用类调用即可
//     * @param []
//     * @date 2020/5/12
//     * @return
//     * @throws
//    **/
//    private EsUtils() {
//
//    }
//
//    /**
//     * 既然是一个工具类，他的所有方法都应该是静态方法
//     * 静态方法就无法使用非静态对象
//     * 使用@Autowired绝对不允许注入静态的对象
//     *      所有的静态对象/静态方法都不再属于类对象，属于类本身
//     *      会随着类加载一起进入方法区(内存方法区)
//     *
//     * @Autowired注入TransportClient对象一定是在ESConfig这个类执行完毕后才开始注入(否则肯定会报空指针)
//     *      !!所有的属性的都是存在原子性的，会发生指令重排
//     *          这个时候恰好ESUtils优先于ESConfig这个类先加载了
//     *          而且transportClient是由static修饰的，凡是static都会随着类一起加载
//     *          也就是说transportClient对象会优先于ESConfig加载
//     *          那么这个时候指定会报错空指针异常!!
//     *
//     *
//     */
//    @Autowired
//    private TransportClient transportClient;
//
//    private static TransportClient client;
//
//    /**
//     * ConcurrentHashMap是一个线程安全的Map
//     *      也就是说在当前线程中去创建Map对象
//     */
//    private static Map<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
//
//    /**
//     * @author Seven Lee
//     * @description
//     *      既然transportClient对象没有办法使用于静态方法，需要进行转换
//     *      @PostConstruct:
//     *          作用就是当springboot所有配置全部加载完毕之后，被@PostConstruct所标识的方法会在
//     *          第一时间执行
//     * @param []
//     * @date 2020/5/12
//     * @return void
//     * @throws
//    **/
//    @PostConstruct
//    public void init() {
//        client = transportClient;
//    }
//
//    /**
//     * @author Seven Lee
//     * @description
//     *      向ES中创建index
//     * @param [index]
//     * @date 2020/5/12
//     * @return java.util.Map<java.lang.String,java.lang.Object>
//     * @throws
//    **/
//    public static Map<String, Object> createIndex(String index) {
//        // 1.先判断ES中是否有这个index
//        if(indexExist(index)) {
//            // 说明index已经存在，不能再添加
//            resultMap.put("code", "10001");
//            resultMap.put("msg", "index存在");
//        } else {
//            CreateIndexResponse createIndexResponse = client.admin().indices().prepareCreate(index).execute().actionGet();
//            if(createIndexResponse.isAcknowledged()) {
//                // 说明index创建成功
//                resultMap.put("code", "20001");
//                resultMap.put("msg", "index创建成功");
//            } else {
//                resultMap.put("code", "10002");
//                resultMap.put("msg", "index创建失败");
//            }
//        }
//        return resultMap;
//    }
//
//    /**
//     * @author Seven Lee
//     * @description
//     *      删除index
//     * @param [index]
//     * @date 2020/5/12
//     * @return java.util.Map<java.lang.String,java.lang.Object>
//     * @throws
//    **/
//    public static Map<String, Object> deleteIndex(String index) {
//        if(indexExist(index)) {
//            // 说明index存在，可以删除
//            DeleteIndexResponse deleteIndexResponse = client.admin().indices().prepareDelete(index).execute().actionGet();
//            if(deleteIndexResponse.isAcknowledged()) {
//                // 说明删除成功
//                resultMap.put("code", "20002");
//                resultMap.put("msg", "index删除成功");
//            } else {
//                // 说明删除失败
//                resultMap.put("code", "10003");
//                resultMap.put("msg", "index删除失败");
//            }
//        } else {
//            /*
//             * map中除了put方法之外，还有一个比较常用方法putIfAbsent()
//             *      其实本质上没有区别，都是向map存入数据
//             * put是无论key值是否已经有了，都会发生覆盖
//             *      Map map = new HashMap();
//             *      map.put("username", "zhangsan");
//             *      map.put("username", "lisi");
//             * putIfAbsent是如果key值存在，则就不会再向Map中存入数据了
//             */
//            resultMap.putIfAbsent("code", "10004");
//            resultMap.putIfAbsent("msg", "index不存在");
//        }
//        return resultMap;
//    }
//
//    /**
//     * @author Seven Lee
//     * @description
//     *      判断index是否存在
//     * @param [index]
//     * @date 2020/5/12
//     * @return java.lang.Boolean
//     * @throws
//    **/
//    public static Boolean indexExist(String index) {
//        IndicesExistsResponse indicesExistsResponse = client.admin().indices().exists(new IndicesExistsRequest(index)).actionGet();
//        return indicesExistsResponse.isExists();
//    }
//
//    /**
//     * @author Seven Lee
//     * @description
//     *      判断指定index下的type是否存在
//     * @param [index, type]
//     * @date 2020/5/12
//     * @return java.lang.Boolean
//     * @throws
//    **/
//    public static Boolean isTypeExist(String index, String type) {
//        // 常规写法
//        // 1.先判断index是否存在
//        /*if(indexExist(index)) {
//            // 说明index 存在，需要判断type是否存在
//            TypesExistsResponse typesExistsResponse = client.admin().indices().prepareTypesExists(index).setTypes(type).execute().actionGet();
//            if(typesExistsResponse.isExists()) {
//                // 说明type存在
//                return true;
//            }
//        }
//        return false;*/
//        return indexExist(index) && client.admin().indices().prepareTypesExists(index).setTypes(type).execute().actionGet().isExists();
//    }
//
//    /**
//     * @author Seven Lee
//     * @description
//     *      向ES中存入数据
//     *
//     *      存数据是有两种方式:
//     *          第一种是不需要指定id，ES会自动生成
//     *          第二种是需要自己指定id
//     *
//     *      注意事项:
//     *          必须要注意ES6.x版本之后必须要使用Map类型来存入数据，不能再使用JSONObject
//     *
//     * @param [mapData, index, type, id]
//     * @date 2020/5/12
//     * @return java.util.Map<java.lang.String,java.lang.Object>
//     * @throws
//    **/
//    public static Map<String, Object> addData(Map<String, Object> mapData, String index, String type, String id) {
//        IndexResponse indexResponse = client.prepareIndex(index, type, id).setSource(mapData).get();
//        // 判断是否创建成功
//        String status = indexResponse.status().toString();
//        /*
//         * 在这里会出现两种情况:
//         *      第一种情况是type和index都存在的情况:
//         *          也就是说可以直接存入数据即可，不需要再去创建index和type
//         *          这里返回的是"OK"
//         *
//         *      第二种情况是type和index都不存在的情况:
//         *          也就是说ES会先默认自动生成出来type和index
//         *          这里返回的是"CREATED"
//         */
//        if("CREATED".equals(status.toUpperCase()) || "OK".equals(status.toUpperCase())) {
//            // 说明数据存储成功
//            resultMap.put("code", "20003");
//            resultMap.put("msg", "数据存储成功");
//        } else {
//            resultMap.put("code", "10005");
//            resultMap.put("msg", "数据存储失败");
//        }
//        return resultMap;
//    }
//
//    /**
//     * @author Seven Lee
//     * @description
//     *      向ES中存入数据，ES自动生成uuid
//     * @param [dataObj, index, type]
//     * @date 2020/5/26
//     * @return java.util.Map<java.lang.String,java.lang.Object>
//     * @throws
//    **/
//    public static Map<String, Object> addData(Map<String, Object> dataObj, String index, String type) {
//        /*
//         * 在uuid里面，会有连接符-(dashjfha-321676413dasdas-3214126)，这个连接符就有可能导致这个字符串发生转译
//         * 这个问题在mysql中非常严重(其实这一块如果放在Navicat中去查询，没有问题，但是如果放到mybatis去查，则查询不到数据)
//         * 因为mybatis遵循java，java中有很多连接符和转义符，所以mybatis也遵循这个原则
//         * (这个问题的解决方案:需要把uuid的"-"替换成"")
//         */
//        String id = UUID.randomUUID().toString().replaceAll("-","");
//        return addData(dataObj, index, type, id);
//    }
//
//    /**
//     * @author Seven Lee
//     * @description
//     *      通过uuid删除ES中的数据
//     * @param [index, type, uuid]
//     * @date 2020/5/26
//     * @return java.util.Map<java.lang.String,java.lang.Object>
//     * @throws
//    **/
//    public static Map<String, Object> deleteDataByUUID(String index, String type, String uuid) {
//        DeleteResponse deleteResponse = client.prepareDelete(index, type, uuid).execute().actionGet();
//        String status = deleteResponse.status().toString().toUpperCase();
//        if("OK".equals(status)) {
//            resultMap.put("code", "20004");
//            resultMap.put("msg", "数据删除成功");
//        } else {
//            resultMap.put("code", "10006");
//            resultMap.put("msg", "数据删除失败");
//        }
//        return resultMap;
//    }
//
//    /**
//     * @author Seven Lee
//     * @description
//     *      通过uuid更新ES中的数据
//     * @param [index, type, uuid, updateObj]
//     * @date 2020/5/26
//     * @return java.util.Map<java.lang.String,java.lang.Object>
//     * @throws
//    **/
//    public static Map<String, Object> updateDataByUUID(String index, String type, String uuid, Map<String, Object> updateObj) {
//        // 1.创建修改请求的request对象
//        UpdateRequest updateRequest = new UpdateRequest();
//        // 2.传入所要修改的数据，以及数据所存放的index，type和uuid
//        updateRequest.index(index).type(type).id(uuid).doc(updateObj);
//        // 3.执行修改操作(使用prepareUpdate和update的区别就在于update是ES自动进行提交事务，但是
//        // prepareUpdate需要手动来进行提交事务)
//        String status = client.update(updateRequest).actionGet().status().toString();
//        // 4.判断是否修改数据成功
//        if("OK".equals(status.toUpperCase())) {
//            // 说明数据修改成功
//            resultMap.put("code", "20005");
//            resultMap.put("msg", "数据修改成功");
//        } else {
//            resultMap.put("code", "10007");
//            resultMap.put("msg", "数据修改失败");
//        }
//        return resultMap;
//    }
//
//    /**
//     * @author Seven Lee
//     * @description
//     *      通过uuid从ES中查询数据
//     *      fields:
//     *          需要显示的数据(规定:如果需要显示多条数据，则使用逗号隔开)("goods_id,goods_name,goods_price")
//     *          eg:
//     *              搜索库中有商品信息:
//     *                  goods_id
//     *                  goods_name
//     *                  goods_price
//     *                  goods_desc
//     *          如果你需要查询所有的数据，则直接传入null(缺省)
//     * @param [index, type, uuid, fields]
//     * @date 2020/5/26
//     * @return java.util.Map<java.lang.String,java.lang.Object>
//     * @throws
//    **/
//    public static Map<String, Object> queryDataByUUID(String index, String type, String uuid, String fields) {
//        GetRequestBuilder getRequestBuilder = client.prepareGet(index, type, uuid);
//        if(null != fields && !"".equals(fields)) {
//            // 说明用户不想显示所有的字段
//            getRequestBuilder.setFetchSource(fields.split(","), null);
//        }
//        GetResponse documentFields = getRequestBuilder.execute().actionGet();
//        // GET /index的名称/type的名称/uuid/_source
//        return documentFields.getSource();
//    }
//
//    /**
//     * @author Seven Lee
//     * @description
//     *      从ES中搜索一个集合数据
//     *      QueryBuilder:ES给java所提供的查询方法(查询所有，分页查询，条件查询，模糊查询...)
//     *      size:具体要显示多少条数据(经常会适用于分页的功能)
//     *      fields:所需要显示的字段
//     *      sortField:所需要排序的字段(eg:商品价格，是否新品，销量排序，评价排序...)
//     *      highLightField:高亮显示
//     * @param [index, type, queryBuilder, size, fields, sortField, highLightField]
//     * @date 2020/5/26
//     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
//     * @throws
//    **/
//    public static List<Map<String, Object>> queryListData(
//            String index, String type, QueryBuilder queryBuilder, Integer size, String fields,
//            String sortField, String highLightField
//    ) {
//        // 1.创建搜索的request对象
//        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
//        // 2.查看是否有type，如果有，则只是查询该type下的所有数据，如果没有则显示所有的数据
//        // 如果查询单个type数据，则只需要传递一个type，如果需要查询多个type中的数据，则需要使用","隔开
//        if(null != type && !"".equals(type)) {
//            searchRequestBuilder.setTypes(type.split(","));
//        }
//        // 3.判断是否要求高亮显示
//        if(null != highLightField && !"".equals(highLightField)) {
//            // 说明需要设置高亮显示字段
//            // 3.1.或者高亮显示的设置对象
//            HighlightBuilder highlightBuilder = new HighlightBuilder();
//            // 3.2.把具体需要设置的高亮显示字段传递到highlightBuilder中
//            highlightBuilder.field(highLightField);
//            searchRequestBuilder.highlighter(highlightBuilder);
//        }
//
//        // ES中规定，以上的三步，必须要进行设置，下面的步骤可以根据需求进行设置
//        // 4.设置具体所要实现的查询方式(match_all...)
//        searchRequestBuilder.setQuery(queryBuilder);
//
//        // 5.设置是否有具体显示的字段，如果该值缺省，则显示所有
//        if(null != fields && !"".equals(fields)) {
//            searchRequestBuilder.setFetchSource(fields.split(","), null);
//        }
//
//        // 6.设置是否有需要排序的字段
//        if(null != sortField && !"".equals(sortField)) {
//            // 按照正规的业务来说，还需要在方法上添加一个字段，该字段是正序还是倒序
//            // 在这我就规定所有都是倒序
//            searchRequestBuilder.addSort(sortField, SortOrder.DESC);
//        }
//
//        // 7.设置是否显示条数
//        if(null != size && 0 < size) {
//            searchRequestBuilder.setSize(size);
//        }
//
//        // 8.执行查询
//        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
//        // 9.判断是否查询成功
//        if(200 ==searchResponse.status().getStatus()) {
//            // 说明查询成功
//            // 虽然咱们这个方法中已经设置可以高亮显示，但是ES给java的API中并没有实现高亮显示功能
//            // 这一块需要自己手动处理
//            return dealSearchResponse(searchResponse, highLightField);
//        }
//        return null;
//    }
//
//    /**
//     * @author Seven Lee
//     * @description
//     *      处理需要高亮显示的结果集
//     *      searchResponse:是ES常规返回回来的结果
//     *      highLightField:所需要高亮显示的字段
//     * @param [searchResponse, highLightField]
//     * @date 2020/5/26
//     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
//     * @throws
//    **/
//    private static List<Map<String, Object>> dealSearchResponse(SearchResponse searchResponse, String highLightField) {
//        // 1.定义最终返回的数据结果
//        List<Map<String, Object>> sourceList = new ArrayList<Map<String, Object>>();
//        /*
//         * StringBuilder和StringBuffer最终有什么区别？
//         */
//        // 2.使用StringBuilder对象来进行拼接(效率是最高的)
//        StringBuilder stringBuilder = new StringBuilder();
//        // 3.searchResponse里面存放了很多返回结果数据(是否成功，查询结果集...)
//        // 使用循环的形式从searchResponse中获取查询结果集
//        for(SearchHit searchHit : searchResponse.getHits().getHits()) {
//            // 之前是一个List，循环之后就获取到了List中的每一个Map--->searchHit
//            searchHit.getSourceAsMap().put("id", searchHit.getId());
//            // 4.严谨判断，再次确认所传递的高亮显示字段存在(double check)
//            if(null != highLightField && !"".equals(highLightField)) {
//                System.out.println("使用高亮显示字段去覆盖从ES中查询出的原有结果集"+searchHit.getSourceAsMap());
//                // 这一步的操作就是使用高亮显示结果集来进行覆盖原有的ES结果集
//                Text[] texts = searchHit.getHighlightFields().get(highLightField).getFragments();
//                // 5.再次判断是否成功
//                if(null !=texts && 0 < texts.length) {
//                    // 6.循环遍历高亮结果，并且拼接到StringBuilder中
//                    for (Text text : texts) {
//                        stringBuilder.append(text.toString());
//                    }
//                    // 7.实现所有的数据替换
//                    searchHit.getSourceAsMap().put(highLightField, stringBuilder.toString());
//                }
//            }
//            sourceList.add(searchHit.getSourceAsMap());
//        }
//        return sourceList;
//    }
//
//}

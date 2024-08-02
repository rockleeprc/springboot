package org.es;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.es.bean.Person;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class ESClientTests {

    RestHighLevelClient esClient;

    @Before
    public void before() {
        HttpHost httpHost = new HttpHost("node3", 9200, "http");
        esClient = new RestHighLevelClient(RestClient.builder(httpHost));
    }

    @After
    public void after() throws IOException {
        esClient.close();
    }

    /**
     * 创建索引
     *
     * @throws IOException
     */
    @Test
    public void createIndexTest() throws IOException {
        // 声明索引
        CreateIndexRequest userIndexRequest = new CreateIndexRequest("person");
        IndicesClient indicesClient = esClient.indices();
        // 创建索引
        CreateIndexResponse userIndexResponse = indicesClient.create(userIndexRequest, RequestOptions.DEFAULT);
        // 创建索引结果
        boolean acknowledged = userIndexResponse.isAcknowledged();
        Assert.assertTrue(acknowledged);
    }

    /**
     * 查询索引
     *
     * @throws IOException
     */
    @Test
    public void getIndex() throws IOException {
        GetIndexRequest userIndex = new GetIndexRequest("user");
        GetIndexResponse userIndexResponse = esClient.indices().get(userIndex, RequestOptions.DEFAULT);

        System.out.println(userIndexResponse.getAliases());
        System.out.println(userIndexResponse.getMappings());
        System.out.println(userIndexResponse.getSettings());
    }

    /**
     * 删除索引
     *
     * @throws IOException
     */
    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexRequest shoppingIndex = new DeleteIndexRequest("shopping");
        AcknowledgedResponse acknowledgedResponse = esClient.indices().delete(shoppingIndex, RequestOptions.DEFAULT);
        Assert.assertTrue(acknowledgedResponse.isAcknowledged());
    }

    /**
     * 添加数据
     *
     * @throws IOException
     */
    @Test
    public void insertDoc() throws IOException {
        List<Person> list = Arrays.asList(
                new Person(1L, "tom", "北京市，东城区", 12),
                new Person(2L, "jack", "上海市，黄浦江", 10),
                new Person(3L, "marry", "深圳市，大湾区", 23)
        );

        ObjectMapper mapper = new ObjectMapper();
        for (Person person : list) {
            String json = mapper.writeValueAsString(person);
            IndexRequest indexRequest = new IndexRequest("person");
            indexRequest.id(person.getId().toString());
            indexRequest.source(json, XContentType.JSON);
            IndexResponse indexResponse = esClient.index(indexRequest, RequestOptions.DEFAULT);
            System.out.println(indexResponse.getResult());
        }
    }

    /**
     * 更新字段
     *
     * @throws IOException
     */
    @Test
    public void updateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest
                .index("person")
                .id("3")
                .doc(XContentType.JSON, "age", "99");

        UpdateResponse response = esClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
    }

    /**
     * 获取文档
     *
     * @throws IOException
     */
    @Test
    public void getDoc() throws IOException {
//        GetRequest request = new GetRequest("person", "1");
//        GetResponse response = esClient.get(request, RequestOptions.DEFAULT);
//        System.out.println(response.getSourceAsString());
    }

    /**
     * 删除文档
     *
     * @throws IOException
     */
    @Test
    public void deleteDoc() throws IOException {
//        DeleteRequest request = new DeleteRequest("person", "1");
//        DeleteResponse response = esClient.delete(request, RequestOptions.DEFAULT);
//        System.out.println(response);
    }

    /**
     * 批量添加
     *
     * @throws IOException
     */
    @Test
    public void batchInsertDoc() throws IOException {
        List<Person> list = Arrays.asList(
                new Person(4L, "jetty", "北京市，西城区", 23),
                new Person(5L, "matting", "上海市，中环", 24),
                new Person(6L, "baker", "深圳市，小湾区", 23)
        );

        BulkRequest bulkRequest = new BulkRequest();
        ObjectMapper mapper = new ObjectMapper();
        for (Person person : list) {
            String json = mapper.writeValueAsString(person);
            IndexRequest indexRequest = new IndexRequest("person");
            indexRequest.id(person.getId().toString());
            indexRequest.source(json, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }

        BulkResponse responses = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(responses.getTook());// 时间
        BulkItemResponse[] items = responses.getItems();
        for (BulkItemResponse item : items) {
            System.out.println(item);
        }
    }

    @Test
    public void searchAll() throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices("person");
        // 查询全部
        request.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));

        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }


    }
}

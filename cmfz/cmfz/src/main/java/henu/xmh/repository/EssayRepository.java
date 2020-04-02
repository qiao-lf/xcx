package henu.xmh.repository;

import henu.xmh.pojo.Essay;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 泛型<操作类实体类对象,序列化逐渐类型>
 */
public interface EssayRepository extends ElasticsearchRepository<Essay,String> {
}

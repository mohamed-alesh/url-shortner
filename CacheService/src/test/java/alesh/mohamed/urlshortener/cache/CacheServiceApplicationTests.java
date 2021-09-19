package alesh.mohamed.urlshortener.cache;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Import({ CacheConfig.class})
@ExtendWith(SpringExtension.class)
@EnableCaching
@ImportAutoConfiguration(classes = { 
  CacheAutoConfiguration.class, 
  RedisAutoConfiguration.class 
})
@SpringBootTest
class CacheServiceApplicationTests {

	@Autowired
    private CacheManager cacheManager;
	
	@Test
	void contextLoads() {
	}
	
	
	@Test
    void givenRedisCaching_whenGettingUrlByHash_thenURLReturnedFromCache() {
//        Item anItem = new Item(AN_ID, A_DESCRIPTION);
//        given(mockItemRepository.findById(AN_ID))
//          .willReturn(Optional.of(anItem));
//
//        URL urlCacheMiss = itemService.getItemForId(AN_ID);
//        Item itemCacheHit = itemService.getItemForId(AN_ID);
//
//        assertThat(itemCacheMiss).isEqualTo(anItem);
//        assertThat(itemCacheHit).isEqualTo(anItem);
//
//        verify(mockItemRepository, times(1)).findById(AN_ID);
//        assertThat(itemFromCache()).isEqualTo(anItem);
    }

}

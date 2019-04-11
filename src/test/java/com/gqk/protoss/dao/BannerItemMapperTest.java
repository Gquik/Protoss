package com.gqk.protoss.dao;

import com.gqk.protoss.entity.BannerItem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BannerItemMapperTest {

    @Autowired
    private BannerItemMapper bannerItemMapper;

    @Test
    public void selectByPrimaryKey() throws Exception {

        BannerItem bannerItem = bannerItemMapper.selectByPrimaryKey(1);
        Assert.assertNotNull(bannerItem);
    }

    @Test
    public void selectListByBannerId() throws Exception {

        List<BannerItem> bannerItems = bannerItemMapper.selectListByBannerId(1);
        Assert.assertNotEquals(0,bannerItems);
    }

}
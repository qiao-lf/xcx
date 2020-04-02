package henu.xmh.service.impl;

import henu.xmh.annotation.AddCache;
import henu.xmh.annotation.DelCache;
import henu.xmh.annotation.LogAnnotation;
import henu.xmh.dao.CfImageMapper;
import henu.xmh.pojo.CfImage;
import henu.xmh.pojo.CfImageExample;
import henu.xmh.service.CfImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @author Administrator
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CfImageServiceImpl implements CfImageService {
    @Autowired
    private CfImageMapper cfImageMapper;

    @DelCache
    @LogAnnotation("上传轮播图")
    @Override
    public void addCfImage(CfImage cfImage) {
        cfImage.setImageId(UUID.randomUUID().toString().replace("-",""));
        cfImageMapper.insertSelective(cfImage);
    }

    @DelCache
    @LogAnnotation("删除轮播图")
    @Override
    public void dropImageById(String imageId) {
        cfImageMapper.deleteByPrimaryKey(imageId);
    }

    @DelCache
    @LogAnnotation("修改轮播图")
    @Override
    public void alterCfImage(CfImage image) {
        cfImageMapper.updateByPrimaryKeySelective(image);
    }

    /**
     * 分页查询
     * @param currentPageNum
     * @param pageSize
     * @return
     */
    @AddCache
    @Override
    public List<CfImage> finaAllCfImageForPage(Integer currentPageNum, Integer pageSize) {
        CfImageExample example = new CfImageExample();
        example.setBeginValue((currentPageNum-1)*pageSize);
        example.setPageSize(pageSize);
        return  cfImageMapper.selectByExample(example);
    }

    @AddCache
    @Override
    public Integer findCountForPage() {
        return cfImageMapper.countByExample(new CfImageExample());
    }

    @DelCache
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String,Object> alterForJq(CfImage cfImage, String oper,String[] id) {
        if("edit".equals(oper)){//修改
            cfImage.setImageId(cfImage.getId());//设置id
            alterCfImage(cfImage);
        }else if("add".equals(oper)){//添加
            addCfImage(cfImage);
        }else if("del".equals(oper)){//删除
            //判断是否为删除单个
            if(cfImage.getImageId()==null){
                List<String> strings = Arrays.asList(id);
                cfImageMapper.deleteByIdList(strings);
            }else {
                cfImageMapper.deleteByPrimaryKey(cfImage.getImageId());
            }
        }
        Map<String,Object> map  = new HashMap<>();
        map.put("imageId",cfImage.getImageId());
        map.put("status",200);
        return map;
    }

    @AddCache
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CfImage> findCfIamgeByJqSerach(String searchField, String searchString, String searchOper, Integer rows, Integer page) {
        CfImageExample example = new CfImageExample();
        example.setBeginValue((page-1)*rows);//起始值
        example.setPageSize(page);//偏移量
        //过滤条件
        CfImageExample.Criteria criteria = example.createCriteria();
        if("imageSummary".equals(searchField)){
            criteria.andImageSummaryLike("%"+searchString+"%");
        }else if("imageSummary".equals(searchField)){
            criteria.andImageSummaryLike("%"+searchString+"%");
        }

        return cfImageMapper.selectByExample(example);
    }

    @AddCache
    @Override
    public Integer findCountForCfIamgeByJqSerach(String searchField, String searchString, String searchOper) {
        CfImageExample example = new CfImageExample();
        //过滤条件
        CfImageExample.Criteria criteria = example.createCriteria();
        if("imageSummary".equals(searchField)){
            criteria.andImageSummaryLike("%"+searchString+"%");
        }else if("imageSummary".equals(searchField)){
            criteria.andImageSummaryLike("%"+searchString+"%");
        }
        return cfImageMapper.countByExample(example);
    }

    @AddCache
    @Override
    public List<CfImage> findAll() {
        return cfImageMapper.selectByExample(new CfImageExample());
    }

    /**
     * 首页轮播图显示
     * @return
     */
    @AddCache
    @Override
    public List<CfImage> findCfImageForApp() {
        CfImageExample example = new CfImageExample();
        example.setPageSize(5);
        example.setBeginValue(0);//首页显示五条轮播图
        CfImageExample.Criteria criteria = example.createCriteria();
        criteria.andImageStatusEqualTo("1");//轮播图状态正常
        List<CfImage> cfImages = cfImageMapper.selectByExample(example);
        return cfImages;
    }


}

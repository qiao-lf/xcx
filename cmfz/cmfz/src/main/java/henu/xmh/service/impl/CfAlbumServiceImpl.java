package henu.xmh.service.impl;

import henu.xmh.annotation.AddCache;
import henu.xmh.annotation.DelCache;
import henu.xmh.annotation.LogAnnotation;
import henu.xmh.dao.CfAlbumMapper;
import henu.xmh.pojo.CfAlbum;
import henu.xmh.pojo.CfAlbumExample;
import henu.xmh.service.CfAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CfAlbumServiceImpl implements CfAlbumService {
    @Autowired
    private CfAlbumMapper cfAlbumMapper;

    @AddCache
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> findAllForPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        CfAlbumExample example = new CfAlbumExample();
        example.setBeginValue((page-1)*rows);//起始
        example.setPageSize(rows);//页面大小
        List<CfAlbum> cfAlbums = cfAlbumMapper.selectByExampleWithBLOBs(example);//当前页面显示的数据
        Integer records = cfAlbumMapper.countByExample(example);//记录数
        Integer total = (records%rows==0)?(records/rows):(records/rows+1);//页数
        map.put("page",page);//页码
        map.put("rows",cfAlbums);
        map.put("total",total);//页数
        map.put("records",records);//记录数
        return map;
    }

    @DelCache
    @LogAnnotation("添加专辑信息")
    @Override
    public void addCfAlbum(CfAlbum cfAlbum) {
        cfAlbumMapper.insertSelective(cfAlbum);
    }

    @DelCache
    @LogAnnotation("删除专辑信息")
    @Override
    public void dropCfAlbum(CfAlbum cfAlbum) {
        cfAlbumMapper.deleteByPrimaryKey(cfAlbum.getAlbumId());
    }

    @DelCache
    @LogAnnotation("修改专辑信息")
    @Override
    public void alterCfAlnum(CfAlbum cfAlbum) {
        cfAlbumMapper.updateByPrimaryKeySelective(cfAlbum);
    }

    /**
     * jqgrid的写操作相关
     * @param oper 操作
     * @param cfAlbum 信息
     * @param id 批量操作的id数组
     * @return
     */
    @DelCache
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> editForJq(String oper, CfAlbum cfAlbum, String[] id) {
        Map<String,Object> map = new HashMap<>();
        if("add".equals(oper)){//添加
            cfAlbum.setAlbumId(UUID.randomUUID().toString().replace("-",""));
            addCfAlbum(cfAlbum);
            map.put("albumId",cfAlbum.getAlbumId());//将主键返回
        }else if("del".equals(oper)){//删除
            System.out.println("this is drop album"+cfAlbum);
            if(cfAlbum.getAlbumId()==null){
                cfAlbumMapper.deleteByIdList(Arrays.asList(id));//批量删除
                map.put("ids",id);//将批量删除的记录的id返回
            }else {
                dropCfAlbum(cfAlbum);
                map.put("albumId",cfAlbum.getAlbumId());
            }
        }else if("edit".equals(oper)){//修改
            alterCfAlnum(cfAlbum);
            map.put("albumId",cfAlbum.getAlbumId());
        }
        map.put("status",200);//状态成功
        return map;
    }

    @AddCache
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> findCfAlbumForJqSearch(Boolean _search, String searchField, String searchString, String searchOper, Integer page, Integer rows) {
        return null;
    }
}

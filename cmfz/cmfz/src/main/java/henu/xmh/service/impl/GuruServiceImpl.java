package henu.xmh.service.impl;

import henu.xmh.annotation.DelCache;
import henu.xmh.annotation.LogAnnotation;
import henu.xmh.dao.GuruMapper;
import henu.xmh.pojo.Guru;
import henu.xmh.pojo.GuruExample;
import henu.xmh.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class GuruServiceImpl implements GuruService {

    @Autowired
    private GuruMapper guruMapper;

    @Override
    public List<Guru> findAll() {
        List<Guru> gurus = guruMapper.selectByExample(new GuruExample());
        return gurus;
    }

    @Override
    public Map<String, Object> findAllForPage(Integer page, Integer rows) {
        GuruExample example = new GuruExample();
        example.setBeginValue((page-1)*rows);
        example.setPageSize(rows);
        List<Guru> gurus = guruMapper.selectByExample(example);
        Integer records = guruMapper.countByExample(example);

        Map<String,Object> map = new HashMap<>();
        map.put("rows",gurus);
        map.put("records",records);
        map.put("total",(records%rows==0)?(records/rows):(records/rows+1));
        map.put("page",page);
        return map;
    }

    @LogAnnotation("jqgrid修改上师（增删改）")
    @Override
    public Map<String, Object> alterForJq(String oper, Guru guru, String[] id) {
        Map<String, Object> map = new HashMap<>();
        if("add".equals(oper)){//添加
            guru.setGuruId(UUID.randomUUID().toString().replace("-",""));
            add(guru);
        }else if("edit".equals(oper)){//修改
            guru.setGuruId(guru.getId());
            alter(guru);
        }else if("del".equals(oper)){//删除
            drop(guru);
        }
        map.put("guruId",guru.getGuruId());
        map.put("status",200);
        return map;
    }

    @LogAnnotation("添加上师")
    @Transactional(propagation =  Propagation.REQUIRED)
    @Override
    public void add(Guru guru) {
        if(guru.getGuruId()==null)guru.setGuruId(UUID.randomUUID().toString().replace("-",""));
        guruMapper.insertSelective(guru);
    }

    @LogAnnotation("删除上师")
    @Transactional(propagation =  Propagation.REQUIRED)
    @Override
    public void drop(Guru guru) {
        guruMapper.deleteByPrimaryKey(guru.getGuruId());
    }

    @LogAnnotation("修改上师")
    @Transactional(propagation =  Propagation.REQUIRED)
    @Override
    public void alter(Guru guru) {
        guruMapper.updateByPrimaryKeySelective(guru);
    }

    @Override
    public Map<String, Object> findAllForJqSearch(String searchField, String searchString, String searchOper, Integer page, Integer rows) {
        return null;
    }

    @LogAnnotation("删除音频文件")
    private void delGuruImage(Guru guru, HttpServletRequest request){
            //删除系统文件
            String fileNewName = guru.getGuruImage().split("/")[6];
            String realPath = request.getRealPath("/upload/guru");
            File file = new File(realPath + "/" + fileNewName);
            if(file.exists()){
                file.delete();//删除文件
                System.out.println(fileNewName+"删除成功！");
            }
    }
}

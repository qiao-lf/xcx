package henu.xmh.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import henu.xmh.pojo.CfImage;
import org.eclipse.jdt.internal.compiler.lookup.SourceTypeBinding;

import java.util.ArrayList;
import java.util.List;

public class CfImageListener extends AnalysisEventListener<CfImage> {
    private List<CfImage> cfImageList = new ArrayList<>();

    @Override
    public void invoke(CfImage cfImage, AnalysisContext analysisContext) {
        cfImageList.add(cfImage);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("即将导入数据：");
        cfImageList.forEach(c-> System.out.println(c));
    }

    public List<CfImage> getCfImageList() {
        return cfImageList;
    }

    public void setCfImageList(List<CfImage> cfImageList) {
        this.cfImageList = cfImageList;
    }
}

package henu.xmh;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class DemoDataListener extends AnalysisEventListener<DemoData> {
    private List<DemoData> lists = new ArrayList<>();
    /**
     * 每一行数据读取完成后调用此方法
     * @param demoData
     * @param analysisContext
     */
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println(demoData);
        lists.add(demoData);
    }

    /**
     * 全部读取完成后执行此方法
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //save(list)
        System.out.println(lists);
    }
    public List<DemoData> getLists() {
        return lists;
    }

    public void setLists(List<DemoData> lists) {
        this.lists = lists;
    }
}

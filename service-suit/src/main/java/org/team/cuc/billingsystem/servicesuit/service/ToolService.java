package org.team.cuc.billingsystem.servicesuit.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.team.cuc.billingsystem.bean.vo.PageVO;
import org.team.cuc.billingsystem.po.suitservice.SuitPo;
import org.team.cuc.billingsystem.po.suitservice.ToolPo;
import org.team.cuc.billingsystem.po.transaction.DepositWithdrawalPo;
import org.team.cuc.billingsystem.servicesuit.bean.bo.SuitBo;
import org.team.cuc.billingsystem.servicesuit.bean.bo.ToolBo;
import org.team.cuc.billingsystem.servicesuit.suitMapper.ToolMapper;
import org.team.cuc.billingsystem.utils.ApaasUtil;
import org.team.cuc.billingsystem.utils.StringUtil;

import javax.tools.Tool;
import java.net.Inet4Address;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author happyelements
 * @description 针对表【t_tool】的数据库操作Service
 * @createDate 2022-10-25 17:48:07
 */
@Service
public class ToolService {

    @Autowired
    ToolMapper toolMapper;
    @Autowired
    SuitService suitService;

    public PageVO listTools(ToolBo toolBo) {
        List<ToolPo> toolPos = toolMapper.selectTool(toolBo);
        int count = toolMapper.count(toolBo);
        return new PageVO<>(toolBo.getPageSize(), toolBo.getPageNum(), count, toolPos);
    }

    public PageVO listToolsFromSuit(SuitBo suitBo) {
        SuitPo suitPo = suitService.selectById(suitBo.getId());
        List<Integer> tidlist = Lists.newArrayList();
        if (suitPo != null) {
            String toolList = suitPo.getToolList();
            tidlist = StringUtil.getListFromString(toolList, ",", Integer::parseInt);
        }
        List<ToolPo> toolPos = toolMapper.selectToolByIds(tidlist);
        return new PageVO<>(1, 1, 1, toolPos);
    }

    public void refreshTool2db() {
        ResponseEntity send = ApaasUtil.send(ApaasUtil.TOOL_LIST_URL, "", String.class);
        String body = (String) send.getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);
        JSONArray data = (JSONArray) jsonObject.get("data");
        List<ToolPo> tools = Lists.newArrayList();
        List<Integer> ids = Lists.newArrayList();
        for (Object toolData : data) {
            JSONObject tdObj = (JSONObject) toolData;
            ToolPo toolPo = tdObj.toJavaObject(ToolPo.class);
            //conflict field
            toolPo.setPriv(Integer.parseInt(String.valueOf(tdObj.get("private"))));
            tools.add(toolPo);
            System.out.println(toolPo.getId());
            ids.add(toolPo.getId());
        }
        insertOrUpdateDb(ids, tools);
    }

    public void insertOrUpdateDb(List<Integer> ids, List<ToolPo> tools) {
        List<ToolPo> oldTools = toolMapper.selectToolByIds(ids);
        List<Integer> oldIds = oldTools.stream().map(ToolPo::getId).collect(Collectors.toList());
        for (ToolPo tool : tools) {
            if (oldIds.contains(tool.getId())) {
                toolMapper.updateById(tool);
                System.out.println("更新+1");
            } else {
                toolMapper.saveOne(tool);
                System.out.println("插入+1");
            }
        }
    }

    public Integer updateTool(ToolBo tool){
        return toolMapper.updateById(tool);
    }

    public Integer insertTool(ToolBo tool){
        return toolMapper.saveOne(tool);
    }
}

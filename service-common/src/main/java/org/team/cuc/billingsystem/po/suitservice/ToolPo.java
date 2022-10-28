package org.team.cuc.billingsystem.po.suitservice;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName t_tool
 */
@Data
public class ToolPo implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String subType;

    /**
     * 
     */
    private String coverAddr;

    /**
     * 
     */
    private Integer classifyId;

    /**
     * 
     */
    private String remarksTitle;

    /**
     * 
     */
    private Double price;

    /**
     * 
     */
    private String remarks;

    /**
     * 
     */
    private Integer type;

    /**
     * 
     */
    private Integer open;

    /**
     * 
     */
    private Integer openAccess;

    /**
     * 
     */
    private Integer frame;

    /**
     * 
     */
    private String systemType;

    /**
     * 
     */
    private String status;

    /**
     * 
     */
    private String authStatus;

    /**
     * 
     */
    private String hide;

    /**
     * 
     */
    private Long createTime;

    /**
     * 
     */
    private Long upTime;

    /**
     * 
     */
    private String toolUrl;

    /**
     * 
     */
    private String toolApi;

    /**
     * 
     */
    private String accessId;

    /**
     * 
     */
    private String accessKey;

    /**
     * 
     */
    private String toolAccessid;

    /**
     * 
     */
    private String toolAccesskey;

    /**
     * 
     */
    private Integer parentUin;

    /**
     * 
     */
    private String userId;

    /**
     * 
     */
    private String backInfo;

    /**
     * 
     */
    private Integer priv;

    /**
     * 
     */
    private String button;

    /**
     * 
     */
    private String inputNum;

    /**
     * 
     */
    private String outputNum;

    /**
     * 
     */
    private Integer costType;

    /**
     * 
     */
    private String spceMoney;

    /**
     *
     */
    private Double cost;

    /**
     *
     */
    private Double sell;

    /**
     *
     */
    private Double userProfit;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ToolPo other = (ToolPo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getSubType() == null ? other.getSubType() == null : this.getSubType().equals(other.getSubType()))
            && (this.getCoverAddr() == null ? other.getCoverAddr() == null : this.getCoverAddr().equals(other.getCoverAddr()))
            && (this.getClassifyId() == null ? other.getClassifyId() == null : this.getClassifyId().equals(other.getClassifyId()))
            && (this.getRemarksTitle() == null ? other.getRemarksTitle() == null : this.getRemarksTitle().equals(other.getRemarksTitle()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getOpen() == null ? other.getOpen() == null : this.getOpen().equals(other.getOpen()))
            && (this.getOpenAccess() == null ? other.getOpenAccess() == null : this.getOpenAccess().equals(other.getOpenAccess()))
            && (this.getFrame() == null ? other.getFrame() == null : this.getFrame().equals(other.getFrame()))
            && (this.getSystemType() == null ? other.getSystemType() == null : this.getSystemType().equals(other.getSystemType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAuthStatus() == null ? other.getAuthStatus() == null : this.getAuthStatus().equals(other.getAuthStatus()))
            && (this.getHide() == null ? other.getHide() == null : this.getHide().equals(other.getHide()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpTime() == null ? other.getUpTime() == null : this.getUpTime().equals(other.getUpTime()))
            && (this.getToolUrl() == null ? other.getToolUrl() == null : this.getToolUrl().equals(other.getToolUrl()))
            && (this.getToolApi() == null ? other.getToolApi() == null : this.getToolApi().equals(other.getToolApi()))
            && (this.getAccessId() == null ? other.getAccessId() == null : this.getAccessId().equals(other.getAccessId()))
            && (this.getAccessKey() == null ? other.getAccessKey() == null : this.getAccessKey().equals(other.getAccessKey()))
            && (this.getToolAccessid() == null ? other.getToolAccessid() == null : this.getToolAccessid().equals(other.getToolAccessid()))
            && (this.getToolAccesskey() == null ? other.getToolAccesskey() == null : this.getToolAccesskey().equals(other.getToolAccesskey()))
            && (this.getParentUin() == null ? other.getParentUin() == null : this.getParentUin().equals(other.getParentUin()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getBackInfo() == null ? other.getBackInfo() == null : this.getBackInfo().equals(other.getBackInfo()))
            && (this.getPriv() == null ? other.getPriv() == null : this.getPriv().equals(other.getPriv()))
            && (this.getButton() == null ? other.getButton() == null : this.getButton().equals(other.getButton()))
            && (this.getInputNum() == null ? other.getInputNum() == null : this.getInputNum().equals(other.getInputNum()))
            && (this.getOutputNum() == null ? other.getOutputNum() == null : this.getOutputNum().equals(other.getOutputNum()))
            && (this.getCostType() == null ? other.getCostType() == null : this.getCostType().equals(other.getCostType()))
            && (this.getSpceMoney() == null ? other.getSpceMoney() == null : this.getSpceMoney().equals(other.getSpceMoney()))
            && (this.getCost() == null ? other.getCost() == null : this.getCost().equals(other.getCost()))
            && (this.getSell() == null ? other.getSell() == null : this.getSell().equals(other.getSell()))
            && (this.getUserProfit() == null ? other.getUserProfit() == null : this.getUserProfit().equals(other.getUserProfit()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getSubType() == null) ? 0 : getSubType().hashCode());
        result = prime * result + ((getCoverAddr() == null) ? 0 : getCoverAddr().hashCode());
        result = prime * result + ((getClassifyId() == null) ? 0 : getClassifyId().hashCode());
        result = prime * result + ((getRemarksTitle() == null) ? 0 : getRemarksTitle().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getOpen() == null) ? 0 : getOpen().hashCode());
        result = prime * result + ((getOpenAccess() == null) ? 0 : getOpenAccess().hashCode());
        result = prime * result + ((getFrame() == null) ? 0 : getFrame().hashCode());
        result = prime * result + ((getSystemType() == null) ? 0 : getSystemType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAuthStatus() == null) ? 0 : getAuthStatus().hashCode());
        result = prime * result + ((getHide() == null) ? 0 : getHide().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpTime() == null) ? 0 : getUpTime().hashCode());
        result = prime * result + ((getToolUrl() == null) ? 0 : getToolUrl().hashCode());
        result = prime * result + ((getToolApi() == null) ? 0 : getToolApi().hashCode());
        result = prime * result + ((getAccessId() == null) ? 0 : getAccessId().hashCode());
        result = prime * result + ((getAccessKey() == null) ? 0 : getAccessKey().hashCode());
        result = prime * result + ((getToolAccessid() == null) ? 0 : getToolAccessid().hashCode());
        result = prime * result + ((getToolAccesskey() == null) ? 0 : getToolAccesskey().hashCode());
        result = prime * result + ((getParentUin() == null) ? 0 : getParentUin().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getBackInfo() == null) ? 0 : getBackInfo().hashCode());
        result = prime * result + ((getPriv() == null) ? 0 : getPriv().hashCode());
        result = prime * result + ((getButton() == null) ? 0 : getButton().hashCode());
        result = prime * result + ((getInputNum() == null) ? 0 : getInputNum().hashCode());
        result = prime * result + ((getOutputNum() == null) ? 0 : getOutputNum().hashCode());
        result = prime * result + ((getCostType() == null) ? 0 : getCostType().hashCode());
        result = prime * result + ((getSpceMoney() == null) ? 0 : getSpceMoney().hashCode());
        result = prime * result + ((getCost() == null) ? 0 : getCost().hashCode());
        result = prime * result + ((getSell() == null) ? 0 : getSell().hashCode());
        result = prime * result + ((getUserProfit() == null) ? 0 : getUserProfit().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", subType=").append(subType);
        sb.append(", coverAddr=").append(coverAddr);
        sb.append(", classifyId=").append(classifyId);
        sb.append(", remarksTitle=").append(remarksTitle);
        sb.append(", price=").append(price);
        sb.append(", remarks=").append(remarks);
        sb.append(", type=").append(type);
        sb.append(", open=").append(open);
        sb.append(", openAccess=").append(openAccess);
        sb.append(", frame=").append(frame);
        sb.append(", systemType=").append(systemType);
        sb.append(", status=").append(status);
        sb.append(", authStatus=").append(authStatus);
        sb.append(", hide=").append(hide);
        sb.append(", createTime=").append(createTime);
        sb.append(", upTime=").append(upTime);
        sb.append(", toolUrl=").append(toolUrl);
        sb.append(", toolApi=").append(toolApi);
        sb.append(", accessId=").append(accessId);
        sb.append(", accessKey=").append(accessKey);
        sb.append(", toolAccessid=").append(toolAccessid);
        sb.append(", toolAccesskey=").append(toolAccesskey);
        sb.append(", parentUin=").append(parentUin);
        sb.append(", userId=").append(userId);
        sb.append(", backInfo=").append(backInfo);
        sb.append(", private=").append(priv);
        sb.append(", button=").append(button);
        sb.append(", inputNum=").append(inputNum);
        sb.append(", outputNum=").append(outputNum);
        sb.append(", costType=").append(costType);
        sb.append(", spceMoney=").append(spceMoney);
        sb.append(", cost=").append(cost);
        sb.append(", sell=").append(sell);
        sb.append(", userProfit=").append(userProfit);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
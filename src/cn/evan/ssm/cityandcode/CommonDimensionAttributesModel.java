package cn.evan.ssm.cityandcode;

import com.alibaba.fastjson.annotation.JSONField;
import com.sun.istack.internal.NotNull;

import java.io.Serializable;
import java.util.Date;


public class CommonDimensionAttributesModel implements Serializable {


    private Integer fid;


    @NotNull
    private Integer fdimensionId;


    @NotNull
    private String fattributeCnName;


    private String fattributeEnName;


    private String fattributeExtendName;


    @NotNull
    private String fattributeValue;


    private Short fstate;


    private String fstateName;


    private String fcreateUser;


    private Date fcreateTime;


    private String fupdateUser;


    private Date fmodifyTime;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getFdimensionId() {
        return fdimensionId;
    }

    public void setFdimensionId(Integer fdimensionId) {
        this.fdimensionId = fdimensionId;
    }

    public String getFattributeCnName() {
        return fattributeCnName;
    }

    public void setFattributeCnName(String fattributeCnName) {
        this.fattributeCnName = fattributeCnName == null ? null : fattributeCnName.trim();
    }

    public String getFattributeEnName() {
        return fattributeEnName;
    }

    public void setFattributeEnName(String fattributeEnName) {
        this.fattributeEnName = fattributeEnName == null ? null : fattributeEnName.trim();
    }

    public String getFattributeExtendName() {
        return fattributeExtendName;
    }

    public void setFattributeExtendName(String fattributeExtendName) {
        this.fattributeExtendName = fattributeExtendName == null ? null : fattributeExtendName.trim();
    }

    public String getFattributeValue() {
        return fattributeValue;
    }

    public void setFattributeValue(String fattributeValue) {
        this.fattributeValue = fattributeValue;
    }

    public Short getFstate() {
        return fstate;
    }

    public void setFstate(Short fstate) {
        this.fstate = fstate;
    }

    public String getFstateName() {
        return fstateName;
    }

    public void setFstateName(String fstateName) {
        this.fstateName = fstateName;
    }

    public String getFcreateUser() {
        return fcreateUser;
    }

    public void setFcreateUser(String fcreateUser) {
        this.fcreateUser = fcreateUser == null ? null : fcreateUser.trim();
    }

    public Date getFcreateTime() {
        return fcreateTime;
    }

    public void setFcreateTime(Date fcreateTime) {
        this.fcreateTime = fcreateTime;
    }

    public String getFupdateUser() {
        return fupdateUser;
    }

    public void setFupdateUser(String fupdateUser) {
        this.fupdateUser = fupdateUser == null ? null : fupdateUser.trim();
    }

    public Date getFmodifyTime() {
        return fmodifyTime;
    }

    public void setFmodifyTime(Date fmodifyTime) {
        this.fmodifyTime = fmodifyTime;
    }

}
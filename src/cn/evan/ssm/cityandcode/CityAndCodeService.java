package cn.evan.ssm.cityandcode;

public interface CityAndCodeService {
    /**
     *将city and code key：value形式 以英文字母排序  三个一组 返回json格式
     * @param fdimensionId
     * @return
     */
    public String transformationCityArray(Integer fdimensionId);
}

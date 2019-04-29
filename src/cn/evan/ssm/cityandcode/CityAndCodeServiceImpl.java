package cn.evan.ssm.cityandcode;

import com.alibaba.fastjson.JSONObject;
import com.tenpay.crisk.common.ErrorCodeInfo;
import com.tenpay.crisk.rule.dao.CommonDimensionAttributesDao;
import com.tenpay.crisk.rule.model.CityCodeContainer;
import com.tenpay.crisk.rule.model.CommonDimensionAttributesModel;
import com.tenpay.crisk.util.ChineseInitialsUtils;
import com.tenpay.fsmart.common.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author V_liwenyang
 */
@Service("cityAndCodeService")
public class CityAndCodeServiceImpl implements CityAndCodeService {
    private static Map<String, String> cityKeyMap = new HashMap();
    private static Map<String, String> codeKeyMap = new HashMap();
    @Autowired
    private CityCodeContainer cityCodeContainer = new CityCodeContainer();
    public static final String stringABC = "ABC";
    public static final String stringDEF = "DEF";
    public static final String stringGHI = "GHI";
    public static final String stringJKL = "JKL";
    public static final String stringMNO = "MNO";
    public static final String stringPQR = "PQR";
    public static final String stringSTU = "STU";
    public static final String stringVWX = "VWX";
    public static final String stringYZ = "YZ";


    public static final String stringA = "A";
    public static final String stringB = "B";
    public static final String stringC = "C";
    public static final String stringD = "D";
    public static final String stringE = "E";
    public static final String stringF = "F";
    public static final String stringG = "G";
    public static final String stringH = "H";
    public static final String stringI = "I";
    public static final String stringJ = "J";
    public static final String stringK = "K";
    public static final String stringL = "L";
    public static final String stringM = "M";
    public static final String stringN = "N";
    public static final String stringO = "O";
    public static final String stringP = "P";
    public static final String stringQ = "Q";
    public static final String stringR = "R";
    public static final String stringS = "S";
    public static final String stringT = "T";
    public static final String stringU = "U";
    public static final String stringV = "V";
    public static final String stringW = "W";
    public static final String stringX = "X";
    public static final String stringY = "Y";
    public static final String stringZ = "Z";
    List<String> cityFirstList = null;
    List<String> cityThreeList = null;

    @Autowired
    private CommonDimensionAttributesDao commonDimensionAttributesDao;

    private static Logger logger = LoggerFactory.getLogger(CityAndCodeServiceImpl.class);


    /**
     * 获取city和code对应数据
     *
     * @param commonDimensionAttributesModelList
     */
    public void getResourceCityAndCode(List<CommonDimensionAttributesModel> commonDimensionAttributesModelList) {
        for (CommonDimensionAttributesModel commonDimensionAttributesModel : commonDimensionAttributesModelList) {
            cityKeyMap.put(commonDimensionAttributesModel.getFattributeCnName(), commonDimensionAttributesModel.getFattributeValue());
            codeKeyMap.put(commonDimensionAttributesModel.getFattributeValue(), commonDimensionAttributesModel.getFattributeCnName());
        }
    }

    public static String getCodeByCity(String city) {
        return cityKeyMap.get(city);
    }

    public static String getCityByCode(String code) {
        return codeKeyMap.get(code);
    }
    @Override
    public String transformationCityArray(Integer fdimensionId) {
        logger.info("CityAndCodeServiceImpl transformationCityArray fdimensionId is {}", fdimensionId);
        List<CommonDimensionAttributesModel> commonDimensionAttributesModelList = commonDimensionAttributesDao.queryByDimensionAllAttributes(fdimensionId);
        logger.info("CityAndCodeServiceImpl transformationCityArray commonDimensionAttributesModelList is {}", JSONObject.toJSONString(commonDimensionAttributesModelList));
        //判断传的list是否为空
        if (commonDimensionAttributesModelList == null) {
            throw new BaseException(ErrorCodeInfo.EXECUTE_FAILED_INVALID_PARAMETER_CODE,
                    ErrorCodeInfo.EXECUTE_FAILED_INVALID_PARAMETER_MSG);
        }
        getResourceCityAndCode(commonDimensionAttributesModelList);
        logger.info("CityAndCodeServiceImpl getResourceCityAndCode cityKeyMap is {},codeKeyMap is{}", JSONObject.toJSONString(cityKeyMap),JSONObject.toJSONString(codeKeyMap));
        //遍历map集合
        for (String key : cityKeyMap.keySet()) {
            String firstSector = ChineseInitialsUtils.getFirstSectorPositionCode(key).toUpperCase();
            //是否新建单个map集合封装数据
            if (cityFirstList == null) {
                cityFirstList = new ArrayList<String>(26);
                cityFirstList.add(firstSector);
                newCityAndCodeOneCollectionMap(firstSector);
            } else {
                if (!cityFirstList.contains(firstSector)) {
                    cityFirstList.add(firstSector);
                    //新建集合
                    newCityAndCodeOneCollectionMap(firstSector);
                }
            }
            //将城市和编码放到map集合当中
            putCityAndCodeMap(firstSector, key);
        }
        //是否需要新建三个英文的map集合
        isNewThreeCityCodeMap();
        //将3个map abc 封装到一个map 集合当中
        putThreeCityTogether();
        //将所有整个到一起
        putAllTogether();
        logger.info("CityAndCodeServiceImpl transformationCityArray fdimensionId is {}", fdimensionId);
        String result = JSONObject.toJSONString(cityCodeContainer.getMapAll());
        logger.info("CityAndCodeServiceImpl transformationCityArray result is {}", result);
        return result;
    }

    /**
     * 是否需要参加三个字母map集合
     */
    public void isNewThreeCityCodeMap() {
        for (String mapFirst : cityFirstList) {
            //如果三个map集合有数据，则新建map集合封装
            if (cityThreeList == null) {
                cityThreeList = new ArrayList<String>();
            }
            if (cityFirstList.contains(stringA) || cityFirstList.contains(stringB) || cityFirstList.contains(stringC)) {
                if (!cityThreeList.contains(stringABC)) {
                    cityThreeList.add(stringABC);
                    newCityAndCodeThreeCollectionMap(stringABC);
                }
            }
            if (cityFirstList.contains(stringD) || cityFirstList.contains(stringE) || cityFirstList.contains(stringF)) {
                if (!cityThreeList.contains(stringDEF)) {
                    cityThreeList.add(stringDEF);
                    newCityAndCodeThreeCollectionMap(stringDEF);
                }
            }
            if (cityFirstList.contains(stringG) || cityFirstList.contains(stringH) || cityFirstList.contains(stringI)) {
                if (!cityThreeList.contains(stringGHI)) {
                    cityThreeList.add(stringGHI);
                    newCityAndCodeThreeCollectionMap(stringGHI);
                }
            }
            if (cityFirstList.contains(stringJ) || cityFirstList.contains(stringK) || cityFirstList.contains(stringL)) {
                if (!cityThreeList.contains(stringJKL)) {
                    cityThreeList.add(stringJKL);
                    newCityAndCodeThreeCollectionMap(stringJKL);
                }
            }
            if (cityFirstList.contains(stringM) || cityFirstList.contains(stringN) || cityFirstList.contains(stringO)) {
                if (!cityThreeList.contains(stringMNO)) {
                    cityThreeList.add(stringMNO);
                    newCityAndCodeThreeCollectionMap(stringMNO);
                }
            }
            if (cityFirstList.contains(stringP) || cityFirstList.contains(stringQ) || cityFirstList.contains(stringR)) {
                if (!cityThreeList.contains(stringPQR)) {
                    cityThreeList.add(stringPQR);
                    newCityAndCodeThreeCollectionMap(stringPQR);
                }
            }
            if (cityFirstList.contains(stringS) || cityFirstList.contains(stringU) || cityFirstList.contains(stringV)) {
                if (!cityThreeList.contains(stringSTU)) {
                    cityThreeList.add(stringSTU);
                    newCityAndCodeThreeCollectionMap(stringSTU);
                }
            }
            if (cityFirstList.contains(stringV) || cityFirstList.contains(stringW) || cityFirstList.contains(stringX)) {
                if (!cityThreeList.contains(stringVWX)) {
                    cityThreeList.add(stringVWX);
                    newCityAndCodeThreeCollectionMap(stringVWX);
                }
            }
            if (cityFirstList.contains(stringY) || cityFirstList.contains(stringZ)) {
                if (!cityThreeList.contains(stringYZ)) {
                    cityThreeList.add(stringYZ);
                    newCityAndCodeThreeCollectionMap(stringYZ);
                }
            }
        }
    }

    /**
     * 新建单个字母map集合
     *
     * @param firstSector
     */
    public void newCityAndCodeOneCollectionMap(String firstSector) {
        switch (firstSector) {
            case stringA:
                cityCodeContainer.setMapA(new TreeMap<String, String>());
                break;
            case stringB:
                cityCodeContainer.setMapB(new TreeMap<String, String>());
                break;
            case stringC:
                cityCodeContainer.setMapC(new TreeMap<String, String>());
                break;
            case stringD:
                cityCodeContainer.setMapD(new TreeMap<String, String>());
                break;
            case stringE:
                cityCodeContainer.setMapE(new TreeMap<String, String>());
                break;
            case stringF:
                cityCodeContainer.setMapF(new TreeMap<String, String>());
                break;
            case stringG:
                cityCodeContainer.setMapG(new TreeMap<String, String>());
                break;
            case stringH:
                cityCodeContainer.setMapH(new TreeMap<String, String>());
                break;
            case stringI:
                cityCodeContainer.setMapI(new TreeMap<String, String>());
                break;
            case stringJ:
                cityCodeContainer.setMapJ(new TreeMap<String, String>());
                break;
            case stringK:
                cityCodeContainer.setMapK(new TreeMap<String, String>());
                break;
            case stringL:
                cityCodeContainer.setMapL(new TreeMap<String, String>());
                break;
            case stringM:
                cityCodeContainer.setMapM(new TreeMap<String, String>());
                break;
            case stringN:
                cityCodeContainer.setMapN(new TreeMap<String, String>());
                break;
            case stringO:
                cityCodeContainer.setMapO(new TreeMap<String, String>());
                break;
            case stringP:
                cityCodeContainer.setMapP(new TreeMap<String, String>());
                break;
            case stringQ:
                cityCodeContainer.setMapQ(new TreeMap<String, String>());
                break;
            case stringR:
                cityCodeContainer.setMapR(new TreeMap<String, String>());
                break;
            case stringS:
                cityCodeContainer.setMapS(new TreeMap<String, String>());
                break;
            case stringT:
                cityCodeContainer.setMapT(new TreeMap<String, String>());
                break;
            case stringU:
                cityCodeContainer.setMapU(new TreeMap<String, String>());
                break;
            case stringV:
                cityCodeContainer.setMapV(new TreeMap<String, String>());
                break;
            case stringW:
                cityCodeContainer.setMapW(new TreeMap<String, String>());
                break;
            case stringX:
                cityCodeContainer.setMapX(new TreeMap<String, String>());
                break;
            case stringY:
                cityCodeContainer.setMapY(new TreeMap<String, String>());
                break;
            case stringZ:
                cityCodeContainer.setMapZ(new TreeMap<String, String>());
                break;
        }
    }

    /**
     * 新建3个英文map集合
     *
     * @param threeCollectionMap
     */
    public void newCityAndCodeThreeCollectionMap(String threeCollectionMap) {
        switch (threeCollectionMap) {
            case stringABC:
                cityCodeContainer.setMapABC(new TreeMap<String, Map<String, String>>());
                break;
            case stringDEF:
                cityCodeContainer.setMapDEF(new TreeMap<String, Map<String, String>>());
                break;
            case stringGHI:
                cityCodeContainer.setMapGHI(new TreeMap<String, Map<String, String>>());
                break;
            case stringJKL:
                cityCodeContainer.setMapJKL(new TreeMap<String, Map<String, String>>());
                break;
            case stringMNO:
                cityCodeContainer.setMapMNO(new TreeMap<String, Map<String, String>>());
                break;
            case stringPQR:
                cityCodeContainer.setMapPQR(new TreeMap<String, Map<String, String>>());
                break;
            case stringSTU:
                cityCodeContainer.setMapSTU(new TreeMap<String, Map<String, String>>());
                break;
            case stringVWX:
                cityCodeContainer.setMapVWX(new TreeMap<String, Map<String, String>>());
                break;
            case stringYZ:
                cityCodeContainer.setMapYZ(new TreeMap<String, Map<String, String>>());
                break;
        }
    }


    /**
     * 将城市和编码放到map集合当中
     *
     * @param firstSector
     * @param key
     */
    public void putCityAndCodeMap(String firstSector, String key) {
        switch (firstSector) {
            case stringA:
                cityCodeContainer.getMapA().put(key, cityKeyMap.get(key));
                break;
            case stringB:
                cityCodeContainer.getMapB().put(key, cityKeyMap.get(key));
                break;
            case stringC:
                cityCodeContainer.getMapC().put(key, cityKeyMap.get(key));
                break;
            case stringD:
                cityCodeContainer.getMapD().put(key, cityKeyMap.get(key));
                break;
            case stringE:
                cityCodeContainer.getMapE().put(key, cityKeyMap.get(key));
                break;
            case stringF:
                cityCodeContainer.getMapF().put(key, cityKeyMap.get(key));
                break;
            case stringG:
                cityCodeContainer.getMapG().put(key, cityKeyMap.get(key));
                break;
            case stringH:
                cityCodeContainer.getMapH().put(key, cityKeyMap.get(key));
                break;
            case stringI:
                cityCodeContainer.getMapI().put(key, cityKeyMap.get(key));
                break;
            case stringJ:
                cityCodeContainer.getMapJ().put(key, cityKeyMap.get(key));
                break;
            case stringK:
                cityCodeContainer.getMapK().put(key, cityKeyMap.get(key));
                break;
            case stringL:
                cityCodeContainer.getMapL().put(key, cityKeyMap.get(key));
                break;
            case stringM:
                cityCodeContainer.getMapM().put(key, cityKeyMap.get(key));
                break;
            case stringN:
                cityCodeContainer.getMapN().put(key, cityKeyMap.get(key));
                break;
            case stringO:
                cityCodeContainer.getMapO().put(key, cityKeyMap.get(key));
                break;
            case stringP:
                cityCodeContainer.getMapP().put(key, cityKeyMap.get(key));
                break;
            case stringQ:
                cityCodeContainer.getMapQ().put(key, cityKeyMap.get(key));
                break;
            case stringR:
                cityCodeContainer.getMapR().put(key, cityKeyMap.get(key));
                break;
            case stringS:
                cityCodeContainer.getMapS().put(key, cityKeyMap.get(key));
                break;
            case stringT:
                cityCodeContainer.getMapT().put(key, cityKeyMap.get(key));
                break;
            case stringU:
                cityCodeContainer.getMapU().put(key, cityKeyMap.get(key));
                break;
            case stringV:
                cityCodeContainer.getMapV().put(key, cityKeyMap.get(key));
                break;
            case stringW:
                cityCodeContainer.getMapW().put(key, cityKeyMap.get(key));
                break;
            case stringX:
                cityCodeContainer.getMapX().put(key, cityKeyMap.get(key));
                break;
            case stringY:
                cityCodeContainer.getMapY().put(key, cityKeyMap.get(key));
                break;
            case stringZ:
                cityCodeContainer.getMapZ().put(key, cityKeyMap.get(key));
                break;
        }
    }

    /**
     * 将map集合三个一组封装
     */
    public void putThreeCityTogether() {
        if (cityCodeContainer.getMapA() != null || cityCodeContainer.getMapB() != null || cityCodeContainer.getMapC() != null) {
            if (cityCodeContainer.getMapA() != null) {
                cityCodeContainer.getMapABC().put(stringA, cityCodeContainer.getMapA());
            }
            if (cityCodeContainer.getMapB() != null) {
                cityCodeContainer.getMapABC().put(stringB, cityCodeContainer.getMapB());
            }
            if (cityCodeContainer.getMapC() != null) {
                cityCodeContainer.getMapABC().put(stringC, cityCodeContainer.getMapC());
            }
        }


        if (cityCodeContainer.getMapD() != null || cityCodeContainer.getMapE() != null || cityCodeContainer.getMapF() != null) {
            if (cityCodeContainer.getMapD() != null) {
                cityCodeContainer.getMapDEF().put(stringD, cityCodeContainer.getMapD());
            }
            if (cityCodeContainer.getMapE() != null) {
                cityCodeContainer.getMapDEF().put(stringE, cityCodeContainer.getMapE());
            }
            if (cityCodeContainer.getMapF() != null) {
                cityCodeContainer.getMapDEF().put(stringF, cityCodeContainer.getMapF());
            }
        }


        if (cityCodeContainer.getMapG() != null || cityCodeContainer.getMapH() != null || cityCodeContainer.getMapI() != null) {
            if (cityCodeContainer.getMapG() != null) {
                cityCodeContainer.getMapGHI().put(stringG, cityCodeContainer.getMapG());
            }
            if (cityCodeContainer.getMapH() != null) {
                cityCodeContainer.getMapGHI().put(stringH, cityCodeContainer.getMapH());
            }
            if (cityCodeContainer.getMapI() != null) {
                cityCodeContainer.getMapGHI().put(stringI, cityCodeContainer.getMapI());
            }
        }


        if ((cityCodeContainer.getMapJ() != null) || (cityCodeContainer.getMapK() != null) || (cityCodeContainer.getMapL() != null)) {
            if (cityCodeContainer.getMapJ() != null) {
                cityCodeContainer.getMapJKL().put(stringJ, cityCodeContainer.getMapJ());
            }
            if (cityCodeContainer.getMapK() != null) {
                cityCodeContainer.getMapJKL().put(stringK, cityCodeContainer.getMapK());
            }
            if (cityCodeContainer.getMapL() != null) {
                cityCodeContainer.getMapJKL().put(stringL, cityCodeContainer.getMapL());
            }
        }


        if ((cityCodeContainer.getMapM() != null) || (cityCodeContainer.getMapN() != null) || (cityCodeContainer.getMapO() != null)) {
            if (cityCodeContainer.getMapM() != null) {
                cityCodeContainer.getMapMNO().put(stringM, cityCodeContainer.getMapM());
            }
            if (cityCodeContainer.getMapN() != null) {
                cityCodeContainer.getMapMNO().put(stringN, cityCodeContainer.getMapN());
            }
            if (cityCodeContainer.getMapO() != null) {
                cityCodeContainer.getMapMNO().put(stringO, cityCodeContainer.getMapO());
            }
        }


        if ((cityCodeContainer.getMapP() != null) || (cityCodeContainer.getMapQ() != null) || (cityCodeContainer.getMapR() != null)) {
            if (cityCodeContainer.getMapP() != null) {
                cityCodeContainer.getMapPQR().put(stringP, cityCodeContainer.getMapP());
            }
            if (cityCodeContainer.getMapQ() != null) {
                cityCodeContainer.getMapPQR().put(stringQ, cityCodeContainer.getMapQ());
            }
            if (cityCodeContainer.getMapR() != null) {
                cityCodeContainer.getMapPQR().put(stringR, cityCodeContainer.getMapR());
            }
        }


        if ((cityCodeContainer.getMapT() != null) || (cityCodeContainer.getMapS() != null) || (cityCodeContainer.getMapU() != null)) {
            if (cityCodeContainer.getMapS() != null) {
                cityCodeContainer.getMapSTU().put(stringS, cityCodeContainer.getMapS());
            }
            if (cityCodeContainer.getMapT() != null) {
                cityCodeContainer.getMapSTU().put(stringT, cityCodeContainer.getMapT());
            }
            if (cityCodeContainer.getMapU() != null) {
                cityCodeContainer.getMapSTU().put(stringU, cityCodeContainer.getMapU());
            }
        }


        if ((cityCodeContainer.getMapV() != null) || (cityCodeContainer.getMapW() != null) || (cityCodeContainer.getMapX() != null)) {
            if (cityCodeContainer.getMapV() != null) {
                cityCodeContainer.getMapVWX().put(stringV, cityCodeContainer.getMapV());
            }
            if (cityCodeContainer.getMapW() != null) {
                cityCodeContainer.getMapVWX().put(stringW, cityCodeContainer.getMapW());
            }
            if (cityCodeContainer.getMapX() != null) {
                cityCodeContainer.getMapVWX().put(stringX, cityCodeContainer.getMapX());
            }
        }


        if ((cityCodeContainer.getMapY() != null) || (cityCodeContainer.getMapZ() != null)) {
            if (cityCodeContainer.getMapY() != null) {
                cityCodeContainer.getMapYZ().put(stringY, cityCodeContainer.getMapY());
            }
            if (cityCodeContainer.getMapZ() != null) {
                cityCodeContainer.getMapYZ().put(stringZ, cityCodeContainer.getMapZ());
            }
        }
    }

    /**
     * 合并成一个map集合
     */
    public void putAllTogether() {
        if ((cityCodeContainer.getMapABC() != null) || (cityCodeContainer.getMapDEF() != null) || (cityCodeContainer.getMapGHI() != null) || (cityCodeContainer.getMapJKL() != null) || (cityCodeContainer.getMapMNO() != null) ||
                (cityCodeContainer.getMapPQR() != null) || (cityCodeContainer.getMapSTU() != null) || (cityCodeContainer.getMapVWX() != null) || (cityCodeContainer.getMapYZ() != null)) {
            cityCodeContainer.setMapAll(new TreeMap<String, Map<String, Map<String, String>>>());
        }
        if (cityCodeContainer.getMapABC() != null) {
            cityCodeContainer.getMapAll().put(stringABC, cityCodeContainer.getMapABC());
        }
        if (cityCodeContainer.getMapDEF() != null) {
            cityCodeContainer.getMapAll().put(stringDEF, cityCodeContainer.getMapDEF());
        }

        if (cityCodeContainer.getMapGHI() != null) {
            cityCodeContainer.getMapAll().put(stringGHI, cityCodeContainer.getMapGHI());
        }

        if (cityCodeContainer.getMapJKL() != null) {
            cityCodeContainer.getMapAll().put(stringJKL, cityCodeContainer.getMapJKL());
        }

        if (cityCodeContainer.getMapMNO() != null) {
            cityCodeContainer.getMapAll().put(stringMNO, cityCodeContainer.getMapMNO());
        }

        if (cityCodeContainer.getMapPQR() != null) {
            cityCodeContainer.getMapAll().put(stringPQR, cityCodeContainer.getMapPQR());
        }

        if (cityCodeContainer.getMapSTU() != null) {
            cityCodeContainer.getMapAll().put(stringSTU, cityCodeContainer.getMapSTU());
        }

        if (cityCodeContainer.getMapVWX() != null) {
            cityCodeContainer.getMapAll().put(stringVWX, cityCodeContainer.getMapVWX());
        }
        if (cityCodeContainer.getMapYZ() != null) {
            cityCodeContainer.getMapAll().put(stringYZ, cityCodeContainer.getMapYZ());
        }
    }

    //测试代码
   /* static {
        cityKeyMap.put("珠海市", "4404");
        codeKeyMap.put("4404", "珠海市");
        cityKeyMap.put("汕头市", "4405");
        codeKeyMap.put("4405", "汕头市");
        cityKeyMap.put("湛江市", "4408");
        codeKeyMap.put("4408", "湛江市");
        cityKeyMap.put("湘西土家族苗族自治州", "4331");
        codeKeyMap.put("4331", "湘西土家族苗族自治州");
        cityKeyMap.put("广州市", "4401");
        codeKeyMap.put("4401", "广州市");
        cityKeyMap.put("苏州市", "3205");
        codeKeyMap.put("3205", "苏州市");
        cityKeyMap.put("衡阳市", "4304");
        codeKeyMap.put("4304", "衡阳市");
        cityKeyMap.put("邵阳市", "4305");
        codeKeyMap.put("4305", "邵阳市");
        cityKeyMap.put("岳阳市", "4306");
        codeKeyMap.put("4306", "岳阳市");
        cityKeyMap.put("常德市", "4307");
        codeKeyMap.put("4307", "常德市");
        cityKeyMap.put("张家界市", "4308");
        codeKeyMap.put("4308", "张家界市");
        cityKeyMap.put("郴州市", "4310");
        codeKeyMap.put("4310", "郴州市");
        cityKeyMap.put("杭州市", "3301");
        codeKeyMap.put("3301", "杭州市");
        cityKeyMap.put("随州市", "4213");
        codeKeyMap.put("4213", "随州市");
        cityKeyMap.put("恩施土家族苗族自治州", "4228");
        codeKeyMap.put("4228", "恩施土家族苗族自治州");
        cityKeyMap.put("三沙市", "4603");
        codeKeyMap.put("4603", "三沙市");
        cityKeyMap.put("重庆市", "5001");
        codeKeyMap.put("5001", "重庆市");
        cityKeyMap.put("辽阳市", "2110");
        codeKeyMap.put("2110", "辽阳市");
        cityKeyMap.put("盘锦市", "2111");
        codeKeyMap.put("2111", "盘锦市");
        cityKeyMap.put("铁岭市", "2112");
        codeKeyMap.put("2112", "铁岭市");
        cityKeyMap.put("葫芦岛市", "2114");
        codeKeyMap.put("2114", "葫芦岛市");
        cityKeyMap.put("长春市", "2201");
        codeKeyMap.put("2201", "长春市");
        cityKeyMap.put("吉林市", "2202");
        codeKeyMap.put("2202", "吉林市");
        cityKeyMap.put("四平市", "2203");
        codeKeyMap.put("2203", "四平市");
        cityKeyMap.put("通化市", "2205");
        codeKeyMap.put("2205", "通化市");
        cityKeyMap.put("滨州市", "3716");
        codeKeyMap.put("3716", "滨州市");
        cityKeyMap.put("郑州市", "4101");
        codeKeyMap.put("4101", "郑州市");
        cityKeyMap.put("开封市", "4102");
        codeKeyMap.put("4102", "开封市");
        cityKeyMap.put("新乡市", "4107");
        codeKeyMap.put("4107", "新乡市");
        cityKeyMap.put("漯河市", "4111");
        codeKeyMap.put("4111", "漯河市");
        cityKeyMap.put("南阳市", "4113");
        codeKeyMap.put("4113", "南阳市");
        cityKeyMap.put("商丘市", "4114");
        codeKeyMap.put("4114", "商丘市");
        cityKeyMap.put("信阳市", "4115");
        codeKeyMap.put("4115", "信阳市");
        cityKeyMap.put("河南省直辖县级行政区划", "4190");
        codeKeyMap.put("4190", "河南省直辖县级行政区划");
        cityKeyMap.put("武汉市", "4201");
        codeKeyMap.put("4201", "武汉市");
        cityKeyMap.put("宜昌市", "4205");
        codeKeyMap.put("4205", "宜昌市");
        cityKeyMap.put("鄂州市", "4207");
        codeKeyMap.put("4207", "鄂州市");
        cityKeyMap.put("孝感市", "4209");
        codeKeyMap.put("4209", "孝感市");
        cityKeyMap.put("福州市", "3501");
        codeKeyMap.put("3501", "福州市");
        cityKeyMap.put("厦门市", "3502");
        codeKeyMap.put("3502", "厦门市");
        cityKeyMap.put("三明市", "3504");
        codeKeyMap.put("3504", "三明市");
        cityKeyMap.put("泉州市", "3505");
        codeKeyMap.put("3505", "泉州市");
        cityKeyMap.put("南平市", "3507");
        codeKeyMap.put("3507", "南平市");
        cityKeyMap.put("宁德市", "3509");
        codeKeyMap.put("3509", "宁德市");
        cityKeyMap.put("南昌市", "3601");
        codeKeyMap.put("3601", "南昌市");
        cityKeyMap.put("深圳市", "4403");
        codeKeyMap.put("4403", "深圳市");
        cityKeyMap.put("成都市", "5101");
        codeKeyMap.put("5101", "成都市");
        cityKeyMap.put("攀枝花市", "5104");
        codeKeyMap.put("5104", "攀枝花市");
        cityKeyMap.put("威海市", "3710");
        codeKeyMap.put("3710", "威海市");
        cityKeyMap.put("日照市", "3711");
        codeKeyMap.put("3711", "日照市");
        cityKeyMap.put("临沂市", "3713");
        codeKeyMap.put("3713", "临沂市");
        cityKeyMap.put("聊城市", "3715");
        codeKeyMap.put("3715", "聊城市");
        cityKeyMap.put("阜新市", "2109");
        codeKeyMap.put("2109", "阜新市");
        cityKeyMap.put("张家口市", "1307");
        codeKeyMap.put("1307", "张家口市");
        cityKeyMap.put("承德市", "1308");
        codeKeyMap.put("1308", "承德市");
        cityKeyMap.put("景德镇市", "3602");
        codeKeyMap.put("3602", "景德镇市");
        cityKeyMap.put("萍乡市", "3603");
        codeKeyMap.put("3603", "萍乡市");
        cityKeyMap.put("九江市", "3604");
        codeKeyMap.put("3604", "九江市");
        cityKeyMap.put("新余市", "3605");
        codeKeyMap.put("3605", "新余市");
        cityKeyMap.put("鹰潭市", "3606");
        codeKeyMap.put("3606", "鹰潭市");
        cityKeyMap.put("吉安市", "3608");
        codeKeyMap.put("3608", "吉安市");
        cityKeyMap.put("抚州市", "3610");
        codeKeyMap.put("3610", "抚州市");
        cityKeyMap.put("上饶市", "3611");
        codeKeyMap.put("3611", "上饶市");
        cityKeyMap.put("锡林郭勒盟", "1525");
        codeKeyMap.put("1525", "锡林郭勒盟");
        cityKeyMap.put("鹤岗市", "2304");
        codeKeyMap.put("2304", "鹤岗市");
        cityKeyMap.put("盐城市", "3209");
        codeKeyMap.put("3209", "盐城市");
        cityKeyMap.put("佳木斯市", "2308");
        codeKeyMap.put("2308", "佳木斯市");
        cityKeyMap.put("七台河市", "2309");
        codeKeyMap.put("2309", "七台河市");
        cityKeyMap.put("牡丹江市", "2310");
        codeKeyMap.put("2310", "牡丹江市");
        cityKeyMap.put("黑河市", "2311");
        codeKeyMap.put("2311", "黑河市");
        cityKeyMap.put("大兴安岭地区", "2327");
        codeKeyMap.put("2327", "大兴安岭地区");
        cityKeyMap.put("南京市", "3201");
        codeKeyMap.put("3201", "南京市");
        cityKeyMap.put("无锡市", "3202");
        codeKeyMap.put("3202", "无锡市");
        cityKeyMap.put("徐州市", "3203");
        codeKeyMap.put("3203", "徐州市");
        cityKeyMap.put("海南藏族自治州", "6325");
        codeKeyMap.put("6325", "海南藏族自治州");
        cityKeyMap.put("果洛藏族自治州", "6326");
        codeKeyMap.put("6326", "果洛藏族自治州");
        cityKeyMap.put("遂宁市", "5109");
        codeKeyMap.put("5109", "遂宁市");
        cityKeyMap.put("柳州市", "4502");
        codeKeyMap.put("4502", "柳州市");
        cityKeyMap.put("桂林市", "4503");
        codeKeyMap.put("4503", "桂林市");
        cityKeyMap.put("北海市", "4505");
        codeKeyMap.put("4505", "北海市");
        cityKeyMap.put("防城港市", "4506");
        codeKeyMap.put("4506", "防城港市");
        cityKeyMap.put("钦州市", "4507");
        codeKeyMap.put("4507", "钦州市");
        cityKeyMap.put("贵港市", "4508");
        codeKeyMap.put("4508", "贵港市");
        cityKeyMap.put("黄冈市", "4211");
        codeKeyMap.put("4211", "黄冈市");
        cityKeyMap.put("喀什地区", "6531");
        codeKeyMap.put("6531", "喀什地区");
        cityKeyMap.put("和田地区", "6532");
        codeKeyMap.put("6532", "和田地区");
        cityKeyMap.put("玉溪市", "5304");
        codeKeyMap.put("5304", "玉溪市");
        cityKeyMap.put("保山市", "5305");
        codeKeyMap.put("5305", "保山市");
        cityKeyMap.put("昭通市", "5306");
        codeKeyMap.put("5306", "昭通市");
        cityKeyMap.put("丽江市", "5307");
        codeKeyMap.put("5307", "丽江市");
        cityKeyMap.put("普洱市", "5308");
        codeKeyMap.put("5308", "普洱市");
        cityKeyMap.put("临沧市", "5309");
        codeKeyMap.put("5309", "临沧市");
        cityKeyMap.put("红河哈尼族彝族自治州", "5325");
        codeKeyMap.put("5325", "红河哈尼族彝族自治州");
        cityKeyMap.put("文山壮族苗族自治州", "5326");
        codeKeyMap.put("5326", "文山壮族苗族自治州");
        cityKeyMap.put("西双版纳傣族自治州", "5328");
        codeKeyMap.put("5328", "西双版纳傣族自治州");
        cityKeyMap.put("大理白族自治州", "5329");
        codeKeyMap.put("5329", "大理白族自治州");
        cityKeyMap.put("内江市", "5110");
        codeKeyMap.put("5110", "内江市");
        cityKeyMap.put("乐山市", "5111");
        codeKeyMap.put("5111", "乐山市");
        cityKeyMap.put("南充市", "5113");
        codeKeyMap.put("5113", "南充市");
        cityKeyMap.put("眉山市", "5114");
        codeKeyMap.put("5114", "眉山市");
        cityKeyMap.put("达州市", "5117");
        codeKeyMap.put("5117", "达州市");
        cityKeyMap.put("雅安市", "5118");
        codeKeyMap.put("5118", "雅安市");
        cityKeyMap.put("资阳市", "5120");
        codeKeyMap.put("5120", "资阳市");
        cityKeyMap.put("汕尾市", "4415");
        codeKeyMap.put("4415", "汕尾市");
        cityKeyMap.put("阳江市", "4417");
        codeKeyMap.put("4417", "阳江市");
        cityKeyMap.put("清远市", "4418");
        codeKeyMap.put("4418", "清远市");
        cityKeyMap.put("东莞市", "4419");
        codeKeyMap.put("4419", "东莞市");
        cityKeyMap.put("中山市", "4420");
        codeKeyMap.put("4420", "中山市");
        cityKeyMap.put("宿迁市", "3213");
        codeKeyMap.put("3213", "宿迁市");
        cityKeyMap.put("怒江傈僳族自治州", "5333");
        codeKeyMap.put("5333", "怒江傈僳族自治州");
        cityKeyMap.put("昌都地区", "5421");
        codeKeyMap.put("5421", "昌都地区");
        cityKeyMap.put("山南地区", "5422");
        codeKeyMap.put("5422", "山南地区");
        cityKeyMap.put("湖北省直辖县级行政区划", "4290");
        codeKeyMap.put("4290", "湖北省直辖县级行政区划");
        cityKeyMap.put("长沙市", "4301");
        codeKeyMap.put("4301", "长沙市");
        cityKeyMap.put("伊犁哈萨克自治州", "6540");
        codeKeyMap.put("6540", "伊犁哈萨克自治州");
        cityKeyMap.put("塔城地区", "6542");
        codeKeyMap.put("6542", "塔城地区");
        cityKeyMap.put("阿勒泰地区", "6543");
        codeKeyMap.put("6543", "阿勒泰地区");
        cityKeyMap.put("自治区直辖县级行政区划", "6590");
        codeKeyMap.put("6590", "自治区直辖县级行政区划");
        cityKeyMap.put("合肥市", "3401");
        codeKeyMap.put("3401", "合肥市");
        cityKeyMap.put("芜湖市", "3402");
        codeKeyMap.put("3402", "芜湖市");
        cityKeyMap.put("蚌埠市", "3403");
        codeKeyMap.put("3403", "蚌埠市");
        cityKeyMap.put("淮南市", "3404");
        codeKeyMap.put("3404", "淮南市");
        cityKeyMap.put("马鞍山市", "3405");
        codeKeyMap.put("3405", "马鞍山市");
        cityKeyMap.put("安庆市", "3408");
        codeKeyMap.put("3408", "安庆市");
        cityKeyMap.put("黄山市", "3410");
        codeKeyMap.put("3410", "黄山市");
        cityKeyMap.put("滁州市", "3411");
        codeKeyMap.put("3411", "滁州市");
        cityKeyMap.put("阜阳市", "3412");
        codeKeyMap.put("3412", "阜阳市");
        cityKeyMap.put("宿州市", "3413");
        codeKeyMap.put("3413", "宿州市");
        cityKeyMap.put("六安市", "3415");
        codeKeyMap.put("3415", "六安市");
        cityKeyMap.put("池州市", "3417");
        codeKeyMap.put("3417", "池州市");
        cityKeyMap.put("伊春市", "2307");
        codeKeyMap.put("2307", "伊春市");
        cityKeyMap.put("廊坊市", "1310");
        codeKeyMap.put("1310", "廊坊市");
        cityKeyMap.put("大同市", "1402");
        codeKeyMap.put("1402", "大同市");
        cityKeyMap.put("长治市", "1404");
        codeKeyMap.put("1404", "长治市");
        cityKeyMap.put("东营市", "3705");
        codeKeyMap.put("3705", "东营市");
        cityKeyMap.put("潍坊市", "3707");
        codeKeyMap.put("3707", "潍坊市");
        cityKeyMap.put("济宁市", "3708");
        codeKeyMap.put("3708", "济宁市");
        cityKeyMap.put("阿拉善盟", "1529");
        codeKeyMap.put("1529", "阿拉善盟");
        cityKeyMap.put("大连市", "2102");
        codeKeyMap.put("2102", "大连市");
        cityKeyMap.put("抚顺市", "2104");
        codeKeyMap.put("2104", "抚顺市");
        cityKeyMap.put("哈尔滨市", "2301");
        codeKeyMap.put("2301", "哈尔滨市");
        cityKeyMap.put("齐齐哈尔市", "2302");
        codeKeyMap.put("2302", "齐齐哈尔市");
        cityKeyMap.put("湖州市", "3305");
        codeKeyMap.put("3305", "湖州市");
        cityKeyMap.put("绍兴市", "3306");
        codeKeyMap.put("3306", "绍兴市");
        cityKeyMap.put("金华市", "3307");
        codeKeyMap.put("3307", "金华市");
        cityKeyMap.put("舟山市", "3309");
        codeKeyMap.put("3309", "舟山市");
        cityKeyMap.put("台州市", "3310");
        codeKeyMap.put("3310", "台州市");
        cityKeyMap.put("武威市", "6206");
        codeKeyMap.put("6206", "武威市");
        cityKeyMap.put("张掖市", "6207");
        codeKeyMap.put("6207", "张掖市");
        cityKeyMap.put("平凉市", "6208");
        codeKeyMap.put("6208", "平凉市");
        cityKeyMap.put("酒泉市", "6209");
        codeKeyMap.put("6209", "酒泉市");
        cityKeyMap.put("定西市", "6211");
        codeKeyMap.put("6211", "定西市");
        cityKeyMap.put("陇南市", "6212");
        codeKeyMap.put("6212", "陇南市");
        cityKeyMap.put("石嘴山市", "6402");
        codeKeyMap.put("6402", "石嘴山市");
        cityKeyMap.put("固原市", "6404");
        codeKeyMap.put("6404", "固原市");
        cityKeyMap.put("中卫市", "6405");
        codeKeyMap.put("6405", "中卫市");
        cityKeyMap.put("乌鲁木齐市", "6501");
        codeKeyMap.put("6501", "乌鲁木齐市");
        cityKeyMap.put("吐鲁番地区", "6521");
        codeKeyMap.put("6521", "吐鲁番地区");
        cityKeyMap.put("昌吉回族自治州", "6523");
        codeKeyMap.put("6523", "昌吉回族自治州");
        cityKeyMap.put("博尔塔拉蒙古自治州", "6527");
        codeKeyMap.put("6527", "博尔塔拉蒙古自治州");
        cityKeyMap.put("安顺市", "5204");
        codeKeyMap.put("5204", "安顺市");
        cityKeyMap.put("毕节市", "5205");
        codeKeyMap.put("5205", "毕节市");
        cityKeyMap.put("铜仁市", "5206");
        codeKeyMap.put("5206", "铜仁市");
        cityKeyMap.put("黔东南苗族侗族自治州", "5226");
        codeKeyMap.put("5226", "黔东南苗族侗族自治州");
        cityKeyMap.put("黔南布依族苗族自治州", "5227");
        codeKeyMap.put("5227", "黔南布依族苗族自治州");
        cityKeyMap.put("昆明市", "5301");
        codeKeyMap.put("5301", "昆明市");
        cityKeyMap.put("曲靖市", "5303");
        codeKeyMap.put("5303", "曲靖市");
        cityKeyMap.put("运城市", "1408");
        codeKeyMap.put("1408", "运城市");
        cityKeyMap.put("忻州市", "1409");
        codeKeyMap.put("1409", "忻州市");
        cityKeyMap.put("临夏回族自治州", "6229");
        codeKeyMap.put("6229", "临夏回族自治州");
        cityKeyMap.put("甘南藏族自治州", "6230");
        codeKeyMap.put("6230", "甘南藏族自治州");
        cityKeyMap.put("海东市", "6302");
        codeKeyMap.put("6302", "海东市");
        cityKeyMap.put("海北藏族自治州", "6322");
        codeKeyMap.put("6322", "海北藏族自治州");
        cityKeyMap.put("黄南藏族自治州", "6323");
        codeKeyMap.put("6323", "黄南藏族自治州");
        cityKeyMap.put("贵阳市", "5201");
        codeKeyMap.put("5201", "贵阳市");
        cityKeyMap.put("六盘水市", "5202");
        codeKeyMap.put("5202", "六盘水市");
        cityKeyMap.put("遵义市", "5203");
        codeKeyMap.put("5203", "遵义市");
        cityKeyMap.put("海西蒙古族藏族自治州", "6328");
        codeKeyMap.put("6328", "海西蒙古族藏族自治州");
        cityKeyMap.put("银川市", "6401");
        codeKeyMap.put("6401", "银川市");
        cityKeyMap.put("本溪市", "2105");
        codeKeyMap.put("2105", "本溪市");
        cityKeyMap.put("锦州市", "2107");
        codeKeyMap.put("2107", "锦州市");
        cityKeyMap.put("营口市", "2108");
        codeKeyMap.put("2108", "营口市");
        cityKeyMap.put("保定市", "1306");
        codeKeyMap.put("1306", "保定市");
        cityKeyMap.put("德阳市", "5106");
        codeKeyMap.put("5106", "德阳市");
        cityKeyMap.put("天津市", "1201");
        codeKeyMap.put("1201", "天津市");
        cityKeyMap.put("沧州市", "1309");
        codeKeyMap.put("1309", "沧州市");
        cityKeyMap.put("鸡西市", "2303");
        codeKeyMap.put("2303", "鸡西市");
        cityKeyMap.put("渭南市", "6105");
        codeKeyMap.put("6105", "渭南市");
        cityKeyMap.put("汉中市", "6107");
        codeKeyMap.put("6107", "汉中市");
        cityKeyMap.put("安康市", "6109");
        codeKeyMap.put("6109", "安康市");
        cityKeyMap.put("商洛市", "6110");
        codeKeyMap.put("6110", "商洛市");
        cityKeyMap.put("嘉峪关市", "6202");
        codeKeyMap.put("6202", "嘉峪关市");
        cityKeyMap.put("金昌市", "6203");
        codeKeyMap.put("6203", "金昌市");
        cityKeyMap.put("白银市", "6204");
        codeKeyMap.put("6204", "白银市");
        cityKeyMap.put("天水市", "6205");
        codeKeyMap.put("6205", "天水市");
        cityKeyMap.put("松原市", "2207");
        codeKeyMap.put("2207", "松原市");
        cityKeyMap.put("白城市", "2208");
        codeKeyMap.put("2208", "白城市");
        cityKeyMap.put("吕梁市", "1411");
        codeKeyMap.put("1411", "吕梁市");
        cityKeyMap.put("包头市", "1502");
        codeKeyMap.put("1502", "包头市");
        cityKeyMap.put("乌海市", "1503");
        codeKeyMap.put("1503", "乌海市");
        cityKeyMap.put("赤峰市", "1504");
        codeKeyMap.put("1504", "赤峰市");
        cityKeyMap.put("鄂尔多斯市", "1506");
        codeKeyMap.put("1506", "鄂尔多斯市");
        cityKeyMap.put("呼伦贝尔市", "1507");
        codeKeyMap.put("1507", "呼伦贝尔市");
        cityKeyMap.put("茂名市", "4409");
        codeKeyMap.put("4409", "茂名市");
        cityKeyMap.put("来宾市", "4513");
        codeKeyMap.put("4513", "来宾市");
        cityKeyMap.put("崇左市", "4514");
        codeKeyMap.put("4514", "崇左市");
        cityKeyMap.put("海口市", "4601");
        codeKeyMap.put("4601", "海口市");
        cityKeyMap.put("泰州市", "3212");
        codeKeyMap.put("3212", "泰州市");
        cityKeyMap.put("拉萨市", "5401");
        codeKeyMap.put("5401", "拉萨市");
        cityKeyMap.put("湘潭市", "4303");
        codeKeyMap.put("4303", "湘潭市");
        cityKeyMap.put("益阳市", "4309");
        codeKeyMap.put("4309", "益阳市");
        cityKeyMap.put("邢台市", "1305");
        codeKeyMap.put("1305", "邢台市");
        cityKeyMap.put("广元市", "5108");
        codeKeyMap.put("5108", "广元市");
        cityKeyMap.put("宜宾市", "5115");
        codeKeyMap.put("5115", "宜宾市");
        cityKeyMap.put("广安市", "5116");
        codeKeyMap.put("5116", "广安市");
        cityKeyMap.put("玉林市", "4509");
        codeKeyMap.put("4509", "玉林市");
        cityKeyMap.put("贺州市", "4511");
        codeKeyMap.put("4511", "贺州市");
        cityKeyMap.put("河池市", "4512");
        codeKeyMap.put("4512", "河池市");
        cityKeyMap.put("阿克苏地区", "6529");
        codeKeyMap.put("6529", "阿克苏地区");
        cityKeyMap.put("克孜勒苏柯尔克孜自治州", "6530");
        codeKeyMap.put("6530", "克孜勒苏柯尔克孜自治州");
        cityKeyMap.put("朝阳市", "2113");
        codeKeyMap.put("2113", "朝阳市");
        cityKeyMap.put("淄博市", "3703");
        codeKeyMap.put("3703", "淄博市");
        cityKeyMap.put("枣庄市", "3704");
        codeKeyMap.put("3704", "枣庄市");
        cityKeyMap.put("南通市", "3206");
        codeKeyMap.put("3206", "南通市");
        cityKeyMap.put("连云港市", "3207");
        codeKeyMap.put("3207", "连云港市");
        cityKeyMap.put("淮安市", "3208");
        codeKeyMap.put("3208", "淮安市");
        cityKeyMap.put("驻马店市", "4117");
        codeKeyMap.put("4117", "驻马店市");
        cityKeyMap.put("襄阳市", "4206");
        codeKeyMap.put("4206", "襄阳市");
        cityKeyMap.put("荆门市", "4208");
        codeKeyMap.put("4208", "荆门市");
        cityKeyMap.put("沈阳市", "2101");
        codeKeyMap.put("2101", "沈阳市");
        cityKeyMap.put("双鸭山市", "2305");
        codeKeyMap.put("2305", "双鸭山市");
        cityKeyMap.put("扬州市", "3210");
        codeKeyMap.put("3210", "扬州市");
        cityKeyMap.put("镇江市", "3211");
        codeKeyMap.put("3211", "镇江市");
        cityKeyMap.put("上海市", "3101");
        codeKeyMap.put("3101", "上海市");
        cityKeyMap.put("佛山市", "4406");
        codeKeyMap.put("4406", "佛山市");
        cityKeyMap.put("百色市", "4510");
        codeKeyMap.put("4510", "百色市");
        cityKeyMap.put("洛阳市", "4103");
        codeKeyMap.put("4103", "洛阳市");
        cityKeyMap.put("平顶山市", "4104");
        codeKeyMap.put("4104", "平顶山市");
        cityKeyMap.put("周口市", "4116");
        codeKeyMap.put("4116", "周口市");
        cityKeyMap.put("朔州市", "1406");
        codeKeyMap.put("1406", "朔州市");
        cityKeyMap.put("晋中市", "1407");
        codeKeyMap.put("1407", "晋中市");
        cityKeyMap.put("临汾市", "1410");
        codeKeyMap.put("1410", "临汾市");
        cityKeyMap.put("辽源市", "2204");
        codeKeyMap.put("2204", "辽源市");
        cityKeyMap.put("十堰市", "4203");
        codeKeyMap.put("4203", "十堰市");
        cityKeyMap.put("常州市", "3204");
        codeKeyMap.put("3204", "常州市");
        cityKeyMap.put("凉山彝族自治州", "5134");
        codeKeyMap.put("5134", "凉山彝族自治州");
        cityKeyMap.put("鞍山市", "2103");
        codeKeyMap.put("2103", "鞍山市");
        cityKeyMap.put("菏泽市", "3717");
        codeKeyMap.put("3717", "菏泽市");
        cityKeyMap.put("鹤壁市", "4106");
        codeKeyMap.put("4106", "鹤壁市");
        cityKeyMap.put("焦作市", "4108");
        codeKeyMap.put("4108", "焦作市");
        cityKeyMap.put("丹东市", "2106");
        codeKeyMap.put("2106", "丹东市");
        cityKeyMap.put("黔西南布依族苗族自治州", "5223");
        codeKeyMap.put("5223", "黔西南布依族苗族自治州");
        cityKeyMap.put("淮北市", "3406");
        codeKeyMap.put("3406", "淮北市");
        cityKeyMap.put("永州市", "4311");
        codeKeyMap.put("4311", "永州市");
        cityKeyMap.put("西安市", "6101");
        codeKeyMap.put("6101", "西安市");
        cityKeyMap.put("三亚市", "4602");
        codeKeyMap.put("4602", "三亚市");
        cityKeyMap.put("海南省直辖县级行政区划", "4690");
        codeKeyMap.put("4690", "海南省直辖县级行政区划");
        cityKeyMap.put("自贡市", "5103");
        codeKeyMap.put("5103", "自贡市");
        cityKeyMap.put("泰安市", "3709");
        codeKeyMap.put("3709", "泰安市");
        cityKeyMap.put("唐山市", "1302");
        codeKeyMap.put("1302", "唐山市");
        cityKeyMap.put("秦皇岛市", "1303");
        codeKeyMap.put("1303", "秦皇岛市");
        cityKeyMap.put("怀化市", "4312");
        codeKeyMap.put("4312", "怀化市");
        cityKeyMap.put("娄底市", "4313");
        codeKeyMap.put("4313", "娄底市");
        cityKeyMap.put("延边朝鲜族自治州", "2224");
        codeKeyMap.put("2224", "延边朝鲜族自治州");
        cityKeyMap.put("宁波市", "3302");
        codeKeyMap.put("3302", "宁波市");
        cityKeyMap.put("温州市", "3303");
        codeKeyMap.put("3303", "温州市");
        cityKeyMap.put("邯郸市", "1304");
        codeKeyMap.put("1304", "邯郸市");
        cityKeyMap.put("莱芜市", "3712");
        codeKeyMap.put("3712", "莱芜市");
        cityKeyMap.put("莆田市", "3503");
        codeKeyMap.put("3503", "莆田市");
        cityKeyMap.put("衢州市", "3308");
        codeKeyMap.put("3308", "衢州市");
        cityKeyMap.put("丽水市", "3311");
        codeKeyMap.put("3311", "丽水市");
        cityKeyMap.put("庆阳市", "6210");
        codeKeyMap.put("6210", "庆阳市");
        cityKeyMap.put("西宁市", "6301");
        codeKeyMap.put("6301", "西宁市");
        cityKeyMap.put("楚雄彝族自治州", "5323");
        codeKeyMap.put("5323", "楚雄彝族自治州");
        cityKeyMap.put("衡水市", "1311");
        codeKeyMap.put("1311", "衡水市");
        cityKeyMap.put("咸阳市", "6104");
        codeKeyMap.put("6104", "咸阳市");
        cityKeyMap.put("阳泉市", "1403");
        codeKeyMap.put("1403", "阳泉市");
        cityKeyMap.put("烟台市", "3706");
        codeKeyMap.put("3706", "烟台市");
        cityKeyMap.put("吴忠市", "6403");
        codeKeyMap.put("6403", "吴忠市");
        cityKeyMap.put("克拉玛依市", "6502");
        codeKeyMap.put("6502", "克拉玛依市");
        cityKeyMap.put("哈密地区", "6522");
        codeKeyMap.put("6522", "哈密地区");
        cityKeyMap.put("巴音郭楞蒙古自治州", "6528");
        codeKeyMap.put("6528", "巴音郭楞蒙古自治州");
        cityKeyMap.put("那曲地区", "5424");
        codeKeyMap.put("5424", "那曲地区");
        cityKeyMap.put("甘孜藏族自治州", "5133");
        codeKeyMap.put("5133", "甘孜藏族自治州");
        cityKeyMap.put("惠州市", "4413");
        codeKeyMap.put("4413", "惠州市");
        cityKeyMap.put("河源市", "4416");
        codeKeyMap.put("4416", "河源市");
        cityKeyMap.put("榆林市", "6108");
        codeKeyMap.put("6108", "榆林市");
        cityKeyMap.put("兰州市", "6201");
        codeKeyMap.put("6201", "兰州市");
        cityKeyMap.put("赣州市", "3607");
        codeKeyMap.put("3607", "赣州市");
        cityKeyMap.put("济南市", "3701");
        codeKeyMap.put("3701", "济南市");
        cityKeyMap.put("德宏傣族景颇族自治州", "5331");
        codeKeyMap.put("5331", "德宏傣族景颇族自治州");
        cityKeyMap.put("濮阳市", "4109");
        codeKeyMap.put("4109", "濮阳市");
        cityKeyMap.put("许昌市", "4110");
        codeKeyMap.put("4110", "许昌市");
        cityKeyMap.put("三门峡市", "4112");
        codeKeyMap.put("4112", "三门峡市");
        cityKeyMap.put("白山市", "2206");
        codeKeyMap.put("2206", "白山市");
        cityKeyMap.put("漳州市", "3506");
        codeKeyMap.put("3506", "漳州市");
        cityKeyMap.put("龙岩市", "3508");
        codeKeyMap.put("3508", "龙岩市");
        cityKeyMap.put("韶关市", "4402");
        codeKeyMap.put("4402", "韶关市");
        cityKeyMap.put("宜春市", "3609");
        codeKeyMap.put("3609", "宜春市");
        cityKeyMap.put("宝鸡市", "6103");
        codeKeyMap.put("6103", "宝鸡市");
        cityKeyMap.put("呼和浩特市", "1501");
        codeKeyMap.put("1501", "呼和浩特市");
        cityKeyMap.put("潮州市", "4451");
        codeKeyMap.put("4451", "潮州市");
        cityKeyMap.put("揭阳市", "4452");
        codeKeyMap.put("4452", "揭阳市");
        cityKeyMap.put("云浮市", "4453");
        codeKeyMap.put("4453", "云浮市");
        cityKeyMap.put("南宁市", "4501");
        codeKeyMap.put("4501", "南宁市");
        cityKeyMap.put("梧州市", "4504");
        codeKeyMap.put("4504", "梧州市");
        cityKeyMap.put("铜陵市", "3407");
        codeKeyMap.put("3407", "铜陵市");
        cityKeyMap.put("迪庆藏族自治州", "5334");
        codeKeyMap.put("5334", "迪庆藏族自治州");
        cityKeyMap.put("日喀则市", "5402");
        codeKeyMap.put("5402", "日喀则市");
        cityKeyMap.put("巴中市", "5119");
        codeKeyMap.put("5119", "巴中市");
        cityKeyMap.put("阿坝藏族羌族自治州", "5132");
        codeKeyMap.put("5132", "阿坝藏族羌族自治州");
        cityKeyMap.put("肇庆市", "4412");
        codeKeyMap.put("4412", "肇庆市");
        cityKeyMap.put("梅州市", "4414");
        codeKeyMap.put("4414", "梅州市");
        cityKeyMap.put("荆州市", "4210");
        codeKeyMap.put("4210", "荆州市");
        cityKeyMap.put("通辽市", "1505");
        codeKeyMap.put("1505", "通辽市");
        cityKeyMap.put("泸州市", "5105");
        codeKeyMap.put("5105", "泸州市");
        cityKeyMap.put("绵阳市", "5107");
        codeKeyMap.put("5107", "绵阳市");
        cityKeyMap.put("乌兰察布市", "1509");
        codeKeyMap.put("1509", "乌兰察布市");
        cityKeyMap.put("兴安盟", "1522");
        codeKeyMap.put("1522", "兴安盟");
        cityKeyMap.put("北京市", "1101");
        codeKeyMap.put("1101", "北京市");
        cityKeyMap.put("亳州市", "3416");
        codeKeyMap.put("3416", "亳州市");
        cityKeyMap.put("大庆市", "2306");
        codeKeyMap.put("2306", "大庆市");
        cityKeyMap.put("绥化市", "2312");
        codeKeyMap.put("2312", "绥化市");
        cityKeyMap.put("嘉兴市", "3304");
        codeKeyMap.put("3304", "嘉兴市");
        cityKeyMap.put("安阳市", "4105");
        codeKeyMap.put("4105", "安阳市");
        cityKeyMap.put("黄石市", "4202");
        codeKeyMap.put("4202", "黄石市");
        cityKeyMap.put("江门市", "4407");
        codeKeyMap.put("4407", "江门市");
        cityKeyMap.put("咸宁市", "4212");
        codeKeyMap.put("4212", "咸宁市");
        cityKeyMap.put("德州市", "3714");
        codeKeyMap.put("3714", "德州市");
        cityKeyMap.put("株洲市", "4302");
        codeKeyMap.put("4302", "株洲市");
        cityKeyMap.put("玉树藏族自治州", "6327");
        codeKeyMap.put("6327", "玉树藏族自治州");
        cityKeyMap.put("青岛市", "3702");
        codeKeyMap.put("3702", "青岛市");
        cityKeyMap.put("太原市", "1401");
        codeKeyMap.put("1401", "太原市");
        cityKeyMap.put("延安市", "6106");
        codeKeyMap.put("6106", "延安市");
        cityKeyMap.put("阿里地区", "5425");
        codeKeyMap.put("5425", "阿里地区");
        cityKeyMap.put("林芝地区", "5426");
        codeKeyMap.put("5426", "林芝地区");
        cityKeyMap.put("铜川市", "6102");
        codeKeyMap.put("6102", "铜川市");
        cityKeyMap.put("晋城市", "1405");
        codeKeyMap.put("1405", "晋城市");
        cityKeyMap.put("巴彦淖尔市", "1508");
        codeKeyMap.put("1508", "巴彦淖尔市");
        cityKeyMap.put("石家庄市", "1301");
        codeKeyMap.put("1301", "石家庄市");
        cityKeyMap.put("宣城市", "3418");
        codeKeyMap.put("3418", "宣城市");
    }
*/
}

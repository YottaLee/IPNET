package com.ipnet.datav.area;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzb
 * @date 2018/11/1 0:10
 */
public class AreaTranslator {

    public Area translateIntoArea(String areaLine){
        String[] attrArray = areaLine.split("\t");
//        for (String s : attrArray) {
//            System.out.println(s);
//        }
        Area area = new Area();
        area.setArea_id(attrArray[0]);
        area.setName(attrArray[1]);
        area.setLng(Double.valueOf(attrArray[2]));
        area.setLat(Double.valueOf(attrArray[3]));
        area.setValue1(Double.valueOf(attrArray[4]));
        area.setValue2(Double.valueOf(attrArray[5]));
        area.setInfo(attrArray[6]);
        return area;
    }

    public List<Area> initArea(String filePath) throws IOException {
        List<Area> areaList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
        String line = null;
        while ((line = reader.readLine())!=null){
            areaList.add(this.translateIntoArea(line));
        }
        return areaList;
    }

}

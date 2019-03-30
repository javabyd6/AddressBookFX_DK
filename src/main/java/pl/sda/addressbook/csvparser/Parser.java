package pl.sda.addressbook.csvparser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Parser {

    private Path file = Paths.get("file.csv");



    public List<RealEstate> readFile() throws IOException {

        byte[] data = Files.readAllBytes(file);
        String convertData = new String(data);
      //  System.out.println(convertData.length());

        String[] dataArray = convertData.split("\\r");
      //  System.out.println(dataArray.length);



        RealEstate[] realEstates= new RealEstate[dataArray.length];
        for (int i = 1; i <dataArray.length ; i++) {
           String[] tab = dataArray[i].split(",");

            realEstates[i-1]= new RealEstate(tab[0],tab[1],Integer.parseInt(tab[2]),
                    tab[3],Integer.parseInt(tab[4]),Integer.parseInt(tab[5]),
                    Integer.parseInt(tab[6]),tab[7],tab[8],Integer.parseInt(tab[9]),
                    Double.parseDouble(tab[10]),Double.parseDouble(tab[11]));
           // System.out.println(realEstates[i]);
        }

        List<RealEstate> estateList = Arrays.asList(realEstates);
         estateList=estateList.subList(0,estateList.size()-1);
        return estateList;
    }

    public void groupByCity1(List<RealEstate> realEstates) throws IOException {
        Map<String,List<RealEstate>> map= new HashMap<>();
        List<RealEstate> realEstateList1 = new ArrayList<>();

        for (RealEstate estate : realEstateList1) {
            String key= estate.getCity();
            if (map.containsKey(key)){
                List<RealEstate> realEstateList= map.get(key);
                realEstateList.add(estate);
                map.replace(key,realEstateList);
            }
            else {
                List<RealEstate> realEstateList= new ArrayList<>();
                realEstateList.add(estate);
                map.put(key,realEstateList);
            }
        }


        System.out.println(map.size());


    }

    public void groupByCity(List<RealEstate> realEstates) throws IOException {


        Map<String, List<RealEstate>> map = new HashMap<>();

      //  List<RealEstate> realEstateList = new ArrayList<>();

        for (RealEstate realEstate : realEstates) {

            if (map.containsKey(realEstate.getCity())) {
                List<RealEstate> list = map.get(realEstate.getCity());
                list.add(realEstate);
                map.put(realEstate.getCity(), list);
            }

            else {
                List<RealEstate> list = new ArrayList<>();
                list.add(realEstate);
                map.put(realEstate.getCity(), list);
            }
        }
        System.out.println(map.size());
    }

}

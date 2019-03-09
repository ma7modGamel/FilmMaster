package com.mgh.filmmaster.Utils;

import com.mgh.filmmaster.Models.moviesModel;
import java.util.ArrayList;

public class Pagination {
    private final  int itemPerPAge;
    private final ArrayList<moviesModel> moviesModelArrayList;
    int totalItem;
    int lastPageItem,lastPage;


    public Pagination(int itemPerPAge, ArrayList<moviesModel> moviesModelArrayList) {
        this.itemPerPAge = itemPerPAge;
        this.moviesModelArrayList = moviesModelArrayList;
        totalItem=moviesModelArrayList.size();  //20
        lastPage=totalItem/itemPerPAge;         //1
        lastPageItem=totalItem%itemPerPAge;     //0
    }

    public ArrayList<moviesModel> generateDataPage(int currentPage ){//1
        ArrayList<moviesModel>  newPageData=new ArrayList<>();
        if(currentPage==lastPage){
            for (int i = 0; i <itemPerPAge ; i++) {
                newPageData.add(moviesModelArrayList.get(i));
            }
        }
        return newPageData;
    }

    public int getLastPage() {
        return lastPage;
    }
}

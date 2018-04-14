package com.surbhi.webProject1.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Pager {
	public Map<String, Object> pager(int currentPage,int totalPage,int limit){

		Map<String, Object> hmap = new HashMap<String, Object>();
		if(currentPage>2){					
			int prevPage = currentPage-1;
			hmap.put("prevPage", prevPage);
		}
		else
			hmap.put("prev",true);

		if(currentPage<Math.ceil(totalPage)){
			int nextPage = currentPage+1;
			hmap.put("nextPage", nextPage);
		}
		else
			hmap.put("next",true);

		hmap.put("limit",limit);

		List<Integer> pager = new ArrayList<Integer>();
		if(totalPage<=10){
			for(int link=1;link<=totalPage;link++){
				pager.add(link);
			System.out.println(link);
			}
		}
		else{
			if(currentPage<=10)
				for(int link = 1;link<10;link++)
					pager.add(link);
			for(int link=currentPage-4;link<=currentPage+5;link++){
				pager.add(link);
			System.out.println(link);
		}
		}
		System.out.println("pager is "+pager);
		System.out.println(pager.get(0));
		hmap.put("pager", pager);
		return hmap;

	}


}

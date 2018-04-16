package com.surbhi.webProject1.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Pager {
	public Map<String, Object> pager(int currentPage,int totalPage,int recordLimit,int pagerSize){
		
		Map<String, Object> hmap = new HashMap<String, Object>();
		if(currentPage>1){					
			int prevPage = currentPage-1;
			hmap.put("prevPage", prevPage);
			System.out.println("Current page greater than 1...");
		}
		else{
			hmap.put("prev",true);
			System.out.println("Current page equal to 1...");
		}

		if(currentPage<(totalPage)){
			System.out.println("Current page less than total ...");

			int nextPage = currentPage+1;
			hmap.put("nextPage", nextPage);
		}
		else{
			hmap.put("next",true);
			System.out.println("Current page equal to total ...");
		}

		hmap.put("limit",recordLimit);

		List<Integer> pager = new ArrayList<Integer>();
		if(totalPage<=pagerSize){
			for(int link=1;link<=totalPage;link++){
				pager.add(link);
				System.out.println(link);
			}
		}
		else{
			if(currentPage<=pagerSize){
				for(int link = 1;link<=pagerSize;link++)
					pager.add(link);
			}
			else if(currentPage>pagerSize && totalPage>=currentPage+(pagerSize/2)){
				if(pagerSize%2!=0){
					for(int link=currentPage-(pagerSize/2);link<=currentPage+(pagerSize/2);link++){
						pager.add(link);
						System.out.println(link);
					}
				}
				else{
					for(int link=currentPage-(pagerSize/2)-1;link<=currentPage+(pagerSize/2);link++){
						pager.add(link);
						System.out.println(link);
					}

				}
			}
			else{
				for(int link=totalPage-pagerSize+1;link<=totalPage;link++){
					pager.add(link);
					System.out.println("Here");
				}
			}
		}

			System.out.println("pager is "+pager);
			System.out.println(pager.get(0));
			hmap.put("pager", pager);
			return hmap;



	}
}
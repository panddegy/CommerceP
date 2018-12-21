package com.biz.commerce.exec;

import com.biz.commerce.service.CommService;

public class CommExec01 {

	public static void main(String[] args) {
		
		String strIOFile="src/com/biz/commerce/매입매출데이터.txt";
		String strDataFile="src/com/biz/commerce/상품정보.txt";
		String strIODataFile="src/com/biz/commerce/매입매출정보.txt";
		
		String[] files= {strIOFile,strDataFile,strIODataFile};
		
		CommService cs=new CommService(files);
		
		cs.readIOFile();
		cs.readDataFile();
		cs.matchList();
		cs.writeList();
	}
}

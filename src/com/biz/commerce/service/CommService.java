package com.biz.commerce.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.biz.commerce.vo.CommVO;

public class CommService {

	List<CommVO> ioList;
	List<CommVO> dataList;
	String[] files;
	
	public CommService(String[] files) {
		
		ioList=new ArrayList<>();
		dataList=new ArrayList<>();
		this.files=files;
		
	}
	
	public void writeList() {
		
		PrintWriter pw;
		
		try {
			pw=new PrintWriter(files[2]);
			for(CommVO vo:ioList) {
				if(vo.getStrIO().equals("매입")) {
					pw.println(vo.getStrDate()+":"+vo.getStrIO()+":"+vo.getStrPCode()+":"+vo.getStrName()+":"+
							vo.getIntIPrice()+":"+vo.getIntQuan()+":"+vo.getIntIPrice()*vo.getIntQuan()+":0");	
				}
				if(vo.getStrIO().equals("매출")) {
					pw.println(vo.getStrDate()+":"+vo.getStrIO()+":"+vo.getStrPCode()+":"+vo.getStrName()+":"+
							vo.getIntOPrice()+":"+vo.getIntQuan()+":0:"+vo.getIntOPrice()*vo.getIntQuan());	
				}
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void matchList() {
		
		for(CommVO ivo:ioList) {
			for(CommVO dvo:dataList) {
				if(ivo.getStrPCode().equals(dvo.getStrPCode())) {
					ivo.setStrName(dvo.getStrName());
					ivo.setIntIPrice(dvo.getIntIPrice());
					ivo.setIntOPrice(dvo.getIntOPrice());
					ivo.setStrTex(dvo.getStrTex());
					ivo.setStrPum(dvo.getStrPum());
					ivo.setStrDept(dvo.getStrDept());
				}
			}
		}
	}
	
	public void readDataFile() {
		
		FileReader fr;
		BufferedReader buffer;
		
		try {
			fr=new FileReader(files[1]);
			buffer=new BufferedReader(fr);
			while(true) {
				String reader=buffer.readLine();
				if(reader==null) break;
				String[] strIOs=reader.split(":");
				CommVO vo=new CommVO();
				vo.setStrPCode(strIOs[0]);
				vo.setStrName(strIOs[1]);
				vo.setStrTex(strIOs[2]);
				int intIPrice=Integer.valueOf(strIOs[3]);
				int intOPrice=Integer.valueOf(strIOs[4]);
				vo.setIntIPrice(intIPrice);
				vo.setIntOPrice(intOPrice);
				vo.setStrPum(strIOs[5]);
				vo.setStrDept(strIOs[6]);
				dataList.add(vo);
			}
			buffer.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readIOFile() {
		
		FileReader fr;
		BufferedReader buffer;
		
		try {
			fr=new FileReader(files[0]);
			buffer=new BufferedReader(fr);
			while(true) {
				String reader=buffer.readLine();
				if(reader==null) break;
				String[] strIOs=reader.split(":");
				CommVO vo=new CommVO();
				vo.setStrDate(strIOs[0]);
				vo.setStrPCode(strIOs[1]);
				int intIO=Integer.valueOf(strIOs[2]);
				if(intIO==1) vo.setStrIO("매입");
				if(intIO==2) vo.setStrIO("매출");
				int intPrice=Integer.valueOf(strIOs[3]);
				vo.setIntIPrice(intPrice);
				int intQuan=Integer.valueOf(strIOs[4]);
				vo.setIntQuan(intQuan);
				ioList.add(vo);
			}
			buffer.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

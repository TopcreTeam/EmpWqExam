package com.emp.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.server.service.EmpService;

@CrossOrigin(origins = "*", maxAge= 3600)
@RestController
@RequestMapping("/api")
public class EmpRestController {
	public static final Logger logger = LoggerFactory.getLogger(EmpRestController.class);
	
	@Autowired
	EmpService empService;
	
	//모든 사원
	//Get 매핑으로 할경우 웹스퀘어 에서 못받음
	@PostMapping("/emp.post")
	public Map getAllEmp() {
		Map resObj = new HashMap<>();
		resObj.put("emp_userInfo", empService.getAllEmps());
		resObj.put("msg", "조회가 완료되었습니다.");
		return resObj;
	}
	
	@PostMapping(value = "/emp/saveAndSelectMember.do")
	public Map saveAndSelectMember(@RequestBody Map param) throws Exception {
		Map resObj = new HashMap();
		List modList = null;
		Map saveResult = null;
		int modListLen;
		Map modParam = new HashMap<String, List>();
		List insert = new ArrayList<Map>();
		List update = new ArrayList<Map>();
		List delete = new ArrayList<Map>();
		Map rowData;
		String rowStatus;

		try {
			modList = (List) param.get("emp_userInfo");
			modListLen = modList.size();

			for (int i = 0; i < modListLen; i++) {
				rowData = (Map) modList.get(i);
				rowStatus = (String) rowData.get("rowStatus");

				if (rowStatus.equals("C")) {
					insert.add(rowData);
				} else if (rowStatus.equals("U")) {
					update.add(rowData);
				} else if (rowStatus.equals("D") || rowStatus.equals("E")) {
					delete.add(rowData);
				}
			}
			modParam.put("insert", insert);
			modParam.put("update", update);
			modParam.put("delete", delete);

			saveResult = empService.saveSpEmp(modParam);

			try {
				resObj.put("emp_userInfo", empService.getAllEmps());
				resObj.put("msg", "조회가 완료되었습니다.");
			} catch (Exception ex) {
				throw new RuntimeException("저장은 완료되었으나 조회도중 오류가 발생하였습니다. 다시 조회 해주시기 바랍니다.");
			}
			resObj.put("rsObj", saveResult);
			resObj.put("msg", "저장이 완료되었습니다.");
			resObj.put("msgCode", "S");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resObj;
	}
	

	
	
}

package com.emp.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.server.mapper.EmpMapper;
import com.emp.server.model.Emp;

@Service
public class EmpServiceImpl implements EmpService{

	
	@Autowired
	EmpMapper empMapper;


	@Override
	public int createEmp(Emp emp) {
		return empMapper.insert(emp);
	}

	@Override
	public int updateEmp(Emp emp) {
		return empMapper.update(emp);
	}

	@Override
	public int deleteEmpByEmpno(int empno) {
		return empMapper.deleteByEmpNo(empno);
	}

	@Override
	public List<Emp> getAllEmps() {
		return empMapper.findAllEmps();
	}

	@Override
	public Map saveSpEmp(Map param) throws Exception {
		Map resMap = new HashMap();

		List arr = null;

		int arrLen = 0;

		int rsNum = 0;

		if (((List) param.get("insert")).size() > 0) {

			resMap.put("I", empMapper.insertEmpWqBatch(param));

		}

		if (((List) param.get("delete")).size() > 0) {

			resMap.put("D", empMapper.deleteEmpWqBatch(param));

		}
		arr = (List) param.get("update");

		arrLen = arr.size();

		if (arrLen > 0) {

			for (int i = 0; i < arrLen; i++) {

				rsNum += empMapper.updateEmpWq((Map) arr.get(i));

			}

			resMap.put("U", rsNum);

		}

		return resMap;
	}


}

package com.emp.server.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.emp.server.model.Emp;


@Mapper
public interface EmpMapper{
	public int insert(Emp emp);
	public int update(Emp emp);
	public int deleteByEmpNo(int empno);
	
	@Select("SELECT * FROM employee")
	public List<Emp> findAllEmps();
	
	public Map selectByEmp(Emp emp);

	public int increment(int empno);
	
	public int insertEmpWq(Map param);
	public int insertEmpWqBatch(Map param);
	public int updateEmpWq(Map param);
	public int updateEmpWqBatch(Map param);
	public int deleteEmpWq(Map param);
	public int deleteEmpWqBatch(Map param);
}

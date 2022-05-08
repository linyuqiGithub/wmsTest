package com._520it.wms.mapper;

import com._520it.wms.domain.Employee;
import com._520it.wms.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
  /**
   * 新建员工
   * @param emp
   */
  void save(Employee emp);

  /**
   * 根据员工id更新员工
   * @param emp
   */
  void update(Employee emp);

  /**
   * 根据员工id删除员工
   * @param id
   */
  void delete(Long id);

  /**
   * 根据id获取员工对象
   * @param id
   * @return
   */
  Employee get(Long id);

  /**
   * 获取所有的员工
   * @return
   */
  List<Employee> list();

  /**
   * 根据查询条件返回总数
   * @param qo
   * @return
   */
  Long queryByConditionCount(QueryObject qo);

  /**
   * 根据查询条件返回员工结果集
   * @param qo
   * @return
   */
  List<Employee> queryByCondition(QueryObject qo);

  /**
   * 处理员工和角色的中间表
   * @param empId
   * @param roleId
   */
  void inserRelation(@Param("empId")Long empId,@Param("roleId")Long roleId);

  /**
   * 删除中间表关系
   * @param empId
   */
  void deleteRelation(Long empId);

  /**
   * 根据用户名和密码登陆
   * @param username
   * @param password
   * @return
   */
  Employee login(@Param("username")String username,@Param("password")String password);

  /**
   * 根据id集合实现批量删除员工
   * @param list
   */
  void batchDelete(List<Long> list);
}

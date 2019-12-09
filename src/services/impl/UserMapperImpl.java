package services.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.UserMapper;
import pojo.Users;
import services.UserService;
@Service("stu")
public class UserMapperImpl implements UserService{
@Resource(name="uMapper")
UserMapper um;
	@Override
	public List<Users> getAll() {
		// TODO Auto-generated method stub
		return  um.getAll();
	}
	@Override
	public int shan(String id) {
		// TODO Auto-generated method stub
		return um.shan(id);
	}
	@Override
	public int add(Users u) {
		// TODO Auto-generated method stub
		return um.add(u);
	}
	@Override
	public List<Users> sou(String name) {
		// TODO Auto-generated method stub
		return um.sou(name);
	}
	@Override
	public List<Users> cha(String id) {
		// TODO Auto-generated method stub
		return um.cha(id);
	}
	@Override
	public int upUser(Users u) {
		// TODO Auto-generated method stub
		return um.upUser(u);
	}


}

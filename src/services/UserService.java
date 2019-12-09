package services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import pojo.Users;
@Repository("uMapper")
public interface UserService {
	public List<Users> getAll();
	public int shan(String id);
	public int add(Users u);
	public List<Users> sou(String name);
	public List<Users> cha(String id);
	public int upUser(Users u);
}

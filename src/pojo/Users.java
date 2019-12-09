package pojo;

public class Users {
private String id,name,sex;

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getSex() {
	return sex;
}

public void setSex(String sex) {
	this.sex = sex;
}

@Override
public String toString() {
	return "Users [id=" + id + ", name=" + name + ", sex=" + sex + "]";
}



}

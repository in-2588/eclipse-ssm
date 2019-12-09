package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import pojo.Users;
import services.UserService;

@Controller
@RequestMapping("/")
public class Home {
	@Resource(name = "stu")
	UserService stu;

	// 利用实体类来获取
	@RequestMapping(value = "/test")
	public String test(Model mo, Users user, String add, String cha) {
		System.out.println("进入...");
		System.out.println("add" + add);
		System.out.println("cha" + cha);
		List<Users> list = stu.getAll();
		mo.addAttribute("list", list);
		mo.addAttribute("add", add);
		mo.addAttribute("cha", cha);
		return "/NewFile.jsp";
	}
//搜索
	@RequestMapping(value = "/sou")
	@ResponseBody
	public List<Users> sou(String name, Map<Object, Object> map,OutputStream os) {
		System.out.println("搜索进入...");		
	    List<Users> list =stu.sou(name);
		System.out.println(list.toString());
		return list;
		

	}
//删除
@RequestMapping(value = "/shan")
@ResponseBody
 boolean cha(Model mo, String id) {
		boolean str=false;
		System.out.println(id);
		System.out.println("进入删除...");
		int i = stu.shan(id);
		if(i>0) {
			str=true;
		}else {
			str=false;
		}
		return str; // 重新查，将参数传到前台
	}
//添加
	@RequestMapping(value = "/add")
	@ResponseBody
	 int add(Users s) {
		int str;
	   System.out.println("添加");
	   System.out.println(s.toString());
		int i=stu.add(s);
		if(i>0) {
			str=2;
		}else {
			str=1;
		}
		System.out.println("添加后结果"+str);
		return str;// 重新查，将参数传到前台

	}
	//查询,根据id查所有
		@RequestMapping(value = "/cha")
		 public String chaall(Model mo,String id) {
		   System.out.println("查询");
		   List<Users> list=stu.cha(id);
		  System.out.println(list.size());
		 mo.addAttribute("list", list);
		 return "/xiu.jsp";

		}
		//修改
		@RequestMapping(value = "/xiu")		
		@ResponseBody
		 int xiu(Users s) {
			int str;
		   System.out.println("xiu");
		   System.out.println(s.toString());
			int i=stu.upUser(s);
			if(i>0) {
				str=2;
			}else {
				str=1;
			}
			System.out.println("修改后结果"+str);
			return str;// 重新查，将参数传到前台

		}
		//导出表格
		@RequestMapping(value="/biao", method = RequestMethod.GET)
		@ResponseBody
		public void biao(HttpServletResponse response) throws IOException {
			System.out.println("导出表格");
			//1.获取数据
			List<Users> list=stu.getAll();
			System.out.println(list);
			//2.创建excel表格
			HSSFWorkbook wb=new HSSFWorkbook();
			//3.创建sheet页
			HSSFSheet sheet=wb.createSheet("员工信息表");
			//4.创建标题行
			HSSFRow titleRow=sheet.createRow(0);
			titleRow.createCell(0).setCellValue("学号");
			titleRow.createCell(1).setCellValue("姓名");
			titleRow.createCell(2).setCellValue("性别");
			//5.编列将数据放到Excel列中
			for(Users user:list) {
				HSSFRow dataRow =sheet.createRow(sheet.getLastRowNum()+1);
				dataRow.createCell(0).setCellValue(user.getId());
				dataRow.createCell(1).setCellValue(user.getName());
				dataRow.createCell(2).setCellValue(user.getSex());
			}
			//6.设置下载时客户端Excal的名称
			response.setContentType("application/octet-stream;charset=utf-8");
		     response.setHeader("Content-Disposition", "attachment;filename="
                     + new String("员工名单".getBytes(),"iso-8859-1") + ".xls");
		     OutputStream out=response.getOutputStream();
			 wb.write(out);
			 out.flush();
			 out.close();
			
			
		}
		
}

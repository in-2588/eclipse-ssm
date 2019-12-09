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

	// ����ʵ��������ȡ
	@RequestMapping(value = "/test")
	public String test(Model mo, Users user, String add, String cha) {
		System.out.println("����...");
		System.out.println("add" + add);
		System.out.println("cha" + cha);
		List<Users> list = stu.getAll();
		mo.addAttribute("list", list);
		mo.addAttribute("add", add);
		mo.addAttribute("cha", cha);
		return "/NewFile.jsp";
	}
//����
	@RequestMapping(value = "/sou")
	@ResponseBody
	public List<Users> sou(String name, Map<Object, Object> map,OutputStream os) {
		System.out.println("��������...");		
	    List<Users> list =stu.sou(name);
		System.out.println(list.toString());
		return list;
		

	}
//ɾ��
@RequestMapping(value = "/shan")
@ResponseBody
 boolean cha(Model mo, String id) {
		boolean str=false;
		System.out.println(id);
		System.out.println("����ɾ��...");
		int i = stu.shan(id);
		if(i>0) {
			str=true;
		}else {
			str=false;
		}
		return str; // ���²飬����������ǰ̨
	}
//���
	@RequestMapping(value = "/add")
	@ResponseBody
	 int add(Users s) {
		int str;
	   System.out.println("���");
	   System.out.println(s.toString());
		int i=stu.add(s);
		if(i>0) {
			str=2;
		}else {
			str=1;
		}
		System.out.println("��Ӻ���"+str);
		return str;// ���²飬����������ǰ̨

	}
	//��ѯ,����id������
		@RequestMapping(value = "/cha")
		 public String chaall(Model mo,String id) {
		   System.out.println("��ѯ");
		   List<Users> list=stu.cha(id);
		  System.out.println(list.size());
		 mo.addAttribute("list", list);
		 return "/xiu.jsp";

		}
		//�޸�
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
			System.out.println("�޸ĺ���"+str);
			return str;// ���²飬����������ǰ̨

		}
		//�������
		@RequestMapping(value="/biao", method = RequestMethod.GET)
		@ResponseBody
		public void biao(HttpServletResponse response) throws IOException {
			System.out.println("�������");
			//1.��ȡ����
			List<Users> list=stu.getAll();
			System.out.println(list);
			//2.����excel���
			HSSFWorkbook wb=new HSSFWorkbook();
			//3.����sheetҳ
			HSSFSheet sheet=wb.createSheet("Ա����Ϣ��");
			//4.����������
			HSSFRow titleRow=sheet.createRow(0);
			titleRow.createCell(0).setCellValue("ѧ��");
			titleRow.createCell(1).setCellValue("����");
			titleRow.createCell(2).setCellValue("�Ա�");
			//5.���н����ݷŵ�Excel����
			for(Users user:list) {
				HSSFRow dataRow =sheet.createRow(sheet.getLastRowNum()+1);
				dataRow.createCell(0).setCellValue(user.getId());
				dataRow.createCell(1).setCellValue(user.getName());
				dataRow.createCell(2).setCellValue(user.getSex());
			}
			//6.��������ʱ�ͻ���Excal������
			response.setContentType("application/octet-stream;charset=utf-8");
		     response.setHeader("Content-Disposition", "attachment;filename="
                     + new String("Ա������".getBytes(),"iso-8859-1") + ".xls");
		     OutputStream out=response.getOutputStream();
			 wb.write(out);
			 out.flush();
			 out.close();
			
			
		}
		
}

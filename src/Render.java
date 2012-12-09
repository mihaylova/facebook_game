import java.io.*;  

import javax.servlet.*;  
import javax.servlet.http.*; 

import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class Render {
	private Configuration cfg; 
	private Map root = new HashMap();
	private Template tmpl;
	
	public Render(ServletContext servlet_context){
	    cfg = new Configuration();
	    cfg.setServletContextForTemplateLoading(servlet_context, "WEB-INF/templates");
	}
	
    public void set(String name, Object value) {
        root.put(name, value);
    }
    
    public void set(String name, int value) {
        root.put(name, new Integer(value));
    }
    
    public void set(String name, double value) {
        root.put(name, new Double(value));
    }

    public void set(String name, boolean value) {
        root.put(name, new Boolean(value));
    }
	
	public void render(HttpServletResponse response, String template){
		try {
			tmpl = cfg.getTemplate(template);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Writer out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		response.setContentType("text/html; charset=" + tmpl.getEncoding());
		
		try {
			tmpl.process(root, out);
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

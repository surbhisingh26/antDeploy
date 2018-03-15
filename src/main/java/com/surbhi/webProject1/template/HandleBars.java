package com.surbhi.webProject1.template;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.github.jknack.handlebars.Handlebars;

import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

/**
 * Servlet implementation class HandleBars
 */
public class HandleBars extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleBars() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter out = response.getWriter();
	/*//	TemplateLoader loader = new ClassPathTemplateLoader("webProject1/hbs", ".hbs");
		String responseJson = "{\"name\":\"Surbhi\"}";
     	JsonNode jsonNode = new ObjectMapper().readValue(responseJson, JsonNode.class);
		Handlebars hbs = new Handlebars();
		//Gson gson = new Gson();
		//setHandlebars(new Handlebars(loader).with((new GuavaTemplateCache(templateCache))));
		//Type type = new TypeToken<Map<String, Object>>(){}.getType();
		//Map<String, Object> map = gson.fromJson(data, type);
		hbs.registerHelper("responseJson", Jackson2Helper.INSTANCE);
		
//		Template template1 = loader.getTemplate("home");
//		template.apply(template.getContext(jsonNode));
		//hbs.registerHelper("json", Jackson2Helper.INSTANCE);
		Context context = Context
	            .newBuilder(jsonNode)
	            .resolver(JsonNodeValueResolver.INSTANCE,
	                    JavaBeanValueResolver.INSTANCE,
	                    FieldValueResolver.INSTANCE,
	                    MapValueResolver.INSTANCE,
	                    MethodValueResolver.INSTANCE
	            )
	            .build();
		Template template =hbs.compile("home");
		out.print(template.apply(context));*/
		TemplateLoader loader = new FileTemplateLoader("C:/Users/Java/workspace1/webProject1/src/main/webapp/hbs", ".hbs");
		Handlebars handlebars = new Handlebars(loader);
		handlebars.compile("home");
	}

}

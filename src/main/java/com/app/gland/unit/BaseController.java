package com.app.gland.unit;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.lang3.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


public abstract class BaseController {
	
	public static final int DEFAULT_PAGE_SIZE = 10;
	
	
	
	protected Log logger = LogFactory.getLog(getClass());
	
	protected int getInt(HttpServletRequest request, String param, int defaultValue) {
		String value = request.getParameter(param);
		return NumberUtils.isNumber(value) ? Integer.parseInt(value) : defaultValue;
	}
	
	protected Integer getInteger(HttpServletRequest request, String param) {
		String value = request.getParameter(param);
		return NumberUtils.isNumber(value) ? Integer.parseInt(value) : null;
	}
	
	protected String get(HttpServletRequest request, String param) {
		String value = request.getParameter(param);
		return value != null ? value.trim() : null;
	}
	
	protected String get(HttpServletRequest request, String param, String defaultValue) {
		String value = request.getParameter(param);
		return StringUtils.isNotBlank(value)? value.trim() : defaultValue;
	}
	
	protected String[] getValues(HttpServletRequest request, String param) {
		String[] values = request.getParameterValues(param);
		return values == null ? new String[0] : values;
	}
	

}

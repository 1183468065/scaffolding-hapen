package com.example.code.gen;

import org.apache.commons.io.IOUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GenMain {

	public static void main(String[] args) throws Exception, XMLParserException {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		InputStream is = GenMain.class.getResourceAsStream("/code/mybatis/t_sys_user.xml");
		try {
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp.parseConfiguration(is);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);

			System.out.println(warnings);
		} finally {
			IOUtils.closeQuietly(is);
		}
	}
}

package com.staryea.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Test {

	public static void main(String[] args) {

		File file = new File("F://download/a.log");

		String line = null;
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map map = JSON.parseObject(sb.toString());

		File nf = new File("F://download/b.log");
		try (PrintWriter pw = new PrintWriter(new FileWriter(nf))) {
			((List<Map>) (((Map) map.get("hits")).get("hits"))).stream().map(m -> m.get("_source")).forEach(m -> {
				pw.println(JSON.toJSONString(m));
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

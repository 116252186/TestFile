package com.staryea.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;

public class Test3 {

	public static void main(String[] args) {

		StringBuilder json = new StringBuilder();
		String line = null;

		try (BufferedReader br = new BufferedReader(new FileReader(new File("F:\\download\\a.log")))) {

			while ((line = br.readLine()) != null)
				json.append(line);

		} catch (Exception e) {
			e.printStackTrace();
		}

		Map map = JSON.parseObject(json.toString(), Map.class);
		List<Map> list = (List<Map>) ((Map) map.get("hits")).get("hits");

		List<Map> source = list.stream().map(m -> {
			return (Map) m.get("_source");
		}).sorted(new Comparator<Map>() {
			@Override
			public int compare(Map pre, Map after) {
				return Integer.parseInt(pre.get("id").toString()) - Integer.parseInt(after.get("id").toString());
			}
		}).collect(Collectors.toList());

		new Test4(source, "Test").makeTree("").close();

	}

}

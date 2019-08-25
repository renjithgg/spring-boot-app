package com.example.demo.util;

import com.example.demo.model.Products;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadJson {
	@SuppressWarnings("unchecked")
	public List<Products> readjsonFile(File localPath) throws FileNotFoundException, IOException{
		List<Products> products = new ArrayList<Products>();
		
		ObjectMapper mapper = new ObjectMapper();
		products = (List<Products>) mapper.readValue(localPath, new TypeReference<List<Products>>(){});
		
		return products;
	}

	@SuppressWarnings("unchecked")
	public List<Products> readjsonFile(String jsonValue) throws FileNotFoundException, IOException{
		List<Products> products = new ArrayList<Products>();

		ObjectMapper mapper = new ObjectMapper();
		products = (List<Products>) mapper.readValue(jsonValue, new TypeReference<List<Products>>(){});

		return products;
	}

}

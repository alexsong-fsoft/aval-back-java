package com.aval.api.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.aval.api.model.Comentario;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilFile {

	@SuppressWarnings("unused")
	public static List<Comentario> readFileFromResources() {

		// new File(new UtilFile().getClass().getResource(fileName).getFile());

		ObjectMapper mapper = new ObjectMapper();
		try {
			File file = new ClassPathResource("data.json").getFile();

			Comentario[] comentarioClassObj = mapper.readValue(file, Comentario[].class);
			List<Comentario> lstComentarios = new LinkedList<Comentario>(Arrays.asList(comentarioClassObj));
			return lstComentarios;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static String guardarDB(List<Comentario> comentarios) {

		// new File(new UtilFile().getClass().getResource(fileName).getFile());

		ObjectMapper mapper = new ObjectMapper();
		try {

			String json = mapper.writeValueAsString(comentarios);
			System.out.println("ResultingJSONstring = " + json);
			String fileName = new ClassPathResource("data.json").getFile().getAbsolutePath();

			// File targetFile = new File(fileName);
			File targetFile = new ClassPathResource("data.json").getFile();
			targetFile.delete();

			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(json);

			writer.close();

			return json;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}

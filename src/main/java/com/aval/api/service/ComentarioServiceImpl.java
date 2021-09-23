package com.aval.api.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aval.api.model.Comentario;
import com.aval.api.model.Comentarios;
import com.aval.api.util.UtilFile;

@Service
public class ComentarioServiceImpl implements ComentarioService {

	@Override
	public List<Comentarios> findAllById(Integer id) {

		List<Comentario> rows = UtilFile.readFileFromResources();

		String json = UtilFile.guardarDB(rows);

		List<Comentario> dataFiltered = rows.stream().filter(article -> article.getProducto() == id)
				.collect(Collectors.toList());

		List<Comentarios> objResp = this.makeTree(dataFiltered, null);

		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		// sdf.setTimeZone(TimeZone.getTimeZone("CET"));
		// String text = sdf.format(date);

		return objResp;
		// return null;
	}

	private List<Comentarios> makeTree(List<Comentario> comentarios, String parentId) {
		List<Comentarios> result = new ArrayList<>();

		for (int i = comentarios.size() - 1; i >= 0; --i) {
			if (comentarios.get(i).getParentId() == parentId || (comentarios.get(i).getParentId() != null
					&& parentId != null && comentarios.get(i).getParentId().equalsIgnoreCase(parentId))) {
				Comentarios obj = new Comentarios();
				String id = comentarios.get(i).getId();
				obj.setId(comentarios.get(i).getId());
				obj.setUser(comentarios.get(i).getUser());
				obj.setComentario(comentarios.get(i).getComentario());
				obj.setEstrellas(comentarios.get(i).getEstrellas());
				obj.setLikes(comentarios.get(i).getLikes());
				obj.setDislikes(comentarios.get(i).getDislikes());
				obj.setFechaCreacion(comentarios.get(i).getFechaCreacion());
				obj.setProducto(comentarios.get(i).getProducto());
				// obj.setParentId(comentarios.get(i).getParentId());

				comentarios.remove(comentarios.get(i));
				obj.setChildren(this.makeTree(comentarios, id));
				result.add(obj);

			}
		}
		return result;
	}

	@Override
	public Comentario save(Comentario comentario) {

		Date fechaCreacion = new Date();
		SimpleDateFormat sdf;
		sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		// sdf.setTimeZone(TimeZone.getTimeZone("CET"));
		// String text = sdf.format(date);

		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();

		Comentario comentarioNew = new Comentario();
		comentarioNew.setId(uuidAsString);
		comentarioNew.setUser(comentario.getUser());
		comentarioNew.setComentario(comentario.getComentario());
		comentarioNew.setEstrellas(comentario.getEstrellas());
		comentarioNew.setLikes(1);
		comentarioNew.setDislikes(1);
		comentarioNew.setFechaCreacion(sdf.format(fechaCreacion));
		comentarioNew.setProducto(comentario.getProducto());
		comentarioNew.setParentId(comentario.getParentId());

		List<Comentario> rows = UtilFile.readFileFromResources();

		rows.add(comentarioNew);
		UtilFile.guardarDB(rows);

		return comentarioNew;

	}

}

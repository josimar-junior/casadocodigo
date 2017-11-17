<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro dos produtos</title>
</head>
<body>
	
	<form action="/casadocodigo/produtos" method="post">
	
		<label for="title">Título</label>
		<input type="text" name="title" id="title"/>
		
		<label for="description">Descrição</label>
		<textarea rows="10" cols="20" name="description" id="description"></textarea>
		
		<label for="title">Número de páginas</label>
		<input type="text" name="pages" id="pages"/>
		
		<br/>
		
		<input type="submit" value="Enviar"/>
	</form>
	
</body>
</html>
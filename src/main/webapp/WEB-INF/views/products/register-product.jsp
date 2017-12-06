<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro dos produtos</title>
</head>
<body>
	
	<form:form action="/casadocodigo/produtos" method="post" commandName="product" enctype="multipart/form-data">
	
		<div>
			<label for="title">Título</label>
			<form:input path="title"/>
			<form:errors path="title"/> <br/>
		</div>
		
		<div>
			<label for="description">Descrição</label><br/>
			<form:textarea rows="10" cols="20" path="description"/>
			<form:errors path="description"/> <br/>
		</div>
		
		<div>
			<label for="pages">Número de páginas</label><br/>
			<form:input type="text" path="pages" id="pages"/>
			<form:errors path="pages"/> <br/>
		</div>
		
		<c:forEach items="${types}" var="bookType" varStatus="status">
			<div>
				<label for="price_${bookType}">${bookType}</label>
				<form:input type="text" path="prices[${status.index}].value" id="price_${bookType}"/>
				<form:input type="hidden" path="prices[${status.index}].bookType" value="${bookType}"/>
			</div>
		</c:forEach>
		
		<div>
			<label for="releaseDate">Data de lançamento</label>
			<form:input type="date" path="releaseDate"/>
			<form:errors path="releaseDate"/>
		</div>
		
		<div>
			<label for="summary">Sumário do livro</label>
			<input type="file" name="summary"/>
			<form:errors path="summaryPath"/>
		</div>
		
		<br/><br/>
		
		<input type="submit" value="Enviar"/>
	</form:form>
	
</body>
</html>
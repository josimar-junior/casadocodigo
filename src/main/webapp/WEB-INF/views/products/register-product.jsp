<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro dos produtos</title>
</head>
<body>
	
	<form action="${spring:mvcUrl("saveProduct").build()}" method="post">
	
		<spring:hasBindErrors name="product">
			<ul>
				<c:forEach items="${errors.allErrors}" var="error">
					<li><spring:message code="${error.code}" text="${error.defaultMessage}"/></li>
				</c:forEach>
			</ul>
		</spring:hasBindErrors>
	
		<label for="title">Título</label>
		<input type="text" name="title" id="title"/> <br/>
		
		<label for="description">Descrição</label><br/>
		<textarea rows="10" cols="20" name="description" id="description"></textarea><br/>
		
		<label for="title">Número de páginas</label><br/>
		<input type="text" name="pages" id="pages"/>
		
		<c:forEach items="${types}" var="bookType" varStatus="status">
			<div>
				<label for="price_${bookType}">${bookType}</label>
				<input type="text" name="prices[${status.index}].value" id="price_${bookType}"/>
				<input type="hidden" name="prices[${status.index}].bookType" value="${bookType}"/>
			</div>
		</c:forEach>
		
		
		<br/><br/>
		
		<input type="submit" value="Enviar"/>
	</form>
	
</body>
</html>
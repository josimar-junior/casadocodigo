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
	
	<form:form action="${spring:mvcUrl('saveProduct').build()}" method="post" commandName="product">
	
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
			<input type="text" name="pages" id="pages"/>
			<form:errors path="pages"/> <br/>
		</div>
		
		<c:forEach items="${types}" var="bookType" varStatus="status">
			<div>
				<label for="price_${bookType}">${bookType}</label>
				<input type="text" name="prices[${status.index}].value" id="price_${bookType}"/>
				<input type="hidden" name="prices[${status.index}].bookType" value="${bookType}"/>
			</div>
		</c:forEach>
		
		
		<br/><br/>
		
		<input type="submit" value="Enviar"/>
	</form:form>
	
</body>
</html>